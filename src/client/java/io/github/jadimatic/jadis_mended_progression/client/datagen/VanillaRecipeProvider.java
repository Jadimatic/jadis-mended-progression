package io.github.jadimatic.jadis_mended_progression.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VanillaRecipeProvider extends FabricRecipeProvider {
	public VanillaRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
		return new RecipeProvider(recipes, advancements) {
			@Override
			public void buildRecipes() {
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);


				shaped(RecipeCategory.TOOLS, Items.STONE_PICKAXE)
					.pattern("FFF")
					.pattern(" S ")
					.pattern(" S ")
					.define('S', Items.STICK)
					.define('F', Items.FLINT)
					.unlockedBy(Items.FLINT.toString(), has(Items.FLINT))
					.save(output);

				shaped(RecipeCategory.TOOLS, Items.STONE_SWORD)
					.pattern(" F ")
					.pattern(" F ")
					.pattern(" S ")
					.define('S', Items.STICK)
					.define('F', Items.FLINT)
					.unlockedBy(Items.FLINT.toString(), has(Items.FLINT))
					.save(output);

				shaped(RecipeCategory.TOOLS, Items.STONE_SHOVEL)
					.pattern(" F ")
					.pattern(" S ")
					.pattern(" S ")
					.define('S', Items.STICK)
					.define('F', Items.FLINT)
					.unlockedBy(Items.FLINT.toString(), has(Items.FLINT))
					.save(output);

				shaped(RecipeCategory.TOOLS, Items.STONE_AXE)
					.pattern("FF ")
					.pattern("FS ")
					.pattern(" S ")
					.define('S', Items.STICK)
					.define('F', Items.FLINT)
					.unlockedBy(Items.FLINT.toString(), has(Items.FLINT))
					.save(output);

				shaped(RecipeCategory.TOOLS, Items.STONE_HOE)
					.pattern("FF ")
					.pattern(" S ")
					.pattern(" S ")
					.define('S', Items.STICK)
					.define('F', Items.FLINT)
					.unlockedBy(Items.FLINT.toString(), has(Items.FLINT))
					.save(output);


				shaped(RecipeCategory.DECORATIONS, Items.BARREL)
					.pattern("T")
					.pattern("L")
					.define('T', ItemTags.WOODEN_TRAPDOORS)
					.define('L', ItemTags.LOGS)
					.unlockedBy(ItemTags.LOGS.toString(), has(ItemTags.LOGS))
					.save(output);

				shaped(RecipeCategory.REDSTONE, Items.LEVER, 2)
					.pattern("S")
					.pattern("C")
					.pattern("R")
					.define('S', Items.STICK)
					.define('C', Items.COBBLESTONE)
					.define('R', Items.REDSTONE)
					.unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
					.save(output);

				//region Generate Button Recipes
				ArrayList<ArrayList<Item>> buttonGenerateList = new ArrayList<>();
				//Argument one is the INPUT item, Argument 2 is the OUTPUT item
				//Argument 3 is the list to add it to! This is important depending on the kind of recipe to be generated!
				createTwoItemList(Items.OAK_PLANKS, Items.OAK_BUTTON, buttonGenerateList);
				createTwoItemList(Items.SPRUCE_PLANKS, Items.SPRUCE_BUTTON, buttonGenerateList);
				createTwoItemList(Items.BIRCH_PLANKS, Items.BIRCH_BUTTON, buttonGenerateList);
				createTwoItemList(Items.JUNGLE_PLANKS, Items.JUNGLE_BUTTON, buttonGenerateList);
				createTwoItemList(Items.ACACIA_PLANKS, Items.ACACIA_BUTTON, buttonGenerateList);
				createTwoItemList(Items.DARK_OAK_PLANKS, Items.DARK_OAK_BUTTON, buttonGenerateList);
				createTwoItemList(Items.MANGROVE_PLANKS, Items.MANGROVE_BUTTON, buttonGenerateList);
				createTwoItemList(Items.CHERRY_PLANKS, Items.CHERRY_BUTTON, buttonGenerateList);
				createTwoItemList(Items.PALE_OAK_PLANKS, Items.PALE_OAK_BUTTON, buttonGenerateList);
				createTwoItemList(Items.POPLAR_PLANKS, Items.POPLAR_BUTTON, buttonGenerateList);
				createTwoItemList(Items.BAMBOO_PLANKS, Items.BAMBOO_BUTTON, buttonGenerateList);
				createTwoItemList(Items.CRIMSON_PLANKS, Items.CRIMSON_BUTTON, buttonGenerateList);
				createTwoItemList(Items.WARPED_PLANKS, Items.WARPED_BUTTON, buttonGenerateList);
				createTwoItemList(Items.STONE, Items.STONE_BUTTON, buttonGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_BUTTON, buttonGenerateList);

				//Loops through the list initialized above and mass registers the recipes
				for (ArrayList<Item> gottenButtonList : buttonGenerateList) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shapeless(RecipeCategory.REDSTONE, gottenButtonList.get(1), 8)
						.requires(gottenButtonList.get(0))
						.requires(Items.REDSTONE)
						.unlockedBy(getHasName(gottenButtonList.get(0)), has(gottenButtonList.get(0)))
						.save(output);
				}
				//endregion

				//region Generate Pressure Plate Recipes
				ArrayList<ArrayList<Item>> pressureplateGenerateList = new ArrayList<>();
				//Argument one is the INPUT item, Argument 2 is the OUTPUT item
				//Argument 3 is the list to add it to! This is important depending on the kind of recipe to be generated!
				createTwoItemList(Items.OAK_PLANKS, Items.OAK_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.SPRUCE_PLANKS, Items.SPRUCE_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.BIRCH_PLANKS, Items.BIRCH_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.JUNGLE_PLANKS, Items.JUNGLE_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.ACACIA_PLANKS, Items.ACACIA_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.DARK_OAK_PLANKS, Items.DARK_OAK_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.MANGROVE_PLANKS, Items.MANGROVE_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.CHERRY_PLANKS, Items.CHERRY_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.PALE_OAK_PLANKS, Items.PALE_OAK_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.POPLAR_PLANKS, Items.POPLAR_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.BAMBOO_PLANKS, Items.BAMBOO_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.CRIMSON_PLANKS, Items.CRIMSON_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.WARPED_PLANKS, Items.WARPED_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.STONE, Items.STONE_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.IRON_INGOT, Items.HEAVY_WEIGHTED_PRESSURE_PLATE, pressureplateGenerateList);
				createTwoItemList(Items.GOLD_INGOT, Items.LIGHT_WEIGHTED_PRESSURE_PLATE, pressureplateGenerateList);


				//Loops through the list initialized above and mass registers the recipes
				for (ArrayList<Item> gottenPressurePlateList : pressureplateGenerateList) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shaped(RecipeCategory.REDSTONE, gottenPressurePlateList.get(1), 4)
						.pattern("PPR")
						.define('P', gottenPressurePlateList.get(0))
						.define('R', Items.REDSTONE)
						.unlockedBy(getHasName(gottenPressurePlateList.get(0)), has(gottenPressurePlateList.get(0)))
						.save(output);
				}
				//endregion

				//region Generate Most Stair Recipes
				ArrayList<ArrayList<Item>> stairGenerateList = new ArrayList<>();
				//Argument one is the INPUT item, Argument 2 is the OUTPUT item
				//Argument 3 is the list to add it to! This is important depending on the kind of recipe to be generated!
				createTwoItemList(Items.OAK_PLANKS, Items.OAK_STAIRS, stairGenerateList);
				createTwoItemList(Items.SPRUCE_PLANKS, Items.SPRUCE_STAIRS, stairGenerateList);
				createTwoItemList(Items.BIRCH_PLANKS, Items.BIRCH_STAIRS, stairGenerateList);
				createTwoItemList(Items.JUNGLE_PLANKS, Items.JUNGLE_STAIRS, stairGenerateList);
				createTwoItemList(Items.ACACIA_PLANKS, Items.ACACIA_STAIRS, stairGenerateList);
				createTwoItemList(Items.DARK_OAK_PLANKS, Items.DARK_OAK_STAIRS, stairGenerateList);
				createTwoItemList(Items.MANGROVE_PLANKS, Items.MANGROVE_STAIRS, stairGenerateList);
				createTwoItemList(Items.CHERRY_PLANKS, Items.CHERRY_STAIRS, stairGenerateList);
				createTwoItemList(Items.PALE_OAK_PLANKS, Items.PALE_OAK_STAIRS, stairGenerateList);
				createTwoItemList(Items.POPLAR_PLANKS, Items.POPLAR_STAIRS, stairGenerateList);
				createTwoItemList(Items.BAMBOO_PLANKS, Items.BAMBOO_STAIRS, stairGenerateList);
				createTwoItemList(Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS, stairGenerateList);
				createTwoItemList(Items.WARPED_PLANKS, Items.WARPED_STAIRS, stairGenerateList);
				createTwoItemList(Items.BAMBOO_MOSAIC, Items.BAMBOO_MOSAIC_STAIRS, stairGenerateList);
				createTwoItemList(Items.STONE, Items.STONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.COBBLESTONE, Items.COBBLESTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.MOSSY_COBBLESTONE, Items.MOSSY_COBBLESTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.STONE_BRICKS, Items.STONE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.MOSSY_STONE_BRICKS, Items.MOSSY_STONE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.GRANITE, Items.GRANITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_GRANITE, Items.POLISHED_GRANITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.DIORITE, Items.DIORITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_DIORITE, Items.POLISHED_DIORITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.ANDESITE, Items.ANDESITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_ANDESITE, Items.POLISHED_ANDESITE_STAIRS, stairGenerateList);
				createTwoItemList(Items.COBBLED_DEEPSLATE, Items.COBBLED_DEEPSLATE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_DEEPSLATE, Items.POLISHED_DEEPSLATE_STAIRS, stairGenerateList);
				createTwoItemList(Items.DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.DEEPSLATE_TILES, Items.DEEPSLATE_TILE_STAIRS, stairGenerateList);
				createTwoItemList(Items.TUFF, Items.TUFF_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_TUFF, Items.POLISHED_TUFF_STAIRS, stairGenerateList);
				createTwoItemList(Items.TUFF_BRICKS, Items.TUFF_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.BRICKS, Items.BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.MUD_BRICKS, Items.MUD_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.RESIN_BRICKS, Items.RESIN_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.SANDSTONE, Items.SANDSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.SMOOTH_SANDSTONE, Items.SMOOTH_SANDSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.RED_SANDSTONE, Items.RED_SANDSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.SMOOTH_RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.CINNABAR, Items.CINNABAR_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_CINNABAR, Items.POLISHED_CINNABAR_STAIRS, stairGenerateList);
				createTwoItemList(Items.CINNABAR_BRICKS, Items.CINNABAR_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.SULFUR, Items.SULFUR_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_SULFUR, Items.POLISHED_SULFUR_STAIRS, stairGenerateList);
				createTwoItemList(Items.SULFUR_BRICKS, Items.SULFUR_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.PRISMARINE, Items.PRISMARINE_STAIRS, stairGenerateList);
				createTwoItemList(Items.PRISMARINE_BRICKS, Items.PRISMARINE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.DARK_PRISMARINE, Items.DARK_PRISMARINE_STAIRS, stairGenerateList);
				createTwoItemList(Items.NETHER_BRICKS, Items.NETHER_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.RED_NETHER_BRICKS, Items.RED_NETHER_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.BLACKSTONE, Items.BLACKSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_STAIRS, stairGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.END_STONE_BRICKS, Items.END_STONE_BRICK_STAIRS, stairGenerateList);
				createTwoItemList(Items.PURPUR_BLOCK, Items.PURPUR_STAIRS, stairGenerateList);
				createTwoItemList(Items.QUARTZ_BLOCK, Items.QUARTZ_STAIRS, stairGenerateList);
				createTwoItemList(Items.SMOOTH_QUARTZ, Items.SMOOTH_QUARTZ_STAIRS, stairGenerateList);


				//Loops through the list initialized above and mass registers the recipes
				for (ArrayList<Item> gottenStairList : stairGenerateList) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT

					shaped(RecipeCategory.BUILDING_BLOCKS, gottenStairList.get(1), 4)
						.pattern("B ")
						.pattern("BB")
						.define('B', gottenStairList.get(0))
						.unlockedBy(getHasName(gottenStairList.get(0)), has(gottenStairList.get(0)))
						.save(output);

					shapeless(RecipeCategory.BUILDING_BLOCKS, gottenStairList.get(0), 3)//Reverse recipe.
						.requires(gottenStairList.get(1), 4)
						.unlockedBy(getHasName(gottenStairList.get(1)), has(gottenStairList.get(1)))
						.save(output, gottenStairList.get(0).toString() + "_from_stairs");

				}
				//endregion

				//region Generate Most Slab Recipes
				ArrayList<ArrayList<Item>> slabGenerateList = new ArrayList<>();
				//Argument one is the INPUT item, Argument 2 is the OUTPUT item
				//Argument 3 is the list to add it to! This is important depending on the kind of recipe to be generated!
				createTwoItemList(Items.OAK_PLANKS, Items.OAK_SLAB, slabGenerateList);
				createTwoItemList(Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB, slabGenerateList);
				createTwoItemList(Items.BIRCH_PLANKS, Items.BIRCH_SLAB, slabGenerateList);
				createTwoItemList(Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB, slabGenerateList);
				createTwoItemList(Items.ACACIA_PLANKS, Items.ACACIA_SLAB, slabGenerateList);
				createTwoItemList(Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB, slabGenerateList);
				createTwoItemList(Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB, slabGenerateList);
				createTwoItemList(Items.CHERRY_PLANKS, Items.CHERRY_SLAB, slabGenerateList);
				createTwoItemList(Items.PALE_OAK_PLANKS, Items.PALE_OAK_SLAB, slabGenerateList);
				createTwoItemList(Items.POPLAR_PLANKS, Items.POPLAR_SLAB, slabGenerateList);
				createTwoItemList(Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB, slabGenerateList);
				createTwoItemList(Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB, slabGenerateList);
				createTwoItemList(Items.WARPED_PLANKS, Items.WARPED_SLAB, slabGenerateList);
				createTwoItemList(Items.BAMBOO_MOSAIC, Items.BAMBOO_MOSAIC_SLAB, slabGenerateList);
				createTwoItemList(Items.STONE, Items.STONE_SLAB, slabGenerateList);
				createTwoItemList(Items.COBBLESTONE, Items.COBBLESTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.MOSSY_COBBLESTONE, Items.MOSSY_COBBLESTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.STONE_BRICKS, Items.STONE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.MOSSY_STONE_BRICKS, Items.MOSSY_STONE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.GRANITE, Items.GRANITE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_GRANITE, Items.POLISHED_GRANITE_SLAB, slabGenerateList);
				createTwoItemList(Items.DIORITE, Items.DIORITE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_DIORITE, Items.POLISHED_DIORITE_SLAB, slabGenerateList);
				createTwoItemList(Items.ANDESITE, Items.ANDESITE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_ANDESITE, Items.POLISHED_ANDESITE_SLAB, slabGenerateList);
				createTwoItemList(Items.COBBLED_DEEPSLATE, Items.COBBLED_DEEPSLATE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_DEEPSLATE, Items.POLISHED_DEEPSLATE_SLAB, slabGenerateList);
				createTwoItemList(Items.DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.DEEPSLATE_TILES, Items.DEEPSLATE_TILE_SLAB, slabGenerateList);
				createTwoItemList(Items.TUFF, Items.TUFF_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_TUFF, Items.POLISHED_TUFF_SLAB, slabGenerateList);
				createTwoItemList(Items.TUFF_BRICKS, Items.TUFF_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.BRICKS, Items.BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.MUD_BRICKS, Items.MUD_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.RESIN_BRICKS, Items.RESIN_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.SANDSTONE, Items.SANDSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.SMOOTH_SANDSTONE, Items.SMOOTH_SANDSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.RED_SANDSTONE, Items.RED_SANDSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.SMOOTH_RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.CINNABAR, Items.CINNABAR_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_CINNABAR, Items.POLISHED_CINNABAR_SLAB, slabGenerateList);
				createTwoItemList(Items.CINNABAR_BRICKS, Items.CINNABAR_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.SULFUR, Items.SULFUR_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_SULFUR, Items.POLISHED_SULFUR_SLAB, slabGenerateList);
				createTwoItemList(Items.SULFUR_BRICKS, Items.SULFUR_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.PRISMARINE, Items.PRISMARINE_SLAB, slabGenerateList);
				createTwoItemList(Items.PRISMARINE_BRICKS, Items.PRISMARINE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.DARK_PRISMARINE, Items.DARK_PRISMARINE_SLAB, slabGenerateList);
				createTwoItemList(Items.NETHER_BRICKS, Items.NETHER_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.RED_NETHER_BRICKS, Items.RED_NETHER_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.BLACKSTONE, Items.BLACKSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_SLAB, slabGenerateList);
				createTwoItemList(Items.POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.END_STONE_BRICKS, Items.END_STONE_BRICK_SLAB, slabGenerateList);
				createTwoItemList(Items.PURPUR_BLOCK, Items.PURPUR_SLAB, slabGenerateList);
				createTwoItemList(Items.QUARTZ_BLOCK, Items.QUARTZ_SLAB, slabGenerateList);
				createTwoItemList(Items.SMOOTH_QUARTZ, Items.SMOOTH_QUARTZ_SLAB, slabGenerateList);


				//Loops through the list initialized above and mass registers the recipes
				for (ArrayList<Item> gottenSlabList : slabGenerateList) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT

					shapeless(RecipeCategory.BUILDING_BLOCKS, gottenSlabList.get(1), 2)
						.requires(gottenSlabList.get(0))
						.unlockedBy(getHasName(gottenSlabList.get(0)), has(gottenSlabList.get(0)))
						.save(output);

					shapeless(RecipeCategory.BUILDING_BLOCKS, gottenSlabList.get(0))//Reverse recipe.
						.requires(gottenSlabList.get(1), 2)
						.unlockedBy(getHasName(gottenSlabList.get(1)), has(gottenSlabList.get(1)))
						.save(output, gottenSlabList.get(0).toString() + "_from_slabs");

				}
				//endregion

				//region Generate Copper Recipes
				List<Block> cutCopperList =  Blocks.CUT_COPPER.asList();
				List<Item> cutCopperSlabList =  Items.CUT_COPPER_SLAB.asList();
				List<Item> cutCopperStairList =  Items.CUT_COPPER_STAIRS.asList();
				List<Item> copperBlockList =  Items.COPPER_BLOCK.asList();
				for (int i = 0; i < copperBlockList.size(); ++i) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shaped(RecipeCategory.BUILDING_BLOCKS, cutCopperList.get(i).asItem(), 16)
						.pattern("CC")
						.pattern("CC")
						.define('C', copperBlockList.get(i))
						.unlockedBy(getHasName(copperBlockList.get(i)), has(copperBlockList.get(i)))
						.save(output);

					shapeless(RecipeCategory.BUILDING_BLOCKS, cutCopperSlabList.get(i), 2)
						.requires(cutCopperList.get(i).asItem())
						.unlockedBy(getHasName(cutCopperList.get(i).asItem()), has(cutCopperList.get(i).asItem()))
						.save(output);


					shapeless(RecipeCategory.BUILDING_BLOCKS, cutCopperList.get(i).asItem())
						.requires(cutCopperSlabList.get(i), 2)
						.unlockedBy(getHasName(cutCopperSlabList.get(i)), has(cutCopperSlabList.get(i)))
						.save(output,getWeatheringName(cutCopperList.get(i)) + "_" + "cut_copper_from_slabs");

					shapeless(RecipeCategory.BUILDING_BLOCKS, cutCopperList.get(i).asItem(), 3)//Return to block
						.requires(cutCopperStairList.get(i), 4)
						.unlockedBy(getHasName(cutCopperStairList.get(i)), has(cutCopperStairList.get(i)))
						.save(output,getWeatheringName(cutCopperList.get(i)) + "_" + "cut_copper_from_stairs");



					shaped(RecipeCategory.BUILDING_BLOCKS, cutCopperStairList.get(i), 4)
						.pattern("C ")
						.pattern("CC")
						.define('C', cutCopperList.get(i).asItem())
						.unlockedBy(getHasName(cutCopperList.get(i).asItem()), has(cutCopperList.get(i).asItem()))
						.save(output);
				}
				//endregion

				//region Generate Wool Recipes
				List<Item> woolSlabList =  Items.WOOL_SLAB.asList();
				List<Item> woolStairList =  Items.WOOL_STAIRS.asList();
				List<Item> woolBlockList =  Items.WOOL.asList();
				for (int i = 0; i < woolBlockList.size(); ++i) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shapeless(RecipeCategory.BUILDING_BLOCKS, woolSlabList.get(i), 2)
						.requires(woolBlockList.get(i))
						.unlockedBy(getHasName(woolBlockList.get(i)), has(woolBlockList.get(i)))
						.save(output);

					shapeless(RecipeCategory.BUILDING_BLOCKS, woolBlockList.get(i))
						.requires(woolSlabList.get(i), 2)
						.unlockedBy(getHasName(woolSlabList.get(i)), has(woolSlabList.get(i)))
						.save(output, woolBlockList.get(i) + "_from_slabs");

					shapeless(RecipeCategory.BUILDING_BLOCKS, woolBlockList.get(i), 3)
						.requires(woolStairList.get(i), 4)
						.unlockedBy(getHasName(woolStairList.get(i)), has(woolStairList.get(i)))
						.save(output, woolBlockList.get(i) + "_from_stairs");

					shaped(RecipeCategory.BUILDING_BLOCKS, woolStairList.get(i), 4)
						.pattern("W ")
						.pattern("WW")
						.define('W', woolBlockList.get(i))
						.unlockedBy(getHasName(woolBlockList.get(i)), has(woolBlockList.get(i)))
						.save(output);
				}
				//endregion

				//region Generate Concrete Recipes
				List<Item> concreteSlabList =  Items.CONCRETE_SLAB.asList();
				List<Item> concreteStairList =  Items.CONCRETE_STAIRS.asList();
				List<Item> concreteBlockList =  Items.CONCRETE.asList();
				for (int i = 0; i < concreteBlockList.size(); ++i) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shapeless(RecipeCategory.BUILDING_BLOCKS, concreteSlabList.get(i), 2)
						.requires(concreteBlockList.get(i))
						.unlockedBy(getHasName(concreteBlockList.get(i)), has(concreteBlockList.get(i)))
						.save(output);

					shapeless(RecipeCategory.BUILDING_BLOCKS, concreteBlockList.get(i))
						.requires(concreteSlabList.get(i), 2)
						.unlockedBy(getHasName(concreteSlabList.get(i)), has(concreteSlabList.get(i)))
						.save(output, concreteBlockList.get(i) + "_from_slabs");

					shapeless(RecipeCategory.BUILDING_BLOCKS, concreteBlockList.get(i), 3)
						.requires(concreteStairList.get(i), 4)
						.unlockedBy(getHasName(concreteStairList.get(i)), has(concreteStairList.get(i)))
						.save(output, concreteBlockList.get(i) + "_from_stairs");

					shaped(RecipeCategory.BUILDING_BLOCKS, concreteStairList.get(i), 4)
						.pattern("C ")
						.pattern("CC")
						.define('C', concreteBlockList.get(i))
						.unlockedBy(getHasName(concreteBlockList.get(i)), has(concreteBlockList.get(i)))
						.save(output);
				}
				//endregion
			}
		};
	}
	private static String getWeatheringName(Block copper) {
		return switch (copper) {
			case WeatheringCopper weathering -> weathering.getAge().getSerializedName();//If not waxed, just return the age SerializedName.
			case Block block when HoneycombItem.WAX_OFF_BY_BLOCK.get().get(block) instanceof WeatheringCopper weathering -> "waxed_" + weathering.getAge().getSerializedName();//If waxed, add "waxed_" before the SerializedName.
			default -> throw new IllegalArgumentException("not a copper block: " + copper);//If the block passed into the method is not copper, throw an error.
		};
	}

	public void createTwoItemList(Item input, Item output, ArrayList<ArrayList<Item>> list) {
		ArrayList<Item> twoitemlist = new ArrayList<>();
		//Adds an input to the list
		twoitemlist.add(input);
		//Adds an output to the list
		twoitemlist.add(output);
		//Adds the new list to the given list (nested)
		list.add(twoitemlist);
	}

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return Identifier.fromNamespaceAndPath("minecraft", identifier.getPath());
	}

	@Override
	public @NonNull String getName() {
		return "minecraft";
	}
}
