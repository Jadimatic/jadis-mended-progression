package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import io.github.jadimatic.jadis_mended_progression.item.ModItems;
import io.github.jadimatic.jadis_mended_progression.references.ModBlockItemIds;
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

				shaped(RecipeCategory.MISC, ModBlocks.CHARCOAL_BLOCK)
					.pattern("CCC")
					.pattern("CCC")
					.pattern("CCC")
					.define('C', Items.CHARCOAL)
					.unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
					.save(output, "charcoal_block");



				shaped(RecipeCategory.BUILDING_BLOCKS, Items.OAK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.OAK_PLANKS)
					.unlockedBy(getHasName(Items.OAK_PLANKS), has(Items.OAK_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				//The following entries are recipes for every other stair block in the game.
				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SPRUCE_PLANKS)
					.unlockedBy(getHasName(Items.SPRUCE_PLANKS), has(Items.SPRUCE_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.BIRCH_PLANKS)
					.unlockedBy(getHasName(Items.BIRCH_PLANKS), has(Items.BIRCH_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.JUNGLE_PLANKS)
					.unlockedBy(getHasName(Items.JUNGLE_PLANKS), has(Items.JUNGLE_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.ACACIA_PLANKS)
					.unlockedBy(getHasName(Items.ACACIA_PLANKS), has(Items.ACACIA_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.DARK_OAK_PLANKS)
					.unlockedBy(getHasName(Items.DARK_OAK_PLANKS), has(Items.DARK_OAK_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.MANGROVE_PLANKS)
					.unlockedBy(getHasName(Items.MANGROVE_PLANKS), has(Items.MANGROVE_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CHERRY_PLANKS)
					.unlockedBy(getHasName(Items.CHERRY_PLANKS), has(Items.CHERRY_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.PALE_OAK_PLANKS)
					.unlockedBy(getHasName(Items.PALE_OAK_PLANKS), has(Items.PALE_OAK_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POPLAR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POPLAR_PLANKS)
					.unlockedBy(getHasName(Items.POPLAR_PLANKS), has(Items.POPLAR_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.BAMBOO_PLANKS)
					.unlockedBy(getHasName(Items.BAMBOO_PLANKS), has(Items.BAMBOO_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CRIMSON_PLANKS)
					.unlockedBy(getHasName(Items.CRIMSON_PLANKS), has(Items.CRIMSON_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WARPED_PLANKS)
					.unlockedBy(getHasName(Items.WARPED_PLANKS), has(Items.WARPED_PLANKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_MOSAIC_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.BAMBOO_MOSAIC)
					.unlockedBy(getHasName(Items.BAMBOO_MOSAIC), has(Items.BAMBOO_MOSAIC))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.STONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.STONE)
					.unlockedBy(getHasName(Items.STONE), has(Items.STONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.COBBLESTONE)
					.unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.MOSSY_COBBLESTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.MOSSY_COBBLESTONE)
					.unlockedBy(getHasName(Items.MOSSY_COBBLESTONE), has(Items.MOSSY_COBBLESTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.STONE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.STONE_BRICKS)
					.unlockedBy(getHasName(Items.STONE_BRICKS), has(Items.STONE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.MOSSY_STONE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.MOSSY_STONE_BRICKS)
					.unlockedBy(getHasName(Items.MOSSY_STONE_BRICKS), has(Items.MOSSY_STONE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.GRANITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.GRANITE)
					.unlockedBy(getHasName(Items.GRANITE), has(Items.GRANITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_GRANITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_GRANITE)
					.unlockedBy(getHasName(Items.POLISHED_GRANITE), has(Items.POLISHED_GRANITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.DIORITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.DIORITE)
					.unlockedBy(getHasName(Items.DIORITE), has(Items.DIORITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_DIORITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_DIORITE)
					.unlockedBy(getHasName(Items.POLISHED_DIORITE), has(Items.POLISHED_DIORITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.ANDESITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.ANDESITE)
					.unlockedBy(getHasName(Items.ANDESITE), has(Items.ANDESITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_ANDESITE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_ANDESITE)
					.unlockedBy(getHasName(Items.POLISHED_ANDESITE), has(Items.POLISHED_ANDESITE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.COBBLED_DEEPSLATE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.COBBLED_DEEPSLATE)
					.unlockedBy(getHasName(Items.COBBLED_DEEPSLATE), has(Items.COBBLED_DEEPSLATE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_DEEPSLATE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_DEEPSLATE)
					.unlockedBy(getHasName(Items.POLISHED_DEEPSLATE), has(Items.POLISHED_DEEPSLATE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.DEEPSLATE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.DEEPSLATE_BRICKS)
					.unlockedBy(getHasName(Items.DEEPSLATE_BRICKS), has(Items.DEEPSLATE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.DEEPSLATE_TILE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.DEEPSLATE_TILES)
					.unlockedBy(getHasName(Items.DEEPSLATE_TILES), has(Items.DEEPSLATE_TILES))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.TUFF_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.TUFF)
					.unlockedBy(getHasName(Items.TUFF), has(Items.TUFF))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_TUFF_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_TUFF)
					.unlockedBy(getHasName(Items.POLISHED_TUFF), has(Items.POLISHED_TUFF))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.TUFF_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.TUFF_BRICKS)
					.unlockedBy(getHasName(Items.TUFF_BRICKS), has(Items.TUFF_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.BRICKS)
					.unlockedBy(getHasName(Items.BRICKS), has(Items.BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.MUD_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.MUD_BRICKS)
					.unlockedBy(getHasName(Items.MUD_BRICKS), has(Items.MUD_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.RESIN_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.RESIN_BRICKS)
					.unlockedBy(getHasName(Items.RESIN_BRICKS), has(Items.RESIN_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SANDSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SANDSTONE)
					.unlockedBy(getHasName(Items.SANDSTONE), has(Items.SANDSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SMOOTH_SANDSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SMOOTH_SANDSTONE)
					.unlockedBy(getHasName(Items.SMOOTH_SANDSTONE), has(Items.SMOOTH_SANDSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.RED_SANDSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.RED_SANDSTONE)
					.unlockedBy(getHasName(Items.RED_SANDSTONE), has(Items.RED_SANDSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SMOOTH_RED_SANDSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SMOOTH_RED_SANDSTONE)
					.unlockedBy(getHasName(Items.SMOOTH_RED_SANDSTONE), has(Items.SMOOTH_RED_SANDSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CINNABAR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CINNABAR)
					.unlockedBy(getHasName(Items.CINNABAR), has(Items.CINNABAR))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_CINNABAR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_CINNABAR)
					.unlockedBy(getHasName(Items.POLISHED_CINNABAR), has(Items.POLISHED_CINNABAR))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CINNABAR_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CINNABAR_BRICKS)
					.unlockedBy(getHasName(Items.CINNABAR_BRICKS), has(Items.CINNABAR_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SULFUR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SULFUR)
					.unlockedBy(getHasName(Items.SULFUR), has(Items.SULFUR))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_SULFUR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_SULFUR)
					.unlockedBy(getHasName(Items.POLISHED_SULFUR), has(Items.POLISHED_SULFUR))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SULFUR_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SULFUR_BRICKS)
					.unlockedBy(getHasName(Items.SULFUR_BRICKS), has(Items.SULFUR_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.PRISMARINE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.PRISMARINE)
					.unlockedBy(getHasName(Items.PRISMARINE), has(Items.PRISMARINE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.PRISMARINE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.PRISMARINE_BRICKS)
					.unlockedBy(getHasName(Items.PRISMARINE_BRICKS), has(Items.PRISMARINE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.DARK_PRISMARINE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.DARK_PRISMARINE)
					.unlockedBy(getHasName(Items.DARK_PRISMARINE), has(Items.DARK_PRISMARINE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.NETHER_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.NETHER_BRICKS)
					.unlockedBy(getHasName(Items.NETHER_BRICKS), has(Items.NETHER_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.RED_NETHER_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.RED_NETHER_BRICKS)
					.unlockedBy(getHasName(Items.RED_NETHER_BRICKS), has(Items.RED_NETHER_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.BLACKSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.BLACKSTONE)
					.unlockedBy(getHasName(Items.BLACKSTONE), has(Items.BLACKSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_BLACKSTONE_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_BLACKSTONE)
					.unlockedBy(getHasName(Items.POLISHED_BLACKSTONE), has(Items.POLISHED_BLACKSTONE))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.POLISHED_BLACKSTONE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.POLISHED_BLACKSTONE_BRICKS)
					.unlockedBy(getHasName(Items.POLISHED_BLACKSTONE_BRICKS), has(Items.POLISHED_BLACKSTONE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.END_STONE_BRICK_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.END_STONE_BRICKS)
					.unlockedBy(getHasName(Items.END_STONE_BRICKS), has(Items.END_STONE_BRICKS))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.PURPUR_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.PURPUR_BLOCK)
					.unlockedBy(getHasName(Items.PURPUR_BLOCK), has(Items.PURPUR_BLOCK))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.QUARTZ_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.QUARTZ_BLOCK)
					.unlockedBy(getHasName(Items.QUARTZ_BLOCK), has(Items.QUARTZ_BLOCK))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.SMOOTH_QUARTZ_STAIRS, 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.SMOOTH_QUARTZ)
					.unlockedBy(getHasName(Items.SMOOTH_QUARTZ), has(Items.SMOOTH_QUARTZ))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CUT_COPPER_STAIRS.weathering().unaffected(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CUT_COPPER.weathering().unaffected())
					.unlockedBy(getHasName(Items.CUT_COPPER.weathering().unaffected()), has(Items.CUT_COPPER.weathering().unaffected()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CUT_COPPER_STAIRS.weathering().exposed(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CUT_COPPER.weathering().exposed())
					.unlockedBy(getHasName(Items.CUT_COPPER.weathering().exposed()), has(Items.CUT_COPPER.weathering().exposed()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CUT_COPPER_STAIRS.weathering().weathered(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CUT_COPPER.weathering().weathered())
					.unlockedBy(getHasName(Items.CUT_COPPER.weathering().weathered()), has(Items.CUT_COPPER.weathering().weathered()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CUT_COPPER_STAIRS.weathering().oxidized(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CUT_COPPER.weathering().oxidized())
					.unlockedBy(getHasName(Items.CUT_COPPER.weathering().oxidized()), has(Items.CUT_COPPER.weathering().oxidized()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.white(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.white())
					.unlockedBy(getHasName(Items.WOOL.white()), has(Items.WOOL.white()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.lightGray(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.lightGray())
					.unlockedBy(getHasName(Items.WOOL.lightGray()), has(Items.WOOL.lightGray()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.gray(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.gray())
					.unlockedBy(getHasName(Items.WOOL.gray()), has(Items.WOOL.gray()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.brown(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.brown())
					.unlockedBy(getHasName(Items.WOOL.brown()), has(Items.WOOL.brown()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.red(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.red())
					.unlockedBy(getHasName(Items.WOOL.red()), has(Items.WOOL.red()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.orange(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.orange())
					.unlockedBy(getHasName(Items.WOOL.orange()), has(Items.WOOL.orange()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.yellow(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.yellow())
					.unlockedBy(getHasName(Items.WOOL.yellow()), has(Items.WOOL.yellow()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.lime(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.lime())
					.unlockedBy(getHasName(Items.WOOL.lime()), has(Items.WOOL.lime()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.green(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.green())
					.unlockedBy(getHasName(Items.WOOL.green()), has(Items.WOOL.green()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.cyan(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.cyan())
					.unlockedBy(getHasName(Items.WOOL.cyan()), has(Items.WOOL.cyan()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.lightBlue(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.lightBlue())
					.unlockedBy(getHasName(Items.WOOL.lightBlue()), has(Items.WOOL.lightBlue()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.blue(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.blue())
					.unlockedBy(getHasName(Items.WOOL.blue()), has(Items.WOOL.blue()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.purple(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.purple())
					.unlockedBy(getHasName(Items.WOOL.purple()), has(Items.WOOL.purple()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.magenta(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.magenta())
					.unlockedBy(getHasName(Items.WOOL.magenta()), has(Items.WOOL.magenta()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.WOOL_STAIRS.pink(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.WOOL.pink())
					.unlockedBy(getHasName(Items.WOOL.pink()), has(Items.WOOL.pink()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.white(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.white())
					.unlockedBy(getHasName(Items.CONCRETE.white()), has(Items.CONCRETE.white()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.lightGray(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.lightGray())
					.unlockedBy(getHasName(Items.CONCRETE.lightGray()), has(Items.CONCRETE.lightGray()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.gray(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.gray())
					.unlockedBy(getHasName(Items.CONCRETE.gray()), has(Items.CONCRETE.gray()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.black(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.black())
					.unlockedBy(getHasName(Items.CONCRETE.black()), has(Items.CONCRETE.black()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.brown(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.brown())
					.unlockedBy(getHasName(Items.CONCRETE.brown()), has(Items.CONCRETE.brown()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.red(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.red())
					.unlockedBy(getHasName(Items.CONCRETE.red()), has(Items.CONCRETE.red()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.orange(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.orange())
					.unlockedBy(getHasName(Items.CONCRETE.orange()), has(Items.CONCRETE.orange()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.yellow(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.yellow())
					.unlockedBy(getHasName(Items.CONCRETE.yellow()), has(Items.CONCRETE.yellow()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.lime(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.lime())
					.unlockedBy(getHasName(Items.CONCRETE.lime()), has(Items.CONCRETE.lime()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.green(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.green())
					.unlockedBy(getHasName(Items.CONCRETE.green()), has(Items.CONCRETE.green()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.cyan(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.cyan())
					.unlockedBy(getHasName(Items.CONCRETE.cyan()), has(Items.CONCRETE.cyan()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.lightBlue(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.lightBlue())
					.unlockedBy(getHasName(Items.CONCRETE.lightBlue()), has(Items.CONCRETE.lightBlue()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.blue(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.blue())
					.unlockedBy(getHasName(Items.CONCRETE.blue()), has(Items.CONCRETE.blue()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.purple(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.purple())
					.unlockedBy(getHasName(Items.CONCRETE.purple()), has(Items.CONCRETE.purple()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.magenta(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.magenta())
					.unlockedBy(getHasName(Items.CONCRETE.magenta()), has(Items.CONCRETE.magenta()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.

				shaped(RecipeCategory.BUILDING_BLOCKS, Items.CONCRETE_STAIRS.pink(), 4)
					.pattern("P  ")
					.pattern("PP ")
					.define('P', Items.CONCRETE.pink())
					.unlockedBy(getHasName(Items.CONCRETE.pink()), has(Items.CONCRETE.pink()))
					.save(output);//Vanilla recipe filtered out in pack.mcmeta.





			}
        };
    }

    @Override
    public @NonNull String getName() {
        return "JadisMendedProgressionRecipeProvider";
    }
}
