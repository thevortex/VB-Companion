package com.allthemods.vb_companion.mixins;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(CreateWorldScreen.class)
public class MixinCreateWorldScreen {
    @Redirect(
            method = "openFresh",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private static ResourceKey<WorldPreset> changePreset() {
        return ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, new ResourceLocation("skyblockbuilder:skyblock"));
    }

    /*
    @ModifyArg(
            method = "openFresh",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/worldselection/WorldGenSettingsComponent;<init>(Lnet/minecraft/client/gui/screens/worldselection/WorldCreationContext;Ljava/util/Optional;Ljava/util/OptionalLong;)V"
            ),
            index = 1
    )
    private static Optional<ResourceKey<WorldPreset>> changeWorldGenSettings(Optional<ResourceKey<WorldPreset>> preset) {
        return Optional.of(ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, new ResourceLocation("skyblockbuilder:skyblock")));
    }*/
}
