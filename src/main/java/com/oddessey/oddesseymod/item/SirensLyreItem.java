package com.example.odysseymod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SirensLyreItem extends Item {

    public SirensLyreItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide) {

            double radius = 5;
            Vec3 playerPos = player.position();

            List<LivingEntity> mobs = level.getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(
                            playerPos.x - radius, playerPos.y - radius, playerPos.z - radius,
                            playerPos.x + radius, playerPos.y + radius, playerPos.z + radius
                    ),
                    e -> e != player
            );

            for (LivingEntity mob : mobs) {
                Vec3 knock = mob.position().subtract(playerPos).normalize().scale(0.8);
                mob.push(knock.x, 0.3, knock.z);
            }

            level.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.NOTE_BLOCK_HARP,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

        player.swing(hand);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
