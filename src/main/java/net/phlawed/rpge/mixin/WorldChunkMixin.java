package net.phlawed.rpge.mixin;

import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.structure.Structure;
import net.phlawed.rpge.loot_blocks.CommonChestBlock;
import org.joml.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.Set;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin {


    @Shadow @Final private World world;

    @Shadow public abstract World getWorld();

    @Inject(method = "setBlockEntity", at = @At("HEAD"))
    private void genEntity(BlockEntity entity, CallbackInfo ci) {
        if(entity.getCachedState().getBlock() instanceof CommonChestBlock) {
            if(world.getRegistryKey() == World.OVERWORLD) {
            }
            BarrelBlockEntity chest = (BarrelBlockEntity) entity;
            NbtCompound nbt = entity.createNbt();
            if(nbt.contains("LootTable")) {
                chest.setCustomName(Text.translatable("text.rpge.loot"));
                chest.setLootTable(new Identifier("minecraft:chests/end_city_treasure"), Random.newSeed());
            }
        }
    }
}
