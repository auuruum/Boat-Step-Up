package com.example.boatstepup;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("boatstepup")
public class BoatStepUpMod {

    public BoatStepUpMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityUpdate(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof Boat) {
            Boat boat = (Boat) event.getEntity();
            boat.maxUpStep = 1.0F; // Allow stepping up one full block
        }
    }
}