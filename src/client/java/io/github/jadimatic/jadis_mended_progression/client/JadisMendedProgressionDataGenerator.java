package io.github.jadimatic.jadis_mended_progression.client;

import io.github.jadimatic.jadis_mended_progression.client.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.SharedConstants;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.ResourceFilterSection;

public class JadisMendedProgressionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModChestLootTableProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(VanillaRecipeProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModPackMetadataGenerator::new);















	}
}
