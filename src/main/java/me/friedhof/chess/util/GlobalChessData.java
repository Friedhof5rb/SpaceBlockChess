package me.friedhof.chess.util;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class GlobalChessData {

    public BlockPos pos;
    public Direction directionWall;
    public int itemRotation;
    public boolean isFigure;


    public GlobalChessData(BlockPos pos, Direction directionWall, int itemRotation, boolean isFigure) {
        this.pos = pos;
        this.directionWall = directionWall;
        this.itemRotation = itemRotation;
        this.isFigure = isFigure;
    }




}
