package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.Calculations.FigureMovesCalculations;
import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculations;
import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculationsForShow;
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
                    FigurePotentialMovesCalculationsForShow.whitePotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.WHITE_TOWER) {


                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "white");


                        }
                        if (item2 == ModItems.WHITE_BISHOP) {
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_KNIGHT) {

                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "white");
                        }
                        if (item2 == ModItems.WHITE_QUEEN) {
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.WHITE_PAWN) {
                            FigurePotentialMovesCalculationsForShow.pawnMoveScheme(w, data, Direction.NORTH, "white");

                        }
                        if (item2 == ModItems.START_WHITE_PAWN) {
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "white");

                        }
                        if (item2 == ModItems.CASTLE_WHITE_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "white");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "white");


                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.EAST, "white");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.WEST, "white");

                        }
                        if (item2 == ModItems.CASTLE_WHITE_TOWER) {
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "white");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "white");

                        }


                    }

                    for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.whitePotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 0, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }


                }
                if (item == ModItems.BLACK_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculationsForShow.blackPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.BLACK_TOWER) {


                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "black");

                        }
                        if (item2 == ModItems.BLACK_BISHOP) {
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_KNIGHT) {

                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "black");
                        }
                        if (item2 == ModItems.BLACK_QUEEN) {
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.BLACK_PAWN) {
                            FigurePotentialMovesCalculationsForShow.pawnMoveScheme(w, data, Direction.NORTH, "black");

                        }
                        if (item2 == ModItems.START_BLACK_PAWN) {
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "black");

                        }
                        if (item2 == ModItems.CASTLE_BLACK_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "black");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "black");


                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.EAST, "black");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.WEST, "black");

                        }
                        if (item2 == ModItems.CASTLE_BLACK_TOWER) {
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "black");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "black");

                        }

                    }

                    for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.blackPotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 1, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }


                }
                if (item == ModItems.YELLOW_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculationsForShow.yellowPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.YELLOW_TOWER) {


                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_BISHOP) {
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_KNIGHT) {

                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "yellow");
                        }
                        if (item2 == ModItems.YELLOW_QUEEN) {
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.YELLOW_PAWN) {
                            FigurePotentialMovesCalculationsForShow.pawnMoveScheme(w, data, Direction.NORTH, "yellow");

                        }
                        if (item2 == ModItems.START_YELLOW_PAWN) {
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "yellow");

                        }
                        if (item2 == ModItems.CASTLE_YELLOW_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "yellow");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "yellow");


                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.EAST, "yellow");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.WEST, "yellow");

                        }
                        if (item2 == ModItems.CASTLE_YELLOW_TOWER) {
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "yellow");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "yellow");

                        }

                    }
                    for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.yellowPotentialMoves) {
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeIntArray(new int[]{data.pos.getX(), data.pos.getY(), data.pos.getZ(), 2, data.directionWall.getId()});
                        ServerPlayNetworking.send(player, ModMessages.SPAWN_PARTICLE, buffer);

                    }

                }
                if (item == ModItems.PINK_TORCH) {
                    int radius = 50;
                    List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius, player.getX() + radius, player.getY() + radius, player.getZ() + radius), EntityPredicates.VALID_ENTITY);
                    FigurePotentialMovesCalculationsForShow.pinkPotentialMoves.clear();
                    for (ItemFrameEntity entity : list) {
                        Item item2 = entity.getHeldItemStack().getItem();
                        GlobalChessData data = MovementCalculations.figureToData(entity);
                        if (item2 == ModItems.PINK_TOWER) {


                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "pink");
                        }
                        if (item2 == ModItems.PINK_BISHOP) {
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_KNIGHT) {

                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculationsForShow.knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, "pink");
                        }
                        if (item2 == ModItems.PINK_QUEEN) {
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.PINK_PAWN) {
                            FigurePotentialMovesCalculationsForShow.pawnMoveScheme(w, data, Direction.NORTH, "pink");

                        }
                        if (item2 == ModItems.START_PINK_PAWN) {
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, "pink");

                        }
                        if (item2 == ModItems.CASTLE_PINK_KING) {
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.EAST, Direction.UP, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.WEST, Direction.UP, "pink");

                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, "pink");


                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.EAST, "pink");
                            FigurePotentialMovesCalculationsForShow.castlingScheme(w, data, Direction.WEST, "pink");

                        }
                        if (item2 == ModItems.CASTLE_PINK_TOWER) {
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.NORTH, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.SOUTH, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.WEST, "pink");
                            FigurePotentialMovesCalculationsForShow.towerMoveScheme(w, data, Direction.EAST, "pink");

                        }


                    }
                    for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.pinkPotentialMoves) {
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
