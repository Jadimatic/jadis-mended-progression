package io.github.jadimatic.jadis_mended_progression.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class JadisMendedEnchantmentTagsProvider extends FabricTagsProvider<Enchantment> {


	/**
	 * Constructs a new {@link FabricTagsProvider} with the default computed path.
	 *
	 * <p>Common implementations of this class are provided.
	 *
	 * @param output               the {@link FabricPackOutput} instance
	 * @param registryLookupFuture the backing registry for the tag type
	 */
	public JadisMendedEnchantmentTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.ENCHANTMENT, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider registries) {
		builder(EnchantmentTags.TRADEABLE).remove(Enchantments.MENDING);//Removes the Mending enchantment from being traded by villagers.
		builder(EnchantmentTags.ON_RANDOM_LOOT).remove(Enchantments.MENDING);//Removes it from random loot.
		builder(EnchantmentTags.TREASURE).remove(Enchantments.MENDING);//Removes it from treasure items.
		builder(EnchantmentTags.IN_ENCHANTING_TABLE).remove(Enchantments.MENDING);//Removes it from the enchanting table.
	}
}
