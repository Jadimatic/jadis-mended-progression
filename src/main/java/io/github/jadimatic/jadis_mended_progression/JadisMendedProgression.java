package io.github.jadimatic.jadis_mended_progression;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlockEntities;
import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import io.github.jadimatic.jadis_mended_progression.events.CubbyShiftClickEvent;
import io.github.jadimatic.jadis_mended_progression.item.JadisMendedItems;
import io.github.jadimatic.jadis_mended_progression.item.component.JadisMendedDataComponents;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import io.github.jadimatic.jadis_mended_progression.worldgen.gen.JadisMendedWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;

public class JadisMendedProgression implements ModInitializer {
	public static final String MOD_ID = "jadis_mended_progression";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static boolean shouldBreak(ItemStack stack){
		return stack.is(JadisMendedTags.Items.BREAKABLE_ITEMS);
		//return !stack.is(JadisMendedTags.Items.BROKEN_BLACKLIST) && stack.getDamageValue() > 0 && stack.getDamageValue() >= stack.getMaxDamage() - (stack.getComponents().has(DataComponents.GLIDER) ? 1 : 0/*If the item is a glider return 1, if not return 0. this will tell isBroken that the item is 1 damage away from breaking if the item is a glider.*/);
	}

	private LootTable fishLootTable (/*EntityType<?> entityType, */ItemLike fishItem, ItemLike skeletalItem, ResourceKey<LootTable> key, HolderLookup.Provider holder){
		LootPool.Builder pool = LootPool.lootPool()// Create a new loot pool that will hold the bone.
			.add(LootItem.lootTableItem(fishItem)//Add the fish as an item...
				.when(LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.ATTACKER,
						EntityPredicate.Builder.entity().equipment(
							EntityEquipmentPredicate.Builder.equipment().mainhand(
								ItemPredicate.Builder.item().of(
									holder.lookupOrThrow(Registries.ITEM),
									ItemTags.SPEARS// When the weapon is a spear...
								)
							)
						)
					).and(randomChance(0.25f))// And this roll succeeds.(~1 in every 4 fish.)
				)
				.otherwise(LootItem.lootTableItem(skeletalItem)));// Otherwise, always drop a bone.
		return LootTable.lootTable().withPool(pool).build();// Create a new loot table with the loot pool.
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Progression Mending Initialized.");
		JadisMendedItems.registerMendedItems();
		JadisMendedBlocks.registerMendedBlocks();
		JadisMendedBlockEntities.registerMendedBlockEntities();
		JadisMendedDataComponents.registerMendedDataComponents();
		JadisMendedWorldGeneration.generateMendedWorldGen();
		CubbyShiftClickEvent.register();
		MidnightConfig.init(MOD_ID, JadisMendedProgressionConfig.class);


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
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 0.50f)
			);

			context.modify(Items.WOODEN_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.STONE_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.GOLDEN_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.COPPER_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.DIAMOND_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.IRON_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);
			context.modify(Items.NETHERITE_SHOVEL, builder -> builder
				.set(JadisMendedDataComponents.REPAIR_AMOUNT, 1f)
			);


			//region Food Effects
			context.modify(Items.CARROT, builder -> builder.set(DataComponents.CONSUMABLE, Consumables.defaultFood().onConsume(//Gives fifteen seconds of night vision.
				new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0), 0.75F)//NOTE: Duration is in ticks, not seconds.
			).build()));
			context.modify(Items.GLOW_BERRIES, builder -> builder.set(DataComponents.CONSUMABLE, Consumables.defaultFood().onConsume(//Gives fifteen seconds of the glowing effect.
				new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 300, 0), 0.90F)
			).build()));
			context.modify(Items.BEETROOT, builder -> builder.set(DataComponents.CONSUMABLE, Consumables.defaultFood().onConsume(//Regenerates half a heart over three seconds.
				new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0))
			).build()));
			context.modify(Items.BEETROOT_SOUP, builder -> builder.set(DataComponents.CONSUMABLE, Consumables.defaultFood().onConsume(//Regenerates three hearts over fifteen seconds.
				new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0))
			).build()));
			//endregion


		});


		//region Creeper Spawn Biome Bias
		/*Predicate<BiomeSelectionContext> creeperBiomeBiasPredicate = (
			biomeSelectionContext) -> biomeSelectionContext.getBiomeKey().equals(Biomes.LUSH_CAVES);
		Consumer<BiomeModificationContext> testo = (wooba) -> wooba.getMobSpawnSettings().getMobs(1);

		BiomeModifications.create(Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, "lush_cave_creeper_bias"))
			.add(ModificationPhase.REPLACEMENTS, creeperBiomeBiasPredicate, );*/
		//endregion



		LootTableEvents.MODIFY_DROPS.register((holder, context, drops) -> {
			// Replace the coal dropped by wither skeletons with charcoal.
			if (holder.is(EntityTypes.WITHER_SKELETON.getDefaultLootTable().orElseThrow())) {//If the entity is a wither skeleton...
				for (int i = 0; i < drops.size(); i++) {//Run an indexed for loop of the drops list.
					ItemStack item = drops.get(i);//Get the currently selected ItemStack from the drops list.
					if(item.is(Items.COAL)){//And if the ItemStack is coal...
						drops.set(i, new ItemStack(Items.CHARCOAL, item.count()));//Replace it with an ItemStack of charcoal, inheriting the count of the original.
					}
				}
			}
		});






		/* Data for the cave spider and normal spider loot tables, which we will be completely replacing with a new version and modified drops.
		this.add(
			EntityTypes.CAVE_SPIDER,
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(
							LootItem.lootTableItem(Items.STRING)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
						)
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(
							LootItem.lootTableItem(Items.SPIDER_EYE)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 1)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
						)
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
		);



		this.add(
			EntityTypes.SPIDER,
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(
							LootItem.lootTableItem(Items.STRING)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
						)
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(ContextIntProviders.exactly(1))
						.add(
							LootItem.lootTableItem(Items.SPIDER_EYE)
								.apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 1)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
						)
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
		);



		*/


		LootTableEvents.REPLACE.register((key, original, source, holder) -> {
			// If the loot table is for an <EntityType>, and it is not overridden by a user...
			if (source.isBuiltin()){// Check that the source is a vanilla loot table...


				if (EntityTypes.COD.getDefaultLootTable().orElseThrow().equals(key)) {// And if the entity is a cod, then drop the appropriate loot.
					return fishLootTable(Items.COD, Items.BONE, key, holder);
				}
				if (EntityTypes.SALMON.getDefaultLootTable().orElseThrow().equals(key)) {// And if the entity is a cod, then drop the appropriate loot.
					return fishLootTable(Items.SALMON, Items.BONE, key, holder);
				}
				if (EntityTypes.TROPICAL_FISH.getDefaultLootTable().orElseThrow().equals(key)) {// And if the entity is a cod, then drop the appropriate loot.
					return fishLootTable(Items.TROPICAL_FISH, Items.BONE, key, holder);
				}
				if (EntityTypes.PUFFERFISH.getDefaultLootTable().orElseThrow().equals(key)) {// And if the entity is a cod, then drop the appropriate loot.
					return fishLootTable(Items.PUFFERFISH, Items.PUFFERFISH, key, holder);//Pufferfish have more sturdy bodies so the body survives more easily.
				}

				if (EntityTypes.WITHER_SKELETON.getDefaultLootTable().orElseThrow().equals(key)) {
					// Create a new loot pool that will hold the charcoal.
					LootPool.Builder pool = LootPool.lootPool()
						// Add the charcoal...
						.add(LootItem.lootTableItem(Items.CHARCOAL));
					// Create a new loot table with the loot pool
					return LootTable.lootTable().withPool(pool).build();
				}







			}
			return null;
		});



	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
