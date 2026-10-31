package io.github.jadimatic.jadis_mended_progression.references;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
///Registers ItemIds for blocks added by this mod, e.g. "example_block".
public class ModBlockItemIds {
	private static BlockItemId create(String name) {
		Identifier id = JadisMendedProgression.id(name);
		return BlockItemId.create(id, id);
	}
	public static final BlockItemId CHARCOAL_BLOCK = create("charcoal_block");
}
