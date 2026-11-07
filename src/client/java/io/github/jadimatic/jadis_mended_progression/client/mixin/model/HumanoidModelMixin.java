package io.github.jadimatic.jadis_mended_progression.client.mixin.model;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.client.model.ReloadHumanoidModelMixinDebug;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin <T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel<T> {
	protected HumanoidModelMixin(ModelPart root) {
		super(root);
	}




	//This is a get mixin
	@Inject(method = "poseRightArm",
		at = @At("HEAD"), cancellable = true)
	void adjustRightArmLanternPose(T state, CallbackInfo ci) {
		if (state.getDataOrDefault(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, false)) {
			//Put right arm pose code here.
			((HumanoidModel<T>) (Object) this).rightArm.xRot = +(float) (((HumanoidModel<T>) (Object) this).head.xRot - 1.4);
			((HumanoidModel<T>) (Object) this).rightArm.yRot = +(float) (((HumanoidModel<T>) (Object) this).head.yRot);
			ci.cancel();
		}
	}
	@Inject(method = "poseLeftArm",
		at = @At("HEAD"), cancellable = true)
	void adjustLeftArmLanternPose(T state, CallbackInfo ci) {
		if (state.getDataOrDefault(JadisMendedProgressionClient.HOLDING_LANTERN_IN_LEFT_ARM, false)){
			//Put left arm pose code here.
			ReloadHumanoidModelMixinDebug.setupArmPose(state, ((HumanoidModel<T>) (Object) this), ci);

			((HumanoidModel<T>) (Object) this).leftArm.xRot = +(float) (((HumanoidModel<T>) (Object) this).head.xRot - 1.4);
			((HumanoidModel<T>) (Object) this).leftArm.yRot = +(float) (((HumanoidModel<T>) (Object) this).head.yRot);
			ci.cancel();
		}
	}




}
