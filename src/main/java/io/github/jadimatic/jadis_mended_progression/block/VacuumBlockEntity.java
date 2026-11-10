package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.util.ImplementedContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class VacuumBlockEntity extends BlockEntity implements ImplementedContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	private int cooldownTime;
	private long tickedGameTime;
	private Direction facing;

	public VacuumBlockEntity(BlockPos pos, BlockState state) {
		super(JadisMendedBlockEntities.VACCUUM_ENTITY, pos, state);

		this.cooldownTime = -1;
		this.facing = state.getValue(VacuumBlock.FACING);
	}

	public ItemStack getTheItem() {
		return this.getItem(0);
	}

	private void setCooldown(final int time) {
		this.cooldownTime = time;
	}

	private boolean isOnCooldown() {
		return this.cooldownTime > 0;
	}

	private boolean isOnCustomCooldown() {
		return this.cooldownTime > 8;
	}

	private static boolean canMergeItems(final ItemStack a, final ItemStack b) {
		return a.getCount() <= a.getMaxStackSize() && ItemStack.isSameItemSameComponents(a, b);
	}

	private static @Nullable Container getAttachedContainer(final Level level, final BlockPos blockPos, final VacuumBlockEntity self) {
		return getContainerAt(level, blockPos.relative(self.facing));
	}

	public static @Nullable Container getContainerAt(final Level level, final BlockPos pos) {
		return getContainerAt(level, pos, level.getBlockState(pos), (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F);
	}

	private static @Nullable Container getContainerAt(final Level level, final BlockPos pos, final BlockState state, final double x, final double y, final double z) {
		Container result = getBlockContainer(level, pos, state);
		if (result == null) {
			result = getEntityContainer(level, x, y, z);
		}

		return result;
	}

	private static @Nullable Container getBlockContainer(final Level level, final BlockPos pos, final BlockState state) {
		Block block = state.getBlock();
		if (block instanceof WorldlyContainerHolder worldlyContainerHolder) {
			return worldlyContainerHolder.getContainer(state, level, pos);
		} else {
			if (state.hasBlockEntity()) {
				BlockEntity entity = level.getBlockEntity(pos);
				if (entity instanceof Container) {
					Container container = (Container)entity;
					if (container instanceof ChestBlockEntity && block instanceof ChestBlock) {
						ChestBlock chestBlock = (ChestBlock)block;
						container = ChestBlock.getContainer(chestBlock, state, level, pos, true);
					}

					return container;
				}
			}

			return null;
		}
	}

	private static @Nullable Container getEntityContainer(final Level level, final double x, final double y, final double z) {
		List<Entity> entities = level.getEntities((Entity)null, new AABB(x - (double)0.5F, y - (double)0.5F, z - (double)0.5F, x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), EntitySelector.CONTAINER_ENTITY_SELECTOR);
		return !entities.isEmpty() ? (Container)entities.get(level.getRandom().nextInt(entities.size())) : null;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items, true);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
		return saveWithoutMetadata(registryLookup);
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	public void setChanged() {
		super.setChanged();

		if (level == null || level.isClientSide()) return;

		BlockState state = getBlockState();
		level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
	}
}
