package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.item.JadisMendedItems;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class JadisMendedItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
	public JadisMendedItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(BlockItemTags.LANTERNS.item());//Adds a reference to #minecraft:lanterns(without this the tag fails to generate).
		builder(JadisMendedTags.Items.LANTERNS)
			.addTag(BlockItemTags.LANTERNS.item());//Adds #minecraft:lanterns to the #jadis_mended_progression:lanterns tag.

		builder(ItemTags.PICKAXES)
			.add(JadisMendedItems.getResourceKey(JadisMendedItems.BRONZE_PICKAXE));
		builder(ItemTags.SHOVELS)
			.add(JadisMendedItems.getResourceKey(JadisMendedItems.BRONZE_SHOVEL));
		builder(ItemTags.HOES)
			.add(JadisMendedItems.getResourceKey(JadisMendedItems.BRONZE_HOE));
		builder(ItemTags.AXES)
			.add(JadisMendedItems.getResourceKey(JadisMendedItems.BRONZE_AXE));
		builder(ItemTags.SPEARS)
			.add(JadisMendedItems.getResourceKey(JadisMendedItems.BRONZE_SPEAR));

		builder(ItemTags.HORSE_TEMPT_ITEMS)//Specifies what tag to register the items to.
			.add(ItemIds.SUGAR)
			.add(ItemIds.APPLE)
			.add(BlockItemIds.CARROT_CROP);//For some reason the block and item share the same id, so this is fine.

		builder(ItemTags.HORSE_FOOD)//Specifies what tag to register the items to.
			.add(ItemIds.BEETROOT);//Mojang probably just forgot to add this when they added the crop to the game.

	}
}
