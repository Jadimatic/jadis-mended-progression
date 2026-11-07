package io.github.jadimatic.jadis_mended_progression.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.AnvilBlock.FACING;

public class ModEvents {
	public static BlockState healAnvil(final BlockState blockState) {
		if (blockState.is(Blocks.CHIPPED_ANVIL)) {
			return (BlockState)Blocks.ANVIL.defaultBlockState().setValue(FACING, (Direction)blockState.getValue(FACING));
		} else if (blockState.is(Blocks.DAMAGED_ANVIL)) {
			return (BlockState)Blocks.CHIPPED_ANVIL.defaultBlockState().setValue(FACING, (Direction)blockState.getValue(FACING));
		} else return null;
	}

	public static InteractionResult useAnvil(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, TagKey<Item> tag) {

		//Open menu if no valid item is detected
		if (!itemStack.is(tag) || state.is(Blocks.ANVIL)) {
			player.openMenu(state.getMenuProvider(level, pos));
			return InteractionResult.SUCCESS;
		}

		//Do clientside effects (sound, swinging hand)
		if (level.isClientSide()) {
			if (itemStack.is(tag) && !state.is(Blocks.ANVIL)) {
				SwingAnimation swingAnimation = player.getItemInHand(hand).getAttackAnimation();
				player.swing(hand, swingAnimation, false);
				RandomSource random = level.getRandom();
				for(int i = 0; i < 8; ++i) {
					level.addParticle(ParticleTypes.CRIT, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2, (double)pos.getZ() + random.nextDouble(), (double)0.0F, (double)0.0F, (double)0.0F);
				}
			}
		} else {
			//Play sound for other players
			if (itemStack.is(tag)) {
				BlockState newBlockState = healAnvil(state);
				if (newBlockState != null) {
					level.setBlock(pos, newBlockState, 2);
					level.playSound(null, pos,
						SoundEvents.ANVIL_LAND, SoundSource.PLAYERS,
						1f, 1.2f);
				}
			}
		}

		return InteractionResult.SUCCESS;
	}
}
