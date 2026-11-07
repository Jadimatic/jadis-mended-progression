package io.github.jadimatic.jadis_mended_progression.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ReloadHumanoidModelMixinDebug {
	public static <T extends HumanoidRenderState> void setupArmPose(T state, HumanoidModel<T> tHumanoidModel, CallbackInfo ci) {
		//Any code you want to rapidly test should go here. Reload using CTRL + Shift + F9.
	}
}
