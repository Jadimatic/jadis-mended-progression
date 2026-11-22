package io.github.jadimatic.jadis_mended_progression.worldgen.gen;

import io.github.jadimatic.jadis_mended_progression.worldgen.JadisMendedPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class JadisMendedWorldGeneration {
	public static void generateMendedWorldGen(){
		//These must be done in this exact order or things will break.
		//RAW GENERATION
		//LAKES
		//LOCAL MODIFICATIONS
		//UNDERGROUND STRUCTURES
		//STRONGHOLDS
		//UNDERGROUND ORES
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES,
			JadisMendedPlacedFeatures.OVERWORLD_TIN_ORE_PLACED_KEY);
		//UNDERGROUND DECORATION
		//FLUID SPRINGS
		//VEGETAL DECORATION
		//TOP LAYER MODIFICATION
	}
}
