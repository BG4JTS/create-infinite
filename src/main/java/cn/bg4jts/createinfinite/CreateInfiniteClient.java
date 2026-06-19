package cn.bg4jts.createinfinite;

import cn.bg4jts.createinfinite.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.motor.CreativeMotorRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Mod(value = CreateInfinite.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CreateInfinite.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class CreateInfiniteClient {

    public CreateInfiniteClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        CreateInfinite.LOGGER.info("Create: Infinite client initialized");
        // Register the creative motor renderer for our infinite motor block entity
        BlockEntityRenderers.register(ModBlockEntities.INFINITE_MOTOR.get(), CreativeMotorRenderer::new);
    }
}
