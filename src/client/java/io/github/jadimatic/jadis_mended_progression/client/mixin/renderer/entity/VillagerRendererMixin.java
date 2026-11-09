package io.github.jadimatic.jadis_mended_progression.client.mixin.renderer.entity;

import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.client.mixin.ItemStackRenderStateAccessor;
import io.github.jadimatic.jadis_mended_progression.client.renderer.ReloadVillagerRendererMixinDebug;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.client.resources.model.cuboid.ItemTransform;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.npc.villager.Villager;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerRenderer.class)
public abstract class VillagerRendererMixin extends AgeableMobRenderer<Villager, VillagerRenderState, VillagerModel> {
	public VillagerRendererMixin(EntityRendererProvider.Context context, VillagerModel adultModel, VillagerModel babyModel, float shadow) {
		super(context, adultModel, babyModel, shadow);
	}

	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/npc/villager/Villager;Lnet/minecraft/client/renderer/entity/state/VillagerRenderState;F)V", at = @At("TAIL"))
	public void adjustVillagerPose(Villager entity, VillagerRenderState state, float partialTicks, CallbackInfo ci) {

		ReloadVillagerRendererMixinDebug.adjustVillagerPoseDebug(entity, state, partialTicks, ci);

		state.setData(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, entity.getItemHeldByArm(HumanoidArm.RIGHT).is(JadisMendedTags.Items.LANTERNS));
		if(state.getDataOrDefault(JadisMendedProgressionClient.HOLDING_LANTERN_IN_RIGHT_ARM, true)){
			((ItemStackRenderStateAccessor) state.heldItem).jadismendedprogression$getLayers()[0].setItemTransform(new ItemTransform(
				new Vector3f(-8, 0, 0),//Rotation
				new Vector3f(0, -0.24f, -0.12f),//Translation
				new Vector3f(0.69f, 0.69f, 0.69f)//Scale
			));

		}

	}
}
