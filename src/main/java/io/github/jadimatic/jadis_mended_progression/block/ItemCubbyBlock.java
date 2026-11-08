package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ItemCubbyBlock extends BaseEntityBlock  {
	public static final EnumProperty<Direction> FACING;
	public static final BooleanProperty OPEN;

	public ItemCubbyBlock(Properties settings) {
		super(settings);

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ItemCubbyBlockEntity(pos, state);
	}

	public boolean checkFrontFace(BlockState state, Direction direction) {
		return state.getValue(HorizontalDirectionalBlock.FACING) == direction;
	}

	protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof ItemCubbyBlockEntity itemCubby) {
			if (level.isClientSide()) {
				return InteractionResult.SUCCESS;
			} else {
				if (player.getItemInHand(hand) == null || !player.getItemInHand(hand).isEmpty()) {
					if (itemCubby.canInsert(itemStack)) {
						int remainder = itemCubby.insert(itemStack);
						itemStack.setCount(remainder);

						level.playSound(null, pos,
							SoundEvents.BARREL_OPEN, SoundSource.BLOCKS,
							1f, 1f);
						level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
						level.scheduleTick(pos, this, 10);

						level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
					}
				} else {
					JadisMendedProgression.LOGGER.info(itemCubby.getTheItem().toString());

					if (player.getInventory().getFreeSlot() != -1 && !itemCubby.getTheItem().isEmpty()) { //Make sure player inventory isn't full
						ItemStack item = itemCubby.remove();
						player.getInventory().add(item);

						level.playSound(null, pos,
							SoundEvents.BARREL_OPEN, SoundSource.BLOCKS,
							1f, 1f);
						level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
						level.scheduleTick(pos, this, 10);

						level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
					}
				}
				return InteractionResult.SUCCESS;
			}
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(OPEN)) {
			level.setBlockAndUpdate(pos, state.setValue(OPEN, false));
		}
	}

	protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
		return InteractionResult.SUCCESS;
	}

	protected @NonNull BlockState rotate(final BlockState state, final Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	protected @NonNull BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(final BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN, false);
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(new Property[]{FACING, OPEN});
	}

	static {
		FACING = HorizontalDirectionalBlock.FACING;
		OPEN = BlockStateProperties.OPEN;
	}
}
