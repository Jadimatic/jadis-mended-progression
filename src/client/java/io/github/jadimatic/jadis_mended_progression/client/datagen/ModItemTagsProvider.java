package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
	public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(BlockItemTags.LANTERNS.item());//Adds a reference to #minecraft:lanterns(without this the tag fails to generate).
		builder(JadisMendedTags.Items.LANTERNS)
			.addTag(BlockItemTags.LANTERNS.item());//Adds #minecraft:lanterns to the #jadis_mended_progression:lanterns tag.
	}
}
