package io.github.jadimatic.jadis_mended_progression.mixin;

import net.minecraft.core.Holder.Reference;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(Reference.class)
public interface ReferenceAccessor<T> {
	@Accessor("tags")
	Set<TagKey<T>> jadisMended$getTags();
}
