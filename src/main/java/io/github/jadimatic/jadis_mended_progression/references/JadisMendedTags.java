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
			return TagKey.create(Registries.BLOCK,//Specifies that these will be block tags.
				Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
		}
		public static final TagKey<Block> NEEDS_BRONZE_TOOL = createTag("needs_bronze_tool");
		public static final TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = createTag("incorrect_for_bronze_tool");
		public static final TagKey<Block> CLIMBABLE_LEAVES = createTag("climbable_leaves");
		public static final TagKey<Block> VALID_FOR_CREEPER_SPAWN = createTag("valid_for_creeper_spawn");
	}

	public static class Items {
		private static TagKey<Item> createTag(String name){
			return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
		}

		public static final TagKey<Item> LANTERNS = createTag("lanterns");
		public static final TagKey<Item> BRONZE_INGOTS = createTag("ingots/bronze");//Exists for mod compatibility uses.
		public static final TagKey<Item> STEEL_INGOTS = createTag("ingots/steel");//Exists for mod compatibility uses.
		public static final TagKey<Item> REPAIRS_BRONZE_GEAR = createTag("repairs_bronze_gear");
		public static final TagKey<Item> REPAIRS_IRON_ANVIL = createTag("repairs_iron_anvil");
		public static final TagKey<Item> BREAKABLE_ITEMS = createTag("breakable_items");
	}
}
