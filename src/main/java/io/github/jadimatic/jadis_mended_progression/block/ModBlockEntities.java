package io.github.jadimatic.jadis_mended_progression.block;


import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
	public static final BlockEntityType<ItemCubbyBlockEntity> ITEM_CUBBY_ENTITY =
		register("item_cubby", ItemCubbyBlockEntity::new, ModBlocks.ITEM_CUBBY);

	private static <T extends BlockEntity> BlockEntityType<T> register(
		String name,
		FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
		Block... blocks
	) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, JadisMendedProgression.id(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}

	public static void registerModBlockEntities() {
		JadisMendedProgression.LOGGER.info("Registering Mod Block Entities for" + JadisMendedProgression.MOD_ID);
	}
}
