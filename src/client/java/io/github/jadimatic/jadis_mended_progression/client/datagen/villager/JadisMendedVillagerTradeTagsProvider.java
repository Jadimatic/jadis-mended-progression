package io.github.jadimatic.jadis_mended_progression.client.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.item.trading.VillagerTrades;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class JadisMendedVillagerTradeTagsProvider extends FabricTagsProvider<VillagerTrade>{


	/**
	 * Constructs a new {@link FabricTagsProvider} with the default computed path.
	 * <p>A list of every vanilla villager trade can be found in "VillagerTrades".
	 *
	 * <p>Common implementations of this class are provided.
	 *
	 * @param output               the {@link FabricPackOutput} instance
	 * @param registryLookupFuture the backing registry for the tag type
	 */
	public JadisMendedVillagerTradeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider registries) {//Registers trades to the specified tags.
		this.builder(VillagerTradeTags.FISHERMAN_LEVEL_1)//Specifies what trade tag to register the trades to.
			.add(JadisMendedVillagerTradesProvider.FISHERMAN_1_FLINT_FLINT)
			.add(VillagerTrades.FISHERMAN_1_STRING_EMERALD)//Adds a trade from the original tag. This will stay even if everything else gets replaced.
			.setReplace(true);//This makes the mod trades for this tag replace everything from the original.

	}
}
