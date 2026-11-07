package io.github.jadimatic.jadis_mended_progression.client.mixin.model;


import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.jadimatic.jadis_mended_progression.client.JadisMendedProgressionClient;
import io.github.jadimatic.jadis_mended_progression.client.datagen.ModBlockTagsProvider;
import io.github.jadimatic.jadis_mended_progression.client.datagen.ModItemTagsProvider;
import io.github.jadimatic.jadis_mended_progression.references.ModTags;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.UndeadRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnimationUtils.class)
public class AnimationUtilsMixin {
	///Fixes an issue where baby zombies don't hold lanterns correctly when in their offhand, because of typical Minecraft code spaghetti.
	@Definition(id = "state", local = @Local(type = UndeadRenderState.class, name = "state", argsOnly = true))
	@Definition(id = "getMainHandItemStack", method = "Lnet/minecraft/client/renderer/entity/state/UndeadRenderState;getMainHandItemStack()Lnet/minecraft/world/item/ItemStack;")
	@Definition(id = "EMPTY", field = "Lnet/minecraft/world/item/ItemStack;EMPTY:Lnet/minecraft/world/item/ItemStack;")
	@Expression("state.getMainHandItemStack() == EMPTY")
	@ModifyExpressionValue(method = "animateZombieArms", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static <T extends UndeadRenderState> boolean animateZombieArms(boolean original, final ModelPart leftArm, final ModelPart rightArm, final boolean aggressive, final T state) {
		return original || state.getMainHandItemStack().is(ModTags.Items.LANTERNS);
	}
}
