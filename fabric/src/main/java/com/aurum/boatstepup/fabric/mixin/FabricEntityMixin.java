package com.aurum.boatstepup.fabric.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class FabricEntityMixin {
    @Inject(method = "getStepHeight", at = @At("HEAD"), cancellable = true)
    private void boatstepup$getStepHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof BoatEntity) {
            cir.setReturnValue(1.0F);
        }
    }
}
