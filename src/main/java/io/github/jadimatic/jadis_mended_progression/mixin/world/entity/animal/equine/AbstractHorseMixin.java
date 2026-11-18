package io.github.jadimatic.jadis_mended_progression.mixin.world.entity.animal.equine;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin extends Animal {

	@ModifyReturnValue(method = "createBaseHorseAttributes", at = @At("RETURN"))
	private static AttributeSupplier.Builder modifyHorseAttributes(AttributeSupplier.Builder original){
		return original
			.add(Attributes.STEP_HEIGHT, 2);
	}


	@Shadow
	public abstract boolean isTamed();

	protected AbstractHorseMixin(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	/*@WrapOperation(method = "handleEating", at = @At(value = "INVOKE", target = "setInLove"))
	void dontLetGoldBreed(AbstractHorse instance, Player player, Operation<Void> original) {
		//code to remove items from breeding
	}*/ //Not quite functional yet.

	@Inject(method = "handleEating", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/equine/AbstractHorse;getHealth()F"))
	void modifyFoodResults(Player player, ItemStack itemStack, CallbackInfoReturnable<Boolean> cir, @Local(name = "itemUsed") LocalBooleanRef itemUsed, @Local(name = "heal") LocalFloatRef heal, @Local(name = "ageUp") LocalIntRef ageUp, @Local(name = "temper") LocalIntRef temper) {


		if (itemStack.is(Items.BEETROOT)) {//Makes the item function as food but doesn't add it as a breeding item.
			heal.set(3F);
			ageUp.set(60);
			temper.set(3);
		}

		if (itemStack.is(Items.APPLE)) {
			// Optional if you want the item to make the horse breedable
			if (!this.level().isClientSide() && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
				itemUsed.set(true);
				this.setInLove(player);
			}
		}
		if (itemStack.is(Items.CARROT)) {
			// Optional if you want the item to make the horse breedable
			if (!this.level().isClientSide() && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
				itemUsed.set(true);
				this.setInLove(player);
			}
		}
		if (itemStack.is(Items.SUGAR)) {
			// Optional if you want the item to make the horse breedable
			if (!this.level().isClientSide() && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
				itemUsed.set(true);
				this.setInLove(player);
			}
		}
	}
}
