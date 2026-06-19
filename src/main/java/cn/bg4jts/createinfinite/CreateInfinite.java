package cn.bg4jts.createinfinite;

import cn.bg4jts.createinfinite.registry.ModBlockEntities;
import cn.bg4jts.createinfinite.registry.ModBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(CreateInfinite.MODID)
public class CreateInfinite {

    public static final String MODID = "create_infinite";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            CREATIVE_MODE_TABS.register("main",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.create_infinite"))
                            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                            .icon(() -> new ItemStack(ModBlocks.INFINITE_MOTOR_ITEM.get()))
                            .displayItems((params, output) -> {
                                output.accept(ModBlocks.INFINITE_MOTOR_ITEM.get());
                            }).build());

    public CreateInfinite(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register blocks and items
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);

        // Register block entities
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        // Register creative tab
        CREATIVE_MODE_TABS.register(modEventBus);

        // Add to Create''s creative tab
        modEventBus.addListener(this::addToCreateTab);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Create: Infinite - Initializing infinite possibilities");
    }

    private void addToCreateTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey()) {
            event.accept(ModBlocks.INFINITE_MOTOR_ITEM.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Create: Infinite is ready");
    }
}
