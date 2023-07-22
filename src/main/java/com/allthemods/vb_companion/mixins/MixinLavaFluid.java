package com.allthemods.vb_companion.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public class MixinLavaFluid {
    @Inject(
            method = "canBeReplacedWith",
            at = @At("RETURN"),
            cancellable = true
    )
    private void onReplaced(FluidState state, BlockGetter getter, BlockPos pos, Fluid fluid, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if (dir == Direction.DOWN) {
            cir.setReturnValue(false);
        }
    }
}
