package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import io.github.jadimatic.jadis_mended_progression.item.ModItems;
import io.github.jadimatic.jadis_mended_progression.references.ModBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
		builder(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(ModBlockItemIds.CHARCOAL_BLOCK.block());
		builder(BlockTags.NEEDS_STONE_TOOL).add(ModBlockItemIds.CHARCOAL_BLOCK.block());


        builder(BlockTags.SUPPORTS_CROPS)
                .add(BlockItemIds.FARMLAND)
                .add(BlockItemIds.GRASS_BLOCK);
        builder(BlockTags.GROWS_CROPS)
                .add(BlockItemIds.FARMLAND)
                .add(BlockItemIds.GRASS_BLOCK);
    }
}
