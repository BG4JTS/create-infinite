package cn.bg4jts.createinfinite.content.kinetics.motor;

import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import cn.bg4jts.createinfinite.registry.ModBlocks;

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
		float speed = Math.abs(this.getSpeed());
		if (speed == 0)
			return 0;
		this.lastCapacityProvided = 0.5f * Float.MAX_VALUE;
		return this.lastCapacityProvided;
	}

}
