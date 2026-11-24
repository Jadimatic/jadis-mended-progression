package io.github.jadimatic.jadis_mended_progression.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class JadisMendedDynamicRegistryProvider extends FabricDynamicRegistryProvider {
	public JadisMendedDynamicRegistryProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE));
		entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
		entries.addAll(registries.lookupOrThrow(Registries.FEATURE));
		entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));


	}

	@Override
	public @NonNull String getName() {
		return "Jadi's Mended Progression Data Provider";
	}
}
