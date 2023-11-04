package net.phlawed.rpge.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.phlawed.rpge.Main;
import net.phlawed.rpge.init.ModData;
import net.phlawed.rpge.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Mixin(Block.class)
public class BlockMixin {

    public long time;

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static void onBreakDrops(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> drops = cir.getReturnValue();
        int level = EnchantmentHelper.getLevel(Main.SMELTING, stack);
        if(!(entity instanceof PlayerEntity)) {
            return;
        }

        if(level == 1 && drops.size() > 0) {
            Optional<SmeltingRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(drops.get(0)), world);

            if(recipe.isPresent()) {
                ItemStack smelted = recipe.get().getOutput(null);
                int amount = drops.get(0).getCount();
                smelted.setCount(amount);
                drops.clear();
                drops.add(smelted);
                cir.setReturnValue(drops);
            }
        }
    }
    @Inject(method = "onBreak", at =@At("TAIL"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {


        if(player.getMainHandStack().getItem() == ModItems.ANCIENT_TREE_AXE && state.isIn(BlockTags.LOGS)) {
            int mana = player.getDataTracker().get(ModData.MANA);

            if(state.isIn(BlockTags.LOGS)) {
                for (int x=0;x<4;x++) {
                    for (int y=0;y<40;y++) {
                        for(int z=0;z<4;z++) {
                            BlockPos shifted = new BlockPos(pos.getX() + (x-2), pos.getY() + (y-10), pos.getZ() + (z-2));
                            BlockState block = world.getBlockState(shifted);
                            if(block.isIn(BlockTags.LOGS)) {
                                world.breakBlock(shifted, true);
                            }
                        }
                    }
                }
            }
        }
    }
}
