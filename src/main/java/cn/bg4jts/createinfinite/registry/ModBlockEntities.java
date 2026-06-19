package cn.bg4jts.createinfinite.registry;

import cn.bg4jts.createinfinite.CreateInfinite;
import cn.bg4jts.createinfinite.content.kinetics.motor.InfiniteMotorBlockEntity;
import com.simibubi.create.AllBlockEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateInfinite.MODID);

    @SuppressWarnings("DataFlowIssue")
    public static final Supplier<BlockEntityType<InfiniteMotorBlockEntity>> INFINITE_MOTOR =
            BLOCK_ENTITIES.register("infinite_motor",
                    () -> BlockEntityType.Builder.of(InfiniteMotorBlockEntity::new, ModBlocks.INFINITE_MOTOR.get()).build(null));
}
