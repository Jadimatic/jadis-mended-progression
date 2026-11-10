package io.github.jadimatic.jadis_mended_progression.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ItemCubbyBlock extends BaseEntityBlock  {
	public static final EnumProperty<Direction> FACING;
	public static final BooleanProperty OPEN;
	public static final BooleanProperty POWERED;

	public ItemCubbyBlock(Properties settings) {
		super(settings);

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(POWERED, false));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ItemCubbyBlockEntity(pos, state);
	}

	public boolean checkFrontFace(BlockState state, Direction direction) {
		return state.getValue(HorizontalDirectionalBlock.FACING) == direction;
	}

	private void checkPoweredState(final Level level, final BlockPos pos, final BlockState state) {
		boolean shouldBeLocked = level.hasNeighborSignal(pos);
		if (shouldBeLocked != state.getValue(POWERED)) {
			if (!shouldBeLocked) {
				BlockEntity blockentity = level.getBlockEntity(pos);
				if (blockentity instanceof ItemCubbyBlockEntity itemCubby) {
					itemCubby.updateFilter();
				}
			}
			level.setBlock(pos, state.setValue(POWERED, shouldBeLocked), 2);
		}
	}

	@Override
	protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final Orientation orientation, final boolean movedByPiston) {
		checkPoweredState(level, pos, state);
	}

	@Override
	protected @NonNull InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
		BlockEntity blockentity = level.getBlockEntity(pos);

		if (blockentity instanceof ItemCubbyBlockEntity itemCubby) {
			if (level.isClientSide()) {
				if (checkFrontFace(state, hitResult.getDirection())) {
					return InteractionResult.SUCCESS;
				} else {
					return InteractionResult.PASS;
				}
			} else {
				if (checkFrontFace(state, hitResult.getDirection())) {
					if (!player.getItemInHand(hand).isEmpty()) {
						if (!itemCubby.inventoryFull()) {
							if (itemCubby.canInsert(itemStack)) {
								if (!player.isShiftKeyDown()) {
									int remainder = itemCubby.insert(itemStack);
									itemStack.setCount(remainder);
								} else {
									itemCubby.insertOne(itemStack);
									itemStack.shrink(1);
								}

								float pitchBend = (float) itemCubby.getSlotsFilled() / itemCubby.getContainerSize();

								level.playSound(null, pos,
									SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS,
									1f, 0.7f + 0.5f * pitchBend);
								level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
								level.scheduleTick(pos, this, 10);

								level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
							}
						} else {
							level.playSound(null, pos,
								SoundEvents.DECORATED_POT_INSERT_FAIL, SoundSource.BLOCKS,
								1f, 1f);
						}
					} else {
						if (player.getInventory().getFreeSlot() != -1 && !itemCubby.getTheItem().isEmpty()) { //Make sure player inventory isn't full
							ItemStack item;
							if (!player.isShiftKeyDown()) {
								item = itemCubby.remove();
							} else {
								item = itemCubby.removeOne();
							}
							player.getInventory().add(item);

							level.playSound(null, pos,
								SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS,
								1f, 0.5f);
							level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
							level.scheduleTick(pos, this, 10);

							level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
						}
					}
					return InteractionResult.SUCCESS;
				} else {
					return InteractionResult.PASS;
				}
			}
		} else {
			return InteractionResult.PASS;
		}
	}

	public InteractionResult shiftClickUse(final UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		ItemStack itemStack = context.getItemInHand();
		BlockState state = level.getBlockState(context.getClickedPos());
		BlockPos pos = context.getClickedPos();

		BlockEntity blockentity = level.getBlockEntity(pos);

		if (blockentity instanceof ItemCubbyBlockEntity itemCubby) {
			if (level.isClientSide()) {
				if (checkFrontFace(state, context.getClickedFace())) {
					return InteractionResult.SUCCESS;
				} else {
					return null;
				}
			} else {
				if (checkFrontFace(state, context.getClickedFace())) {
					if (!player.getItemInHand(player.getUsedItemHand()).isEmpty()) {
						if (!itemCubby.inventoryFull()) {
							if (itemCubby.canInsert(itemStack)) {
								itemCubby.insertOne(itemStack);
								itemStack.shrink(1);

								float pitchBend = (float) itemCubby.getSlotsFilled() / itemCubby.getContainerSize();

								level.playSound(null, pos,
									SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS,
									1f, 0.7f + 0.5f * pitchBend);
								level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
								level.scheduleTick(pos, this, 10);

								level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
							}
						} else {
							level.playSound(null, pos,
								SoundEvents.DECORATED_POT_INSERT_FAIL, SoundSource.BLOCKS,
								1f, 1f);
						}
					} else {
						if (player.getInventory().getFreeSlot() != -1 && !itemCubby.getTheItem().isEmpty()) { //Make sure player inventory isn't full
							ItemStack item = itemCubby.removeOne();
							player.getInventory().add(item);

							level.playSound(null, pos,
								SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS,
								1f, 0.5f);
							level.setBlockAndUpdate(pos, state.setValue(OPEN, true));
							level.scheduleTick(pos, this, 10);

							level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
						}
					}
					return InteractionResult.SUCCESS;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}

	@Override
	protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
		BlockEntity blockentity = level.getBlockEntity(pos);

		if (blockentity instanceof ItemCubbyBlockEntity itemCubby) {
			return itemCubby.getRedstoneSignalFromContainer();
		}
		return 0;
	}

	@Override
	protected boolean hasAnalogOutputSignal(final BlockState state) {
		return true;
	}

	@Override
	protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
		if (!oldState.is(state.getBlock())) {
			this.checkPoweredState(level, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(OPEN)) {
			level.setBlockAndUpdate(pos, state.setValue(OPEN, false));
		}
	}

	@Override
	protected @NonNull InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
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
		builder.add(new Property[]{FACING, OPEN, POWERED});
	}

	static {
		FACING = HorizontalDirectionalBlock.FACING;
		OPEN = BlockStateProperties.OPEN;
		POWERED = BlockStateProperties.POWERED;
	}
}
