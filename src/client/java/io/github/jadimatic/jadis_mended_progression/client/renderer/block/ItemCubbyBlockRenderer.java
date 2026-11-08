package io.github.jadimatic.jadis_mended_progression.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.jadimatic.jadis_mended_progression.block.ItemCubbyBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class ItemCubbyBlockRenderer implements BlockEntityRenderer<ItemCubbyBlockEntity, ItemCubbyBlockEntityRenderState> {
	private final ItemModelResolver itemModelResolver;

	public ItemCubbyBlockRenderer(BlockEntityRendererProvider.Context context) {
		itemModelResolver = context.itemModelResolver();
	}

	@Override
	public ItemCubbyBlockEntityRenderState createRenderState() {
		return new ItemCubbyBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(ItemCubbyBlockEntity blockEntity, ItemCubbyBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

		state.level = blockEntity.getLevel();
		state.direction = blockEntity.getDirection();
		state.dont_draw = (blockEntity.getTheItem().isEmpty()) || blockEntity.getOpened();

		itemModelResolver.updateForTopItem(state.itemStackRenderState,blockEntity.getTheItem(), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
	}

	@Override
	public void submit(ItemCubbyBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();

		Direction direction = state.direction;
		poseStack.translate(0.5f, 0.3f, 0.5f);

		poseStack.translate(direction.getStepX() * 0.5F, direction.getStepY() * 0.5F, direction.getStepZ() * 0.5F);
		float xRot = 0;
		float yRot = 0;
		if (direction.getAxis().isHorizontal()) {
			xRot = 0.0F;
			yRot = 180.0F - direction.toYRot();
		}

		poseStack.rotateDegrees(Axis.XP, xRot);
		poseStack.rotateDegrees(Axis.YP, yRot);


		poseStack.scale(0.5F, 0.5F, 0.5F);

		if (!state.dont_draw) {
			int lightAbove = LightCoordsUtil.getLightCoords(state.level, state.blockPos.relative(direction, 1));
			state.itemStackRenderState.submit(poseStack, submitNodeCollector, lightAbove, OverlayTexture.NO_OVERLAY, 0);
		}

		poseStack.popPose();
	}
}
