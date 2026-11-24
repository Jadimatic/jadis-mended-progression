package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import io.github.jadimatic.jadis_mended_progression.item.JadisMendedItems;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JadisMendedRecipeProvider extends FabricRecipeProvider {
    public JadisMendedRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);


				List<ItemLike> TIN_SMELTABLES = List.of(JadisMendedItems.RAW_TIN, JadisMendedBlocks.TIN_ORE);
				oreSmelting(TIN_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, JadisMendedItems.TIN_INGOT, 0f, 200, "tin");



                shapeless(RecipeCategory.MISC, Items.FLINT) // You can also specify an int to produce more than one
                        .requires(Items.FLINT) // You can also specify an int to require more than one, or a tag to accept multiple things
                        // Create an advancement that gives you the recipe
                        .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                        .save(output, "flint_from_flint");//This is just a silly test recipe.

                shaped(RecipeCategory.MISC, Items.FLINT)
                        .pattern("CC")
                        .pattern("CC")
                        .define('C', Items.COBBLESTONE)
                        .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                        .save(output, "flint_from_cobblestone");//This is to test alternate recipes for the same item in the recipe book.

				shapeless(RecipeCategory.MISC, Items.STICK, 7)
					.requires(Items.LADDER)
					.unlockedBy(getHasName(Items.LADDER), has(Items.LADDER))
					.save(output, "sticks_from_ladder");

				shaped(RecipeCategory.MISC, JadisMendedBlocks.CHARCOAL_BLOCK)
					.pattern("CCC")
					.pattern("CCC")
					.pattern("CCC")
					.define('C', Items.CHARCOAL)
					.unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
					.save(output);

				shaped(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CUT_IRON, 16)
					.pattern("II")
					.pattern("II")
					.define('I', Items.IRON_BLOCK)
					.unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
					.save(output);

				shaped(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CHISELED_IRON, 2)
					.pattern("I")
					.pattern("I")
					.define('I', JadisMendedBlocks.CUT_IRON)
					.unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
					.save(output);

				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CUT_IRON,Items.IRON_BLOCK, 4);
				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CHISELED_IRON,Items.IRON_BLOCK, 4);
				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CHISELED_IRON, JadisMendedBlocks.CUT_IRON, 1);
				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, JadisMendedBlocks.CUT_IRON, JadisMendedBlocks.CHISELED_IRON, 1);

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.AMETHYST_CLUSTER)
					.pattern("CC")
					.define('C', Items.AMETHYST_SHARD)
					.unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
					.save(output);

				shapeless(RecipeCategory.MISC, Items.AMETHYST_SHARD, 2)
					.requires(Items.AMETHYST_CLUSTER)
					.unlockedBy(getHasName(Items.AMETHYST_CLUSTER), has(Items.AMETHYST_CLUSTER))
					.save(output, "amethyst_shards_from_amethyst_cluster");

				shapeless(RecipeCategory.MISC, Items.AMETHYST_CLUSTER, 2)
					.requires(Items.AMETHYST_BLOCK)
					.unlockedBy(getHasName(Items.AMETHYST_BLOCK), has(Items.AMETHYST_BLOCK))
					.save(output, "amethyst_cluster_from_amethyst_block");

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.END_PORTAL_FRAME)
					.pattern("P P")
					.pattern("EOE")
					.pattern("EEE")
					.define('P', Items.PRISMARINE)//Only change if we can find out what the weird blue stuff seen in the End in Dungeons 1 is and if it makes sense.
					.define('O', Items.OBSIDIAN)
					.define('E', Items.END_STONE)
					.unlockedBy(getHasName(Items.END_STONE), has(Items.END_STONE))
					.save(output);

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SOUL_SOIL)
					.pattern("SS")
					.pattern("SS")
					.define('S', Items.SOUL_SAND)
					.unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
					.save(output);

				buildPickaxeRecipe(Items.STICK, JadisMendedTags.Items.BRONZE_INGOTS, JadisMendedItems.BRONZE_PICKAXE).save(output);
			}

			///Generates a generic pickaxe recipe.
			private ShapedRecipeBuilder buildPickaxeRecipe(Item handleMaterial, TagKey<Item> headMaterial, Item pickaxe){
				return shaped(RecipeCategory.TOOLS, pickaxe)
					.pattern("MMM")
					.pattern(" H ")
					.pattern(" H ")
					.define('M', headMaterial)
					.define('H', handleMaterial)
					.unlockedBy("has_needed_materials", has(headMaterial));// "has_needed_materials" exists purely so the advancement doesn't break.

			}
        };
    }




    @Override
    public @NonNull String getName() {
        return "JadisMendedProgressionRecipeProvider";
    }
}
