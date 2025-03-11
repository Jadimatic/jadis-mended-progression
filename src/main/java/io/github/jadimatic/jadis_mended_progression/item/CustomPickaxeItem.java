package io.github.jadimatic.jadis_mended_progression.item;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class CustomPickaxeItem extends PickaxeItem {
//This class exists to allow for overriding of some vanilla rules, making it possible to further customize tools
	public CustomPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
}
