package io.github.jadimatic.jadis_mended_progression.client.mixin.tutorial;

import io.github.jadimatic.jadis_mended_progression.client.tutorial.FindShelterTutorialStepInstance;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.client.tutorial.TutorialStepInstance;
import net.minecraft.client.tutorial.TutorialSteps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Function;

@Mixin(TutorialSteps.class)
enum TutorialStepsMixin {
	FIND_SHELTER("find_shelter", FindShelterTutorialStepInstance::new);




	@Shadow
	private <T extends TutorialStepInstance> TutorialStepsMixin(String name, Function<Tutorial, T> constructor) {

	}
}





