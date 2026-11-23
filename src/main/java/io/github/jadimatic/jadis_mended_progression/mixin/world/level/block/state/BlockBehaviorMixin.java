package io.github.jadimatic.jadis_mended_progression.mixin.world.level.block.state;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockBehaviour.class)
abstract class BlockBehaviorMixin {

	//Find a way to inject the below method into this class and use it.

	@ModifyReturnValue(method = "getCollisionShape", at = @At("RETURN"))
	private VoxelShape overrideLeafBlockCollision(VoxelShape original){
		return original;//change this once we know how to properly return a new value for leaves.
	}





	/*

	@ModifyReturnValue(
    method = "getPose",
    at = @At("RETURN")
  )
  private Pose overrideForPlayer(Pose pose) {
    // the compiler doesn't believe `this` can ever be `Player`, so a cast to `Object` is required
    return (Object) this instanceof Player ? Pose.SWIMMING : pose;



	@Override
	protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		if (context.isPlacement()) {
			return Shapes.empty();
		} else if (context.isAbove(Shapes.block(), pos, true) && !context.isDescending()) {
			return SHAPE_STABLE;
		} else {
			return state.getValue(DISTANCE) != 0 && state.getValue(BOTTOM) && context.isAbove(SHAPE_BELOW_BLOCK, pos, true) ? SHAPE_UNSTABLE_BOTTOM : Shapes.empty();
		}


  }









	}*/



}
