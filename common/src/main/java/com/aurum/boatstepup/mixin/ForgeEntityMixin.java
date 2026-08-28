package com.aurum.boatstepup.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ForgeEntityMixin {
    @Inject(method = "maxUpStep", at = @At("HEAD"), cancellable = true)
    private void boatstepup$maxUpStep(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof Boat) {
            cir.setReturnValue(1.0F);
        }
    }
}
