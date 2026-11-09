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
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
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

				shaped(RecipeCategory.REDSTONE, Items.LEVER)
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

				for (ArrayList<Item> gottenButtonList : buttonGenerateList) {
					shapeless(RecipeCategory.REDSTONE, gottenButtonList.get(1), 8)
						.requires(gottenButtonList.get(0))
						.requires(Items.REDSTONE)
						.unlockedBy(getHasName(gottenButtonList.get(0)), has(gottenButtonList.get(0)))
						.save(output);
				}
				//endregion

				//region Generate Pressure Plate Recipes
				ArrayList<ArrayList<Item>> pressureplateGenerateList = new ArrayList<>();
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

				for (ArrayList<Item> gottenBarrelList : pressureplateGenerateList) {
					shaped(RecipeCategory.REDSTONE, gottenBarrelList.get(1), 8)
						.pattern("PPR")
						.define('P', gottenBarrelList.get(0))
						.define('R', Items.REDSTONE)
						.unlockedBy(getHasName(gottenBarrelList.get(0)), has(gottenBarrelList.get(0)))
						.save(output);
				}
				//endregion
			}
		};
	}

	public void createTwoItemList(Item plank, Item button, ArrayList<ArrayList<Item>> list) {
		ArrayList<Item> twoitemlist = new ArrayList<>();
		twoitemlist.add(plank);
		twoitemlist.add(button);
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
