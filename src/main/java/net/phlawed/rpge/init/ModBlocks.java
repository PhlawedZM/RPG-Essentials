package net.phlawed.rpge.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.phlawed.rpge.loot_blocks.*;
import net.phlawed.rpge.ores.RichGoldOreBlock;
import net.phlawed.rpge.ores.RichIronOreBlock;

public class ModBlocks {

    public static Block LOOT_BARREL = new LootBarrelBlock(FabricBlockSettings.copyOf(Blocks.CHEST).nonOpaque());
    public static Block COMMON_CHEST = new CommonChestBlock(FabricBlockSettings.copyOf(Blocks.CHEST).nonOpaque());
    public static Block ONYX_CHEST = new OnyxChestBlock(FabricBlockSettings.copyOf(Blocks.CHEST).nonOpaque());
    public static Block GOLD_CHEST = new GoldChestBlock(FabricBlockSettings.copyOf(Blocks.CHEST).nonOpaque());
    public static Block GRASS_CHEST = new GrassChestBlock(FabricBlockSettings.copyOf(Blocks.CHEST).nonOpaque());

    public static Block RICH_IRON_ORE = new RichIronOreBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(3.0F, 3.0F), UniformIntProvider.create(10,20));
    public static Block RICH_GOLD_ORE = new RichGoldOreBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(3.0F, 3.0F), UniformIntProvider.create(10,20));
    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier("rpge", "loot_barrel"), LOOT_BARREL);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "common_chest"), COMMON_CHEST);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "onyx_chest"), ONYX_CHEST);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "gold_chest"), GOLD_CHEST);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "grass_chest"), GRASS_CHEST);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "rich_iron_ore"), RICH_IRON_ORE);
        Registry.register(Registries.BLOCK, new Identifier("rpge", "rich_gold_ore"), RICH_GOLD_ORE);
    }

}
