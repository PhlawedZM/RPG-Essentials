package net.phlawed.rpge.mixin;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.*;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.phlawed.rpge.init.ModItems;
import org.jetbrains.annotations.Nullable;
import org.joml.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LootableContainerBlockEntity.class)
public abstract class LootContainerMixin {

    @Shadow @Nullable protected Identifier lootTableId;

    @Shadow public abstract void setLootTable(Identifier id, long seed);

    @Shadow public abstract void setStack(int slot, ItemStack stack);

    @Inject(method = "setStack", at = @At("HEAD"))
    public void upgradeEnchantment(int slot, ItemStack stack, CallbackInfo ci) {
        if(stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            enchantments.forEach((enchantment, integer) -> {
                if(enchantment.getMaxLevel() > 1) {
                    enchantments.put(enchantment, enchantment.getMaxLevel() + 1);
                }
            });

            EnchantmentHelper.set(enchantments, stack);
        }
    }

    @Inject(method = "setStack", at = @At("RETURN"))
    public void upgradeStack(int slot, ItemStack stack, CallbackInfo ci) {
        if(stack.isIn(ItemTags.TRIMMABLE_ARMOR) && stack.getNbt() != null && !(stack.getNbt().contains("Trim"))) {
            System.out.println(ArmorTrimPatterns.DUNE.getValue().getNamespace());
            NbtCompound nbt = stack.getOrCreateSubNbt("Trim");
            nbt.putString("material","minecraft:" + ArmorTrimMaterials.GOLD.getValue().getPath());
            nbt.putString("pattern","minecraft:" + ArmorTrimPatterns.DUNE.getValue().getPath());
        }
        if(stack.getNbt() != null && !(stack.getNbt().contains("Mana")) && stack.getItem() instanceof ArmorItem) {
            NbtCompound nbt = stack.getNbt();
            nbt.putInt("Mana", 1);
        }
        if(stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            enchantments.forEach((enchantment, integer) -> {
                if(enchantment.getMaxLevel() > 1) {
                    enchantments.put(enchantment, enchantment.getMaxLevel() + 1);
                }
            });
        }
    }


    @Inject(method = "checkLootInteraction", at = @At("HEAD"))
    public void setTier(@Nullable PlayerEntity player, CallbackInfo ci) {
        if (this.lootTableId != null && player instanceof ServerPlayerEntity && player.getServer() != null) {
            ServerPlayerEntity splayer = (ServerPlayerEntity) player;
            splayer.sendMessage(Text.literal(splayer.getEntityName()));
            //((ServerPlayerEntity) player).server.getDataPackManager().getEnabledNames();
            //this.setLootTable(LootTables.VILLAGE_FISHER_CHEST, Random.newSeed());
        }
    }
}
