package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.Calculations.FigureMovesCalculations;
import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculations;
import me.friedhof.chess.util.Calculations.MovementCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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

import java.util.List;

public class StartServerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {

        World w = server.getOverworld();
        if(w.getGameRules().getBoolean(ModGamerules.canUseChessTorches)) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                Item item = player.getInventory().getMainHandStack().getItem();
                if (item == ModItems.WHITE_TORCH) {

                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculations.whitePotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.WHITE_TOWER) {


                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "white");


                        }
                        if (item2 == ModItems.WHITE_BISHOP) {
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_KNIGHT) {

                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "white");
                        }
                        if (item2 == ModItems.WHITE_QUEEN) {
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_PAWN) {
                            FigurePotentialMovesCalculations.pawnMoveScheme(w, data, Direction.NORTH, "white");

                        }
                        if (item2 == ModItems.START_WHITE_PAWN) {
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "white");

                        }
                        if (item2 == ModItems.CASTLE_WHITE_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");


                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.EAST, "white");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.CASTLE_WHITE_TOWER) {
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "white");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "white");

                        }


                    }

                    for (GlobalChessData data : FigurePotentialMovesCalculations.whitePotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 0, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }


                }
                if (item == ModItems.BLACK_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculations.blackPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.BLACK_TOWER) {


                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "black");

                        }
                        if (item2 == ModItems.BLACK_BISHOP) {
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_KNIGHT) {

                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "black");
                        }
                        if (item2 == ModItems.BLACK_QUEEN) {
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_PAWN) {
                            FigurePotentialMovesCalculations.pawnMoveScheme(w, data, Direction.NORTH, "black");

                        }
                        if (item2 == ModItems.START_BLACK_PAWN) {
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "black");

                        }
                        if (item2 == ModItems.CASTLE_BLACK_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");


                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.EAST, "black");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.CASTLE_BLACK_TOWER) {
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "black");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "black");

                        }

                    }

                    for (GlobalChessData data : FigurePotentialMovesCalculations.blackPotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 1, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }


                }
                if (item == ModItems.YELLOW_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculations.yellowPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.YELLOW_TOWER) {


                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_BISHOP) {
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_KNIGHT) {

                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "yellow");
                        }
                        if (item2 == ModItems.YELLOW_QUEEN) {
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_PAWN) {
                            FigurePotentialMovesCalculations.pawnMoveScheme(w, data, Direction.NORTH, "yellow");

                        }
                        if (item2 == ModItems.START_YELLOW_PAWN) {
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "yellow");

                        }
                        if (item2 == ModItems.CASTLE_YELLOW_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");


                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.CASTLE_YELLOW_TOWER) {
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "yellow");

                        }

                    }
                    for (GlobalChessData data : FigurePotentialMovesCalculations.yellowPotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 2, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }

                }
                if (item == ModItems.PINK_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculations.pinkPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.PINK_TOWER) {


                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "pink");
                        }
                        if (item2 == ModItems.PINK_BISHOP) {
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_KNIGHT) {

                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculations.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "pink");
                        }
                        if (item2 == ModItems.PINK_QUEEN) {
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_PAWN) {
                            FigurePotentialMovesCalculations.pawnMoveScheme(w, data, Direction.NORTH, "pink");

                        }
                        if (item2 == ModItems.START_PINK_PAWN) {
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "pink");

                        }
                        if (item2 == ModItems.CASTLE_PINK_KING) {
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");


                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.EAST, "pink");
                            FigurePotentialMovesCalculations.castlingScheme(w, data, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.CASTLE_PINK_TOWER) {
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.WEST, "pink");
                            FigurePotentialMovesCalculations.towerMoveScheme(w, data, Direction.EAST, "pink");

                        }


                    }
                    for (GlobalChessData data : FigurePotentialMovesCalculations.pinkPotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 3, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

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
