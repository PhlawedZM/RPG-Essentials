package net.phlawed.rpge.init;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class ModData {
    public static TrackedData<Integer> MANA;
    public static TrackedData<Integer> MAX_MANA;
    public static TrackedData<Integer> MANA_REGEN;
    public static void register() {
        MANA = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
        MAX_MANA = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
        MANA_REGEN = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
