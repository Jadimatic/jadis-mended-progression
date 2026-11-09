package io.github.jadimatic.jadis_mended_progression.client.mixin.renderer.entity;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

///Checks mobs holding the item
@Debug(export = true)
@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin <T extends Mob, S extends HumanoidRenderState, M extends HumanoidModel<S>> extends AgeableMobRenderer<T, S, M> {
	public HumanoidMobRendererMixin(EntityRendererProvider.Context context, M adultModel, M babyModel, float shadow) {
		super(context, adultModel, babyModel, shadow);
	}

	//This is a set mixin.
	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;F)V", at = @At("TAIL"))
	void setLanternRenderState(T entity, S state, float partialTicks, CallbackInfo ci) {
		state.setData(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, entity
			.getItemHeldByArm(HumanoidArm.RIGHT)
			.is(JadisMendedTags.Items.LANTERNS));
	}
}
