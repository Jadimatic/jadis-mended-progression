package io.github.jadimatic.jadis_mended_progression.mixin.world.level.block.state;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.jadimatic.jadis_mended_progression.JadisMendedProgressionConfig;
import io.github.jadimatic.jadis_mended_progression.mixin.ReferenceAccessor;
import io.github.jadimatic.jadis_mended_progression.references.JadisMendedTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockBehaviour.class)
abstract class BlockBehaviorMixin {
	@Unique
	private static final VoxelShape SHAPE_BELOW_BLOCK = Shapes.block().move(0.0, -1.0, 0.0).optimize();
	@Unique
	private static final VoxelShape SHAPE_UNSTABLE_BOTTOM = Block.column(16.0, 0.0, 2.0);
	@Unique
	private static final VoxelShape SHAPE_STABLE = Shapes.or(
		Block.column(16.0, 14.0, 16.0), Shapes.rotateHorizontal(Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 2.0)).values().stream().reduce(Shapes.empty(), Shapes::or)
	);
	@Unique
	private static final VoxelShape SHAPE_UNSTABLE = Shapes.or(
		SHAPE_STABLE, SHAPE_UNSTABLE_BOTTOM, Shapes.rotateHorizontal(Block.boxZ(16.0, 0.0, 2.0, 0.0, 2.0)).values().stream().reduce(Shapes.empty(), Shapes::or)
	);
	@Unique
	private static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;
	@Unique
	boolean debugLeafBottom;

	//Find a way to inject the below method into this class and use it.

	@ModifyReturnValue(method = "getCollisionShape", at = @At("RETURN"))
	private VoxelShape overrideLeafBlockCollision(VoxelShape original, final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context){
		if(state.getBlock() instanceof LeavesBlock){
			if (context.isPlacement()) {
				return Shapes.empty();
			} else if (context.isAbove(Shapes.block(), pos, true) && !context.isDescending()) {
				return SHAPE_STABLE;
			} else {
				BlockState stateBelow = level.getBlockState(pos.below());
				if(!JadisMendedProgressionConfig.fallOutOfTrees){
					return !(stateBelow.is(JadisMendedTags.Blocks.CLIMBABLE_LEAVES) || stateBelow.isFaceSturdy(level, pos, Direction.UP)) && context.isAbove(SHAPE_BELOW_BLOCK, pos, true) ? SHAPE_UNSTABLE_BOTTOM : Shapes.empty();
				}
				else{
					return Shapes.empty();
				}
			}
		}
		else {return original;}
	}
	///Makes this method also apply to every block in the climbable leaves tag.
	@ModifyExpressionValue(method = "getCollisionShape", at = @At(value = "FIELD:ONE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour;hasCollision:Z", opcode = Opcodes.GETFIELD))
	private boolean overrideGetCollisionShape (boolean original){
		return original && !(BuiltInRegistries.BLOCK.wrapAsHolder(this.asBlock()) instanceof Holder.Reference<Block> reference && ((ReferenceAccessor<Block>) reference).jadisMended$getTags() != null && reference.is(JadisMendedTags.Blocks.CLIMBABLE_LEAVES));
	}

	@Shadow
	protected abstract Block asBlock();





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
