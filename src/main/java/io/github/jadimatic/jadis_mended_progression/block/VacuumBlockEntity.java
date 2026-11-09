package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.util.ImplementedContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.function.BooleanSupplier;

public class VacuumBlockEntity extends BlockEntity implements ImplementedContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	private int cooldownTime;
	private long tickedGameTime;
	private Direction facing;

	public VacuumBlockEntity(BlockPos pos, BlockState state) {
		super(JadisMendedBlockEntities.VACCUUM_ENTITY, pos, state);

		this.facing = state.getValue(VacuumBlock.FACING);
	}

	public ItemStack getTheItem() {
		return this.getItem(0);
	}

	/*private static boolean tryMoveItems(final Level level, final BlockPos pos, final BlockState state, final HopperBlockEntity entity, final BooleanSupplier action) {
		if (level.isClientSide()) {
			return false;
		} else {
			if (!entity.isOnCooldown() && (Boolean)state.getValue(HopperBlock.ENABLED)) {
				boolean changed = false;
				if (!entity.isEmpty()) {
					changed = ejectItems(level, pos, entity);
				}

				if (!entity.inventoryFull()) {
					changed |= action.getAsBoolean();
				}

				if (changed) {
					entity.setCooldown(8);
					setChanged(level, pos, state);
					return true;
				}
			}

			return false;
		}
	}

	private static boolean ejectItems(final Level level, final BlockPos blockPos, final HopperBlockEntity self) {
		Container container = getAttachedContainer(level, blockPos, self);
		if (container == null) {
			return false;
		} else {
			Direction direction = self.facing.getOpposite();
			if (isFullContainer(container, direction)) {
				return false;
			} else {
				for(int slot = 0; slot < self.getContainerSize(); ++slot) {
					ItemStack itemStack = self.getItem(slot);
					if (!itemStack.isEmpty()) {
						int originalCount = itemStack.getCount();
						ItemStack result = addItem(self, container, self.removeItem(slot, 1), direction);
						if (result.isEmpty()) {
							container.setChanged();
							return true;
						}

						itemStack.setCount(originalCount);
						if (originalCount == 1) {
							self.setItem(slot, itemStack);
						}
					}
				}

				return false;
			}
		}
	}

	private static boolean tryTakeInItemFromSlot(final Hopper hopper, final Container container, final int slot, final Direction direction) {
		ItemStack itemStack = container.getItem(slot);
		if (!itemStack.isEmpty() && canTakeItemFromContainer(hopper, container, itemStack, slot, direction)) {
			int originalCount = itemStack.getCount();
			ItemStack result = this.addItem(container, hopper, container.removeItem(slot, 1), (Direction)null);
			if (result.isEmpty()) {
				container.setChanged();
				return true;
			}

			itemStack.setCount(originalCount);
			if (originalCount == 1) {
				container.setItem(slot, itemStack);
			}
		}

		return false;
	}
*/
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
