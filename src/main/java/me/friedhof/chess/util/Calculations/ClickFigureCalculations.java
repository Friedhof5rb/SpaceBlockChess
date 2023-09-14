package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class ClickFigureCalculations {


    public static void selectFigure(World w, ItemFrameEntity frame) {
        deselectFigure(w,frame);
        //White and Black
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_TOWER){



            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"white");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"white");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"white");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"white");


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

            FigureMovesCalculations.towerMoveScheme(w,forward,Direction.NORTH,"black");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"black");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"black");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"black");


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

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"white");
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"white");
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"white");
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"white");


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

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"black");
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"black");
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"black");
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"black");


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

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"white");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,"white");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,"white");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,"white");
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,"white");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,"white");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,"white");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,"white");

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

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"black");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,"black");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,"black");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,"black");
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,"black");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,"black");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,"black");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,"black");

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

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,"white");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"white");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,"white");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,"white");

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"white");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"white");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"white");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"white");

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

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,"black");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"black");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,"black");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,"black");

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"black");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"black");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"black");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"black");

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

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"white");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"white");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"white");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"white");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"white");

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

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"black");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"black");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"black");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"black");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"black");

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


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,"white");


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


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,"black");


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


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,"white");
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,"white");

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


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,"black");
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,"black");

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

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"white");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"white");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"white");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"white");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"white");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"white");




            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,"white");
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,"white");
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,"white");
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,"white");







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

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"black");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"black");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"black");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"black");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"black");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"black");


            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,"black");
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,"black");
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,"black");
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,"black");



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

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"white");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"white");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"white");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"white");


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

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"black");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"black");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"black");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"black");


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
        //Yellow and Pink

        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_TOWER){



            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"yellow");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.PINK_TOWER){


            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward,Direction.NORTH,"pink");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"pink");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"pink");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"pink");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_BISHOP) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"yellow");
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"yellow");
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"yellow");
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"yellow");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_BISHOP);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);




        }

        if(frame.getHeldItemStack().getItem() == ModItems.PINK_BISHOP) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"pink");
            FigureMovesCalculations.bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"pink");
            FigureMovesCalculations.bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"pink");
            FigureMovesCalculations.bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"pink");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_BISHOP);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_KNIGHT) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,"yellow");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,"yellow");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_KNIGHT);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.PINK_KNIGHT) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"pink");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,"pink");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,"pink");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,"pink");
            FigureMovesCalculations.knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,"pink");
            FigureMovesCalculations.knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,"pink");
            FigureMovesCalculations.knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,"pink");
            FigureMovesCalculations.knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,"pink");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_KNIGHT);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_QUEEN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,"yellow");

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"yellow");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"yellow");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_QUEEN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.PINK_QUEEN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,"pink");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"pink");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.EAST,Direction.UP,"pink");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.WEST,Direction.UP,"pink");

            FigureMovesCalculations.queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"pink");
            FigureMovesCalculations.queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"pink");
            FigureMovesCalculations.queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"pink");
            FigureMovesCalculations.queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"pink");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_QUEEN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_KING) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"yellow");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"yellow");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.PINK_KING) {

            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"pink");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"pink");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.YELLOW_PAWN) {


            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,"yellow");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_YELLOW_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.PINK_PAWN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.pawnMoveScheme(w,forward,Direction.NORTH,"pink");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_PINK_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_YELLOW_PAWN) {


            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,"yellow");
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,"yellow");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_YELLOW_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_PINK_PAWN) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);


            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,"pink");
            FigureMovesCalculations.startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,"pink");

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_PINK_PAWN);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);

        }

        //Castling

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_KING) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"yellow");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"yellow");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"yellow");




            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,"yellow");
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,"yellow");
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,"yellow");
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,"yellow");







            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_YELLOW_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_PINK_KING) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.EAST,Direction.UP,"pink");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.WEST,Direction.UP,"pink");

            FigureMovesCalculations.kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,"pink");
            FigureMovesCalculations.kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,"pink");


            FigureMovesCalculations.castlingScheme(w,forward,Direction.NORTH,"pink");
            FigureMovesCalculations.castlingScheme(w,backward,Direction.SOUTH,"pink");
            FigureMovesCalculations.castlingScheme(w,left,Direction.EAST,"pink");
            FigureMovesCalculations.castlingScheme(w,right,Direction.WEST,"pink");



            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_PINK_KING);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_TOWER) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"yellow");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"yellow");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_YELLOW_TOWER);
            newFrame.setHeldItemStack(stack);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newFrame.setInvulnerable(true);
            }
            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.CASTLE_PINK_TOWER) {
            GlobalChessData forward = MovementCalculations.figureToData(frame);
            GlobalChessData backward = MovementCalculations.figureToData(frame);
            GlobalChessData left = MovementCalculations.figureToData(frame);
            GlobalChessData right = MovementCalculations.figureToData(frame);

            FigureMovesCalculations.towerMoveScheme(w,forward, Direction.NORTH,"pink");
            FigureMovesCalculations.towerMoveScheme(w,backward,Direction.SOUTH,"pink");
            FigureMovesCalculations.towerMoveScheme(w,left,Direction.WEST,"pink");
            FigureMovesCalculations.towerMoveScheme(w,right,Direction.EAST,"pink");


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.CASTLE_SELECTED_PINK_TOWER);
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

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem()) ){
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
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){
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



    public static GlobalChessData moveFigure(World w, ItemFrameEntity frame) {
        GlobalChessData result = MovementCalculations.figureToData(frame);
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

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem())  || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())  ){
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
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) ||
                        Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){
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

                    if(item == ModItems.START_YELLOW_PAWN ){
                        item = ModItems.YELLOW_PAWN;
                    }
                    if(item == ModItems.START_PINK_PAWN ){
                        item = ModItems.PINK_PAWN;
                    }
                    if(item == ModItems.CASTLE_YELLOW_KING){
                        item = ModItems.YELLOW_KING;
                    }
                    if(item == ModItems.CASTLE_YELLOW_TOWER){
                        item = ModItems.YELLOW_TOWER;
                    }
                    if(item == ModItems.CASTLE_PINK_KING){
                        item = ModItems.PINK_KING;
                    }
                    if(item == ModItems.CASTLE_PINK_TOWER){
                        item = ModItems.PINK_TOWER;
                    }

                    result = currentPosition;
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
        return result;
    }

    public static GlobalChessData takeWithFigure( World w, ItemFrameEntity frame) {
        GlobalChessData result = MovementCalculations.figureToData(frame);
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

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())) {
                Item item = FigureMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(), true);


                if (w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {

                    ItemStack stack3 = new ItemStack(item);
                    ItemEntity e3 = new ItemEntity(w, currentPosition.pos.getX(), currentPosition.pos.getY(), currentPosition.pos.getZ(), stack3);
                    w.spawnEntity(e3);

                }
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
                    rotation = entity.getHeldItemStack().getNbt().getInt("rotation");





                    if(e.getHeldItemStack().getItem() == ModItems.WHITE_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_KING){


                        String dead = "The White King is Dead!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true);


                    }
                    if(e.getHeldItemStack().getItem() == ModItems.BLACK_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_KING){
                        String dead = "The Black King is Dead!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true);
                    }


                    if(e.getHeldItemStack().getItem() == ModItems.YELLOW_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_KING){


                        String dead = "The Yellow King is Dead!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true);


                    }
                    if(e.getHeldItemStack().getItem() == ModItems.PINK_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_PINK_KING){
                        String dead = "The Pink King is Dead!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true);
                    }



                }

            }

        }
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) |
                    Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() )  || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() ) ){
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

                if(item == ModItems.START_YELLOW_PAWN ){
                    item = ModItems.YELLOW_PAWN;
                }
                if(item == ModItems.START_PINK_PAWN ){
                    item = ModItems.PINK_PAWN;
                }

                if(item == ModItems.CASTLE_YELLOW_KING){
                    item = ModItems.YELLOW_KING;
                }
                if(item == ModItems.CASTLE_YELLOW_TOWER){
                    item = ModItems.YELLOW_TOWER;
                }
                if(item == ModItems.CASTLE_PINK_KING){
                    item = ModItems.PINK_KING;
                }
                if(item == ModItems.CASTLE_PINK_TOWER){
                    item = ModItems.PINK_TOWER;
                }
                
                result = currentPosition;
                ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);


                e.setRotation(rotation);
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);
            }


        }
        return result;
    }



    public static void sendMessageToClosePlayers(World w, String s, int radius, BlockPos currentPosition, boolean alsoActionbar){

        List<PlayerEntity> list = w.getEntitiesByType(EntityType.PLAYER,new Box(currentPosition.getX()-radius,currentPosition.getY()-radius,currentPosition.getZ()-radius,currentPosition.getX()+radius,currentPosition.getY()+radius,currentPosition.getZ()+radius),EntityPredicates.VALID_ENTITY);
        for(PlayerEntity p : list){

            p.sendMessage(Text.literal(s),false);
            if(alsoActionbar){
                p.sendMessage(Text.literal(s),true);
            }
        }


    }





    public static GlobalChessData switchFigure( World w, ItemFrameEntity frame){
        GlobalChessData result1 = MovementCalculations.figureToData(frame);
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);

        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if (entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                w.getEntityById(entity.getId()).kill();
            }

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())){


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
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) ||
                    Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){

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

                if(item2 == ModItems.CASTLE_YELLOW_KING){
                    item2 = ModItems.YELLOW_KING;
                }
                if(item == ModItems.CASTLE_YELLOW_TOWER){
                    item = ModItems.YELLOW_TOWER;
                }
                if(item2 == ModItems.CASTLE_PINK_KING){
                    item2 = ModItems.PINK_KING;
                }
                if(item == ModItems.CASTLE_PINK_TOWER){
                    item = ModItems.PINK_TOWER;
                }

            ItemFrameEntity e = new ItemFrameEntity(w,newSwitchPiece.pos,newSwitchPiece.directionWall);
            ItemStack stack = new ItemStack(item);
            e.setHeldItemStack(stack);
            e.setRotation(newSwitchPiece.itemRotation);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
            result1 = newPiece;
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
    return result1;
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
