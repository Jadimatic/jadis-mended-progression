package io.github.jadimatic.jadis_mended_progression.client.mixin.tutorial;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import io.github.jadimatic.jadis_mended_progression.client.tutorial.FindShelterTutorialStepInstance;
import net.minecraft.client.tutorial.CraftPlanksTutorialStep;
import net.minecraft.client.tutorial.TutorialSteps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(CraftPlanksTutorialStep.class)
public class CraftPlanksTutorialStepMixin {


	@Definition(id = "tutorial", field = "Lnet/minecraft/client/tutorial/CraftPlanksTutorialStep;tutorial:Lnet/minecraft/client/tutorial/Tutorial;")
	@Definition(id = "setStep", method = "Lnet/minecraft/client/tutorial/Tutorial;setStep(Lnet/minecraft/client/tutorial/TutorialSteps;)V")
	@Definition(id = "NONE", field = "Lnet/minecraft/client/tutorial/TutorialSteps;NONE:Lnet/minecraft/client/tutorial/TutorialSteps;")
	@Expression("@(this.tutorial.setStep(?))")//Targets the lines containing "this.tutorial.setStep()"
	@ModifyArg(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"),//Targets an expression in the "tick" method.
		slice = @Slice(from = @At(value = "INVOKE",//The @Slice tells the @ModifyArg to start from the line containing "getMinecraft()", this way it doesn't modify anything above that.
			target = "Lnet/minecraft/client/tutorial/Tutorial;getMinecraft()Lnet/minecraft/client/Minecraft;")))
	private static TutorialSteps test(TutorialSteps originalStep){
		return FindShelterTutorialStepInstance.FIND_SHELTER;
	}
}
