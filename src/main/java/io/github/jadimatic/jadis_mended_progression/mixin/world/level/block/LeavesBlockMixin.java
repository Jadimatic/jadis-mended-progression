package io.github.jadimatic.jadis_mended_progression.mixin.world.level.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.LeavesBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeavesBlock.class)
abstract class LeavesBlockMixin {

	//Find a way to inject the below method into this class and use it.

	/*@Override
	protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		if (context.isPlacement()) {
			return Shapes.empty();
		} else if (context.isAbove(Shapes.block(), pos, true) && !context.isDescending()) {
			return SHAPE_STABLE;
		} else {
			return state.getValue(DISTANCE) != 0 && state.getValue(BOTTOM) && context.isAbove(SHAPE_BELOW_BLOCK, pos, true) ? SHAPE_UNSTABLE_BOTTOM : Shapes.empty();
		}



		// Mixin the parent, and use instanceof to check what `this` is
@Mixin(Entity.class)
abstract class EntityMixin {
  @ModifyReturnValue(
    method = "getPose",
    at = @At("RETURN")
  )
  private Pose overrideForPlayer(Pose pose) {
    // the compiler doesn't believe `this` can ever be `Player`, so a cast to `Object` is required
    return (Object) this instanceof Player ? Pose.SWIMMING : pose;
  }









	}*/



}
