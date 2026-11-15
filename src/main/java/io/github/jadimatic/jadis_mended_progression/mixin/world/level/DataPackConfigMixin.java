package io.github.jadimatic.jadis_mended_progression.mixin.world.level;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.minecraft.world.level.DataPackConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

@Mixin(DataPackConfig.class)
public abstract class DataPackConfigMixin {

	@Definition(id = "DEFAULT", field = "Lnet/minecraft/world/level/DataPackConfig;DEFAULT:Lnet/minecraft/world/level/DataPackConfig;")
	@Definition(id = "DataPackConfig", type = DataPackConfig.class)
	@Expression("DEFAULT = @(new DataPackConfig(?, ?))")
	@ModifyArg(method = "<clinit>", index = 0, at = @At("MIXINEXTRAS:EXPRESSION"))
	private static List<String> enableMinecartImprovementsDataPack(List<String> enabled){return enabled;}
}
