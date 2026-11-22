package io.github.jadimatic.jadis_mended_progression.block;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedBlockItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.spongepowered.asm.mixin.Mutable;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class JadisMendedBlocks {

	public static final Block ITEM_CUBBY = registerBlock(JadisMendedBlockItemIds.ITEM_CUBBY,
		properties -> new ItemCubbyBlock(properties//Defines block properties.
			.strength(2.0f, 4.0f)
			.mapColor(MapColor.COLOR_BROWN)
			.sound(SoundType.WOOD)
		), properties -> properties//Defines block item properties.
	);

	public static final Block VACUUM = registerBlock(JadisMendedBlockItemIds.VACUUM,
		properties -> new ItemCubbyBlock(properties//Defines block properties.
			.strength(2.0f, 4.0f)
			.mapColor(MapColor.METAL)
			.sound(SoundType.IRON)
		), properties -> properties//Defines block item properties.
	);

	public static final Block CUT_IRON = registerBlock(JadisMendedBlockItemIds.CUT_IRON,
		properties -> new Block(properties//Defines block properties.
			.strength(5.0F, 6.0F)
			.mapColor(MapColor.METAL)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresCorrectToolForDrops()
			.sound(SoundType.IRON)
		), properties -> properties//Defines block item properties.
	);

	public static final Block CHISELED_IRON = registerBlock(JadisMendedBlockItemIds.CHISELED_IRON,
		properties -> new Block(properties//Defines block properties.
			.strength(5.0F, 6.0F)
			.mapColor(MapColor.METAL)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresCorrectToolForDrops()
			.sound(SoundType.IRON)
		), properties -> properties//Defines block item properties.
	);

	public static final Block CHARCOAL_BLOCK = registerBlock(JadisMendedBlockItemIds.CHARCOAL_BLOCK,
		properties -> new Block(properties//Defines block properties.
			.requiresCorrectToolForDrops()
			.strength(5.0f, 6.0f)
			.mapColor(MapColor.COLOR_BLACK)
			.instrument(NoteBlockInstrument.BASEDRUM)
			.sound(SoundType.STONE)
		), properties -> properties//Defines block item properties.
			.cookingFuel(ContextIntProviders.COOKING_TIME_COAL_BLOCK)
	);

	public static final Block TIN_ORE = registerBlock(JadisMendedBlockItemIds.TIN_ORE,
		properties -> new DropExperienceBlock(ConstantInt.of(0), properties),
		properties -> properties,//Defines block item properties. This is currently just here by necessity.
		BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE)

	);
	public static final Block DEEPSLATE_TIN_ORE = registerBlock(JadisMendedBlockItemIds.DEEPSLATE_TIN_ORE,
		properties -> new DropExperienceBlock(ConstantInt.of(0), properties),
		properties -> properties,//Defines block item properties. This is currently just here by necessity.
		BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE)

	);



	///Registers both the block and it's item simultaneously. This method also takes a BlockBehavior.Properties parameter.
	private static Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFunction, UnaryOperator<Item.Properties> itemFunction, BlockBehaviour.Properties blockProperties){
		Block toRegister = blockFunction.apply(blockProperties.setId(id.block()));
		registerBlockItem(id, toRegister, itemFunction);
		return Registry.register(BuiltInRegistries.BLOCK, id.block(), toRegister);
	}
	/* //Concept for a shorter version, convenient for simple ore block registries.
	///Delegates to the original and lacks the itemFunction parameter.
	private static Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFunction, BlockBehaviour.Properties blockProperties){
		return registerBlock(id, blockFunction, UnaryOperator<Item.Properties>, blockProperties);
	}
	*/

	public static ResourceKey<Block> getResourceKey(Block block){return BuiltInRegistries.BLOCK.getResourceKey(block).get();}

	///Delegates to the original and lacks the blockProperties parameter.
	private static Block registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFunction, UnaryOperator<Item.Properties> itemFunction){
		return registerBlock(id, blockFunction, itemFunction, BlockBehaviour.Properties.of());
	}



	///Registers the item for the block.
	private static void registerBlockItem(BlockItemId id, Block block, UnaryOperator<Item.Properties> itemFunction){
		Registry.register(BuiltInRegistries.ITEM, id.item(),
			new BlockItem(block, itemFunction.apply(new Item.Properties()).useBlockDescriptionPrefix()
				.setId(id.item())));
	}

	public static void registerMendedBlocks(){
		JadisMendedProgression.LOGGER.info("Registering Mod Blocks for " + JadisMendedProgression.MOD_ID);
	}
}
