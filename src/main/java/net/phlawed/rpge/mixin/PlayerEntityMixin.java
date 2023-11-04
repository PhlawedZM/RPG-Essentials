package net.phlawed.rpge.mixin;

import com.google.gson.internal.reflect.ReflectionHelper;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.phlawed.rpge.init.ModAttributes;
import net.phlawed.rpge.init.ModData;
import net.phlawed.rpge.items.ArmorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.phlawed.rpge.init.ModData.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    public int mana_count = 0;
    public int count = 0;
    public boolean mana_used;

    @Shadow @Final private PlayerInventory inventory;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean canHarvest(BlockState state);

    @Shadow protected abstract void initDataTracker();

    @Shadow public abstract PlayerInventory getInventory();

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"))
    public void mining(BlockState block, CallbackInfoReturnable<Float> cir) {
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void setMana(CallbackInfo ci) {
        this.dataTracker.startTracking(MANA, 1);
        this.dataTracker.startTracking(MANA_REGEN, 80);
        this.dataTracker.startTracking(MAX_MANA, 1);
    }

    @Inject(method="tick", at= @At("TAIL"))
    public void tick(CallbackInfo ci) {
        PlayerEntity player = inventory.player;
        int mana = player.getDataTracker().get(MANA);
        int max_mana = player.getDataTracker().get(MAX_MANA);
        int mana_regen = player.getDataTracker().get(MANA_REGEN);

        if(mana > ArmorHelper.getMaxMana(player)) {
            player.getDataTracker().set(MANA,ArmorHelper.getMaxMana(player));
        }
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    public void tickMovement(CallbackInfo ci) {
        PlayerEntity player = inventory.player;
        int mana = player.getDataTracker().get(MANA);
        int max_mana = player.getDataTracker().get(MAX_MANA);
        int mana_regen = player.getDataTracker().get(MANA_REGEN);


        if(mana != ArmorHelper.getMaxMana(player) && player.getWorld().isClient()) {
            mana_count++;
            if(mana_count > mana_regen) {
                mana_count = 0;
                player.getDataTracker().set(MANA,mana + 1);
            }
        }
    }
}
