package com.allthemods.vb_companion.worldgen;

import com.allthemods.vb_companion.VBAdditions;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Structures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, VBAdditions.MODID);

    public static final RegistryObject<StructureType<ShipDungeon>> ShipsDungeon = STRUCTURES.register("shipdungeon1", () -> () -> ShipDungeon.CODEC);
    public static final RegistryObject<StructureType<Crane>> PBCrane = STRUCTURES.register("pb_crane", () -> () -> Crane.CODEC);
    public static final RegistryObject<StructureType<Crates>> PBCrates = STRUCTURES.register("pb_crates", () -> () -> Crates.CODEC);
    public static final RegistryObject<StructureType<Refinery>> PBRefinery = STRUCTURES.register("pb_refinery", () -> () -> Refinery.CODEC);
    public static final RegistryObject<StructureType<Ship>> PBShip = STRUCTURES.register("pb_ship", () -> () -> Ship.CODEC);
    public static final RegistryObject<StructureType<Submarine>> PBSubmarine = STRUCTURES.register("pb_submarine", () -> () -> Submarine.CODEC);
    public static final RegistryObject<StructureType<Train>> PBTrain = STRUCTURES.register("pb_train", () -> () -> Train.CODEC);


}
