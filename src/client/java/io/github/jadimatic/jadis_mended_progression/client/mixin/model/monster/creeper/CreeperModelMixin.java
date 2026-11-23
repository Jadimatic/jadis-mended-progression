package io.github.jadimatic.jadis_mended_progression.client.mixin.model.monster.creeper;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreeperModel.class)
public class CreeperModelMixin extends EntityModel<CreeperRenderState> {
	@Shadow
	public static LayerDefinition createBodyLayer(CubeDeformation g) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}


	///WE THINK THIS IS NO LONGER NECESSARY, SO WE WILL DO NOTHING IN HERE UNLESS IT BECOMES NECESSARY, IF IT ISN'T THIS FILE WILL BE DELETED.

	protected CreeperModelMixin(ModelPart root, CreeperModel model)
	{super(root);
	}
/*
	@Unique
	LayerDefinition test = CreeperModel.createBodyLayer(new CubeDeformation(2.0F));
*/












	/* //Shelved for now, not sure how to add more boxes.
	@Definition(id = "leg", local = @Local(type = CubeListBuilder.class, name = "leg"))
	@Definition(id = "create", method = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;create()Lnet/minecraft/client/model/geom/builders/CubeListBuilder;")
	@Definition(id = "texOffs", method = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;texOffs(II)Lnet/minecraft/client/model/geom/builders/CubeListBuilder;")
	@Definition(id = "addBox", method = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;addBox(FFFFFFLnet/minecraft/client/model/geom/builders/CubeDeformation;)Lnet/minecraft/client/model/geom/builders/CubeListBuilder;")
	@Expression("leg = @(create().texOffs(?, ?).addBox(?, ?, ?, ?, ?, ?, ?))")
	@ModifyArg(method = "createBodyLayer", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static CubeListBuilder()


	@Unique//Prevents horrible errors from other mods also trying to add something.
	private static LayerDefinition createSleeveLayer(final CubeDeformation g) {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, g), PartPose.offset(0.0F, 6.0F, 0.0F));
		root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, g), PartPose.offset(0.0F, 6.0F, 0.0F));
		CubeListBuilder leg = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, g);
		root.addOrReplaceChild("right_hind_leg", leg, PartPose.offset(-2.0F, 18.0F, 4.0F));
		root.addOrReplaceChild("left_hind_leg", leg, PartPose.offset(2.0F, 18.0F, 4.0F));
		root.addOrReplaceChild("right_front_leg", leg, PartPose.offset(-2.0F, 18.0F, -4.0F));
		root.addOrReplaceChild("left_front_leg", leg, PartPose.offset(2.0F, 18.0F, -4.0F));
		return LayerDefinition.create(mesh, 64, 32);
	}
	*/


}
