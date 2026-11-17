package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.client.datagen.villager.JadisMendedVillagerTradesProvider;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.VillagerTrades;

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

		this.builder(ItemTags.HORSE_TEMPT_ITEMS)//Specifies what tag to register the items to.
			.add(ItemIds.SUGAR)
			.add(ItemIds.APPLE)
			.add(BlockItemIds.CARROT_CROP);//For some reason the block and item share the same id, so this is fine.

		this.builder(ItemTags.HORSE_FOOD)//Specifies what tag to register the items to.
			.add(ItemIds.BEETROOT);//Mojang probably just forgot to add this when they added the crop to the game.

	}
}
