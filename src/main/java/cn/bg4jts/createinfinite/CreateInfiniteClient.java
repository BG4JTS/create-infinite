package cn.bg4jts.createinfinite;

import cn.bg4jts.createinfinite.content.kinetics.motor.InfiniteMotorRenderer;
import cn.bg4jts.createinfinite.registry.ModBlockEntities;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CreateInfinite.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CreateInfinite.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class CreateInfiniteClient {

    public CreateInfiniteClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        CreateInfinite.LOGGER.info("Create: Infinite client initialized");

        // Register BER for fallback rendering
        BlockEntityRenderers.register(ModBlockEntities.INFINITE_MOTOR.get(), InfiniteMotorRenderer::new);

        // Register Flywheel visual for instanced rendering
        SimpleBlockEntityVisualizer.builder(ModBlockEntities.INFINITE_MOTOR.get())
                .factory(OrientedRotatingVisual.of(AllPartialModels.SHAFT_HALF))
                .apply();
    }
}
