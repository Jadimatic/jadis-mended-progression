package io.github.jadimatic.jadis_mended_progression.block;

import com.mojang.serialization.Codec;
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
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ItemCubbyBlockEntity extends BlockEntity implements ImplementedContainer {
	private final NonNullList<ItemStack> items = NonNullList.withSize(64, ItemStack.EMPTY);

	private Identifier filter_item;

	public ItemCubbyBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ITEM_CUBBY_ENTITY, pos, state);
		this.filter_item = BuiltInRegistries.ITEM.getKey(Items.AIR);
	}

	public ItemStack getTheItem() {
		for (int i = 0; i < this.getContainerSize(); i++) {
			ItemStack stack = this.getItem(i);

			if (stack != null && !stack.isEmpty()) {
				return stack;
			}
		}

		return ItemStack.EMPTY;
	}

	public ItemStack getFilteredItem() {
		return new ItemStack(BuiltInRegistries.ITEM.getValue(filter_item));
	}

	public void updateFilter() {
		Identifier filter = BuiltInRegistries.ITEM.getKey(getTheItem().getItem());

		if (!getPowered()) {
			this.filter_item = filter;
		}
	}

	public int insert(ItemStack itemStack) {
		int remaining_items = itemStack.getCount();

		for (int i = 0; i < this.getContainerSize(); i++) {
			ItemStack stack = this.getItem(i);

			if (stack == null || stack.isEmpty()) {
				this.setItem(i, itemStack.copy());
				remaining_items = 0;
				break;
			} else {
				int available_items = stack.getMaxStackSize() - stack.getCount();
				if (available_items < remaining_items && ItemStack.isSameItemSameComponents(itemStack,stack)) {
					stack.grow(available_items);
					remaining_items -= available_items;
				}
			}
		}

		setChanged();

		return remaining_items;
	}

	public boolean canInsert(ItemStack itemStack) {
		return BuiltInRegistries.ITEM.getKey(itemStack.getItem()) == this.filter_item || (isEmpty() && !getPowered());
	}

	public ItemStack remove() {
		int stack_items = 0;
		ItemStack returning_stack = ItemStack.EMPTY;

		for (int i = 0; i < this.getContainerSize(); i++) {
			ItemStack stack = this.getItem(i);

			if (stack != null && !stack.isEmpty()) {
				returning_stack = stack.copy();
				if (stack.getCount() + stack_items <= stack.getMaxStackSize()) {
					stack_items += stack.getCount();
					this.removeItem(i, stack.getCount());
				} else if (ItemStack.isSameItemSameComponents(returning_stack,stack)) {
					stack_items += stack.getMaxStackSize() - stack_items;
					this.removeItem(i, stack.getMaxStackSize() - stack_items);
				}

				if (stack_items >= stack.getMaxStackSize()) {
					break;
				}
 			}
		}

		//i dont know if theres a better way to retrieve the item stack out of the inventory
		if (stack_items != 0) {
			setChanged();
		}

		return returning_stack;
	}

	@Override
	public boolean canPlaceItem(final int slot, final ItemStack itemStack) {
		return canInsert(itemStack);
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	public boolean getPowered() {
		return this.getBlockState().getValue(BlockStateProperties.POWERED);
	}

	public boolean getOpened() {
		return this.getBlockState().getValue(BlockStateProperties.OPEN);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.items.clear();
		ContainerHelper.loadAllItems(input, this.items);
		this.filter_item = input.read("filter", Identifier.CODEC).orElse(BuiltInRegistries.ITEM.getKey(Items.AIR));
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, this.items, true);
		output.store("filter", Identifier.CODEC, this.filter_item);
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

		updateFilter();

		BlockState state = getBlockState();
		level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
	}
}
