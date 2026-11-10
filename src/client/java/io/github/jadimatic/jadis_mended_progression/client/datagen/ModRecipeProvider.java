package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import io.github.jadimatic.jadis_mended_progression.item.JadisMendedItems;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

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

				shaped(RecipeCategory.MISC, JadisMendedBlocks.CHARCOAL_BLOCK)
					.pattern("CCC")
					.pattern("CCC")
					.pattern("CCC")
					.define('C', Items.CHARCOAL)
					.unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
					.save(output, "charcoal_block");

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
			}
        };
    }

    @Override
    public @NonNull String getName() {
        return "JadisMendedProgressionRecipeProvider";
    }
}
