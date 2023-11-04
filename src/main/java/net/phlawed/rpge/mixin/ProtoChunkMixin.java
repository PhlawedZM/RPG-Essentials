package net.phlawed.rpge.mixin;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ProtoChunk;
import net.phlawed.rpge.init.ModBlocks;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ProtoChunk.class)
public abstract class ProtoChunkMixin {
    @Shadow @Nullable public abstract BlockState setBlockState(BlockPos pos, BlockState state, boolean moved);

    @Shadow @Nullable public abstract BlockEntity getBlockEntity(BlockPos pos);

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "setBlockState", at = @At("RETURN"))
    private void changeBlock(BlockPos pos, BlockState state, boolean moved, CallbackInfoReturnable<BlockState> cir) {
        if(state.getBlock() == Blocks.CHEST) {
            int onyx = new Random().nextInt(25);
            int gold = new Random().nextInt(15);
            Direction direction = state.get(Properties.HORIZONTAL_FACING);
            System.out.println(state);
            this.setBlockState(pos, ModBlocks.COMMON_CHEST.getDefaultState().with(Properties.FACING, direction), false);
            if(gold == 0) {
                this.setBlockState(pos, ModBlocks.GOLD_CHEST.getDefaultState().with(Properties.FACING, direction), false);
            }
            if(onyx == 0) {
                this.setBlockState(pos, ModBlocks.ONYX_CHEST.getDefaultState().with(Properties.FACING, direction), false);
            }
        }
        if(state.getBlock() == Blocks.BARREL) {
            Direction direction = state.get(Properties.FACING);
            System.out.println(state);
            this.setBlockState(pos, ModBlocks.LOOT_BARREL.getDefaultState().with(Properties.FACING, direction), false);
        }
    }
}
