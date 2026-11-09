package io.github.jadimatic.jadis_mended_progression.client.renderer;

import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ReloadVillagerRendererMixinDebug {
	public static void adjustVillagerPoseDebug(Villager entity, VillagerRenderState state, float partialTicks, CallbackInfo ci){
		//Any code you want to rapidly test should go here. Reload using CTRL + Shift + F9.
	}
}
