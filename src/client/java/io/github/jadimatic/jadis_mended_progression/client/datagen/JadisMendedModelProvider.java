package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import io.github.jadimatic.jadis_mended_progression.item.JadisMendedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class JadisMendedModelProvider extends FabricModelProvider {
    public JadisMendedModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {
		blockModelGenerators.createTrivialCube(JadisMendedBlocks.CHARCOAL_BLOCK);
		blockModelGenerators.createTrivialCube(JadisMendedBlocks.CUT_IRON);
		blockModelGenerators.createTrivialCube(JadisMendedBlocks.CHISELED_IRON);
		blockModelGenerators.createTrivialCube(JadisMendedBlocks.TIN_ORE);
		blockModelGenerators.createTrivialCube(JadisMendedBlocks.DEEPSLATE_TIN_ORE);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(JadisMendedItems.EXAMPLE_ITEM, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.RAW_TIN, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.TIN_INGOT, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_INGOT, ModelTemplates.FLAT_ITEM);

		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerators.generateFlatItem(JadisMendedItems.BRONZE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
		itemModelGenerators.generateSpear(JadisMendedItems.BRONZE_SPEAR);
    }
}
