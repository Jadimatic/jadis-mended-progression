package io.github.jadimatic.jadis_mended_progression;

import io.github.jadimatic.jadis_mended_progression.item.CustomPickaxeItem;
import io.github.jadimatic.jadis_mended_progression.item.ModToolMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import net.minecraft.block.FallingBlock;


public class JadisMendedProgressionItems {





	public static final Item JADIS_PLANT_FIBER = new Item(new QuiltItemSettings());
	public static final Item JADIS_TIN_INGOT = new Item(new QuiltItemSettings());
	public static final Item JADIS_BRONZE_INGOT = new Item(new QuiltItemSettings());
	public static final Item JADIS_STEEL_INGOT = new Item(new QuiltItemSettings());
	public static final Item FLINT_PICKAXE = new CustomPickaxeItem(ModToolMaterials.FLINT, 1, 1, new QuiltItemSettings());
	public static final Item COPPER_PICKAXE = new CustomPickaxeItem(ModToolMaterials.COPPER, 1, 1, new QuiltItemSettings());
	public static final Item BRONZE_PICKAXE = new CustomPickaxeItem(ModToolMaterials.BRONZE, 1, 1, new QuiltItemSettings());
	public static final Item STEEL_PICKAXE = new CustomPickaxeItem(ModToolMaterials.STEEL, 1, 1, new QuiltItemSettings());

	//Block Fields
	public static final Block CHARCOAL_BLOCK = new Block(QuiltBlockSettings.create()
		.requiresTool()
		.hardness(5.0f)
		.resistance(6.0f));
	public static final FallingBlock STEEL_CHARGE_BLOCK = new FallingBlock(QuiltBlockSettings.create()
		.requiresTool()
		.hardness(1.0f)
		.resistance(1200.0f));

	//End Of Fields

	public static void register(ModContainer mod) {

		//Block Registries
		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "charcoal_block"), CHARCOAL_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "charcoal_block"), new BlockItem(CHARCOAL_BLOCK, new QuiltItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "steel_charge_block"), STEEL_CHARGE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "steel_charge_block"), new BlockItem(STEEL_CHARGE_BLOCK, new QuiltItemSettings().fireproof()));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.addItem(CHARCOAL_BLOCK.asItem());
			entries.addItem(STEEL_CHARGE_BLOCK.asItem());
		});
		//End Of Registries

		//Item Registries
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "plant_fiber"), JADIS_PLANT_FIBER);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "tin_ingot"), JADIS_TIN_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "bronze_ingot"), JADIS_BRONZE_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "steel_ingot"), JADIS_STEEL_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "flint_pickaxe"), FLINT_PICKAXE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "bronze_pickaxe"), BRONZE_PICKAXE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "steel_pickaxe"), STEEL_PICKAXE);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addItem(JADIS_PLANT_FIBER);
			entries.addItem(JADIS_BRONZE_INGOT);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(entries -> {
			entries.addItem(FLINT_PICKAXE); entries.addItem(COPPER_PICKAXE); entries.addItem(BRONZE_PICKAXE); entries.addItem(STEEL_PICKAXE);
		});
		//End Of Registries
	}
}
