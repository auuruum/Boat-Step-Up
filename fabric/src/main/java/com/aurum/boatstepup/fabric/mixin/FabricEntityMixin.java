package com.aurum.boatstepup.fabric.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class FabricEntityMixin {
    @Inject(method = "getStepHeight", at = @At("HEAD"), cancellable = true)
    private void boatstepup$getStepHeight(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof AbstractBoatEntity && fabric$boatstepup$isIceSurface((Entity) (Object) this)) {
            cir.setReturnValue(1.0F);
        }
    }

    private static boolean fabric$boatstepup$isIceSurface(Entity entity) {
        BlockPos pos = entity.getSteppingPos();
        BlockState state = entity.getSteppingBlockState();
        World world = ((FabricEntityAccessor) (Object) entity).boatstepup$getWorld();
        return state.isIn(BlockTags.ICE)
            || state.isIn(BlockTags.WOOL_CARPETS) && world.getBlockState(pos.down()).isIn(BlockTags.ICE);
    }
}
