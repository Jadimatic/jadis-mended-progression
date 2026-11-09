package io.github.jadimatic.jadis_mended_progression.references;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class JadisMendedTags {

	public static class Blocks {
		private static TagKey<Block> createTag(String name) {
			return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> LANTERNS = createTag("lanterns");

		public static final TagKey<Item> REPAIRS_IRON_ANVIL = createTag("repairs_iron_anvil");

		private static TagKey<Item> createTag(String name){
			return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
		}
	}
}
