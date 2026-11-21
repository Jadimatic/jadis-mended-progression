package io.github.jadimatic.jadis_mended_progression.client.model.geom;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.impl.client.rendering.ModelLayerImpl;
import net.fabricmc.fabric.mixin.client.rendering.ModelLayersAccessor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;

import java.util.Objects;

public class JadisMendedModelLayers {
	//This is where we'll be storing the new layer for the creeper.

	public static final ModelLayerLocation NEW_CREEPER_LAYER = createMain("new_creeper_layer");//Defines the location the layer will be stored.

	public static LayerDefinition creeperModelLayerBuilder() {//Creates a new layer and returns it.
		return CreeperModel.createBodyLayer(new CubeDeformation(1.5F));
	}

	public static void registerModelLayers() {//Registers the new model layers.
		ModelLayerRegistry.registerModelLayer(JadisMendedModelLayers.NEW_CREEPER_LAYER, JadisMendedModelLayers::creeperModelLayerBuilder);
	}

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(JadisMendedProgression.id(name), "main");
	}
}
