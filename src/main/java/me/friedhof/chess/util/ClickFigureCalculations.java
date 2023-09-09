package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class ClickFigureCalculations {


    public static void selectFigure(World w, ItemFrameEntity frame) {
        deselectFigure(w,frame);
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_TOWER){



            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,true);
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,true);
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,true);
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_TOWER){


            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward,Direction.NORTH,false);
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,false);
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,false);
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_BISHOP) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_BISHOP);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);




        }

        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_BISHOP) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_BISHOP);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_KNIGHT) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,true);
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,true);
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,true);
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,true);
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,true);
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,true);
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_KNIGHT);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_KNIGHT) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,false);
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,false);
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,false);
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,false);
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,false);
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,false);
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_KNIGHT);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_QUEEN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,true);
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,true);
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,true);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_QUEEN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_QUEEN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,false);
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,false);
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,false);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_QUEEN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_KING) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,true);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_KING) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,false);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_PAWN) {


            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_PAWN);
            newFrame.setHeldItemStack(stack);
            newFrame.setInvulnerable(true);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_PAWN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_PAWN);
            newFrame.setHeldItemStack(stack);
            newFrame.setInvulnerable(true);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_WHITE_PAWN) {


            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_WHITE_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_BLACK_PAWN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_BLACK_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);

        }

            //Castling

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_KING) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,true);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,true);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);




            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,true);
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,true);
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,true);
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,true);







            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_WHITE_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_KING) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,false);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,false);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);


            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,false);
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,false);
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,false);
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,false);



            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_BLACK_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_TOWER) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,true);
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,true);
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,true);
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_WHITE_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_TOWER) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,false);
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,false);
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,false);
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_BLACK_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }
    }

    public static void deselectFigure(World w, ItemFrameEntity frame){
        int radius = UseEntityHandler.searchRadius;
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-radius,currentPosition.pos.getY()-radius,currentPosition.pos.getZ()-radius,currentPosition.pos.getX()+radius,currentPosition.pos.getY()+radius,currentPosition.pos.getZ()+radius), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i < list.size();i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }

                if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }


            }


        }



    }



    public static void moveFigure(World w, ItemFrameEntity frame) {
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()- UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);
        for(int j = 0; j < list.size();j++) {
            if (list.get(j) instanceof ItemFrameEntity) {
                ItemFrameEntity entity = (ItemFrameEntity) list.get(j);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }

                if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);


                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
                    if(item == ModItems.START_WHITE_PAWN ){
                        item = ModItems.WHITE_PAWN;
                    }
                    if(item == ModItems.START_BLACK_PAWN ){
                        item = ModItems.BLACK_PAWN;
                    }
                    if(item == ModItems.CASTLE_WHITE_KING){
                        item = ModItems.WHITE_KING;
                    }
                    if(item == ModItems.CASTLE_WHITE_TOWER){
                        item = ModItems.WHITE_TOWER;
                    }
                    if(item == ModItems.CASTLE_BLACK_KING){
                        item = ModItems.BLACK_KING;
                    }
                    if(item == ModItems.CASTLE_BLACK_TOWER){
                        item = ModItems.BLACK_TOWER;
                    }


                    ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(currentPosition.itemRotation);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }



            }
        }
    }

    public static void takeWithFigure( World w, ItemFrameEntity frame) {

        int rotation = 0;
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                w.getEntityById(entity.getId()).kill();
            }
            if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);

            }

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ) {
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(), true);




                ItemFrameEntity e = new ItemFrameEntity(w, entity.getBlockPos(), entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());

                w.getEntityById(entity.getId()).kill();
                if (!(e.getBlockPos().getX() == currentPosition.pos.getX() && e.getBlockPos().getY() == currentPosition.pos.getY() && e.getBlockPos().getZ() == currentPosition.pos.getZ() && e.getHorizontalFacing() == currentPosition.directionWall)){
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }

                    w.spawnEntity(e);

                }else{
                    rotation = entity.getHeldItemStack().getDamage();
                    if(e.getHeldItemStack().getItem() == ModItems.WHITE_KING){


                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeString("The White King is Dead!");
                        ClientPlayNetworking.send(ModMessages.SEND_CHAT, buffer);


                    }
                    if(e.getHeldItemStack().getItem() == ModItems.BLACK_KING){
                        PacketByteBuf buffer = PacketByteBufs.create();
                        buffer.writeString("The Black King is Dead!");
                        ClientPlayNetworking.send(ModMessages.SEND_CHAT, buffer);
                    }
                }

            }

        }
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
                if(item == ModItems.START_WHITE_PAWN ){
                    item = ModItems.WHITE_PAWN;
                }
                if(item == ModItems.START_BLACK_PAWN ){
                    item = ModItems.BLACK_PAWN;
                }

                if(item == ModItems.CASTLE_WHITE_KING){
                    item = ModItems.WHITE_KING;
                }
                if(item == ModItems.CASTLE_WHITE_TOWER){
                    item = ModItems.WHITE_TOWER;
                }
                if(item == ModItems.CASTLE_BLACK_KING){
                    item = ModItems.BLACK_KING;
                }
                if(item == ModItems.CASTLE_BLACK_TOWER){
                    item = ModItems.BLACK_TOWER;
                }


                ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);


                e.setRotation(rotation-1);
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);
            }


        }
    }

    public static void switchFigure( World w, ItemFrameEntity frame){

        int rotation = 0;
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);

        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if (entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                w.getEntityById(entity.getId()).kill();
            }

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ){
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);


                ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);

            }


            if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())) {
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(), true);


                ItemFrameEntity e = new ItemFrameEntity(w, entity.getBlockPos(), entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());

                if(!(e.getBlockPos().getX() == currentPosition.pos.getX() && e.getBlockPos().getY() == currentPosition.pos.getY() && e.getBlockPos().getZ() == currentPosition.pos.getZ() && e.getHorizontalFacing() == currentPosition.directionWall)){

                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                     w.getEntityById(entity.getId()).kill();
                     w.spawnEntity(e);
                }
            }



        }

        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() )){

            GlobalChessData selectedPiece = MovementCalculations.figureToData(entity);
            Direction result = Direction.UP;
            if(isSwitchPiece(w,Direction.NORTH,selectedPiece,currentPosition)){
                result = Direction.NORTH;
            }
            if(isSwitchPiece(w,Direction.SOUTH,selectedPiece,currentPosition)){
                    result = Direction.SOUTH;
            }
            if(isSwitchPiece(w,Direction.EAST,selectedPiece,currentPosition)){
                    result = Direction.EAST;
            }
            if(isSwitchPiece(w,Direction.WEST,selectedPiece,currentPosition)){
                    result = Direction.WEST;
            }
            GlobalChessData newSwitchPiece = MovementCalculations.moveOneInDirection(w,selectedPiece,result);
            GlobalChessData newPiece = MovementCalculations.moveOneInDirection(w,newSwitchPiece,result);

            w.getEntityById(frame.getId()).kill();
            w.getEntityById(entity.getId()).kill();


            Item item2 = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
            Item item = FigureMovesCalculations.exchangeItems(frame.getHeldItemStack().getItem(),false);

                if(item2 == ModItems.CASTLE_WHITE_KING){
                    item2 = ModItems.WHITE_KING;
                }
                if(item == ModItems.CASTLE_WHITE_TOWER){
                    item = ModItems.WHITE_TOWER;
                }
                if(item2 == ModItems.CASTLE_BLACK_KING){
                    item2 = ModItems.BLACK_KING;
                }
                if(item == ModItems.CASTLE_BLACK_TOWER){
                    item = ModItems.BLACK_TOWER;
                }

            ItemFrameEntity e = new ItemFrameEntity(w,newSwitchPiece.pos,newSwitchPiece.directionWall);
            ItemStack stack = new ItemStack(item);
            e.setHeldItemStack(stack);
            e.setRotation(newSwitchPiece.itemRotation);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);

            ItemFrameEntity e2 = new ItemFrameEntity(w,newPiece.pos,newPiece.directionWall);
            ItemStack stack2 = new ItemStack(item2);
            e2.setHeldItemStack(stack2);
            e2.setRotation(newPiece.itemRotation);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e2.setInvulnerable(true);
            }
            w.spawnEntity(e2);



            }

        }

    }

    private static boolean isSwitchPiece(World w, Direction direction,GlobalChessData selectedPiece, GlobalChessData switchPiece) {
        for (int i = 0; i < UseEntityHandler.figureDrawDistance; i++) {
            selectedPiece = MovementCalculations.moveOneInDirection(w, selectedPiece, direction);

            if(selectedPiece == null){
                return false;
            }
            if(selectedPiece.pos.getX() == switchPiece.pos.getX() && selectedPiece.pos.getY() == switchPiece.pos.getY() && selectedPiece.pos.getZ() == switchPiece.pos.getZ() && selectedPiece.directionWall == switchPiece.directionWall){
                return true;
            }


        }
        return false;
    }







}
