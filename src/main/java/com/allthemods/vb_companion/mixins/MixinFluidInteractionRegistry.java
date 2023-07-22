package com.allthemods.vb_companion.mixins;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FluidInteractionRegistry.class)
public class MixinFluidInteractionRegistry {
    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/FluidInteractionRegistry;addInteraction(Lnet/minecraftforge/fluids/FluidType;Lnet/minecraftforge/fluids/FluidInteractionRegistry$InteractionInformation;)V",
                    ordinal = 0
            ),
            index = 1
    )
    private static FluidInteractionRegistry.InteractionInformation modifyLavaWater(FluidType fluidType, FluidInteractionRegistry.InteractionInformation info) {
        return new FluidInteractionRegistry.InteractionInformation(
                ((level, currentPos, relativePos, currentState) -> level.dimension() != Level.OVERWORLD && level.getFluidState(relativePos).getFluidType() == ForgeMod.WATER_TYPE.get()),
                fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState()
        );
    }
}
