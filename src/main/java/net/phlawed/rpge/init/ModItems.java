package net.phlawed.rpge.init;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.items.AncientWoodAxe;

public class ModItems {

    public static Item ANCIENT_TREE_AXE;

    public static void register() {

        ANCIENT_TREE_AXE = Registry.register(Registries.ITEM, new Identifier("rpge", "ancient_wood_axe"), new AncientWoodAxe(ToolMaterials.WOOD, 1, -3.0F, new Item.Settings()));
    }

}
