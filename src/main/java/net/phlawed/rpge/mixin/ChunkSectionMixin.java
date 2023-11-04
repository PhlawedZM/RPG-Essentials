package net.phlawed.rpge.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkSection;
import net.phlawed.rpge.config.Config;
import net.phlawed.rpge.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.util.Random;

@Mixin(ChunkSection.class)
public abstract class ChunkSectionMixin {

    @Shadow public abstract BlockState setBlockState(int x, int y, int z, BlockState state, boolean lock);

    @Shadow public abstract BlockState getBlockState(int x, int y, int z);

    @Inject(method = "setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", at = @At("TAIL"))
    public void setOre(int x, int y, int z, BlockState state, boolean lock, CallbackInfoReturnable<BlockState> cir) {
        boolean gen = Config.getBoolean("RichOres");

        if(state.getBlock() == Blocks.IRON_ORE && gen) {
            int rand = new Random().nextInt(20);
            if (rand == 1) {
                this.setBlockState(x,y,z, ModBlocks.RICH_IRON_ORE.getDefaultState(), false);
                System.out.println(new BlockPos(x,y,z));
            }
        }
        if(state.getBlock() == Blocks.GOLD_ORE) {
            int rand = new Random().nextInt(20);
            if (rand == 1) {
                this.setBlockState(x,y,z, ModBlocks.RICH_GOLD_ORE.getDefaultState(), false);
            }
        }
    }
}
