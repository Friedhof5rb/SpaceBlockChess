package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class checkCalculations {



    public static ArrayList<FigureOnBoard> compareBoardStates(BoardState initial, BoardState changed){

        ArrayList<FigureOnBoard> list = new ArrayList<>();

        for(FigureOnBoard fc: changed.allFiguresList){
            boolean contains = false;
            for(FigureOnBoard fi : initial.allFiguresList){

                if(fc.data.pos.getX() == fi.data.pos.getX() && fc.data.pos.getY() == fi.data.pos.getY() && fc.data.pos.getZ() == fi.data.pos.getZ() && fc.data.directionWall == fi.data.directionWall){
                    contains = true;
                    if(  fc.stack.getItem() != fi.stack.getItem()){
                        list.add(fc);
                    }
                }
            }
            if(!contains){
                list.add(fc);
            }
        }
        return list;

    }






    public static boolean isKingOfColourInCheck(World w, String team, BoardState b){


        ArrayList<FigureOnBoard> kingsList = new ArrayList<>();


        for(FigureOnBoard f : b.allFiguresList){
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            if(Chess.itemMap.get(f.stack.getItem()).equals(sb.append(team).append( " king").toString()) || Chess.itemMap.get(f.stack.getItem()).equals(sb2.append(team).append( " castle_king").toString())){
                kingsList.add(f);
            }

        }

        FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();


            switch (team) {
                case "white" -> {
                    calc.calculateAllMovesForColour(w, "black", b);
                    calc.calculateAllMovesForColour(w, "yellow", b);
                    calc.calculateAllMovesForColour(w, "pink", b);
                    calc.whitePotentialMoves.clear();
                }
                case "black" -> {
                    calc.calculateAllMovesForColour(w, "white", b);
                    calc.calculateAllMovesForColour(w, "yellow", b);
                    calc.calculateAllMovesForColour(w, "pink", b);
                    calc.blackPotentialMoves.clear();

                }
                case "yellow" -> {
                    calc.calculateAllMovesForColour(w, "white", b);
                    calc.calculateAllMovesForColour(w, "black", b);
                    calc.calculateAllMovesForColour(w, "pink", b);
                    calc.yellowPotentialMoves.clear();
                }
                case "pink" -> {
                    calc.calculateAllMovesForColour(w, "white", b);
                    calc.calculateAllMovesForColour(w, "yellow", b);
                    calc.calculateAllMovesForColour(w, "black", b);
                    calc.pinkPotentialMoves.clear();
                }
            }


            for (FigureOnBoard king : kingsList) {
                for (GlobalChessData data : calc.whitePotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : calc.blackPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : calc.yellowPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : calc.pinkPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }

            }

        return false;
    }


    public static boolean isKingOfColourInPotentialCheck(World w, String team, BoardState b) {

        ArrayList<BoardState> possibleMoves = b.allPossibleMoves(w,team);

        int length = possibleMoves.size();
        if(length == 0){
            return false;
        }
        int count = 0;
        for(BoardState move : possibleMoves){
            if(isKingOfColourInCheck(w,team,move)){
                count += 1;
            }
        }
        return count == length;
    }

    public static BoardState getCurrentBoardState(World w,GlobalChessData currentPosition){
        ArrayList<FigureOnBoard> allFiguresList = new ArrayList<>();
        int radius =  50;
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(currentPosition.pos.getX()-radius,currentPosition.pos.getY()-radius,currentPosition.pos.getZ()-radius,currentPosition.pos.getX()+radius,currentPosition.pos.getY()+radius,currentPosition.pos.getZ()+radius), EntityPredicates.VALID_ENTITY);
        for(ItemFrameEntity entity : list){
            ItemStack item = entity.getHeldItemStack();
            if(Chess.arrayContains(UseEntityHandler.whitePieces,item.getItem()) || Chess.arrayContains(UseEntityHandler.blackPieces,item.getItem()) |
                    Chess.arrayContains(UseEntityHandler.yellowPieces,item.getItem()) || Chess.arrayContains(UseEntityHandler.pinkPieces,item.getItem())) {


                FigureOnBoard figure = new FigureOnBoard(MovementCalculations.figureToData(entity), item);
                allFiguresList.add(figure);
            }
        }
        return new BoardState(allFiguresList);
    }









}
