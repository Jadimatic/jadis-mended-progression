package io.github.jadimatic.jadis_mended_progression.client.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CreeperModelMixin.class)
public class CreeperModelMixin extends EntityModel<CreeperRenderState> {
	protected CreeperModelMixin(ModelPart root) {
		super(root);
	}


	@Unique
	private void replaceCreeperModelMixin(){


	}

}
