package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.references.JadisMendedBlockItemIds;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class JadisMendedBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public JadisMendedBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {

		builder(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(JadisMendedBlockItemIds.CHISELED_IRON.block())
			.add(JadisMendedBlockItemIds.CHARCOAL_BLOCK.block())
			.add(JadisMendedBlockItemIds.TIN_ORE.block())
			.add(JadisMendedBlockItemIds.CUT_IRON.block());

		builder(BlockTags.NEEDS_STONE_TOOL)
			.add(JadisMendedBlockItemIds.CHARCOAL_BLOCK.block())
			.add(JadisMendedBlockItemIds.TIN_ORE.block());

		builder(JadisMendedTags.Blocks.NEEDS_BRONZE_TOOL)
			.add(BlockItemIds.IRON_ORE.block())
			.add(BlockItemIds.RAW_IRON_BLOCK.block())
			.add(BlockItemIds.IRON_BLOCK.block());


		//builder(BlockTags.INCORRECT_FOR_COPPER_TOOL);


		builder(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(JadisMendedTags.Blocks.NEEDS_BRONZE_TOOL);//Should make wooden tools invalid for blocks in the NEEDS_BRONZE_TOOL tag.
		builder(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(JadisMendedTags.Blocks.NEEDS_BRONZE_TOOL);//Should make stone tools invalid for blocks in the NEEDS_BRONZE_TOOL tag.


		builder(BlockTags.NEEDS_STONE_TOOL)
			.add(JadisMendedBlockItemIds.CHISELED_IRON.block())
			.add(JadisMendedBlockItemIds.CUT_IRON.block());


        builder(BlockTags.SUPPORTS_CROPS)
			.add(BlockItemIds.FARMLAND.block())
			.add(BlockItemIds.GRASS_BLOCK.block());
        builder(BlockTags.GROWS_CROPS)
			.add(BlockItemIds.FARMLAND.block())
			.add(BlockItemIds.GRASS_BLOCK.block());

		builder(BlockItemTags.LEAVES.block());//Adds a reference to #minecraft:leaves(without this it fails to add this tag to #minecraft:climbable).
		builder(JadisMendedTags.Blocks.CLIMBABLE_LEAVES).addTag(BlockTags.LEAVES);
		builder(BlockTags.CLIMBABLE).addTag(JadisMendedTags.Blocks.CLIMBABLE_LEAVES);
    }
}
