package io.github.jadimatic.jadis_mended_progression.events;

import io.github.jadimatic.jadis_mended_progression.block.ItemCubbyBlock;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CubbyShiftClickEvent {
	public static void register() {
		ItemEvents.USE_ON.register(CubbyShiftClickEvent::onItemUse);
	}

	private static InteractionResult onItemUse(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();

		if (player == null || level == null || !context.isSecondaryUseActive()) {
			return null;
		}

		BlockState state = level.getBlockState(context.getClickedPos());

		if (state.getBlock() instanceof ItemCubbyBlock block) {
			return block.shiftClickUse(context);
		}

		return null;
	}
}
