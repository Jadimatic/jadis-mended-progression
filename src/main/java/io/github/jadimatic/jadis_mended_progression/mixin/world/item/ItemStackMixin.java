package io.github.jadimatic.jadis_mended_progression.mixin.world.item;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	@Shadow
	public abstract boolean isBroken();

	@Unique
	private boolean shouldBreak() {
		return JadisMendedProgression.shouldBreak((ItemStack) (Object) this);
	}

	//region Stop Tools From Shattering
	@ModifyArg(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
	private int overrideItemDeletion(int amount){
		return this.shouldBreak() ? amount : 0;//If shouldBreak is true, the item should break per vanilla logic, otherwise return 0 so it does nothing.
	}

	@Inject(method = "applyDamage", at = @At("HEAD"))
	private void getIsBrokenBoolean(int newDamage, @Nullable ServerPlayer player, Consumer<ItemStack> onBreak, CallbackInfo ci, @Share("alreadyBroken")LocalBooleanRef alreadyBroken){
		alreadyBroken.set(this.isBroken());//Set the shared reference to the value of isBroken.
	}

	@Definition(id = "onBreak", local = @Local(type = Consumer.class, name = "onBreak", argsOnly = true))
	@Definition(id = "accept", method = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V")
	@Expression("onBreak.accept(?)")
	@WrapWithCondition(method = "applyDamage", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean makeOnBreakConditional(Consumer<?> instance, Object t, @Share("alreadyBroken")LocalBooleanRef alreadyBroken){
		return !alreadyBroken.get();//If alreadyBroken is not true, return the original code, otherwise don't.
	}
	//endregion
}
