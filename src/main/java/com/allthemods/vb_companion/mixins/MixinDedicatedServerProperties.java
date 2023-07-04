package com.allthemods.vb_companion.mixins;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DedicatedServerProperties.class)
public class MixinDedicatedServerProperties {

    @Redirect(
            method = "<init>",
        at = @At(
                value = "FIELD",
                target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;",
                opcode = Opcodes.GETSTATIC
        )
    )
    private ResourceKey<WorldPreset> changePreset() {
        return ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, new ResourceLocation("skyblockbuilder:skyblock"));
    }

    @Mixin(DedicatedServerProperties.WorldGenProperties.class)
    private static class MixinWorldGenProperties {
        @Redirect(
                method = "create",
                at = @At(
                        value = "FIELD",
                        target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;",
                        opcode = Opcodes.GETSTATIC
                )
        )
        private ResourceKey<WorldPreset> changePreset() {
            return ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, new ResourceLocation("skyblockbuilder:skyblock"));
        }
    }
}
