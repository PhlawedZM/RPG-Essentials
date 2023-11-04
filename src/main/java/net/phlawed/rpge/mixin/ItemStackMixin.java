package net.phlawed.rpge.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();
    @Shadow @Nullable public abstract NbtCompound getNbt();

    @Shadow public abstract boolean hasNbt();

    @Shadow public abstract NbtList getEnchantments();

    @Shadow public abstract Text getName();

    @Shadow public abstract ItemStack copy();

    @Shadow public abstract boolean hasEnchantments();

    @Shadow public abstract Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot);

    private static EntityAttributeModifier HEALTH_2 = new EntityAttributeModifier("Health_2", 2, EntityAttributeModifier.Operation.ADDITION);
    private static EntityAttributeModifier SPEED_20 = new EntityAttributeModifier("Speed_20", 2000, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private void getToolTip(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {
        Item item = this.getItem();
        Multimap<EntityAttribute, EntityAttributeModifier> map = cir.getReturnValue();
        map.clear();
        cir.setReturnValue(map);
    }
    @Inject(method = "setNbt", at = @At("TAIL"))
    private void setNBT(NbtCompound nbt, CallbackInfo ci) {

    }

    @Inject(method = "onCraft", at = @At("TAIL"))
    private void onCraft(World world, PlayerEntity player, int amount, CallbackInfo ci) {

    }

    @Inject(method = "getTooltip", at = @At("TAIL"), cancellable = true)
    private void tooltip(PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        List<Text> list = new ArrayList<>();

        if(this.hasEnchantments()) {
            list.add(Text.empty().append(this.getName()).formatted(Formatting.BLUE));
        }
        else {
            list.add(Text.empty().append(this.getName()).formatted(Formatting.GRAY));
        }

        if(this.hasNbt()) {

            if(this.getItem() instanceof MiningToolItem || this.getItem() instanceof SwordItem) {
                int damage = 0;

                if(this.getItem() instanceof MiningToolItem) {
                    damage = (int) ((MiningToolItem) this.getItem()).getAttackDamage();
                }

                if(this.getItem() instanceof SwordItem) {
                    damage = (int) ((SwordItem) this.getItem()).getAttackDamage();
                }

                if(player != null) {
                    float sharp = EnchantmentHelper.getAttackDamage(this.copy(), player.getGroup());
                    int damage_final = (int) (damage + (damage * sharp));

                    list.add(Text.literal("Damage: ").formatted(Formatting.WHITE).append(Text.literal("+" + damage_final).formatted(Formatting.RED)));
                }
            }

            if(this.getItem() instanceof ArmorItem) {
                int armor = ((ArmorItem) this.getItem()).getProtection();
                int resistance = (int) ((ArmorItem) this.getItem()).getToughness();

                list.add(Text.literal("Armor: ").formatted(Formatting.WHITE).append(Text.literal("+" + armor).formatted(Formatting.RED)));
                list.add(Text.literal("Damage Resistance: ").formatted(Formatting.WHITE).append(Text.literal("+" + resistance + "%").formatted(Formatting.RED)));
            }

            if(this.hasEnchantments()) {
                list.add(Text.empty());
                list.add(Text.literal("Enchantments").formatted(Formatting.LIGHT_PURPLE));
            }

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(this.copy());
            enchantments.forEach((enchantment, integer) -> {
                list.add(Text.empty().append(enchantment.getName(integer)));
            });
        }


        cir.setReturnValue(list);
    }
}

