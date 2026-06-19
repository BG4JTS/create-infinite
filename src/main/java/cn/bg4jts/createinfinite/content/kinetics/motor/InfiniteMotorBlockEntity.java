package cn.bg4jts.createinfinite.content.kinetics.motor;

import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class InfiniteMotorBlockEntity extends CreativeMotorBlockEntity {

    public InfiniteMotorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float calculateAddedStressCapacity() {
        float speed = Math.abs(this.getSpeed());
        if (speed == 0) return 0;
        // Infinite stress: return an extremely large value
        return 0.5f * Float.MAX_VALUE;
    }
}
