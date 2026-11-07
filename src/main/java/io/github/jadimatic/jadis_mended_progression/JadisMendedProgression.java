package io.github.jadimatic.jadis_mended_progression;

import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import io.github.jadimatic.jadis_mended_progression.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JadisMendedProgression implements ModInitializer {
	public static final String MOD_ID = "jadis_mended_progression";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Progression Mending Initialized.");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
