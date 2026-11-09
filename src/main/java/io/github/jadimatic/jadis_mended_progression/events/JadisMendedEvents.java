package io.github.jadimatic.jadis_mended_progression.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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

public class JadisMendedEvents {
	public static BlockState healAnvil(final BlockState blockState) {
		if (blockState.is(Blocks.CHIPPED_ANVIL)) {
			return Blocks.ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING));
		} else if (blockState.is(Blocks.DAMAGED_ANVIL)) {
			return Blocks.CHIPPED_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING));
		} else return null;
	}

	public static InteractionResult useAnvil(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, TagKey<Item> tag) {

		//Open menu if no valid item is detected
		if (!itemStack.is(tag) || state.is(Blocks.ANVIL)) {
			player.openMenu(state.getMenuProvider(level, pos));
			player.awardStat(Stats.INTERACT_WITH_ANVIL);
			return InteractionResult.SUCCESS;
		}

		//Do clientside effects (particles, swinging hand)
		if (level.isClientSide()) {
			if (itemStack.is(tag) && !state.is(Blocks.ANVIL)) {
				SwingAnimation swingAnimation = player.getItemInHand(hand).getAttackAnimation();
				player.swing(hand, swingAnimation, false);
				RandomSource random = level.getRandom();
				for(int i = 0; i < 8; ++i) {
					level.addParticle(ParticleTypes.CRIT, pos.getX() + random.nextDouble(), pos.getY() + 1.2, pos.getZ() + random.nextDouble(), 0.0F, 0.0F, 0.0F);
				}
			}
		} else {
			if (itemStack.is(tag)) {
				BlockState newBlockState = healAnvil(state);
				if (newBlockState != null) { //Detect if the anvil changes
					//Deplete Item
					itemStack.consume(1, player);

					//Play sound for other players
					level.setBlock(pos, newBlockState, 2);
					level.playSound(null, pos,
						SoundEvents.ANVIL_LAND, SoundSource.BLOCKS,
						1f, 1.2f);
				}
			}
		}

		return InteractionResult.SUCCESS;
	}
}
