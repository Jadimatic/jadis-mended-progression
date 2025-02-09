package io.github.jadimatic.jadis_mended_progression;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.datafixer.fix.ItemRemoveBlockEntityTagFix;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.data.callback.api.predicate.AndPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTML;

public class JadisMendedProgression implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Booting mod: {}!", mod.metadata().name());

		// in onInitialize
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(id.equals(Blocks.GRASS.getLootTableId()))// Check key to see if this is the grass loot table
			{
				tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(JadisMendedProgressionItems.JADIS_PLANT_FIBER))//Drop plant fiber if...
					.conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.HOES)))//Player is holding a hoe and...
					.conditionally(RandomChanceLootCondition.builder(0.64f)));//An RNG check succeeds.
			}
		});

		JadisMendedProgressionItems.register(mod);
	}



}
