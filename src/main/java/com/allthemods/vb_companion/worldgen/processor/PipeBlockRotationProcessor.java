package com.allthemods.vb_companion.worldgen.processor;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.*;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nullable;
import java.util.List;

public class PipeBlockRotationProcessor extends StructureProcessor {

    public static final Codec<PipeBlockRotationProcessor> CODEC =
            BlockState.CODEC.xmap(
                            BlockBehaviour.BlockStateBase::getBlock,
                            Block::defaultBlockState
                    )
                    .listOf()
                    .fieldOf("blocks")
                    .xmap(PipeBlockRotationProcessor::new,  (srp) -> {
                        return srp.blocks;
                    }).codec();
    
    private final ImmutableList<Block> blocks;
    private BlockState newState;

    public PipeBlockRotationProcessor(List<Block> block) {
        super();
        this.blocks = ImmutableList.copyOf(block);
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos pos1, BlockPos pos2, StructureTemplate.StructureBlockInfo rawInfo, StructureTemplate.StructureBlockInfo info, StructurePlaceSettings place, StructureTemplate template) {
        if (this.blocks.contains(info.state.getBlock())) {
            newState = info.state;
            for (Direction dir:Direction.values()) {
                Direction newDir = place.getRotation().rotate(dir);
                if (info.state.hasProperty(PipeBlock.PROPERTY_BY_DIRECTION.get(dir))) {
                    newState = newState.setValue(
                            PipeBlock.PROPERTY_BY_DIRECTION.get(newDir),
                            info.state.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get(dir))
                    );
                }
            }
            return new StructureTemplate.StructureBlockInfo(info.pos, newState, info.nbt);
        }
        return info;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorType.PipeBlockRotation.get();
    }
}
