package net.phlawed.rpge.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;
import net.phlawed.rpge.init.ModAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract float getHealth();

    @Shadow protected abstract void applyDamage(DamageSource source, float amount);

    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    @Shadow public abstract void setHealth(float health);

    @Shadow @Final public int defaultMaxHealth;

    @Inject(method = "damage", at = @At("RETURN"))
    public void onDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.getAttacker() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();

        }

    }

    @Inject(method = "onEquipStack", at = @At("TAIL"))
    public void onEquip(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        if(!(newStack.isEmpty())) {
        }

    }

    @Inject(method = "<init>" , at = @At("TAIL"))
    public void init(EntityType entityType, World world, CallbackInfo ci) {
        if(entityType != EntityType.PLAYER) {
            EntityAttributeInstance attribute = this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            if (attribute != null) {
                if (attribute.getValue() < 100) {
                    attribute.setBaseValue(attribute.getValue() * 3);
                    this.setHealth(this.defaultMaxHealth*3);
                }

            }
        }
    }
}
