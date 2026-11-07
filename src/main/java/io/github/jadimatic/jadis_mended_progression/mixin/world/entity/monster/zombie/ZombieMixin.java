package io.github.jadimatic.jadis_mended_progression.mixin.world.entity.monster.zombie;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
	protected ZombieMixin(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
	void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
		EquipmentSlot selectedHand;

		if(this.getMainHandItem().isEmpty()){
			boolean rand = random.nextBoolean();
			if(rand){selectedHand = EquipmentSlot.MAINHAND;}//Coin flip whether to assign the MAINHAND...
			else{selectedHand = EquipmentSlot.OFFHAND;}// or OFFHAND.
		}
		else {selectedHand = EquipmentSlot.OFFHAND;}//Defaults to OFFHAND when MAINHAND is full.

		if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.5F : 1.0F)) {// 50% chance on HARD difficulty and 100% chance on anything else.
			int rand = random.nextInt(6);
			if (rand == 0) {
			this.setItemSlot(selectedHand, new ItemStack(Items.COPPER_LANTERN.weathering().weathered()));
			} else if (rand == 1) {
				this.setItemSlot(selectedHand, new ItemStack(Items.POISONOUS_POTATO));
			} else {
				this.setItemSlot(selectedHand, new ItemStack(Items.LANTERN));
			}
		}
	}

}
