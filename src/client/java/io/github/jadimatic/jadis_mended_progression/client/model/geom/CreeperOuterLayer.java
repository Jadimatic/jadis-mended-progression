package io.github.jadimatic.jadis_mended_progression.client.model.geom;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class CreeperOuterLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
	private final CreeperModel model;
	public CreeperOuterLayer(RenderLayerParent renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new CreeperModel(modelSet.bakeLayer(JadisMendedModelLayers.CREEPER_OUTER_LAYER));//Saves the model to the modelSet field.
	}

	protected CreeperModel model() {
		return this.model;
	}

	@Override
	public void submit(@NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, int lightCoords, @NonNull CreeperRenderState state, float yRot, float xRot) {
		submitNodeCollector.submitModel(this.model, state, poseStack, RenderTypes.entityTranslucent(Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, "textures/entity/creeper/creeper_outer_layer.png")), lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
	}
}
