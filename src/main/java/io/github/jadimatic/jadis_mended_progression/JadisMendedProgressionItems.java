package io.github.jadimatic.jadis_mended_progression;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;


public class JadisMendedProgressionItems {
	public static final Item JADIS_PLANT_FIBER = new Item(new Item.Settings());

	public static void register() {
		Registry.register(Registries.ITEM, new Identifier(JadisMendedProgression.NAMESPACE, "plant_fiber"), JADIS_PLANT_FIBER);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addItem(JADIS_PLANT_FIBER);
		});
	}
}
