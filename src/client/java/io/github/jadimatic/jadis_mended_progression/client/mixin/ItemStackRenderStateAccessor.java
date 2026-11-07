package io.github.jadimatic.jadis_mended_progression.client.mixin;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import org.spongepowered.asm.mixin.gen.Accessor;
///Makes "layers" accessible outside its original context.
@org.spongepowered.asm.mixin.Mixin(net.minecraft.client.renderer.item.ItemStackRenderState.class)
public interface ItemStackRenderStateAccessor {
	@Accessor("layers")
	ItemStackRenderState.LayerRenderState[] jadismendedprogression$getLayers();
}
