package io.github.jadimatic.jadis_mended_progression;

import com.mojang.serialization.DataResult;
import io.github.jadimatic.jadis_mended_progression.block.ModBlockEntities;
import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import io.github.jadimatic.jadis_mended_progression.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.minecraft.core.component.DataComponents.REPAIR_COST;

public class JadisMendedProgression implements ModInitializer {
	public static final String MOD_ID = "jadis_mended_progression";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Progression Mending Initialized.");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();


		DefaultItemComponentEvents.MODIFY.register(context -> {
			/*context.modify(Items.STONE_PICKAXE, builder -> builder
				.set(DataComponents.MAX_DAMAGE, 3)//This is the tool's durability
				.set(DataComponents.TOOL,
					new Tool(
						List.of(
							Tool.Rule.deniesDrops(registrationLookup.getOrThrow(ToolMaterial.STONE.incorrectBlocksForDrops())),//NO TOUCHY, THIS IS ONLY HERE TO MAKE SURE IT STILL EXISTS!!!
							Tool.Rule.minesAndDrops(registrationLookup.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), 1f)//Tool speed is changed here.
						),
						1.0F,//NO TOUCHY THIS, IT ONLY EXISTS FOR THE METHOD TO FUNCTION!!!
						1,//How much durability is lost per block mined.
						true//Whether the tool can destroy blocks in creative, this is pretty much redundant, but still needs to exist.
					))
				.set(REPAIR_COST, 1)
			);
			*/

			context.modify(Items.STONE_PICKAXE, builder -> builder.set(DataComponents.TOOL,
				new Tool(
					List.of(
						Tool.Rule.deniesDrops(BuiltInRegistries.BLOCK.getOrThrow(ToolMaterial.STONE.incorrectBlocksForDrops())),
						Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), 4.0f)
					),
					1.0F,
					1,
					true
				)));

			context.modify(Items.COPPER_PICKAXE, builder -> builder
				.set(REPAIR_COST, 1)
			);
		});






		/*
			LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			// If the loot table is for a white sheep, and it is not overridden by a user:
			if (source.isBuiltin() && BuiltInLootTables.   .equals(key)) {
				// Create a new loot pool that will hold the diamonds.
				LootPool.Builder pool = LootPool.lootPool()
					// Add diamonds...
					.add(LootItem.lootTableItem(Items.DIAMOND))
					// ...only if the sheep was killed with a diamond sword.
					.when(LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.ATTACKER,
						EntityPredicate.Builder.entity().equipment(
							EntityEquipmentPredicate.Builder.equipment().mainhand(
								ItemPredicate.Builder.item().of(
									registries.lookupOrThrow(Registries.ITEM),
									Items.DIAMOND_SWORD
								)
							)
						)
					));
				// Add the loot pool to the loot table
				tableBuilder.withPool(pool);
			}
		});
		 */






	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
