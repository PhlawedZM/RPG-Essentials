package net.phlawed.rpge.mobs.goals;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.phlawed.rpge.init.ModEntity;
import net.phlawed.rpge.mobs.ChampCreeper;

import java.util.function.Predicate;

public class TeleportGoal extends ActiveTargetGoal {


    public TeleportGoal(MobEntity mob, Class targetClass, boolean checkVisibility) {
        super(mob, targetClass, checkVisibility);
    }

    @Override
    public boolean canStart() {
        if(targetClass == PlayerEntity.class) {
            return true;
        }
        else {
            return false;
        }
    }


}
