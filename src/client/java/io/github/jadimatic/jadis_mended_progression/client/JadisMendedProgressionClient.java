package io.github.jadimatic.jadis_mended_progression.client;

import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlockEntities;
import io.github.jadimatic.jadis_mended_progression.client.renderer.block.ItemCubbyBlockRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class JadisMendedProgressionClient implements ClientModInitializer {
	public static final RenderStateDataKey<Boolean> HOLDING_LANTERN_IN_LEFT_ARM = RenderStateDataKey.create(() -> "holding_lantern_in_left_arm");
	public static final RenderStateDataKey<Boolean> HOLDING_LANTERN_IN_RIGHT_ARM = RenderStateDataKey.create(() -> "holding_lantern_in_right_arm");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		BlockEntityRenderers.register(JadisMendedBlockEntities.ITEM_CUBBY_ENTITY, ItemCubbyBlockRenderer::new);
	}
}
