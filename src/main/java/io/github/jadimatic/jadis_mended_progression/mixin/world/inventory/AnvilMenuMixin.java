package io.github.jadimatic.jadis_mended_progression.mixin.world.inventory;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.jadimatic.jadis_mended_progression.item.component.JadisMendedDataComponents;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {

///Used in the process of fixing the material cost of anvil repairs.
	@Definition(id = "repairAmount", local = @Local(type = int.class, name = "repairAmount"))
	@Definition(id = "min", method = "Ljava/lang/Math;min(II)I")
	@Definition(id = "result", local = @Local(type = ItemStack.class, name = "result"))
	@Definition(id = "getDamageValue", method = "Lnet/minecraft/world/item/ItemStack;getDamageValue()I")
	@Definition(id = "getMaxDamage", method = "Lnet/minecraft/world/item/ItemStack;getMaxDamage()I")
	@Expression("repairAmount = min(result.getDamageValue(), @(result.getMaxDamage() / 4))")
	@ModifyExpressionValue(method = "createResult", at = @At("MIXINEXTRAS:EXPRESSION"))
	public int mendedRepairAmount(int original, @Local(name = "result") ItemStack result){

		Float repairAmount = result.get(JadisMendedDataComponents.REPAIR_AMOUNT);
		if(repairAmount == null){
			return original;

		}
		else {
			return (int) (result.getMaxDamage() * repairAmount);
		}

	}
}
