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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
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




				//region Generate Stair Recipes
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
				}
				//endregion




				//region Generate Copper Recipes
				List<Item> cutList =  Items.CUT_COPPER.asList();
				List<Item> stairList =  Items.CUT_COPPER_STAIRS.asList();
				List<Item> blockList =  Items.COPPER_BLOCK.asList();
				for (int i = 0; i < blockList.size(); ++i) {
					//Loops through the list and obtains the values from the nested list to fill out the recipe
					//Index 0 is the INPUT
					//Index 1 is the OUTPUT
					shaped(RecipeCategory.BUILDING_BLOCKS, cutList.get(i), 16)
						.pattern("CC")
						.pattern("CC")
						.define('C', blockList.get(i))
						.unlockedBy(getHasName(blockList.get(i)), has(blockList.get(i)))
						.save(output);

					shaped(RecipeCategory.BUILDING_BLOCKS, stairList.get(i), 4)
						.pattern("C ")
						.pattern("CC")
						.define('C', cutList.get(i))
						.unlockedBy(getHasName(cutList.get(i)), has(cutList.get(i)))
						.save(output);
				}
				//endregion
			}
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
