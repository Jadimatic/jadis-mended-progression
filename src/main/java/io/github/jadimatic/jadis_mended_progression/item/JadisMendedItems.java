package io.github.jadimatic.jadis_mended_progression.item;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.block.JadisMendedBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class JadisMendedItems {


    public static final Item EXAMPLE_ITEM = registerItem("example_item", Item::new);
	public static final Item RAW_TIN = registerItem("raw_tin", Item::new);
	public static final Item TIN_INGOT = registerItem("tin_ingot", Item::new);
	public static final Item BRONZE_INGOT = registerItem("bronze_ingot", Item::new);
	public static final Item BRONZE_PICKAXE = registerItem("bronze_pickaxe",
		properties -> new Item(properties.pickaxe(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.4F)));
	public static final Item BRONZE_SHOVEL = registerItem("bronze_shovel",
		properties -> new Item(properties.shovel(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.4F)));
	public static final Item BRONZE_HOE = registerItem("bronze_hoe",
		properties -> new Item(properties.hoe(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.4F)));
	public static final Item BRONZE_AXE = registerItem("bronze_axe",
		properties -> new Item(properties.axe(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.4F)));
	public static final Item BRONZE_SWORD = registerItem("bronze_sword",
		properties -> new Item(properties.sword(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.4F)));
	public static final Item BRONZE_SPEAR = registerItem("bronze_spear",
		properties -> new Item(properties.spear(JadisMendedToolMaterials.BRONZE_TOOL_MATERIAL, 0.95F, 0.95F,
			0.6F, 2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F)));


	public static ResourceKey<Item> getResourceKey(Item item){return BuiltInRegistries.ITEM.getResourceKey(item).get();}

    /** Registers items under this mod's namespace with an assigned name and properties. */
    private  static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name)))));
    }


    public static void registerMendedItems() {
        JadisMendedProgression.LOGGER.info("Registering Mod Items for" + JadisMendedProgression.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {

            output.accept(EXAMPLE_ITEM);
			output.insertAfter(Items.RAW_COPPER, RAW_TIN);
			output.insertAfter(Items.COPPER_INGOT, TIN_INGOT);
			output.insertAfter(TIN_INGOT, BRONZE_INGOT);
        });

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {

			output.insertAfter(Items.COPPER_SWORD, BRONZE_SWORD);
			output.insertAfter(Items.COPPER_SPEAR, BRONZE_SPEAR);
			output.insertAfter(Items.COPPER_AXE, BRONZE_AXE);
		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {

			output.insertAfter(Items.COPPER_HOE, BRONZE_SHOVEL);
			output.insertAfter(JadisMendedItems.BRONZE_SHOVEL, BRONZE_PICKAXE);
			output.insertAfter(JadisMendedItems.BRONZE_PICKAXE, BRONZE_AXE);
			output.insertAfter(JadisMendedItems.BRONZE_AXE, BRONZE_HOE);
		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {

			output.insertAfter(Items.COAL_BLOCK, JadisMendedBlocks.CHARCOAL_BLOCK);
		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {

			output.insertAfter(Items.COPPER_ORE, JadisMendedBlocks.TIN_ORE);
		});

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(output -> {
            output.accept(Items.SPRUCE_DOOR);
            output.accept(Items.BIRCH_DOOR);
            output.accept(Items.BIRCH_DOOR);
            output.accept(Items.JUNGLE_DOOR);
            output.accept(Items.ACACIA_DOOR);
            output.accept(Items.DARK_OAK_DOOR);
            output.accept(Items.MANGROVE_DOOR);
            output.accept(Items.CHERRY_DOOR);
            output.accept(Items.PALE_OAK_DOOR);
            output.accept(Items.POPLAR_DOOR);
            output.accept(Items.BAMBOO_DOOR);
            output.accept(Items.CRIMSON_DOOR);
            output.accept(Items.WARPED_DOOR);
            Items.COPPER_DOOR.forEach(output::accept);

            output.accept(Items.SPRUCE_FENCE_GATE);
            output.accept(Items.BIRCH_FENCE_GATE);
            output.accept(Items.JUNGLE_FENCE_GATE);
            output.accept(Items.ACACIA_FENCE_GATE);
            output.accept(Items.DARK_OAK_FENCE_GATE);
            output.accept(Items.MANGROVE_FENCE_GATE);
            output.accept(Items.CHERRY_FENCE_GATE);
            output.accept(Items.PALE_OAK_FENCE_GATE);
            output.accept(Items.POPLAR_FENCE_GATE);
            output.accept(Items.BAMBOO_FENCE_GATE);
            output.accept(Items.CRIMSON_FENCE_GATE);
            output.accept(Items.WARPED_FENCE_GATE);

            output.accept(Items.SPRUCE_TRAPDOOR);
            output.accept(Items.BIRCH_TRAPDOOR);
            output.accept(Items.JUNGLE_TRAPDOOR);
            output.accept(Items.ACACIA_TRAPDOOR);
            output.accept(Items.DARK_OAK_TRAPDOOR);
            output.accept(Items.MANGROVE_TRAPDOOR);
            output.accept(Items.CHERRY_TRAPDOOR);
            output.accept(Items.PALE_OAK_TRAPDOOR);
            output.accept(Items.POPLAR_TRAPDOOR);
            output.accept(Items.BAMBOO_TRAPDOOR);
            output.accept(Items.CRIMSON_TRAPDOOR);
            output.accept(Items.WARPED_TRAPDOOR);
            Items.COPPER_TRAPDOOR.forEach(output::accept);

            output.accept(Items.SPRUCE_BUTTON);
            output.accept(Items.BIRCH_BUTTON);
            output.accept(Items.JUNGLE_BUTTON);
            output.accept(Items.ACACIA_BUTTON);
            output.accept(Items.DARK_OAK_BUTTON);
            output.accept(Items.MANGROVE_BUTTON);
            output.accept(Items.CHERRY_BUTTON);
            output.accept(Items.PALE_OAK_BUTTON);
            output.accept(Items.POPLAR_BUTTON);
            output.accept(Items.BAMBOO_BUTTON);
            output.accept(Items.CRIMSON_BUTTON);
            output.accept(Items.WARPED_BUTTON);
            output.accept(Items.POLISHED_BLACKSTONE_BUTTON);
        });

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
			output.insertAfter(Items.BARREL, JadisMendedBlocks.ITEM_CUBBY);
		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(output -> {
			output.insertAfter(Items.BARREL, JadisMendedBlocks.ITEM_CUBBY);
		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
			output.insertAfter(Items.IRON_BLOCK, JadisMendedBlocks.CHISELED_IRON);
			output.insertAfter(JadisMendedBlocks.CHISELED_IRON, JadisMendedBlocks.CUT_IRON);
		});
    }


}
