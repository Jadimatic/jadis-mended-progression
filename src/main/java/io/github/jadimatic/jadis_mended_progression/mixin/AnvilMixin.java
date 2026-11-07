package io.github.jadimatic.jadis_mended_progression.mixin;

import io.github.jadimatic.jadis_mended_progression.ModTags;
import io.github.jadimatic.jadis_mended_progression.events.ModEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.world.level.block.AnvilBlock.FACING;

@Mixin(AnvilBlock.class)
public abstract class AnvilMixin extends FallingBlock {
	@Shadow
	public static @Nullable BlockState damage(BlockState blockState) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}

	protected  AnvilMixin(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		ModEvents.useAnvil(stack, state, level, pos, player, player.getUsedItemHand(), ModTags.REPAIRS_IRON_ANVIL);
		return InteractionResult.SUCCESS;
	}
}
