package io.github.jadimatic.jadis_mended_progression.client.datagen;

import com.google.common.collect.ImmutableList;
import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import io.github.jadimatic.jadis_mended_progression.block.ModBlocks;
import io.github.jadimatic.jadis_mended_progression.client.datagen.util.provider.ModBlockLootSubProvider;
import io.github.jadimatic.jadis_mended_progression.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.SequentialEntry;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootPredicates;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.floats.UniformGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.tags.ItemTags;

import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;

public class ModBlockLootTableProvider extends ModBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {



        add(//Contains instructions to generate a block loot table. This one specifically gives gravel a 10% chance to drop itself when not using a shovel, and is always guaranteed to drop 1 flint.
                Blocks.GRAVEL,//Assigns the following loot table to this block.
                LootTable.lootTable()//Creates a loot table.
                        .withPool(
                                new LootPool.Builder().when(hasItemTag(ItemTags.SHOVELS))
                                        .add(LootItem.lootTableItem(Items.GRAVEL))
                        )
                        .withPool(
                                new LootPool.Builder().when(hasItemTag(ItemTags.SHOVELS).invert())
                                        .add(LootItem.lootTableItem(Items.FLINT))
                        )
                        .withPool(
                                new LootPool.Builder().when(hasItemTag(ItemTags.SHOVELS).invert())
                                        .add(LootItem.lootTableItem(Items.GRAVEL)).when(randomChance(0.1f))//randomChance randomizes whether this loot will drop.
                        )
        );
		add(//Makes grass blocks drop themselves when using a shovel and dirt when not.
			Blocks.GRASS_BLOCK,
			LootTable.lootTable()
				.withPool(
					new LootPool.Builder().when(hasItemTag(ItemTags.SHOVELS))
						.add(LootItem.lootTableItem(Items.GRASS_BLOCK))
				)
				.withPool(
					new LootPool.Builder().when(hasItemTag(ItemTags.SHOVELS).invert())
						.add(LootItem.lootTableItem(Items.DIRT))
				)
		);
		add(//Since the tool requirements are handled elsewhere for this one, it merely specifies what loot should be dropped.
				ModBlocks.CHARCOAL_BLOCK,
				LootTable.lootTable()
						.withPool(
							new LootPool.Builder()
								.add(LootItem.lootTableItem(ModBlocks.CHARCOAL_BLOCK))
						)
		);


        //The following is just testing stuff, do not include these in the final mod!
        add(
                Blocks.SHORT_GRASS,
                createShovelOnlyDrop(Items.EMERALD)
                        .withPool(//Adds an extra item to drop.
                                new LootPool.Builder()
                                        .add(LootItem.lootTableItem(Items.DIAMOND)).when(hasItem(Items.STONE_SWORD))
                        )
        );


            // Make condensed dirt drop its block item.
            // Also adds the condition that it survives the explosion that broke it, if applicable,
        //dropSelf(ModBlocks.CONDENSED_DIRT);
            // Make prismarine lamps drop themselves with silk touch only
        //dropWhenSilkTouch(ModBlocks.PRISMARINE_LAMP);
            // Make condensed oak logs drop between 7 and 9 oak logs
        //add(ModBlocks.CONDENSED_OAK_LOG, LootTable.lootTable().withPool(applyExplosionCondition(Items.OAK_LOG, LootPool.lootPool()
        //        .setRolls(new UniformGenerator(new ConstantValue(7), new ConstantValue(9)))
        //        .add(LootItem.lootTableItem(Items.OAK_LOG))))
        //);
    }
}
