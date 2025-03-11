package io.github.jadimatic.jadis_mended_progression.item;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;


public enum ModToolMaterials implements ToolMaterial{
	FLINT(1, 64, 3.0F, 1.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{Items.FLINT})),
	COPPER(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.ofItems(new ItemConvertible[]{Items.COPPER_INGOT})),
	BRONZE(1, 190, 6.0F, 1.0F, 5, () -> Ingredient.ofItems(new ItemConvertible[]{Items.COPPER_INGOT})),
	STEEL(3, 432, 10.0F, 1.0F, 5, () -> Ingredient.ofItems(new ItemConvertible[]{Items.IRON_INGOT}));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = miningLevel;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = new Lazy(repairIngredient);
	}

	public int getDurability() {
		return this.itemDurability;
	}

	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public int getMiningLevel() {
		return this.miningLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredient.get();
	}
}
