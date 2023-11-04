package net.phlawed.rpge.mixin;

import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.mobs.NPCEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieBaseEntityRenderer.class)
public class NPCMixin {

    private static final Identifier STEVE = new Identifier("rpge","textures/entity/npc/steve.png");

    @Inject(method = "getTexture(Lnet/minecraft/entity/mob/ZombieEntity;)Lnet/minecraft/util/Identifier;", at = @At("TAIL"), cancellable = true)
    public void getTexture(ZombieEntity zombie, CallbackInfoReturnable<Identifier> cir) {
        if(zombie instanceof NPCEntity) {
            cir.setReturnValue(STEVE);
        }

    }
}
