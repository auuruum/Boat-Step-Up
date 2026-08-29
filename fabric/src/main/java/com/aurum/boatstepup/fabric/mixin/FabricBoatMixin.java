package com.aurum.boatstepup.fabric.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBoatEntity.class)
public abstract class FabricBoatMixin {
    @Inject(method = "getNearbySlipperiness", at = @At("RETURN"), cancellable = true)
    private void fabric$boatstepup$ignoreCarpetFriction(CallbackInfoReturnable<Float> cir) {
        Entity boat = (Entity) (Object) this;
        BlockPos pos = boat.getSteppingPos();
        BlockState state = boat.getSteppingBlockState();
        World world = ((FabricEntityAccessor) (Object) boat).boatstepup$getWorld();
        if (state.isIn(BlockTags.WOOL_CARPETS) && world.getBlockState(pos.down()).isIn(BlockTags.ICE)) {
            cir.setReturnValue(0.98F);
        }
    }
}
