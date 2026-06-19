package cn.bg4jts.createinfinite.content.kinetics.motor;

import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import cn.bg4jts.createinfinite.registry.ModBlockEntities;

public class InfiniteMotorBlock extends CreativeMotorBlock {

    public InfiniteMotorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.INFINITE_MOTOR.create(pos, state);
    }
}
