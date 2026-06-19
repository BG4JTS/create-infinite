package cn.bg4jts.createinfinite.registry;

import cn.bg4jts.createinfinite.CreateInfinite;
import cn.bg4jts.createinfinite.content.kinetics.motor.InfiniteMotorBlock;
import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CreateInfinite.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(CreateInfinite.MODID);

    // Infinite Motor
    public static final DeferredBlock<InfiniteMotorBlock> INFINITE_MOTOR =
            BLOCKS.register("infinite_motor",
                    () -> new InfiniteMotorBlock(BlockBehaviour.Properties.of()
                            .strength(5.0f, 10.0f)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredItem<BlockItem> INFINITE_MOTOR_ITEM =
            ITEMS.register("infinite_motor",
                    () -> new BlockItem(INFINITE_MOTOR.get(), new Item.Properties()));
}
