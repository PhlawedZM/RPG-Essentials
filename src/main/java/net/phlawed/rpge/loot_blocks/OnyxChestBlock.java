package net.phlawed.rpge.loot_blocks;

import net.minecraft.block.BlockState;
import net.minecraft.particle.DustColorTransitionParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.stream.IntStream;

public class OnyxChestBlock extends CommonChestBlock{

    public OnyxChestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        for(int i = 0; i < 10; ++i) {
            world.addParticle(new DustParticleEffect(new Vector3f(0,0,0), 1.0F), (double)pos.getX() + random.nextFloat() * 1.1, (double)pos.getY() + random.nextFloat() * 1.1, (double)pos.getZ() + random.nextFloat() * 1.1, 0.0, 0.0, 0.0);
        }
    }


}
