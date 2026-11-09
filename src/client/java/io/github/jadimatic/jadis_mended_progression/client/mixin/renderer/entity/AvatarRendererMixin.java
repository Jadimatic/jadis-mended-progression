package io.github.jadimatic.jadis_mended_progression.client.mixin.renderer.entity;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

///Checks players holding the item
@Debug(export = true)
@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin <AvatarlikeEntity extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<AvatarlikeEntity, AvatarRenderState, PlayerModel> {
	public AvatarRendererMixin(EntityRendererProvider.Context context, PlayerModel model, float shadow) {
		super(context, model, shadow);
	}

	//This is a set mixin.
	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At("TAIL"))
	void setLanternRenderState(AvatarlikeEntity entity, AvatarRenderState state, float partialTicks, CallbackInfo ci) {
		state.setData(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, entity
			.getItemHeldByArm(HumanoidArm.RIGHT)
			.is(JadisMendedTags.Items.LANTERNS));
		state.setData(JadisMendedProgressionClient.HOLDING_LANTERN_IN_LEFT_ARM, entity
			.getItemHeldByArm(HumanoidArm.LEFT)
			.is(JadisMendedTags.Items.LANTERNS));
	}
}
