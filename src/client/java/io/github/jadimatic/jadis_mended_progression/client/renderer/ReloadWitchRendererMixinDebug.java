package io.github.jadimatic.jadis_mended_progression.client.renderer;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.client.mixin.ItemStackRenderStateAccessor;
import io.github.jadimatic.jadis_mended_progression.references.ModTags;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.client.resources.model.cuboid.ItemTransform;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Witch;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ReloadWitchRendererMixinDebug {
	public static void adjustWitchPoseDebug(Witch entity, WitchRenderState state, float partialTicks, CallbackInfo ci){
		//Any code you want to rapidly test should go here. Reload using CTRL + Shift + F9.

	}
}
