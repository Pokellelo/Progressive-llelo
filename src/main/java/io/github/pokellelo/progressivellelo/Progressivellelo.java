package io.github.pokellelo.progressivellelo;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.network.chat.Component;

@Mod(Progressivellelo.MOD_ID)
public class Progressivellelo {
    
    public static final String MOD_ID = "progressivellelo";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // List of events: https://forge.gemwire.uk/wiki/Events
    public Progressivellelo(IEventBus bus) {
        NeoForge.EVENT_BUS.addListener(Progressivellelo::doSomething);
        // NeoForge.EVENT_BUS.addListener(Progressivellelo::myLoot);
    }

    private static void doSomething(EntityPlaceEvent event) {
        net.minecraft.world.entity.Entity entity = event.getEntity();
        // Only heal on the server side
        if (!entity.level().isClientSide()) {
            entity.kill();
        }

    }
    // private static void myLoot(GatherDataEvent event){
    // }

     public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(MOD_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MOD_ID)) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //.icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
            //   output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

}
