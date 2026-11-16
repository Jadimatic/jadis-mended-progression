package io.github.jadimatic.jadis_mended_progression.client.datagen.villager;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

/**
 * Handles the creation of new villager trades.
 * <p>A list of every vanilla villager trade can be found in "VillagerTrades".
 */
public class JadisMendedVillagerTradesProvider {
	public static final ResourceKey<VillagerTrade> FISHERMAN_1_FLINT_FLINT = createKey("fisherman/1/flint_flint");//Creates a trade JSON file.

	public static void bootstrap(BootstrapContext<VillagerTrade> context){//Generates the trades.
		context.register(FISHERMAN_1_FLINT_FLINT, new VillagerTrade.Builder(//This is a dummy example trade, remove later.
			new TradeCost(Items.FLINT, 1),//Item goes in.
			new ItemStackTemplate(Items.FLINT, 1),//Item comes out.
			ContextIntProviders.exactly(12),//Max uses.
			ContextIntProviders.exactly(0),//Exp reward.
			ContextFloatProviders.exactly(0.05f))//Reputation discount(This should stay very small to keep the economy balanced).
			//.merchantPredicate(<predicate>)//Add predicates here if you want the trade to only be available circumstantially.
			.build());//Turns this trade into an object from the Builder, allowing it to register properly.
	}

	private static ResourceKey<VillagerTrade> createKey(String name){
		return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
	}
}
