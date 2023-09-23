package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.fluid.Fluids;
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

        //inner edges
        if(!(w.getBlockState(nextTo).getBlock() instanceof AirBlock)){

            if(w.getBlockState(nextTo).getBlock() instanceof GlassBlock || w.getBlockState(nextTo).getFluidState() != Fluids.EMPTY.getDefaultState()){
                return null;
            }


            int newItemRotation = RotationCalculations.correctRotationsInnerEdges(data.itemRotation,data.directionWall,absolute.getOpposite());

            return new GlobalChessData(sameBlock,absolute.getOpposite(),newItemRotation,false);

            //smooth
        }else if(!(w.getBlockState(diagonal).getBlock() instanceof AirBlock)){
            if( w.getBlockState(diagonal).getBlock() instanceof GlassBlock || w.getBlockState(diagonal).getFluidState() != Fluids.EMPTY.getDefaultState()){
                return null;
            }
            return new GlobalChessData(nextTo,data.directionWall, data.itemRotation, false);

            //outer edges
        }else{

            return new GlobalChessData(diagonal,absolute,RotationCalculations.correctRotationsOuterEdges(data.itemRotation,data.directionWall, absolute),false);
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
