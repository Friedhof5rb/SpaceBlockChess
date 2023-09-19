package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class StartServerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {




        if(Chess.lastMoveFrom != null) {

            Vec3d vec = calculateParticleOffset(Chess.lastMoveFrom);
            server.getOverworld().spawnParticles(ParticleTypes.FLAME, Chess.lastMoveFrom.pos.getX()+vec.getX(), Chess.lastMoveFrom.pos.getY()+vec.getY(),Chess.lastMoveFrom.pos.getZ()+vec.getZ(),3,0,0,0,0);
        }
        if(Chess.lastMoveTo != null) {

            Vec3d vec = calculateParticleOffset(Chess.lastMoveTo);
            server.getOverworld().spawnParticles(ParticleTypes.FLAME, Chess.lastMoveTo.pos.getX()+vec.getX(), Chess.lastMoveTo.pos.getY()+vec.getY(),Chess.lastMoveTo.pos.getZ()+vec.getZ(),3,0,0,0,0);
        }


    }

    private Vec3d calculateParticleOffset(GlobalChessData data){
        float xOffset = 0;
        float yOffset = 0;
        float zOffset = 0;

        switch(data.directionWall){
            case UP -> {
                xOffset = 0.5f;
                yOffset = 0;
                zOffset = 0.5f;
                break;
            }
            case DOWN -> {
                xOffset = 0.5f;
                yOffset = 1;
                zOffset = 0.5f;
                break;
            }
            case NORTH -> {
                xOffset = 0.5f;
                yOffset = 0.5f;
                zOffset = 1f;
                break;
            }
            case SOUTH -> {
                xOffset = 0.5f;
                yOffset = 0.5f;
                zOffset = 0f;
                break;
            }
            case EAST -> {
                xOffset = 0f;
                yOffset = 0.5f;
                zOffset = 0.5f;
                break;
            }
            case WEST -> {
                xOffset = 1f;
                yOffset = 0.5f;
                zOffset = 0.5f;
                break;
            }


        }

        return new Vec3d(xOffset,yOffset,zOffset);




    }




}
