package io.github.jadimatic.jadis_mended_progression.mixin;

import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlockOverrides;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.Properties.class)
public abstract class BlockBehaviorPropertiesMixin {
	@Inject(method = "setId", at = @At("TAIL"))
	private void modifyVanillaBlockProperties(ResourceKey<Block> id, CallbackInfoReturnable<BlockBehaviour.Properties> cir) {
		BlockBehaviour.Properties properties = (BlockBehaviour.Properties)(Object)this;

		JadisMendedBlockOverrides.modifyVanillaBlocks(id, properties);
	}
}
