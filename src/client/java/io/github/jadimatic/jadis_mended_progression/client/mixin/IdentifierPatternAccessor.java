package io.github.jadimatic.jadis_mended_progression.client.mixin;

import net.minecraft.util.IdentifierPattern;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;
import java.util.regex.Pattern;

@Mixin(IdentifierPattern.class)
public interface IdentifierPatternAccessor {
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	@Invoker("<init>")
	static IdentifierPattern jadis_mended_progression_datagen$construct(
		Optional<Pattern> namespacePattern,
		Optional<Pattern> pathPattern
	) {
		throw new IllegalStateException("Dummy method body invoked!");
	}
}
