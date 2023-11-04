package net.phlawed.rpge.init;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModAttributes {

    public static EntityAttribute MANA;
    public static EntityAttribute MINING_SPEED;

    public static void register() {
        MINING_SPEED = Registry.register(Registries.ATTRIBUTE, new Identifier("rpge.attribute.mining_speed"), new ClampedEntityAttribute("Mining Speed", 4.0, 0.0, 1024.0).setTracked(true));
    }
}
