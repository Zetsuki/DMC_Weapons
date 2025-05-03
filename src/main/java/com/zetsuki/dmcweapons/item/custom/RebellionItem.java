package com.zetsuki.dmcweapons.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

public class RebellionItem extends Item {
    public RebellionItem(Properties pProperties) { super(pProperties); }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack p_43417_) {
        return ItemUseAnimation.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack p_40680_, LivingEntity p_344246_) {
        return 72000;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useTime = this.getUseDuration(stack, entity) - timeLeft;
            if (useTime < 10 || !player.onGround()) {
                return false;
            }

            float yaw = player.getYRot();
            float pitch = player.getXRot();

            float xDir = -Mth.sin(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));
            float yDir = -Mth.sin(pitch * ((float) Math.PI / 180F));
            float zDir = Mth.cos(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));

            float magnitude = Mth.sqrt(xDir * xDir + yDir * yDir + zDir * zDir);
            xDir /= magnitude;
            yDir /= magnitude;
            zDir /= magnitude;

            float maxAllowedPitch = 50.0F;
            float angleFactor = 1.0f - (Math.abs(pitch)/maxAllowedPitch);
            angleFactor = Mth.clamp(angleFactor, 0.0f, 1.0f);
            float dashPower = 6.0F * angleFactor;

            player.push(xDir * dashPower, yDir * dashPower, zDir * dashPower);

            level.playSound(null, player, SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.PLAYERS, 1.0F, 1.0F);

            player.getCooldowns().addCooldown(stack, 50);

            return true;
        }

        return false;
    }


    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
}
