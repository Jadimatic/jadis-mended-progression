package io.github.jadimatic.jadis_mended_progression;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			if(id.equals(Blocks.GRASS.getLootTableId()))// check key to see if this is the grass loot table
			{//Don't forget to fix the fact this currently replaces all grass drops.
				tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(JadisMendedProgressionItems.JADIS_PLANT_FIBER)));// use tableBuilder methods to add a pool
			}
		});

		JadisMendedProgressionItems.register(mod);
	}



}
