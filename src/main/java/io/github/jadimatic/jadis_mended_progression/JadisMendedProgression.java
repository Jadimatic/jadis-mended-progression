package io.github.jadimatic.jadis_mended_progression;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JadisMendedProgression implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Jadis Mended Progression");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Booting mod: {}!", mod.metadata().name());




		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(id.equals(Blocks.GRASS.getLootTableId()))// Check key to see if this is the grass loot table
			{
				tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(JadisMendedProgressionItems.JADIS_PLANT_FIBER))//Drop plant fiber if...
					.conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.HOES)))//Player is holding a hoe and...
					.conditionally(RandomChanceLootCondition.builder(0.65f)));//An RNG check succeeds.
			}
		});

		JadisMendedProgressionItems.register(mod);
	}



}
