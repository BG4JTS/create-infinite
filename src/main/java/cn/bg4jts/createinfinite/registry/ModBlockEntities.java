package cn.bg4jts.createinfinite.registry;

import cn.bg4jts.createinfinite.CreateInfinite;
import cn.bg4jts.createinfinite.content.kinetics.motor.InfiniteMotorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateInfinite.MODID);

    public static final Supplier<BlockEntityType<InfiniteMotorBlockEntity>> INFINITE_MOTOR;
    static {
        @SuppressWarnings("unchecked")
        BlockEntityType<InfiniteMotorBlockEntity>[] holder = new BlockEntityType[1];
        INFINITE_MOTOR = BLOCK_ENTITIES.register("infinite_motor",
                () -> {
                    holder[0] = new BlockEntityType<>(
                            (pos, state) -> new InfiniteMotorBlockEntity(holder[0], pos, state),
                            Set.of(ModBlocks.INFINITE_MOTOR.get())
                    );
                    return holder[0];
                });
    }
}
