package io.github.jadimatic.jadis_mended_progression.client.mixin.renderer.entity;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.client.mixin.ItemStackRenderStateAccessor;
import io.github.jadimatic.jadis_mended_progression.client.renderer.ReloadVillagerRendererMixinDebug;
import io.github.jadimatic.jadis_mended_progression.client.renderer.ReloadWitchRendererMixinDebug;
import io.github.jadimatic.jadis_mended_progression.references.ModTags;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.cuboid.ItemTransform;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitchRenderer.class)
public abstract class WitchRendererMixin extends MobRenderer<Witch, WitchRenderState, WitchModel> {
	public WitchRendererMixin(EntityRendererProvider.Context context, WitchModel model, float shadow) {
		super(context, model, shadow);
	}

	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/monster/Witch;Lnet/minecraft/client/renderer/entity/state/WitchRenderState;F)V", at = @At("TAIL"))
	public void adjustWitchPose (Witch entity, WitchRenderState state, float partialTicks, CallbackInfo ci){
		ReloadWitchRendererMixinDebug.adjustWitchPoseDebug(entity, state, partialTicks, ci);

		state.setData(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, entity.getItemHeldByArm(HumanoidArm.RIGHT).is(ModTags.Items.LANTERNS));
		if(state.getDataOrDefault(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, true)){
			((ItemStackRenderStateAccessor) state.heldItem).jadismendedprogression$getLayers()[0].setItemTransform(new ItemTransform(
				new Vector3f(-8, 0, 0),//Rotation
				new Vector3f(0, -0.24f, -0.12f),//Translation
				new Vector3f(0.69f, 0.69f, 0.69f)//Scale
			));
		}

		if(entity.getMainHandItem().isEmpty()){
			//((ItemStackRenderStateAccessor) state.heldItem).jadismendedprogression$getLayers()[0] = entity.getOffhandItem(Display.RenderState);
		}
		state.isHoldingItem = state.isHoldingPotion;//Fixes a bug where the witch's nose moved when holding any item.


	}


}
