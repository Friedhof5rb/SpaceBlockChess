package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        if(player.isSpectator()){
            return ActionResult.PASS;
        }





        if(world.isClient()) {
            return ActionResult.PASS;
        }

        if(entity instanceof ZombieEntity && world.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)){
          if(((ZombieEntity) entity).getHealth() < 1) {
              Item item = ModItems.CHESS_CORE;
              ItemStack stack = new ItemStack(item);
              ItemEntity e = new ItemEntity(world,entity.getX(),entity.getY(),entity.getZ(),stack);
              world.spawnEntity(e);
          }
        }



        if (world.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            return ActionResult.PASS;
        }
        if(player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_REMOVAL){
            return ActionResult.PASS;
        }
        Item[] items = Chess.combineLists();
        if(entity instanceof ItemFrameEntity){
            ItemFrameEntity e = (ItemFrameEntity) entity;
            if(Chess.arrayContains(items, e.getHeldItemStack().getItem() )){
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;

    }






}
