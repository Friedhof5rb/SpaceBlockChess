package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.item.custom.TorchItem;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.Calculations.*;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StartServerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {


        World w = server.getOverworld();
        if(w.getGameRules().getBoolean(ModGamerules.canUseChessTorches) && server.getTicks() % 10 == 0) {

            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                Item item = player.getInventory().getMainHandStack().getItem();
                if (item instanceof TorchItem) {

                        if (item == ModItems.WHITE_TORCH) {
                            if (!(((TorchItem) item).justShowCheck)) {
                                GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                                BoardState b = checkCalculations.getCurrentBoardState(w,data1);
                                calc.calculateAllMovesForColour(w,"white",b);

                                ArrayList<GlobalChessData> potentialMoves = new ArrayList<>(calc.whitePotentialMoves);

                                for (GlobalChessData data : potentialMoves) {
                                    PacketByteBuf buffer = PacketByteBufs.create();
                                    buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 0, data.directionWall.getId()});
                                    ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                                }


                            }else{
                                GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                                BoardState b = checkCalculations.getCurrentBoardState(w,data1);

                                ArrayList<BoardState> list = b.allPossibleMoves(w,"white");



                                for (BoardState b2 : list) {
                                    if(!checkCalculations.isKingOfColourInCheck(w,"white",b2)) {
                                        for(FigureOnBoard f : checkCalculations.compareBoardStates(b,b2)) {
                                            PacketByteBuf buffer = PacketByteBufs.create();
                                            buffer.writeIntArray(new int[]{f.data.pos.getX(), f.data.pos.getY(), f.data.pos.getZ(), 0, f.data.directionWall.getId()});
                                            ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);
                                        }
                                    }
                                }

                            }
                        }
                    if (item == ModItems.BLACK_TORCH) {
                        if (!(((TorchItem) item).justShowCheck)) {
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);
                            calc.calculateAllMovesForColour(w,"black",b);

                            ArrayList<GlobalChessData> potentialMoves = new ArrayList<>(calc.blackPotentialMoves);

                            for (GlobalChessData data : potentialMoves) {
                                PacketByteBuf buffer = PacketByteBufs.create();
                                buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 1, data.directionWall.getId()});
                                ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                            }


                        }else{
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);


                            ArrayList<BoardState> list = b.allPossibleMoves(w,"black");

                            for (BoardState b2 : list) {
                                if(!checkCalculations.isKingOfColourInCheck(w,"black",b2)) {
                                    for(FigureOnBoard f : checkCalculations.compareBoardStates(b,b2)) {

                                        PacketByteBuf buffer = PacketByteBufs.create();
                                        buffer.writeIntArray(new int[]{f.data.pos.getX(), f.data.pos.getY(), f.data.pos.getZ(), 1, f.data.directionWall.getId()});
                                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);
                                    }
                                }
                            }

                        }
                    }
                    if (item == ModItems.YELLOW_TORCH) {
                        if (!(((TorchItem) item).justShowCheck)) {
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);
                            calc.calculateAllMovesForColour(w,"yellow",b);

                            ArrayList<GlobalChessData> potentialMoves = new ArrayList<>(calc.yellowPotentialMoves);

                            for (GlobalChessData data : potentialMoves) {
                                PacketByteBuf buffer = PacketByteBufs.create();
                                buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 2, data.directionWall.getId()});
                                ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                            }


                        }else{
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);


                            ArrayList<BoardState> list = b.allPossibleMoves(w,"yellow");

                            for (BoardState b2 : list) {
                                if(!checkCalculations.isKingOfColourInCheck(w,"yellow",b2)) {
                                    for(FigureOnBoard f : checkCalculations.compareBoardStates(b,b2)) {
                                        PacketByteBuf buffer = PacketByteBufs.create();
                                        buffer.writeIntArray(new int[]{f.data.pos.getX(), f.data.pos.getY(), f.data.pos.getZ(), 2, f.data.directionWall.getId()});
                                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);
                                    }
                                }
                            }

                        }

                    }
                    if (item == ModItems.PINK_TORCH) {
                        if (!(((TorchItem) item).justShowCheck)) {
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);
                            calc.calculateAllMovesForColour(w,"pink",b);

                            ArrayList<GlobalChessData> potentialMoves = new ArrayList<>(calc.pinkPotentialMoves);

                            for (GlobalChessData data : potentialMoves) {
                                PacketByteBuf buffer = PacketByteBufs.create();
                                buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 3, data.directionWall.getId()});
                                ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                            }


                        }else{
                            GlobalChessData data1 = new GlobalChessData(player.getBlockPos(),Direction.UP,0,false);
                            BoardState b = checkCalculations.getCurrentBoardState(w,data1);


                            ArrayList<BoardState> list = b.allPossibleMoves(w,"pink");

                            for (BoardState b2 : list) {
                                if(!checkCalculations.isKingOfColourInCheck(w,"pink",b2)) {
                                    for(FigureOnBoard f : checkCalculations.compareBoardStates(b,b2)) {
                                        PacketByteBuf buffer = PacketByteBufs.create();
                                        buffer.writeIntArray(new int[]{f.data.pos.getX(), f.data.pos.getY(), f.data.pos.getZ(), 3, f.data.directionWall.getId()});
                                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);
                                    }
                                }
                            }

                        }
                    }

                }
            }




        }


        for(PlayerEntity player : server.getPlayerManager().getPlayerList()) {


            String uuid = player.getUuidAsString();

            if(Chess.lastMoveFrom.containsKey(uuid)) {
                GlobalChessData lastMoveFromData = Chess.lastMoveFrom.get(uuid);
                Vec3d vec = calculateParticleOffset(lastMoveFromData);
                server.getOverworld().spawnParticles(ParticleTypes.FLAME, lastMoveFromData.pos.getX() + vec.getX(), lastMoveFromData.pos.getY() + vec.getY(), lastMoveFromData.pos.getZ() + vec.getZ(), 3, 0, 0, 0, 0);
            }else{
                continue;
            }

            if(Chess.lastMoveTo.containsKey(uuid)) {
                GlobalChessData lastMoveToData = Chess.lastMoveTo.get(uuid);
                Vec3d vec = calculateParticleOffset(lastMoveToData);
                server.getOverworld().spawnParticles(ParticleTypes.FLAME, lastMoveToData.pos.getX() + vec.getX(), lastMoveToData.pos.getY() + vec.getY(), lastMoveToData.pos.getZ() + vec.getZ(), 3, 0, 0, 0, 0);

            }else{
                continue;
            }

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
