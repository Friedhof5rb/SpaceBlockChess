package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.block.Material;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class MovementCalculations {

    public static GlobalChessData moveOneInDirection(World w, GlobalChessData data, Direction forwardNorth) {

        if(data == null){
            return null;
        }


        Direction absolute = RotationCalculations.relativeToAbsolute(data, forwardNorth);
        BlockPos sameBlock = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ());
        BlockPos attachedBlock = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ()).offset(data.directionWall.getOpposite(), 1);
        BlockPos nextTo = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ()).offset(absolute,1);
        BlockPos diagonal = new BlockPos(attachedBlock.getX(), attachedBlock.getY(), attachedBlock.getZ()).offset(absolute,1);

        //innenkanten
        if(w.getBlockState(nextTo).getMaterial() != Material.AIR){

            if(w.getBlockState(nextTo).getMaterial() == Material.WATER || w.getBlockState(nextTo).getMaterial() == Material.LAVA || w.getBlockState(nextTo).getMaterial() == Material.GLASS){
                return null;
            }

            int newItemRotation = RotationCalculations.correctRotations(data.itemRotation,data.directionWall,absolute);
             if(data.directionWall == Direction.UP || data.directionWall == Direction.DOWN  || data.directionWall == Direction.EAST ||  data.directionWall == Direction.WEST){
                newItemRotation = (newItemRotation + 4) % 8;
            }

            GlobalChessData newPosition = new GlobalChessData(sameBlock,absolute.getOpposite(),newItemRotation,false);

            return newPosition;

            //glatt
        }else if(w.getBlockState(diagonal).getMaterial() != Material.AIR){
            if(w.getBlockState(diagonal).getMaterial() == Material.WATER || w.getBlockState(diagonal).getMaterial() == Material.LAVA|| w.getBlockState(diagonal).getMaterial() == Material.GLASS){
                return null;
            }
            GlobalChessData newPosition = new GlobalChessData(nextTo,data.directionWall, data.itemRotation, false);

            return newPosition;

            //außenkanten
        }else{



            GlobalChessData newPosition = new GlobalChessData(diagonal,absolute,RotationCalculations.correctRotations(data.itemRotation,data.directionWall, absolute),false);

            return newPosition;
        }





    }

    public static ItemFrameEntity dataToFigureWithDamage(World w, GlobalChessData data, Item item, int rotation){

        ItemFrameEntity frame = new ItemFrameEntity(w,data.pos,data.directionWall);
        ItemStack stack = new ItemStack(item);
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("rotation",rotation);
        stack.setNbt(nbt);
        frame.setHeldItemStack(stack);
        frame.setRotation(data.itemRotation);
        return frame;
    }

    public static ItemFrameEntity dataToFigure(World w, GlobalChessData data, Item item){

        ItemFrameEntity frame = new ItemFrameEntity(w,data.pos,data.directionWall);
        ItemStack stack = new ItemStack(item);
        frame.setHeldItemStack(stack);
        frame.setRotation(data.itemRotation);
        return frame;
    }

    public static GlobalChessData figureToData(ItemFrameEntity frame){

        GlobalChessData data = new GlobalChessData(frame.getBlockPos(),frame.getHorizontalFacing(),frame.getRotation(),false);

        return data;
    }
    public static ItemFrameEntity getItemFrame(World w, BlockPos pos, Direction direction){
        ItemFrameEntity frame = new ItemFrameEntity(w,pos,direction);
        List list = w.getEntitiesByType(frame.getType(),new Box(pos.getX()-1,pos.getY()-1,pos.getZ()-1,pos.getX()+1, pos.getY()+1, pos.getZ()+1), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getBlockPos().getX() == pos.getX() && entity.getBlockPos().getY() == pos.getY() && entity.getBlockPos().getZ() == pos.getZ() && entity.getHorizontalFacing() == direction){
                    return entity;
                }
            }

        }
        return frame;
    }

    public static boolean isItemFrame(World w, BlockPos pos, Direction direction){
        ItemFrameEntity frame = new ItemFrameEntity(w,pos,direction);
        List list = w.getEntitiesByType(frame.getType(),new Box(pos.getX()-1,pos.getY()-1,pos.getZ()-1,pos.getX()+1, pos.getY()+1, pos.getZ()+1), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getBlockPos().getX() == pos.getX() && entity.getBlockPos().getY() == pos.getY() && entity.getBlockPos().getZ() == pos.getZ() && entity.getHorizontalFacing() == direction){
                    return true;
                }
            }

        }
        return false;
    }




}
