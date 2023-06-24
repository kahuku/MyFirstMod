package com.kahuku.minecraft.tutorialmod.common.item;

import com.kahuku.minecraft.tutorialmod.TutorialMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PasterItem extends Item {
    public PasterItem(Properties properties) {
        super(properties);
    }

    public static boolean canInteract(Player player, BlockPos pos) {
        float speed = player.getDigSpeed(player.level.getBlockState(pos), pos);
        return player.isCreative() || player.mayBuild() && speed > 0 && speed < Float.MAX_VALUE;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        Level level = player.level;
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Direction face = context.getClickedFace();
        if (player.isCrouching()) {
            return InteractionResult.FAIL;
        }
        if (!canInteract(player, pos)) {
            return InteractionResult.FAIL;
        }
        if (!stack.getOrCreateTag().contains(TutorialMod.MODID, CompoundTag.TAG_COMPOUND)) {
            stack.getOrCreateTag().put(TutorialMod.MODID, new CompoundTag());
        }
        CompoundTag nbt = stack.getOrCreateTag().getCompound(TutorialMod.MODID);

        BlockPos targetPos = pos.relative(face);
        BlockState targetState = level.getBlockState(targetPos);

        if (!nbt.contains("ContainedBlock", CompoundTag.TAG_COMPOUND)) {
            if (!state.isAir()) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                nbt.put("ContainedBlock", NbtUtils.writeBlockState(state));
                return InteractionResult.SUCCESS;
            }
        }
        else if (state.canBeReplaced(new BlockPlaceContext(context))) {
            level.setBlockAndUpdate(pos, NbtUtils.readBlockState(nbt.getCompound("ContainedBlock")));
            nbt.remove("ContainedBlock");
            return InteractionResult.SUCCESS;
        }
        else if (targetState.canBeReplaced(new BlockPlaceContext(context))) {
            level.setBlockAndUpdate(targetPos, NbtUtils.readBlockState(nbt.getCompound("ContainedBlock")));
            nbt.remove("ContainedBlock");
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult result = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        BlockState state = level.getBlockState(result.getBlockPos());
        if (player.isCrouching()) {
            return InteractionResultHolder.fail(stack);
        }
        if (!canInteract(player, result.getBlockPos()) || !state.isAir() || !state.canBeReplaced(new BlockPlaceContext(player, hand, stack, result))) {
            return InteractionResultHolder.fail(stack);
        }
        if (!stack.getOrCreateTag().contains(TutorialMod.MODID, CompoundTag.TAG_COMPOUND)) {
            stack.getOrCreateTag().put(TutorialMod.MODID, new CompoundTag());
            return InteractionResultHolder.fail(stack);
        }
        CompoundTag nbt = stack.getOrCreateTag().getCompound(TutorialMod.MODID);
        if (!nbt.contains("ContainedBlock", CompoundTag.TAG_COMPOUND)) {
            return InteractionResultHolder.fail(stack);
        }

        BlockState toPlace = NbtUtils.readBlockState(nbt.getCompound("ContainedBlock"));
        level.setBlockAndUpdate(result.getBlockPos(), toPlace);
        nbt.remove("ContainedBlock");
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.contains(TutorialMod.MODID, CompoundTag.TAG_COMPOUND) && nbt.getCompound(TutorialMod.MODID).contains("ContainedBlock", CompoundTag.TAG_COMPOUND);
    }
}
