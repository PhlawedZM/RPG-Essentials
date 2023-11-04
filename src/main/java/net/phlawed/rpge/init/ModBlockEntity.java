package net.phlawed.rpge.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.loot_blocks.CommonChestBlockEntity;
import net.phlawed.rpge.loot_blocks.LootBarrelBlockEntity;

public class ModBlockEntity {

    public static BlockEntityType<LootBarrelBlockEntity> LOOT_BARREL_BLOCK_ENTITY;
    public static BlockEntityType<CommonChestBlockEntity> COMMON_CHEST_BLOCK_ENTITY;
    public static void register() {
        LOOT_BARREL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("rpge", "loot_barrel_entity"), BlockEntityType.Builder.create(LootBarrelBlockEntity::new, ModBlocks.LOOT_BARREL).build(null));
        COMMON_CHEST_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("rpge", "common_chest_entity"), BlockEntityType.Builder.create(CommonChestBlockEntity::new, ModBlocks.COMMON_CHEST).build(null));
    }

}
