package io.github.jadimatic.jadis_mended_progression.numbers;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootPredicates;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.DispatcherProvider;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProvider;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.*;
import net.minecraft.world.level.storage.loot.providers.score.ContextScoreboardNameProvider;

import java.util.List;

public class ModContextIntProviders {
	public static final Codec<ContextIntProvider> DIRECT_CODEC = Codec.lazyInitialized(
		() -> {
			Codec<ContextIntProvider> typedCodec = BuiltInRegistries.CONTEXT_INT_PROVIDER_TYPE.byNameCodec().dispatch(ContextIntProvider::codec, c -> c);
			return Codec.either(ConstantValue.INLINE_CODEC, typedCodec)
				.xmap(Either::unwrap, provider -> provider instanceof ConstantValue constant ? Either.left(constant) : Either.right(provider));
		}
	);

	public static final ResourceKey<ContextIntProvider> COOKING_TIME_EXAMPLE_BLOCK = createKey("cooking/time_coal_block");
	public static final ResourceKey<ContextIntProvider> COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR = createKey("cooking/normal_burn_time_reduction_factor");
	public static final ResourceKey<ContextIntProvider> COOKING_FAST_BURN_TIME_REDUCTION_FACTOR = createKey("cooking/fast_burn_time_reduction_factor");
	public static final ResourceKey<ContextIntProvider> BREWING_DEFAULT_USES = createKey("brewing/uses_default");

	private static ResourceKey<ContextIntProvider> createKey(final String location) {
		return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, Identifier.withDefaultNamespace(location));
	}

	public static void bootstrap(final BootstrapContext<ContextIntProvider> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
		HolderGetter<LootItemCondition> predicates = context.lookup(Registries.PREDICATE);
		Holder.Reference<ContextIntProvider> normalBurnTime = context.register(COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(1));
		Holder.Reference<ContextIntProvider> fastBurnTime = context.register(COOKING_FAST_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(2));

		context.register(COOKING_TIME_EXAMPLE_BLOCK, cooking(predicates, normalBurnTime, fastBurnTime, 16000));
		context.register(BREWING_DEFAULT_USES, new ConstantValue(20));//Used for Blaze Rods in the brewing stand.
	}

	private static ContextIntProvider compostable(final HolderGetter<Block> blocks, final int layerIncreaseChance) {
		if (layerIncreaseChance >= 100) {
			return new ConstantValue(1);
		}

		DispatcherProvider.Case<ContextIntProvider> emptyCase = new DispatcherProvider.Case<>(
			Holder.direct(MatchBlock.blockMatches(blocks, Blocks.COMPOSTER, StatePropertiesPredicate.Builder.properties().hasProperty(ComposterBlock.LEVEL, 0)).build()), exactly(1)
		);
		WeightedList<Holder<ContextIntProvider>> cases = WeightedList.<Holder<ContextIntProvider>>builder()
			.add(exactly(1), layerIncreaseChance)
			.add(exactly(0), 100 - layerIncreaseChance)
			.build();
		return new NumberDispatcher(List.of(emptyCase), weighted(cases));
	}

	private static ContextIntProvider cooking(
		final HolderGetter<LootItemCondition> predicates,
		final Holder.Reference<ContextIntProvider> normalBurnTimeDivisor,
		final Holder.Reference<ContextIntProvider> fastBurnTimeDivisor,
		final int timeSeconds
	) {
		Holder<LootItemCondition> fasterCookingBlocks = predicates.getOrThrow(LootPredicates.FAST_FURNACE);
		ContextIntProvider fastConditional = new ConditionalValue(fasterCookingBlocks, fastBurnTimeDivisor, normalBurnTimeDivisor);
		return div(exactly(timeSeconds), Holder.direct(fastConditional)).value();
	}

	public static Holder<ContextIntProvider> fromFloat(final Holder<ContextFloatProvider> input) {
		return Holder.direct(new FromFloat(input));
	}

	public static Holder<ContextIntProvider> binomial(final int n, final float p) {
		return Holder.direct(new BinomialDistributionGenerator(exactly(n), ContextFloatProviders.exactly(p)));
	}

	public static Holder<ContextIntProvider> exactly(final int value) {
		return Holder.direct(new ConstantValue(value));
	}

	public static Holder<ContextIntProvider> fromScoreboard(final LootContext.EntityTarget entityTarget, final String score) {
		return Holder.direct(new ScoreboardValue(ContextScoreboardNameProvider.forTarget(entityTarget), score, exactly(0)));
	}

	@SafeVarargs
	public static Holder<ContextIntProvider> avg(final Holder<ContextIntProvider>... inputs) {
		return Holder.direct(new Average(HolderSet.<ContextIntProvider>direct(inputs)));
	}

	public static Holder<ContextIntProvider> sub(final Holder<ContextIntProvider> left, final Holder<ContextIntProvider> right) {
		return Holder.direct(new Difference(left, right));
	}

	@SafeVarargs
	public static Holder<ContextIntProvider> max(final Holder<ContextIntProvider>... inputs) {
		return Holder.direct(new Maximum(HolderSet.<ContextIntProvider>direct(inputs)));
	}

	@SafeVarargs
	public static Holder<ContextIntProvider> min(final Holder<ContextIntProvider>... inputs) {
		return Holder.direct(new Minimum(HolderSet.<ContextIntProvider>direct(inputs)));
	}

	public static Holder<ContextIntProvider> mod(final Holder<ContextIntProvider> left, final Holder<ContextIntProvider> right) {
		return Holder.direct(new Modulus(left, right));
	}

	public static Holder<ContextIntProvider> floorMod(final Holder<ContextIntProvider> left, final Holder<ContextIntProvider> right) {
		return Holder.direct(new FloorModulus(left, right));
	}

	public static Holder<ContextIntProvider> negate(final Holder<ContextIntProvider> input) {
		return Holder.direct(new Negate(input));
	}

	@SafeVarargs
	public static Holder<ContextIntProvider> mul(final Holder<ContextIntProvider>... inputs) {
		return Holder.direct(new Product(HolderSet.<ContextIntProvider>direct(inputs)));
	}

	public static Holder<ContextIntProvider> div(final Holder<ContextIntProvider> left, final Holder<ContextIntProvider> right) {
		return Holder.direct(new Quotient(left, right));
	}

	public static Holder<ContextIntProvider> floorDiv(final Holder<ContextIntProvider> left, final Holder<ContextIntProvider> right) {
		return Holder.direct(new FloorQuotient(left, right));
	}

	@SafeVarargs
	public static Holder<ContextIntProvider> add(final Holder<ContextIntProvider>... inputs) {
		return Holder.direct(new Sum(HolderSet.<ContextIntProvider>direct(inputs)));
	}

	public static Holder<ContextIntProvider> between(final int min, final int max) {
		return Holder.direct(new UniformGenerator(exactly(min), exactly(max)));
	}

	public static Holder<ContextIntProvider> weighted(final WeightedList<Holder<ContextIntProvider>> cases) {
		return Holder.direct(new WeightedListValue(cases));
	}
}
