package io.github.jadimatic.jadis_mended_progression;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import org.quiltmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JadisMendedProgression implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Jadis Mended Progression");
	public static final String NAMESPACE = "jadis_mended_progression";
	@Override
	public void onInitialize() {
		//LOGGER.info("Booting mod: {}!", mod.metadata().name());

		// in onInitialize
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(id.equals(Blocks.GRASS.getLootTableId()))// check key to see if this is the grass loot table
			{//Don't forget to fix the fact this currently replaces all grass drops.
				tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(JadisMendedProgressionItems.JADIS_PLANT_FIBER)));// use tableBuilder methods to add a pool
			}
		});

		JadisMendedProgressionItems.register();
	}



}
