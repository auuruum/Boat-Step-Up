package com.aurum.boatstepup.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBoat.class)
public abstract class ForgeBoatMixin {
    @Inject(method = "getGroundFriction", at = @At("RETURN"), cancellable = true)
    private void boatstepup$ignoreCarpetFriction(CallbackInfoReturnable<Float> cir) {
        AbstractBoat boat = (AbstractBoat) (Object) this;
        BlockPos pos = boat.getOnPos();
        BlockState state = boat.level().getBlockState(pos);
        if (state.is(BlockTags.WOOL_CARPETS) && boat.level().getBlockState(pos.below()).is(BlockTags.ICE)) {
            cir.setReturnValue(0.98F);
        }
    }
}
