package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class AttackBlockHandler implements AttackBlockCallback {


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {

        if(player.isSpectator()){
            return ActionResult.PASS;
        }



        if(world.isClient()) {
            return ActionResult.PASS;
        }


        if (world.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            return ActionResult.PASS;
        }

        if(player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_REMOVAL){
            return ActionResult.PASS;
        }

        List list = world.getEntitiesByType(EntityType.ITEM_FRAME,new Box(pos.getX()-2,pos.getY()-2, pos.getZ()-2,pos.getX()+2,pos.getY()+2, pos.getZ()+2), EntityPredicates.VALID_ENTITY);
        Item[] items = Chess.combineLists();
        for(Object object : list){
            if(object instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) object;
                if(Chess.arrayContains(items, entity.getHeldItemStack().getItem() )) {
                    if (entity.getBlockPos().getX() == pos.getX() &&entity.getBlockPos().getZ() == pos.getZ() && entity.getBlockPos().getY() == pos.getY() - 1 && entity.getHorizontalFacing() == Direction.DOWN) {
                        return ActionResult.FAIL;
                    }
                    if (entity.getBlockPos().getX() == pos.getX() &&entity.getBlockPos().getZ() == pos.getZ() &&entity.getBlockPos().getY() == pos.getY() + 1 && entity.getHorizontalFacing() == Direction.UP) {
                        return ActionResult.FAIL;
                    }
                    if (entity.getBlockPos().getY() == pos.getY() &&entity.getBlockPos().getZ() == pos.getZ() &&entity.getBlockPos().getX() == pos.getX() - 1 && entity.getHorizontalFacing() == Direction.WEST) {
                        return ActionResult.FAIL;
                    }
                    if (entity.getBlockPos().getY() == pos.getY() &&entity.getBlockPos().getZ() == pos.getZ() &&entity.getBlockPos().getX() == pos.getX() + 1 && entity.getHorizontalFacing() == Direction.EAST) {
                        return ActionResult.FAIL;
                    }
                    if (entity.getBlockPos().getX() == pos.getX() &&entity.getBlockPos().getY() == pos.getY() &&entity.getBlockPos().getZ() == pos.getZ() - 1 && entity.getHorizontalFacing() == Direction.NORTH) {
                        return ActionResult.FAIL;
                    }
                    if (entity.getBlockPos().getX() == pos.getX() &&entity.getBlockPos().getY() == pos.getY() &&entity.getBlockPos().getZ() == pos.getZ() + 1 && entity.getHorizontalFacing() == Direction.SOUTH) {
                        return ActionResult.FAIL;
                    }
                }
            }
        }
        return ActionResult.PASS;
    }



}
