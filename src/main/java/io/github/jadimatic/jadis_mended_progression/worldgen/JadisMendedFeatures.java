package io.github.jadimatic.jadis_mended_progression.worldgen;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class JadisMendedFeatures {

	public static ResourceKey<Feature> OVERWORLD_TIN_ORE_KEY = registerKey("overworld_tin_ore_key");

	public static void bootstrap(BootstrapContext<Feature> context){
		RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
		RuleTest endStoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

		context.register(OVERWORLD_TIN_ORE_KEY, new OreFeature(
			List.of(BlockReplacement.replace(stoneReplaceables, JadisMendedBlocks.TIN_ORE.defaultBlockState()),
			BlockReplacement.replace(deepslateReplaceables, JadisMendedBlocks.DEEPSLATE_TIN_ORE.defaultBlockState())), 60));
	}



	public static ResourceKey<Feature> registerKey(String name){
		return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name));
	}
}
