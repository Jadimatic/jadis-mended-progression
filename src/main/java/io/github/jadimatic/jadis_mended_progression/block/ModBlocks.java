package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.references.ModBlockItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModBlocks {

	public static final Block CHARCOAL_BLOCK = registerBlock(ModBlockItemIds.CHARCOAL_BLOCK,
		properties -> new Block(properties//Defines block properties.
				.requiresCorrectToolForDrops()
				.strength(5.0f, 6.0f)
				.mapColor(MapColor.COLOR_BLACK)
				.instrument(NoteBlockInstrument.BASEDRUM)
				.sound(SoundType.STONE)
		), properties -> properties//Defines block item properties.
				.cookingFuel(ContextIntProviders.COOKING_TIME_COAL_BLOCK)
	);

	///Registers both the block and it's item simultaneously.
	private static Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFunction, UnaryOperator<Item.Properties> itemFunction){
		Block toRegister = blockFunction.apply(BlockBehaviour.Properties.of().setId(id.block()));
		registerBlockItem(id, toRegister, itemFunction);
		return Registry.register(BuiltInRegistries.BLOCK, id.block(), toRegister);
	}
	///Registers the item for the block.
	private static void registerBlockItem(BlockItemId id, Block block, UnaryOperator<Item.Properties> itemFunction){
		Registry.register(BuiltInRegistries.ITEM, id.item(),
			new BlockItem(block, itemFunction.apply(new Item.Properties()).useBlockDescriptionPrefix()
				.setId(id.item())));
	}

	public static void registerModBlocks(){
		JadisMendedProgression.LOGGER.info("Registering blocks for " + JadisMendedProgression.MOD_ID);
	}
}
