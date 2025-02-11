package io.github.jadimatic.jadis_mended_progression;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;


public class JadisMendedProgressionItems {
	public static final Item JADIS_PLANT_FIBER = new Item(new QuiltItemSettings());

	//Block Fields
	public static final Block CHARCOAL_BLOCK = new Block(QuiltBlockSettings.create()
		.requiresTool()
		.hardness(1.0f));
	public static final Block EXAMPLE_BLOCK = new Block(QuiltBlockSettings.create()
		.hardness(0.5f));
	//End Of Fields

	public static void register(ModContainer mod) {

		//Block Registries
		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "charcoal_block"), CHARCOAL_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "charcoal_block"), new BlockItem(CHARCOAL_BLOCK, new QuiltItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_block"), new BlockItem(EXAMPLE_BLOCK, new QuiltItemSettings()));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.addItem(CHARCOAL_BLOCK.asItem());
			entries.addItem(EXAMPLE_BLOCK.asItem());
		});
		//End Of Registries

		//Item Registries
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "plant_fiber"), JADIS_PLANT_FIBER);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addItem(JADIS_PLANT_FIBER);
		});
		//End Of Registries
	}
}
