package io.github.jadimatic.jadis_mended_progression.client.renderer.block;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class ItemCubbyBlockEntityRenderState extends BlockEntityRenderState {
	public Level level;
	public Direction direction;
	public boolean dont_draw;

	final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();

	public ItemCubbyBlockEntityRenderState() {
		this.direction = Direction.NORTH;
		this.dont_draw = true;
	}
}
