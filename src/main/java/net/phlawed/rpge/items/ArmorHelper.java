package net.phlawed.rpge.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ArmorHelper {

    public static int getMaxMana(PlayerEntity player) {
        ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack weapon = player.getEquippedStack(EquipmentSlot.MAINHAND);
        ItemStack offhand = player.getEquippedStack(EquipmentSlot.OFFHAND);
        int amount = 1;
        if(head.getNbt() != null && head.getNbt().contains("Mana")) {
            amount = amount + head.getNbt().getInt("Mana");
        }
        if(chest.getNbt() != null && chest.getNbt().contains("Mana")) {
            amount = amount + chest.getNbt().getInt("Mana");
        }
        if(legs.getNbt() != null && legs.getNbt().contains("Mana")) {
            amount = amount + legs.getNbt().getInt("Mana");
        }
        if(feet.getNbt() != null && feet.getNbt().contains("Mana")) {
            amount = amount + feet.getNbt().getInt("Mana");
        }
        if(weapon.getNbt() != null && weapon.getNbt().contains("Mana")) {
            amount = amount + weapon.getNbt().getInt("Mana");
        }
        if(offhand.getNbt() != null && offhand.getNbt().contains("Mana")) {
            amount = amount + offhand.getNbt().getInt("Mana");
        }
        return amount;
    }

    public int getMana(ItemStack stack) {
        if(stack.getNbt() != null && stack.getNbt().contains("Mana")) {
            return stack.getNbt().getInt("Mana");
        }
        return 0;
    }
}
