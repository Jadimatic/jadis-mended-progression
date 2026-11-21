package io.github.jadimatic.jadis_mended_progression.item;

import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.minecraft.world.item.ToolMaterial;

public class JadisMendedToolMaterials {


	public static final ToolMaterial BRONZE_TOOL_MATERIAL = new ToolMaterial(
		JadisMendedTags.Blocks.INCORRECT_FOR_BRONZE_TOOL, // incorrect blocks for drops
		455, // durability
		5.0F, // speed
		1.5F, // attack damage bonus
		22, // enchantment value
		JadisMendedTags.Items.REPAIRS_BRONZE_GEAR // repair items
	);




}
