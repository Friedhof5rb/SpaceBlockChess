package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.*;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UseEntityHandler implements UseEntityCallback {


    public static int figureDrawDistance = 7;
    public static int searchRadius = 20;
    public static Item[] whitePieces = {ModItems.CASTLE_WHITE_KING, ModItems.CASTLE_WHITE_TOWER,ModItems.START_WHITE_PAWN,ModItems.WHITE_BISHOP, ModItems.WHITE_KING, ModItems.WHITE_KNIGHT, ModItems.WHITE_PAWN, ModItems.WHITE_QUEEN, ModItems.WHITE_TOWER};

    public static Item[] blackPieces = {ModItems.CASTLE_BLACK_KING, ModItems.CASTLE_BLACK_TOWER,ModItems.START_BLACK_PAWN,ModItems.BLACK_BISHOP, ModItems.BLACK_KING, ModItems.BLACK_KNIGHT, ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER};
    public static Item[] whiteCapturePieces = {ModItems.CASTLE_CAPTURE_WHITE_KING, ModItems.CASTLE_CAPTURE_WHITE_TOWER,ModItems.START_CAPTURE_WHITE_PAWN,ModItems.CAPTURE_WHITE_BISHOP, ModItems.CAPTURE_WHITE_KING, ModItems.CAPTURE_WHITE_KNIGHT, ModItems.CAPTURE_WHITE_PAWN, ModItems.CAPTURE_WHITE_QUEEN, ModItems.CAPTURE_WHITE_TOWER};

    public static Item[] blackCapturePieces = {ModItems.CASTLE_CAPTURE_BLACK_KING, ModItems.CASTLE_CAPTURE_BLACK_TOWER,ModItems.START_CAPTURE_BLACK_PAWN,ModItems.CAPTURE_BLACK_BISHOP, ModItems.CAPTURE_BLACK_KING, ModItems.CAPTURE_BLACK_KNIGHT, ModItems.CAPTURE_BLACK_PAWN, ModItems.CAPTURE_BLACK_QUEEN, ModItems.CAPTURE_BLACK_TOWER};

    public static Item[] whiteSelectedPieces = {ModItems.CASTLE_SELECTED_WHITE_KING, ModItems.CASTLE_SELECTED_WHITE_TOWER,ModItems.START_SELECTED_WHITE_PAWN,ModItems.SELECTED_WHITE_BISHOP, ModItems.SELECTED_WHITE_KING, ModItems.SELECTED_WHITE_KNIGHT, ModItems.SELECTED_WHITE_PAWN, ModItems.SELECTED_WHITE_QUEEN, ModItems.SELECTED_WHITE_TOWER};

    public static Item[] blackSelectedPieces = {ModItems.CASTLE_SELECTED_BLACK_KING, ModItems.CASTLE_SELECTED_BLACK_TOWER,ModItems.START_SELECTED_BLACK_PAWN,ModItems.SELECTED_BLACK_BISHOP, ModItems.SELECTED_BLACK_KING, ModItems.SELECTED_BLACK_KNIGHT, ModItems.SELECTED_BLACK_PAWN, ModItems.SELECTED_BLACK_QUEEN, ModItems.SELECTED_BLACK_TOWER};

    public static Item[] switchPieces = {ModItems.CASTLE_SWITCH_WHITE_KING,ModItems.CASTLE_SWITCH_WHITE_TOWER,ModItems.CASTLE_SWITCH_BLACK_KING,ModItems.CASTLE_SWITCH_BLACK_TOWER};


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        boolean whitesTurn = false;

        if (player.getInventory().getMainHandStack().getItem() != ModItems.WHITE_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.BLACK_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.ROD_OF_ROTATION) {
            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem())
                        || Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem())
                        || frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER || Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem()) ) {
                    //prevent Rotation of Figures with other Items as "Rod of Rotation"
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.PASS;
                }
            } else {
                return ActionResult.PASS;
            }
        }



        if (player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_ROTATION) {
            return ActionResult.PASS;
        }

        if (player.getInventory().getMainHandStack().getItem() == ModItems.WHITE_ROD_OF_MOVING) {
            whitesTurn = true;
        }


        if(world.isClient()){
            return ActionResult.SUCCESS;
        }


        if (whitesTurn) {

            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem())) {
                    ClickFigureCalculations.selectFigure(world, frame);
                    return ActionResult.SUCCESS;
                } else if (Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem())) {
                    ClickFigureCalculations.takeWithFigure(world, frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem())){
                    ClickFigureCalculations.deselectFigure(world, frame);
                    return ActionResult.SUCCESS;
                }else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {
                    ClickFigureCalculations.moveFigure(world, frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())){
                    ClickFigureCalculations.switchFigure(world,frame);
                    return ActionResult.SUCCESS;
                }

            }

        } else {

            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem())) {
                    ClickFigureCalculations.selectFigure(world, frame);
                    return ActionResult.SUCCESS;
                } else if (Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem())) {
                    ClickFigureCalculations.takeWithFigure(world,frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem())){
                    ClickFigureCalculations.deselectFigure(world,frame);
                    return ActionResult.SUCCESS;
                } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {
                    ClickFigureCalculations.moveFigure(world,frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())){
                    ClickFigureCalculations.switchFigure(world,frame);
                    return ActionResult.SUCCESS;

                }
            }
        }

        return ActionResult.SUCCESS;
    }




















































}
