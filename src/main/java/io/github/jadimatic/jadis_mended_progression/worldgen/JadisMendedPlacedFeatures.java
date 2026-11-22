package io.github.jadimatic.jadis_mended_progression.worldgen;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class JadisMendedPlacedFeatures {
	public static final ResourceKey<PlacedFeature> OVERWORLD_TIN_ORE_PLACED_KEY = registerKey("overworld_tin_ore_replaced_key");

	public static void bootstrap(BootstrapContext<PlacedFeature> context){
		var configuredFeatures = context.lookup(Registries.FEATURE);
		register(context, OVERWORLD_TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(JadisMendedFeatures.OVERWORLD_TIN_ORE_KEY),
			OrePlacements.commonOrePlacement(12,//The number of ore deposits per chunk, placed randomly within the valid space.
				HeightRangePlacement.triangle(VerticalAnchor.absolute(0),//Lowest Y level.
					VerticalAnchor.absolute(50))));//Highest Y level.
						//The value right in the middle of them is where the most of this ore can be found.




	}


	private static ResourceKey<PlacedFeature> registerKey(String name){
		return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
	}
	private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
								 Holder<Feature> configuration, List<PlacementModifier> modifiers){
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}
}
