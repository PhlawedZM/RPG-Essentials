package net.phlawed.rpge.ores;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class RichIronOreBlock extends ExperienceDroppingBlock {
    public RichIronOreBlock(Settings settings, IntProvider experience) {
        super(settings, experience);
    }
}
