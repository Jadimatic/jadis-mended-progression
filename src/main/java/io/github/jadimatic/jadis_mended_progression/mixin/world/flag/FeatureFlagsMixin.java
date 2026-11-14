package io.github.jadimatic.jadis_mended_progression.mixin.world.flag;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraft.world.flag.FeatureFlags.MINECART_IMPROVEMENTS;

@Debug(export = true)
@Mixin(FeatureFlags.class)
public class FeatureFlagsMixin {

	@Definition(id = "DEFAULT_FLAGS", field = "Lnet/minecraft/world/flag/FeatureFlags;DEFAULT_FLAGS:Lnet/minecraft/world/flag/FeatureFlagSet;")
	@Definition(id = "VANILLA_SET", field = "Lnet/minecraft/world/flag/FeatureFlags;VANILLA_SET:Lnet/minecraft/world/flag/FeatureFlagSet;")
	@Expression("DEFAULT_FLAGS = @(VANILLA_SET)")
	@ModifyExpressionValue(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static FeatureFlagSet test(FeatureFlagSet original){

		return original.join(FeatureFlagSet.of(MINECART_IMPROVEMENTS));
	}





}
