package io.github.jadimatic.jadis_mended_progression.mixin.world.entity;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity{

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	///Fixes awkward behavior where the sneaking function of ladders conflicts with descending.
	@Definition(id = "getInBlockState", method = "Lnet/minecraft/world/entity/LivingEntity;getInBlockState()Lnet/minecraft/world/level/block/state/BlockState;")
	@Definition(id = "is", method = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z")
	@Definition(id = "SCAFFOLDING", field = "Lnet/minecraft/world/level/block/Blocks;SCAFFOLDING:Lnet/minecraft/world/level/block/Block;")
	@Expression("@(this.getInBlockState().is(SCAFFOLDING))")
	@ModifyExpressionValue(method = "handleOnClimbable", at = @At("MIXINEXTRAS:EXPRESSION"))
	boolean overrideHandleOnClimbable (boolean original){
		return original || this.getInBlockState().is(BlockTags.LEAVES);
	}
}
