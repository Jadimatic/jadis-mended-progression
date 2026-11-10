package io.github.jadimatic.jadis_mended_progression.block;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class JadisMendedBlockOverrides {
	public static void modifyVanillaBlocks(ResourceKey<Block> id, BlockBehaviour.Properties properties) {
		//Add any overrides to vanilla blocks here
		//We have to do an id check because calling the Blocks class directly while that class is currently registering blocks causes big errors! Don't do it!
		//Use namespace id instead!
		if (id.identifier().toString().equals("minecraft:end_portal_frame")) {
			properties.strength(10, 8000);
		}
	}
}
