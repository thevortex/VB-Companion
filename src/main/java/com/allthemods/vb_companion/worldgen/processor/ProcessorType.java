package com.allthemods.vb_companion.worldgen.processor;

import com.allthemods.vb_companion.VBAdditions;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ProcessorType {
    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, VBAdditions.MODID);

    public static final RegistryObject<StructureProcessorType<PipeBlockRotationProcessor>> PipeBlockRotation = PROCESSORS.register("pipe_rotation", () -> () -> PipeBlockRotationProcessor.CODEC);
}
