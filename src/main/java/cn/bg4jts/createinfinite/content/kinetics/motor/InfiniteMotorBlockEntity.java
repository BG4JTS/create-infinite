package cn.bg4jts.createinfinite.content.kinetics.motor;

import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class InfiniteMotorBlockEntity extends GeneratingKineticBlockEntity {

	public InfiniteMotorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public float getGeneratedSpeed() {
		return 0;
	}

	@Override
	public float calculateAddedStressCapacity() {
		this.lastCapacityProvided = Float.MAX_VALUE;
		return this.lastCapacityProvided;
	}

}
