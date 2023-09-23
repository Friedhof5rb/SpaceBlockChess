package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculations;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

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

        for(int i = 0;i < b.allFiguresList.size(); i++){

            FigureOnBoard figure = b.allFiguresList.get(i);
            Item item2 = figure.item;
            GlobalChessData data = figure.data;

            if(!Chess.itemMap.containsKey(item2)) {
                continue;
            }

            if(!Chess.ItemToColour(item2).equals(colour)) {
                continue;
            }

            switch (Chess.itemMap.get(item2)) {
                case "white tower", "black tower", "yellow tower", "pink tower" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForTower(w, data, colour, b);
                case "white bishop", "black bishop", "yellow bishop", "pink bishop" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForBishop(w, data, colour, b);
                case "white king", "black king", "yellow king", "pink king" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForKing(w, data, colour, b);
                case "white queen", "black queen", "yellow queen", "pink queen" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForQueen(w, data, colour, b);
                case "white knight", "black knight", "yellow knight", "pink knight" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForKnight(w, data, colour, b);
                case "white pawn", "black pawn", "yellow pawn", "pink pawn" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForPawn(w, data, colour, b);
                case "white start_pawn", "black start_pawn", "yellow start_pawn", "pink start_pawn" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForStartPawn(w, data, colour, b);
                case "white castle_king", "black castle_king", "yellow castle_king", "pink castle_king" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForCastleKing(w, data, colour, b);
                case "white castle_tower", "black castle_tower", "yellow castle_tower", "pink castle_tower" ->
                        FigurePotentialMovesCalculations.calculateAllMovesForCastleTower(w, data, colour, b);
            }


            switch (colour) {
                case "white" -> {

                    boolean isEmpty = FigurePotentialMovesCalculations.whitePotentialMoves.isEmpty();

                    if (!isEmpty) {
                        for (GlobalChessData data2 : FigurePotentialMovesCalculations.whitePotentialMoves) {
                            BoardState state = new BoardState();

                            state.allFiguresList.addAll(b.allFiguresList);
                            state.allFiguresList.remove(b.allFiguresList.get(i));
                            FigureOnBoard f = new FigureOnBoard(data2, figure.item);
                            state.allFiguresList.add(i,f);

                            list.add(state);
                        }
                    }

                }
                case "black" -> {

                    boolean isEmpty = FigurePotentialMovesCalculations.blackPotentialMoves.isEmpty();

                    if (!isEmpty) {
                        for (GlobalChessData data2 : FigurePotentialMovesCalculations.blackPotentialMoves) {
                            BoardState state = new BoardState();

                            state.allFiguresList.addAll(b.allFiguresList);
                            state.allFiguresList.remove(b.allFiguresList.get(i));
                            FigureOnBoard f = new FigureOnBoard(data2, figure.item);
                            state.allFiguresList.add(i,f);

                            list.add(state);
                        }
                    }
                }
                case "yellow" -> {

                    boolean isEmpty = FigurePotentialMovesCalculations.yellowPotentialMoves.isEmpty();

                    if (!isEmpty) {
                        for (GlobalChessData data2 : FigurePotentialMovesCalculations.yellowPotentialMoves) {
                            BoardState state = new BoardState();

                            state.allFiguresList.addAll(b.allFiguresList);
                            state.allFiguresList.remove(b.allFiguresList.get(i));
                            FigureOnBoard f = new FigureOnBoard(data2, figure.item);
                            state.allFiguresList.add(i,f);

                            list.add(state);
                        }
                    }
                }
                case "pink" -> {

                    boolean isEmpty = FigurePotentialMovesCalculations.pinkPotentialMoves.isEmpty();

                    if (!isEmpty) {
                        for (GlobalChessData data2 : FigurePotentialMovesCalculations.pinkPotentialMoves) {
                            BoardState state = new BoardState();

                            state.allFiguresList.addAll(b.allFiguresList);
                            state.allFiguresList.remove(b.allFiguresList.get(i));
                            FigureOnBoard f = new FigureOnBoard(data2, figure.item);
                            state.allFiguresList.add(i,f);

                            list.add(state);
                        }
                    }

                }
            }




            clearList(colour);
        }


        return list;

    }

    private static void clearList(String colour){

        switch (colour) {
            case "white" -> FigurePotentialMovesCalculations.whitePotentialMoves.clear();
            case "black" -> FigurePotentialMovesCalculations.blackPotentialMoves.clear();
            case "yellow" -> FigurePotentialMovesCalculations.yellowPotentialMoves.clear();
            case "pink" -> FigurePotentialMovesCalculations.pinkPotentialMoves.clear();
        }


    }







}
