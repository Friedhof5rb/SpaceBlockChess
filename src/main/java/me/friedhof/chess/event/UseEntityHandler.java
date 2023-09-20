package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.Calculations.ClickFigureCalculations;
import me.friedhof.chess.util.Calculations.FigureMovesCalculations;
import me.friedhof.chess.util.Calculations.MovementCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class UseEntityHandler implements UseEntityCallback {


    public static int figureDrawDistance = 7;
    public static int searchRadius = 100;



    public static Item[] whitePieces = {ModItems.CASTLE_WHITE_KING, ModItems.CASTLE_WHITE_TOWER,ModItems.START_WHITE_PAWN,ModItems.WHITE_BISHOP, ModItems.WHITE_KING, ModItems.WHITE_KNIGHT, ModItems.WHITE_PAWN, ModItems.WHITE_QUEEN, ModItems.WHITE_TOWER};
    public static Item[] blackPieces = {ModItems.CASTLE_BLACK_KING, ModItems.CASTLE_BLACK_TOWER,ModItems.START_BLACK_PAWN,ModItems.BLACK_BISHOP, ModItems.BLACK_KING, ModItems.BLACK_KNIGHT, ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER};


    public static Item[] whiteCapturePieces = {ModItems.CASTLE_CAPTURE_WHITE_KING, ModItems.CASTLE_CAPTURE_WHITE_TOWER,ModItems.START_CAPTURE_WHITE_PAWN,ModItems.CAPTURE_WHITE_BISHOP, ModItems.CAPTURE_WHITE_KING, ModItems.CAPTURE_WHITE_KNIGHT, ModItems.CAPTURE_WHITE_PAWN, ModItems.CAPTURE_WHITE_QUEEN, ModItems.CAPTURE_WHITE_TOWER};
    public static Item[] blackCapturePieces = {ModItems.CASTLE_CAPTURE_BLACK_KING, ModItems.CASTLE_CAPTURE_BLACK_TOWER,ModItems.START_CAPTURE_BLACK_PAWN,ModItems.CAPTURE_BLACK_BISHOP, ModItems.CAPTURE_BLACK_KING, ModItems.CAPTURE_BLACK_KNIGHT, ModItems.CAPTURE_BLACK_PAWN, ModItems.CAPTURE_BLACK_QUEEN, ModItems.CAPTURE_BLACK_TOWER};



    public static Item[] whiteSelectedPieces = {ModItems.CASTLE_SELECTED_WHITE_KING, ModItems.CASTLE_SELECTED_WHITE_TOWER,ModItems.START_SELECTED_WHITE_PAWN,ModItems.SELECTED_WHITE_BISHOP, ModItems.SELECTED_WHITE_KING, ModItems.SELECTED_WHITE_KNIGHT, ModItems.SELECTED_WHITE_PAWN, ModItems.SELECTED_WHITE_QUEEN, ModItems.SELECTED_WHITE_TOWER};
    public static Item[] blackSelectedPieces = {ModItems.CASTLE_SELECTED_BLACK_KING, ModItems.CASTLE_SELECTED_BLACK_TOWER,ModItems.START_SELECTED_BLACK_PAWN,ModItems.SELECTED_BLACK_BISHOP, ModItems.SELECTED_BLACK_KING, ModItems.SELECTED_BLACK_KNIGHT, ModItems.SELECTED_BLACK_PAWN, ModItems.SELECTED_BLACK_QUEEN, ModItems.SELECTED_BLACK_TOWER};


    public static Item[] yellowPieces = {ModItems.CASTLE_YELLOW_KING, ModItems.CASTLE_YELLOW_TOWER,ModItems.START_YELLOW_PAWN,ModItems.YELLOW_BISHOP, ModItems.YELLOW_KING, ModItems.YELLOW_KNIGHT, ModItems.YELLOW_PAWN, ModItems.YELLOW_QUEEN, ModItems.YELLOW_TOWER};
    public static Item[] pinkPieces = {ModItems.CASTLE_PINK_KING, ModItems.CASTLE_PINK_TOWER,ModItems.START_PINK_PAWN,ModItems.PINK_BISHOP, ModItems.PINK_KING, ModItems.PINK_KNIGHT, ModItems.PINK_PAWN, ModItems.PINK_QUEEN, ModItems.PINK_TOWER};


    public static Item[] yellowCapturePieces = {ModItems.CASTLE_CAPTURE_YELLOW_KING, ModItems.CASTLE_CAPTURE_YELLOW_TOWER,ModItems.START_CAPTURE_YELLOW_PAWN,ModItems.CAPTURE_YELLOW_BISHOP, ModItems.CAPTURE_YELLOW_KING, ModItems.CAPTURE_YELLOW_KNIGHT, ModItems.CAPTURE_YELLOW_PAWN, ModItems.CAPTURE_YELLOW_QUEEN, ModItems.CAPTURE_YELLOW_TOWER};
    public static Item[] pinkCapturePieces = {ModItems.CASTLE_CAPTURE_PINK_KING, ModItems.CASTLE_CAPTURE_PINK_TOWER,ModItems.START_CAPTURE_PINK_PAWN,ModItems.CAPTURE_PINK_BISHOP, ModItems.CAPTURE_PINK_KING, ModItems.CAPTURE_PINK_KNIGHT, ModItems.CAPTURE_PINK_PAWN, ModItems.CAPTURE_PINK_QUEEN, ModItems.CAPTURE_PINK_TOWER};



    public static Item[] yellowSelectedPieces = {ModItems.CASTLE_SELECTED_YELLOW_KING, ModItems.CASTLE_SELECTED_YELLOW_TOWER,ModItems.START_SELECTED_YELLOW_PAWN,ModItems.SELECTED_YELLOW_BISHOP, ModItems.SELECTED_YELLOW_KING, ModItems.SELECTED_YELLOW_KNIGHT, ModItems.SELECTED_YELLOW_PAWN, ModItems.SELECTED_YELLOW_QUEEN, ModItems.SELECTED_YELLOW_TOWER};
    public static Item[] pinkSelectedPieces = {ModItems.CASTLE_SELECTED_PINK_KING, ModItems.CASTLE_SELECTED_PINK_TOWER,ModItems.START_SELECTED_PINK_PAWN,ModItems.SELECTED_PINK_BISHOP, ModItems.SELECTED_PINK_KING, ModItems.SELECTED_PINK_KNIGHT, ModItems.SELECTED_PINK_PAWN, ModItems.SELECTED_PINK_QUEEN, ModItems.SELECTED_PINK_TOWER};


    public static Item[] switchPieces = {ModItems.CASTLE_SWITCH_WHITE_TOWER,ModItems.CASTLE_SWITCH_BLACK_TOWER,ModItems.CASTLE_SWITCH_YELLOW_TOWER,ModItems.CASTLE_SWITCH_PINK_TOWER};


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        String whosturn = "";

        if (entity instanceof ItemFrameEntity) {
            ItemFrameEntity e5 = (ItemFrameEntity) entity;
            if(Chess.arrayContains(Chess.combineLists(),player.getInventory().getMainHandStack().getItem()) || (player.getInventory().getMainHandStack().getItem() instanceof AirBlockItem && Chess.arrayContains(Chess.combineLists(),player.getOffHandStack().getItem()))){
                e5.setInvisible(true);
            }
        }


        if (player.getInventory().getMainHandStack().getItem() != ModItems.WHITE_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.BLACK_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.ROD_OF_ROTATION && player.getInventory().getMainHandStack().getItem() != ModItems.YELLOW_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.PINK_ROD_OF_MOVING) {
            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem())
                        || Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem())
                        || frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER || Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(yellowPieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(yellowCapturePieces, frame.getHeldItemStack().getItem())
                        || Chess.arrayContains(pinkPieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(pinkCapturePieces, frame.getHeldItemStack().getItem())
                         || Chess.arrayContains(pinkSelectedPieces, frame.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(yellowSelectedPieces, frame.getHeldItemStack().getItem()) ) {
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
            whosturn = "white";
        }
        if (player.getInventory().getMainHandStack().getItem() == ModItems.BLACK_ROD_OF_MOVING) {
            whosturn = "black";
        }
        if (player.getInventory().getMainHandStack().getItem() == ModItems.YELLOW_ROD_OF_MOVING) {
            whosturn = "yellow";
        }
        if (player.getInventory().getMainHandStack().getItem() == ModItems.PINK_ROD_OF_MOVING) {
            whosturn = "pink";
        }

        String playerName = player.getDisplayName().getString();

        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        switch (whosturn) {
            case "white" -> {
                if (entity instanceof ItemFrameEntity) {
                    ItemFrameEntity frame = (ItemFrameEntity) entity;
                    if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.selectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(yellowCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(pinkCapturePieces, frame.getHeldItemStack().getItem())) {



                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.takeWithFigure(world, frame);



                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.deselectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.moveFigure(world, frame);

                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())) {




                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.switchFigure(world, frame);
                        return ActionResult.SUCCESS;
                    }

                }
            }
            case "black" -> {
                if (entity instanceof ItemFrameEntity) {
                    ItemFrameEntity frame = (ItemFrameEntity) entity;
                    if (Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.selectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(yellowCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(pinkCapturePieces, frame.getHeldItemStack().getItem())) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);

                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.takeWithFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.deselectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.moveFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.switchFigure(world, frame);

                        return ActionResult.SUCCESS;

                    }
                }
            }
            case "yellow" -> {
                if (entity instanceof ItemFrameEntity) {
                    ItemFrameEntity frame = (ItemFrameEntity) entity;
                    if (Chess.arrayContains(yellowPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.selectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(pinkCapturePieces, frame.getHeldItemStack().getItem())){


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.takeWithFigure(world, frame);

                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(yellowSelectedPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.deselectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                       ClickFigureCalculations.moveFigure(world, frame);

                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.switchFigure(world, frame);

                        return ActionResult.SUCCESS;
                    }

                }
            } case "pink" -> {
                if (entity instanceof ItemFrameEntity) {
                    ItemFrameEntity frame = (ItemFrameEntity) entity;
                    if (Chess.arrayContains(pinkPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.selectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem()) ||
                            Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(yellowCapturePieces, frame.getHeldItemStack().getItem())){

                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.takeWithFigure(world, frame);


                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(pinkSelectedPieces, frame.getHeldItemStack().getItem())) {
                        ClickFigureCalculations.deselectFigure(world, frame);
                        return ActionResult.SUCCESS;
                    } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {


                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.moveFigure(world, frame);

                        return ActionResult.SUCCESS;
                    } else if (Chess.arrayContains(switchPieces, frame.getHeldItemStack().getItem())) {

                        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
                        sendMovement(world, whosturn, playerName, currentPosition);
                        ClickFigureCalculations.switchFigure(world, frame);

                        return ActionResult.SUCCESS;
                    }

                }
            } default -> {
            }
        }




        return ActionResult.SUCCESS;
    }

    private static void sendMovement(World world, String colour, String playerName, GlobalChessData currentPosition){


        int r = 20;
        GlobalChessData originPosition = currentPosition;
        String piece = colour + "Piece";
        List<ItemFrameEntity> list = world.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-r,currentPosition.pos.getY()-r,currentPosition.pos.getZ()-r,currentPosition.pos.getX()+r,currentPosition.pos.getY()+r,currentPosition.pos.getZ()+r), EntityPredicates.VALID_ENTITY);
        for(ItemFrameEntity e : list){
            if((Chess.arrayContains(whiteSelectedPieces,e.getHeldItemStack().getItem()) && Objects.equals(colour, "white"))|| (Chess.arrayContains(blackSelectedPieces,e.getHeldItemStack().getItem()) && Objects.equals(colour, "black")) ||
                    (Chess.arrayContains(yellowSelectedPieces,e.getHeldItemStack().getItem()) && Objects.equals(colour, "yellow")) || (Chess.arrayContains(pinkSelectedPieces,e.getHeldItemStack().getItem()) && Objects.equals(colour, "pink"))){
                    originPosition = MovementCalculations.figureToData(e);
                    piece = Text.translatable(FigureMovesCalculations.exchangeItems(e.getHeldItemStack().getItem(),false).getTranslationKey()).getString();
                    break;
            }
        }


        String message = playerName + " moved a " +piece + " from: [" + originPosition.pos.getX() + ", " +  originPosition.pos.getY() + ", " + originPosition.pos.getZ() + ", " +originPosition.directionWall.name() + "]\n" +
                " to: [" + currentPosition.pos.getX() + ", " +  currentPosition.pos.getY() + ", " + currentPosition.pos.getZ() + ", " +currentPosition.directionWall.name() + "]";


        int radius = 50;
        List<PlayerEntity> list2 = world.getEntitiesByType(EntityType.PLAYER,new Box(currentPosition.pos.getX()-radius,currentPosition.pos.getY()-radius,currentPosition.pos.getZ()-radius,currentPosition.pos.getX()+radius,currentPosition.pos.getY()+radius,currentPosition.pos.getZ()+radius),EntityPredicates.VALID_ENTITY);

        for(PlayerEntity p : list2) {
            String uuid = p.getUuidAsString();

            Chess.lastMoveFrom.put(uuid, originPosition);
            Chess.lastMoveTo.put(uuid, currentPosition);

        }


        ClickFigureCalculations.sendMessageToClosePlayers(world,message,50,currentPosition.pos,false,true);



    }




























































}
