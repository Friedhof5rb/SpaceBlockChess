package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
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


    private static Item[] items = {ModItems.BLACK_BISHOP,ModItems.BLACK_KING,ModItems.BLACK_KNIGHT,ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER,
            ModItems.WHITE_BISHOP,ModItems.WHITE_KING,ModItems.WHITE_KNIGHT,ModItems.WHITE_PAWN,ModItems.WHITE_QUEEN,ModItems.WHITE_TOWER,
            ModItems.CAPTURE_BLACK_BISHOP,ModItems.CAPTURE_BLACK_KING,ModItems.CAPTURE_BLACK_KNIGHT,ModItems.CAPTURE_BLACK_PAWN,ModItems.CAPTURE_BLACK_QUEEN,ModItems.CAPTURE_BLACK_TOWER,
            ModItems.CAPTURE_WHITE_BISHOP,ModItems.CAPTURE_WHITE_KING,ModItems.CAPTURE_WHITE_KNIGHT,ModItems.CAPTURE_WHITE_PAWN,ModItems.CAPTURE_WHITE_QUEEN,ModItems.CAPTURE_WHITE_TOWER,ModItems.MOVE_HIGHLIGHTER,
            ModItems.SELECTED_WHITE_BISHOP, ModItems.SELECTED_WHITE_KING, ModItems.SELECTED_WHITE_KNIGHT, ModItems.SELECTED_WHITE_PAWN, ModItems.SELECTED_WHITE_QUEEN, ModItems.SELECTED_WHITE_TOWER,
            ModItems.SELECTED_BLACK_BISHOP, ModItems.SELECTED_BLACK_KING, ModItems.SELECTED_BLACK_KNIGHT, ModItems.SELECTED_BLACK_PAWN, ModItems.SELECTED_BLACK_QUEEN, ModItems.SELECTED_BLACK_TOWER,
            ModItems.START_WHITE_PAWN,ModItems.START_BLACK_PAWN,ModItems.START_SELECTED_BLACK_PAWN,ModItems.START_SELECTED_WHITE_PAWN,ModItems.START_CAPTURE_BLACK_PAWN,ModItems.START_CAPTURE_WHITE_PAWN,
            ModItems.CASTLE_WHITE_TOWER, ModItems.CASTLE_BLACK_TOWER, ModItems.CASTLE_WHITE_KING, ModItems.CASTLE_BLACK_KING, ModItems.CASTLE_SWITCH_BLACK_TOWER, ModItems.CASTLE_SWITCH_WHITE_TOWER,
            ModItems.CASTLE_CAPTURE_WHITE_TOWER,ModItems.CASTLE_CAPTURE_BLACK_TOWER,ModItems.CASTLE_CAPTURE_WHITE_KING,ModItems.CASTLE_CAPTURE_BLACK_KING,
            ModItems.CASTLE_SELECTED_WHITE_TOWER, ModItems.CASTLE_SELECTED_BLACK_TOWER, ModItems.CASTLE_SELECTED_WHITE_KING, ModItems.CASTLE_SELECTED_BLACK_KING};

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        if(player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_REMOVAL) {
            return ActionResult.PASS;
        }

        List list = world.getEntitiesByType(EntityType.ITEM_FRAME,new Box(pos.getX()-2,pos.getY()-2, pos.getZ()-2,pos.getX()+2,pos.getY()+2, pos.getZ()+2), EntityPredicates.VALID_ENTITY);

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
