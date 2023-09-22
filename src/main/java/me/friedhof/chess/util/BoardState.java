package me.friedhof.chess.util;

import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculations;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BoardState {

    public ArrayList<FigureOnBoard> allFiguresList;

    public BoardState(ArrayList<FigureOnBoard> allFiguresList) {
        this.allFiguresList = allFiguresList;
    }
    public BoardState() {
        this.allFiguresList = new ArrayList<>();
    }


    public static ArrayList<BoardState> allPossibleMoves(World w, BoardState b, String colour){
        ArrayList<BoardState> list = new ArrayList<>();

        clearList(colour);

        for(FigureOnBoard figure : b.allFiguresList){

            Item item2 = figure.item;
            GlobalChessData data = figure.data;


            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "tower")) {

                FigurePotentialMovesCalculations.calculateAllMovesForTower(w,data,colour, b);

            }
            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "bishop")) {
                FigurePotentialMovesCalculations.calculateAllMovesForBishop(w,data,colour,b);

            }
            if (item2 ==FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "knight")) {
                FigurePotentialMovesCalculations.calculateAllMovesForKnight(w,data,colour,b);

            }
            if (item2 ==FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "queen")) {

                FigurePotentialMovesCalculations.calculateAllMovesForQueen(w,data,colour,b);
            }
            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "king")) {
                FigurePotentialMovesCalculations.calculateAllMovesForKing(w,data,colour,b);

            }
            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "pawn")) {
                FigurePotentialMovesCalculations.calculateAllMovesForPawn(w,data,colour,b);

            }
            if (item2 ==FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "start_pawn")) {
                FigurePotentialMovesCalculations.calculateAllMovesForStartPawn(w,data,colour,b);

            }
            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "castle_king")) {
                FigurePotentialMovesCalculations.calculateAllMovesForCastleKing(w,data,colour,b);

            }
            if (item2 == FigurePotentialMovesCalculations.colourAndFigureTypeToItem(colour, "castle_tower")) {
                FigurePotentialMovesCalculations.calculateAllMovesForCastleTower(w,data,colour,b);

            }

            if (colour.equals("white")) {


                boolean isEmpty = FigurePotentialMovesCalculations.whitePotentialMoves.isEmpty();

                if (!isEmpty) {
                for (GlobalChessData data2 : FigurePotentialMovesCalculations.whitePotentialMoves) {
                    BoardState state = new BoardState();
                    for (FigureOnBoard figure2 : b.allFiguresList) {
                        if (figure2.data.pos.getX() == figure.data.pos.getX() && figure2.data.pos.getY() == figure.data.pos.getY() && figure2.data.pos.getZ() == figure.data.pos.getZ()
                                && figure2.data.directionWall == figure.data.directionWall) {

                            FigureOnBoard f = new FigureOnBoard(data2,figure.item);
                            state.allFiguresList.add(f);
                        } else {
                            state.allFiguresList.add(figure2);
                        }
                    }
                    list.add(state);
                }
            }



            }
            if (colour.equals("black")) {

                boolean isEmpty = FigurePotentialMovesCalculations.blackPotentialMoves.isEmpty();

                if (!isEmpty) {
                    for (GlobalChessData data2 : FigurePotentialMovesCalculations.blackPotentialMoves) {
                        BoardState state = new BoardState();
                        for (FigureOnBoard figure2 : b.allFiguresList) {
                            if (figure2.data.pos.getX() == figure.data.pos.getX() && figure2.data.pos.getY() == figure.data.pos.getY() && figure2.data.pos.getZ() == figure.data.pos.getZ()
                                    && figure2.data.directionWall == figure.data.directionWall) {

                                FigureOnBoard f = new FigureOnBoard(data2,figure.item);
                                state.allFiguresList.add(f);
                            } else {
                                state.allFiguresList.add(figure2);
                            }
                        }
                        list.add(state);
                    }
                }
            }
            if (colour.equals("yellow")) {

                boolean isEmpty = FigurePotentialMovesCalculations.yellowPotentialMoves.isEmpty();

                if (!isEmpty) {
                    for (GlobalChessData data2 : FigurePotentialMovesCalculations.yellowPotentialMoves) {
                        BoardState state = new BoardState();
                        for (FigureOnBoard figure2 : b.allFiguresList) {
                            if (figure2.data.pos.getX() == figure.data.pos.getX() && figure2.data.pos.getY() == figure.data.pos.getY() && figure2.data.pos.getZ() == figure.data.pos.getZ()
                                    && figure2.data.directionWall == figure.data.directionWall) {

                                FigureOnBoard f = new FigureOnBoard(data2,figure.item);
                                state.allFiguresList.add(f);
                            } else {
                                state.allFiguresList.add(figure2);
                            }
                        }
                        list.add(state);
                    }
                }
            }
            if (colour.equals("pink")) {

                boolean isEmpty = FigurePotentialMovesCalculations.pinkPotentialMoves.isEmpty();

                if (!isEmpty) {
                    for (GlobalChessData data2 : FigurePotentialMovesCalculations.pinkPotentialMoves) {
                        BoardState state = new BoardState();
                        for (FigureOnBoard figure2 : b.allFiguresList) {
                            if (figure2.data.pos.getX() == figure.data.pos.getX() && figure2.data.pos.getY() == figure.data.pos.getY() && figure2.data.pos.getZ() == figure.data.pos.getZ()
                                    && figure2.data.directionWall == figure.data.directionWall) {

                                FigureOnBoard f = new FigureOnBoard(data2,figure.item);
                                state.allFiguresList.add(f);
                            } else {
                                state.allFiguresList.add(figure2);
                            }
                        }
                        list.add(state);
                    }
                }

            }




            clearList(colour);
        }


        return list;

    }

    private static void clearList(String colour){

        if (colour.equals("white")) {

            FigurePotentialMovesCalculations.whitePotentialMoves.clear();
        }
        if (colour.equals("black")) {

            FigurePotentialMovesCalculations.blackPotentialMoves.clear();
        }
        if (colour.equals("yellow")) {

            FigurePotentialMovesCalculations.yellowPotentialMoves.clear();
        }
        if (colour.equals("pink")) {

            FigurePotentialMovesCalculations.pinkPotentialMoves.clear();
        }


    }







}
