package io.github.jadimatic.jadis_mended_progression.util;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

	public static class Blocks{
		public static final TagKey<Block> NEEDS_FLINT_TOOL = createTag("needs_flint_tool");
		private  static TagKey<Block> createTag(String name) {
			return TagKey.of(RegistryKeys.BLOCK, new Identifier(JadisMendedProgression.MOD_ID));
		}
	}
	public static class Items{
		public static final TagKey<Item> EXAMPLE_TAG_ITEMS = createTag("example_tag_items");

		private  static TagKey<Item> createTag(String name) {
			return TagKey.of(RegistryKeys.ITEM, new Identifier(JadisMendedProgression.MOD_ID));
		}
	}
}
