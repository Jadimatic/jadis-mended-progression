package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ItemCubbyBlockEntity extends BlockEntity implements ImplementedContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(64, ItemStack.EMPTY);

	public ItemCubbyBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ITEM_CUBBY_ENTITY, pos, state);
	}

	public ItemStack getTheItem() {
		int stack_items = 0;
		ItemStack stack = null;
		ItemStack returning_stack = ItemStack.EMPTY;
		Item item = Items.AIR;

		for (int i = 0; i < this.getContainerSize(); i++) {
			stack = this.getItem(i);

			if (stack != null && !stack.isEmpty()) {
				item = stack.getItem();
				stack_items += stack.getCount();

				break;
			}
		}

		//i dont know if theres a better way to retrieve the item stack out of the inventory
		if (stack_items != 0) {
			returning_stack = new ItemStack(item);
			returning_stack.setCount(stack_items);
		}

		return returning_stack;
	}

	public int insert(ItemStack itemStack) {
		int remaining_items = itemStack.getCount();

		for (int i = 0; i < this.getContainerSize(); i++) {
			ItemStack stack = this.getItem(i);

			if (stack == null || stack.isEmpty()) {
				setItem(i, itemStack.copy());
				remaining_items = 0;
				return remaining_items;
			} else {
				int available_items = stack.getMaxStackSize() - stack.getCount();
				if (available_items < remaining_items) {
					stack.grow(available_items);
					remaining_items -= available_items;
				}
			}
		}

		setChanged();

		return remaining_items;
	}

	public boolean canInsert(ItemStack itemStack) {
		for (int i = 0; i < this.getContainerSize(); i++) {
			ItemStack stack = this.getItem(i);

			if (!stack.isEmpty() && itemStack.getItem() != stack.getItem() && stack.getItem() != Items.AIR) {
				return false;
			}
		}

		return true;
	}

	public ItemStack remove() {
		int stack_items = 0;
		ItemStack stack = null;
		ItemStack returning_stack = ItemStack.EMPTY;
		Item item = Items.AIR;

		for (int i = 0; i < this.getContainerSize(); i++) {
			stack = this.getItem(i);

			if (stack != null && !stack.isEmpty()) {
				item = stack.getItem();
				if (stack.getCount() + stack_items <= stack.getMaxStackSize()) {
					stack_items += stack.getCount();
					stack.shrink(stack.getCount());
				} else {
					stack_items += stack.getMaxStackSize() - stack_items;
					stack.shrink(stack.getMaxStackSize() - stack_items);
				}

				if (stack_items >= stack.getMaxStackSize()) {
					break;
				}
 			}
		}

		//i dont know if theres a better way to retrieve the item stack out of the inventory
		if (stack_items != 0) {
			returning_stack = new ItemStack(item);
			returning_stack.setCount(stack_items);

			setChanged();
		}

		return returning_stack;
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	public boolean getOpened() {
		return this.getBlockState().getValue(BlockStateProperties.OPEN);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		ContainerHelper.loadAllItems(input, this.items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		ContainerHelper.saveAllItems(output, this.items);
		super.saveAdditional(output);
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
		JadisMendedProgression.LOGGER.info("CHANGED!!!!!");

		super.setChanged();

		if (level == null || level.isClientSide()) return;

		BlockState state = getBlockState();
		level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
	}
}
