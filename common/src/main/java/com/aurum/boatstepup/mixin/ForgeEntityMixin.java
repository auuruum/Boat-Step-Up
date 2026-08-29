package com.aurum.boatstepup.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ForgeEntityMixin {
    @Inject(method = "maxUpStep", at = @At("HEAD"), cancellable = true)
    private void boatstepup$maxUpStep(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof AbstractBoat && boatstepup$isIceSurface((Entity) (Object) this)) {
            cir.setReturnValue(1.0F);
        }
    }

    private static boolean boatstepup$isIceSurface(Entity entity) {
        BlockPos pos = entity.getOnPos();
        BlockState state = entity.level().getBlockState(pos);
        return state.is(BlockTags.ICE)
            || state.is(BlockTags.WOOL_CARPETS) && entity.level().getBlockState(pos.below()).is(BlockTags.ICE);
    }
}
