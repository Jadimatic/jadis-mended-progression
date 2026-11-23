package io.github.jadimatic.jadis_mended_progression.client.model.geom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;

public class CreeperOuterLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
	private final CreeperModel model;
	public CreeperOuterLayer(RenderLayerParent renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new CreeperModel(modelSet.bakeLayer(JadisMendedModelLayers.NEW_CREEPER_LAYER));//Saves the model to the modelSet field.
	}

	protected CreeperModel model() {
		return this.model;
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CreeperRenderState state, float yRot, float xRot) {

	}
}
