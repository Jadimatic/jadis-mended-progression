package io.github.jadimatic.jadis_mended_progression.client;

import io.github.jadimatic.jadis_mended_progression.client.datagen.*;
import io.github.jadimatic.jadis_mended_progression.client.datagen.villager.JadisMendedVillagerTradeTagsProvider;
import io.github.jadimatic.jadis_mended_progression.client.datagen.villager.JadisMendedVillagerTradesProvider;
import io.github.jadimatic.jadis_mended_progression.worldgen.JadisMendedFeatures;
import io.github.jadimatic.jadis_mended_progression.worldgen.JadisMendedPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

public class JadisMendedProgressionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(JadisMendedModelProvider::new);
		pack.addProvider(JadisMendedBlockLootTableProvider::new);
		pack.addProvider(ModChestLootTableProvider::new);
		pack.addProvider(JadisMendedBlockTagsProvider::new);
		pack.addProvider(JadisMendedRecipeProvider::new);
		pack.addProvider(VanillaRecipeProvider::new);
		pack.addProvider(JadisMendedItemTagsProvider::new);
		pack.addProvider(ModPackMetadataGenerator::new);
		pack.addProvider(JadisMendedVillagerTradeTagsProvider::new);
		pack.addProvider(JadisMendedDynamicRegistryProvider::new);
		pack.addProvider(JadisMendedEnchantmentTagsProvider::new);


	}
	@Override
	public void buildRegistry(@NonNull RegistrySetBuilder registryBuilder){
		registryBuilder.add(Registries.VILLAGER_TRADE, JadisMendedVillagerTradesProvider::bootstrap);

		registryBuilder.add(Registries.FEATURE, JadisMendedFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, JadisMendedPlacedFeatures::bootstrap);
	}

}
