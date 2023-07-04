package com.allthemods.vb_companion.mixins;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldPresets.class)
public class MixinWorldPresets {
    @Redirect(
            method = "createNormalWorldFromPreset(Lnet/minecraft/core/RegistryAccess;JZZ)Lnet/minecraft/world/level/levelgen/WorldGenSettings;",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private static ResourceKey<WorldPreset> changePreset() {
        return ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, new ResourceLocation("skyblockbuilder:skyblock"));
    }
}
