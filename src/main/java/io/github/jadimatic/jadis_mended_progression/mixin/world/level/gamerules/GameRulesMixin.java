package io.github.jadimatic.jadis_mended_progression.mixin.world.level.gamerules;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRules.class)
public abstract class GameRulesMixin {
	@Definition(id = "REDUCED_DEBUG_INFO", field = "Lnet/minecraft/world/level/gamerules/GameRules;REDUCED_DEBUG_INFO:Lnet/minecraft/world/level/gamerules/GameRule;")
	@Definition(id = "registerBoolean", method = "Lnet/minecraft/world/level/gamerules/GameRules;registerBoolean(Ljava/lang/String;Lnet/minecraft/world/level/gamerules/GameRuleCategory;Z)Lnet/minecraft/world/level/gamerules/GameRule;")
	@Expression("REDUCED_DEBUG_INFO = @(registerBoolean(?, ?, ?))")
	@ModifyArg(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static boolean modifyReducedDebugInfoGameRule(boolean original){return true;}//Reduces excess F3 info Jens considers cheating by default.

	@Definition(id = "SPAWN_PHANTOMS", field = "Lnet/minecraft/world/level/gamerules/GameRules;SPAWN_PHANTOMS:Lnet/minecraft/world/level/gamerules/GameRule;")
	@Definition(id = "registerBoolean", method = "Lnet/minecraft/world/level/gamerules/GameRules;registerBoolean(Ljava/lang/String;Lnet/minecraft/world/level/gamerules/GameRuleCategory;Z)Lnet/minecraft/world/level/gamerules/GameRule;")
	@Expression("SPAWN_PHANTOMS = @(registerBoolean(?, ?, ?))")
	@ModifyArg(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static boolean modifySpawnPhantomsGameRule(boolean original){return false;}//Sets phantoms to not spawn by default.

	@Definition(id = "BLOCK_EXPLOSION_DROP_DECAY", field = "Lnet/minecraft/world/level/gamerules/GameRules;BLOCK_EXPLOSION_DROP_DECAY:Lnet/minecraft/world/level/gamerules/GameRule;")
	@Definition(id = "registerBoolean", method = "Lnet/minecraft/world/level/gamerules/GameRules;registerBoolean(Ljava/lang/String;Lnet/minecraft/world/level/gamerules/GameRuleCategory;Z)Lnet/minecraft/world/level/gamerules/GameRule;")
	@Expression("BLOCK_EXPLOSION_DROP_DECAY = @(registerBoolean(?, ?, ?))")
	@ModifyArg(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static boolean modifyBlockExplosionDropDecayGameRule(boolean original){return false;}//Makes explosions always drop the blocks destroyed.

	@Definition(id = "MOB_EXPLOSION_DROP_DECAY", field = "Lnet/minecraft/world/level/gamerules/GameRules;MOB_EXPLOSION_DROP_DECAY:Lnet/minecraft/world/level/gamerules/GameRule;")
	@Definition(id = "registerBoolean", method = "Lnet/minecraft/world/level/gamerules/GameRules;registerBoolean(Ljava/lang/String;Lnet/minecraft/world/level/gamerules/GameRuleCategory;Z)Lnet/minecraft/world/level/gamerules/GameRule;")
	@Expression("MOB_EXPLOSION_DROP_DECAY = @(registerBoolean(?, ?, ?))")
	@ModifyArg(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static boolean modifyMobExplosionDropDecay(boolean original){return false;}//Makes mob explosions always drop the blocks destroyed.
}
