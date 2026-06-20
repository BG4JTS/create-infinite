package cn.bg4jts.createinfinite.registry;

import cn.bg4jts.createinfinite.CreateInfinite;
import cn.bg4jts.createinfinite.content.kinetics.motor.InfiniteMotorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateInfinite.MODID);

    // Holder to break self-reference cycle; set before any BE is created
    @SuppressWarnings("unchecked")
    private static final BlockEntityType<InfiniteMotorBlockEntity>[] HOLDER = new BlockEntityType[1];

    public static final Supplier<BlockEntityType<InfiniteMotorBlockEntity>> INFINITE_MOTOR =
            BLOCK_ENTITIES.register("infinite_motor",
                    () -> {
                        BlockEntityType<InfiniteMotorBlockEntity> type = BlockEntityType.Builder.of(
                                (pos, state) -> new InfiniteMotorBlockEntity(HOLDER[0], pos, state),
                                ModBlocks.INFINITE_MOTOR.get()
                        ).build(null);
                        HOLDER[0] = type;
                        return type;
                    });
}
