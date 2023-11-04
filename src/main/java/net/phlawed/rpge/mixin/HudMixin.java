package net.phlawed.rpge.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.init.ModAttributes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.phlawed.rpge.init.ModData.MANA;

@Mixin(InGameHud.class)
public abstract class HudMixin {

    @Shadow private int scaledHeight;
    @Shadow private int scaledWidth;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow @Final private static Identifier ICONS;
    public Identifier mana = new Identifier("rpge", "textures/gui/mana.png");
    public Identifier mana_l = new Identifier("rpge", "textures/gui/mana_l.png");

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {

        //MOJANK WTF IS THIS MAF
        //MOJANK WTF IS THIS MAF
        //MOJANK WTF IS THIS MAF

        for(int i = 0; i < 10; i++) {
            if(this.getCameraPlayer() != null && !(this.getCameraPlayer().isSubmergedInWater()) && !(this.getCameraPlayer().isSpectator()) && !(this.getCameraPlayer().isCreative())) {
                PlayerEntity player = this.getCameraPlayer();
                int value = player.getDataTracker().get(MANA);


                if(value > i) {
                    context.drawTexture(mana,(this.scaledWidth/2 - 91) + (100 + (i*8)),this.scaledHeight-49,0,0,0,9,9,9,9);
                }
                else {
                    context.drawTexture(mana_l,(this.scaledWidth/2 - 91) + (100 + (i*8)),this.scaledHeight-49,0,0,0,9,9,9,9);
                }
            }
        }
    }
}
