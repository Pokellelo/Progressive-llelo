package com.progressivellelo;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerDestroyItemEvent;

@Mod("progressivellelo")

public class Progressivellelo {

    public Progressivellelo(IEventBus bus){
        NeoForge.EVENT_BUS.addListener(Progressivellelo::doSomething);
    }


    private static void doSomething(PlayerDestroyItemEvent event){
        net.minecraft.world.entity.Entity entity = event.getEntity();

        // Only heal on the server side
        if (!entity.level().isClientSide()) {
            entity.kill();
        }

    }
}
