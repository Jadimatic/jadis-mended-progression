package io.github.jadimatic.jadis_mended_progression.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class VacuumBlock extends BaseEntityBlock  {
	public static final EnumProperty<Direction> FACING;
	public static final BooleanProperty POWERED;

	public VacuumBlock(Properties settings) {
		super(settings);

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new VacuumBlockEntity(pos, state);
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
	protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
		if (!oldState.is(state.getBlock())) {
			this.checkPoweredState(level, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

	}

	protected @NonNull BlockState rotate(final BlockState state, final Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	protected @NonNull BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(final BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(new Property[]{FACING, POWERED});
	}

	static {
		FACING = HorizontalDirectionalBlock.FACING;
		POWERED = BlockStateProperties.POWERED;
	}
}
