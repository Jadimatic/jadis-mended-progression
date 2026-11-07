package io.github.jadimatic.jadis_mended_progression;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
	public static final TagKey<Item> REPAIRS_IRON_ANVIL = registerItemTag("repairs_iron_anvil");

	private static TagKey<Item> registerItemTag(String id) {
		return TagKey.create(Registries.ITEM, JadisMendedProgression.id(id));
	}
}
