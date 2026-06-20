package cn.bg4jts.createinfinite.content.kinetics.motor;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.motor.KineticScrollValueBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
import cn.bg4jts.createinfinite.registry.ModBlocks;

import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class InfiniteMotorBlockEntity extends GeneratingKineticBlockEntity {

	public static final int DEFAULT_SPEED = 16;
	public static final int MAX_SPEED = 256;

	public ScrollValueBehaviour generatedSpeed;

	public InfiniteMotorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		super.addBehaviours(behaviours);
		int max = MAX_SPEED;
		generatedSpeed = new KineticScrollValueBehaviour(
			CreateLang.translateDirect("kinetics.creative_motor.rotation_speed"),
			this, new MotorValueBox());
		generatedSpeed.between(-max, max);
		generatedSpeed.value = DEFAULT_SPEED;
		generatedSpeed.withCallback(i -> this.updateGeneratedRotation());
		behaviours.add(generatedSpeed);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
			updateGeneratedRotation();
	}

	@Override
	public float getGeneratedSpeed() {
		if (!getBlockState().is(ModBlocks.INFINITE_MOTOR.get()))
			return 0;
		return convertToDirection(generatedSpeed.getValue(),
			getBlockState().getValue(InfiniteMotorBlock.FACING));
	}

	@Override
	public float calculateAddedStressCapacity() {
		float speed = Math.abs(this.getSpeed());
		if (speed == 0)
			return 0;
		this.lastCapacityProvided = 0.5f * Float.MAX_VALUE;
		return this.lastCapacityProvided;
	}

	static class MotorValueBox extends ValueBoxTransform.Sided {
		@Override
		protected Vec3 getSouthLocation() {
			return VecHelper.voxelSpace(8, 8, 12.5);
		}

		@Override
		public Vec3 getLocalOffset(LevelAccessor level, BlockPos pos, BlockState state) {
			Direction facing = state.getValue(InfiniteMotorBlock.FACING);
			return super.getLocalOffset(level, pos, state)
				.add(Vec3.atLowerCornerOf(facing.getNormal()).scale(-1 / 16f));
		}

		@Override
		public void rotate(LevelAccessor level, BlockPos pos, BlockState state, PoseStack ms) {
			super.rotate(level, pos, state, ms);
			Direction facing = state.getValue(InfiniteMotorBlock.FACING);
			if (facing.getAxis() == Axis.Y)
				return;
			if (getSide() != Direction.UP)
				return;
			TransformStack.of(ms)
				.rotateZDegrees(-AngleHelper.horizontalAngle(facing) + 180);
		}

		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			Direction facing = state.getValue(InfiniteMotorBlock.FACING);
			if (facing.getAxis() != Axis.Y && direction == Direction.DOWN)
				return false;
			return direction.getAxis() != facing.getAxis();
		}
	}

}
