package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
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
                    if(  fc.item != fi.item){
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






    public static boolean isKingOfColourInCheck(World w, String team, BoardState b, boolean forShow){


        ArrayList<FigureOnBoard> kingsList = new ArrayList<>();

        ArrayList<FigureOnBoard> allFiguresList = new ArrayList<>(b.allFiguresList);

        for(FigureOnBoard f : b.allFiguresList){
            StringBuilder sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " king").toString())){
                kingsList.add(f);
            }

        }


        if(!forShow) {
            switch (team) {
                case "white" -> {
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "black", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "yellow", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "pink", allFiguresList, b);
                    FigurePotentialMovesCalculations.whitePotentialMoves.clear();
                }
                case "black" -> {
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "white", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "yellow", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "pink", allFiguresList, b);
                    FigurePotentialMovesCalculations.blackPotentialMoves.clear();

                }
                case "yellow" -> {
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "white", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "black", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "pink", allFiguresList, b);
                    FigurePotentialMovesCalculations.yellowPotentialMoves.clear();
                }
                case "pink" -> {
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "white", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "yellow", allFiguresList, b);
                    FigurePotentialMovesCalculations.calculateAllMovesForColour(w, "black", allFiguresList, b);
                    FigurePotentialMovesCalculations.pinkPotentialMoves.clear();
                }
            }


            for (FigureOnBoard king : kingsList) {
                for (GlobalChessData data : FigurePotentialMovesCalculations.whitePotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculations.blackPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculations.yellowPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculations.pinkPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }

            }
        }else{
            switch (team) {
                case "white" -> {
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "black", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "yellow", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "pink", b);
                    FigurePotentialMovesCalculationsForShow.whitePotentialMoves.clear();
                }
                case "black" -> {
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "white", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "yellow", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "pink", b);
                    FigurePotentialMovesCalculationsForShow.blackPotentialMoves.clear();

                }
                case "yellow" -> {
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "white", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "black", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "pink", b);
                    FigurePotentialMovesCalculationsForShow.yellowPotentialMoves.clear();
                }
                case "pink" -> {
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "white", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "yellow", b);
                    FigurePotentialMovesCalculationsForShow.calculateAllMovesForColour(w, "black", b);
                    FigurePotentialMovesCalculationsForShow.pinkPotentialMoves.clear();
                }
            }


            for (FigureOnBoard king : kingsList) {
                for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.whitePotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.blackPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.yellowPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }
                for (GlobalChessData data : FigurePotentialMovesCalculationsForShow.pinkPotentialMoves) {
                    if (data.pos.getX() == king.data.pos.getX() && data.pos.getY() == king.data.pos.getY() && data.pos.getZ() == king.data.pos.getZ()
                            && data.directionWall == king.data.directionWall) {
                        return true;
                    }
                }

            }


        }


        return false;
    }


    public static boolean isKingOfColourInPotentialCheck(World w, String team, GlobalChessData currentPosition, BoardState b, boolean forShow) {

        ArrayList<BoardState> possibleMoves = BoardState.allPossibleMoves(w,b,team,forShow);

        int length = possibleMoves.size();
        if(length == 0){
            return false;
        }
        int count = 0;
       // System.out.println(length);
        for(BoardState move : possibleMoves){
            if(isKingOfColourInCheck(w,team,move,forShow)){
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
            FigureOnBoard figure = new FigureOnBoard(MovementCalculations.figureToData(entity),entity.getHeldItemStack().getItem());
            allFiguresList.add(figure);
        }
        return new BoardState(allFiguresList);
    }









}
