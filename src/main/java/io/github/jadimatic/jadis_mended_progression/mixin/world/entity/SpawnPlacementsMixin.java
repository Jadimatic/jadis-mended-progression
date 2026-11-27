package io.github.jadimatic.jadis_mended_progression.mixin.world.entity;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SpawnPlacements.class)
public class SpawnPlacementsMixin {
	@Definition(id = "register", method = "Lnet/minecraft/world/entity/SpawnPlacements;register(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/SpawnPlacementType;Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/world/entity/SpawnPlacements$SpawnPredicate;)V")
	@Definition(id = "CREEPER", field = "Lnet/minecraft/world/entity/EntityTypes;CREEPER:Lnet/minecraft/world/entity/EntityType;")
	@Expression("register(CREEPER, ?, ?, ?)")
	@ModifyArg(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static SpawnPlacements.SpawnPredicate<Mob> overrideCreeperSpawnConditions(SpawnPlacements.SpawnPredicate<Mob> spawnPredicate){


		return (
			creeper,
			serverLevelAccessor,
			entitySpawnReason,
			blockPos,
			randomSource) -> {
			boolean original = spawnPredicate.test(creeper,
				serverLevelAccessor,
				entitySpawnReason,
				blockPos,
				randomSource);
			if(entitySpawnReason.equals(EntitySpawnReason.NATURAL) || entitySpawnReason.equals(EntitySpawnReason.CHUNK_GENERATION)){
				return original && serverLevelAccessor.getBlockState(blockPos.below()).is(JadisMendedTags.Blocks.VALID_FOR_CREEPER_SPAWN);
			}
			else{
				return original;
			}

		};

	}
}
