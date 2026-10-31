package io.github.jadimatic.jadis_mended_progression.item;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WeatheringCopperCollection;

import java.util.function.Function;

public class ModItems {


    public static final Item EXAMPLE_ITEM = registerItem("example_item", Item::new);

    /** Registers items under this mod's namespace with an assigned name and properties. */
    private  static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JadisMendedProgression.MOD_ID, name)))));
    }


    public static void registerModItems() {
        JadisMendedProgression.LOGGER.info("Registering Mod Items for" + JadisMendedProgression.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {

            output.accept(EXAMPLE_ITEM);
			output.accept(ModBlocks.CHARCOAL_BLOCK);
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
    }


}
