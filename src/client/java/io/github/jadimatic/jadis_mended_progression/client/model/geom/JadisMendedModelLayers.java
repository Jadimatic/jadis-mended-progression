package io.github.jadimatic.jadis_mended_progression.client.model.geom;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;

public class JadisMendedModelLayers {
	//This is where we'll be storing the new layer for the creeper.

	public static final ModelLayerLocation CREEPER_OUTER_LAYER = createMain("creeper_outer_layer");//Defines the location the layer will be stored.

	public static LayerDefinition creeperModelLayerBuilder() {//Creates a new layer and returns it.
		return CreeperModel.createBodyLayer(new CubeDeformation(0.25F));
	}

	public static void registerModelLayers() {//Registers the new model layers.
		ModelLayerRegistry.registerModelLayer(JadisMendedModelLayers.CREEPER_OUTER_LAYER, JadisMendedModelLayers::creeperModelLayerBuilder);
	}

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(JadisMendedProgression.id(name), "main");
	}
}
