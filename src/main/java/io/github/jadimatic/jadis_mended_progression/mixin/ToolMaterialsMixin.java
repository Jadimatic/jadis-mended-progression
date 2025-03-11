package io.github.jadimatic.jadis_mended_progression.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {





	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;WOOD:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterWoodStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			miningLevel,
			32,
			miningSpeed,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}

	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			from = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;WOOD:Lnet/minecraft/item/ToolMaterials;"
			),
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;STONE:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterStoneStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			0,
			32,
			miningSpeed,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}

	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			from = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;STONE:Lnet/minecraft/item/ToolMaterials;"
			),
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;IRON:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterIronStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			miningLevel,
			326,
			7f,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}

	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			from = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;IRON:Lnet/minecraft/item/ToolMaterials;"
			),
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;DIAMOND:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterDiamondStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			2,
			250,
			8f,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}

	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			from = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;DIAMOND:Lnet/minecraft/item/ToolMaterials;"
			),
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;GOLD:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterGoldStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			miningLevel,
			96,
			5f,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}

	@WrapOperation(
		method = "<clinit>",
		allow = 1, require = 1,
		slice = @Slice(
			from = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;GOLD:Lnet/minecraft/item/ToolMaterials;"
			),
			to = @At(
				value = "FIELD", opcode = Opcodes.PUTSTATIC,
				target = "Lnet/minecraft/item/ToolMaterials;NETHERITE:Lnet/minecraft/item/ToolMaterials;"
			)
		),
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)Lnet/minecraft/item/ToolMaterials;"
		)
	)
	private static ToolMaterials alterNetheriteStats(
		String name,
		int ordinal,
		int miningLevel,
		int itemDurability,
		float miningSpeed,
		float attackDamage,
		int enchantability,
		Supplier<Ingredient> repairIngredient,
		Operation<ToolMaterials> original
	) {
		return original.call(
			name,
			ordinal,
			miningLevel,
			564,
			9f,
			attackDamage,
			enchantability,
			repairIngredient
		);
	}
}
