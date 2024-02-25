package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.Calculations.FigurePotentialMovesCalculations;
import me.friedhof.chess.util.Calculations.checkCalculations;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class BoardState {

    public ArrayList<FigureOnBoard> allFiguresList;

    public BoardState(ArrayList<FigureOnBoard> allFiguresList) {
        this.allFiguresList = allFiguresList;
    }
    public BoardState() {
        this.allFiguresList = new ArrayList<>();
    }


    public ArrayList<BoardState> allPossibleMoves(World w, String colour){
        ArrayList<BoardState> list = new ArrayList<>();

            BoardState b = this;
            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();

            clearList(colour, calc);
            for (int i = 0; i < b.allFiguresList.size(); i++) {

                FigureOnBoard figure = b.allFiguresList.get(i);
                ItemStack item2 = figure.stack;
                GlobalChessData data = figure.data;

                if (!Chess.itemMap.containsKey(item2.getItem())) {
                    continue;
                }

                if (!Chess.ItemToColour(item2.getItem()).equals(colour)) {
                    continue;
                }

                switch (Chess.itemMap.get(item2.getItem())) {
                    case "white tower", "black tower", "yellow tower", "pink tower" -> calc.calculateAllMovesForTower(w, data, colour, b);
                    case "white bishop", "black bishop", "yellow bishop", "pink bishop" ->
                            calc.calculateAllMovesForBishop(w, data, colour, b);
                    case "white king", "black king", "yellow king", "pink king" -> calc.calculateAllMovesForKing(w, data, colour, b);
                    case "white queen", "black queen", "yellow queen", "pink queen" -> calc.calculateAllMovesForQueen(w, data, colour, b);
                    case "white knight", "black knight", "yellow knight", "pink knight" ->
                            calc.calculateAllMovesForKnight(w, data, colour, b);
                    case "white pawn", "black pawn", "yellow pawn", "pink pawn" -> calc.calculateAllMovesForPawn(w, data, colour, b);
                    case "white start_pawn", "black start_pawn", "yellow start_pawn", "pink start_pawn" ->
                            calc.calculateAllMovesForStartPawn(w, data, colour, b);
                    case "white castle_king", "black castle_king", "yellow castle_king", "pink castle_king" ->
                            calc.calculateAllMovesForCastleKing(w, data, colour, b);
                    case "white castle_tower", "black castle_tower", "yellow castle_tower", "pink castle_tower" ->
                            calc.calculateAllMovesForCastleTower(w, data, colour, b);
                }


                switch (colour) {
                    case "white" -> {

                        boolean isEmpty = calc.whitePotentialMoves.isEmpty();

                        if (!isEmpty) {
                            for (GlobalChessData data2 : calc.whitePotentialMoves) {
                                BoardState state = new BoardState();

                                
                                
                                state.allFiguresList.addAll(b.allFiguresList);
                                state.allFiguresList.remove(b.allFiguresList.get(i));

                                ItemStack newItem;
                                if(figure.stack.getItem() == ModItems.START_WHITE_PAWN){
                                    newItem = new ItemStack(ModItems.WHITE_PAWN);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_WHITE_KING){
                                    newItem = new ItemStack(ModItems.WHITE_KING);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_WHITE_TOWER){
                                    newItem = new ItemStack(ModItems.WHITE_TOWER);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else{
                                    newItem = figure.stack;
                                }
                                
                                
                                FigureOnBoard f = new FigureOnBoard(data2, newItem);
                                
                                for( FigureOnBoard f2 : b.allFiguresList){
                                    if(f2.data.pos.getX() == f.data.pos.getX() && f2.data.pos.getY() == f.data.pos.getY() && f2.data.pos.getZ() == f.data.pos.getZ()
                                            && f2.data.directionWall== f.data.directionWall) {
                                        state.allFiguresList.remove(f2);
                                    }
                                }
                                state.allFiguresList.add(f);

                                list.add(state);
                            }
                        }

                    }
                    case "black" -> {

                        boolean isEmpty = calc.blackPotentialMoves.isEmpty();

                        if (!isEmpty) {
                            for (GlobalChessData data2 : calc.blackPotentialMoves) {
                                BoardState state = new BoardState();


                                state.allFiguresList.addAll(b.allFiguresList);
                                state.allFiguresList.remove(b.allFiguresList.get(i));

                                ItemStack newItem;
                                if(figure.stack.getItem() == ModItems.START_BLACK_PAWN){
                                    newItem = new ItemStack(ModItems.BLACK_PAWN);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_BLACK_KING){
                                    newItem = new ItemStack(ModItems.BLACK_KING);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_BLACK_TOWER){
                                    newItem = new ItemStack(ModItems.BLACK_TOWER);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else{
                                   newItem = figure.stack;
                                }
                                
                                FigureOnBoard f = new FigureOnBoard(data2, newItem);
                                for( FigureOnBoard f2 :  b.allFiguresList){
                                    if(f2.data.pos.getX() == f.data.pos.getX() && f2.data.pos.getY() == f.data.pos.getY() && f2.data.pos.getZ() == f.data.pos.getZ()
                                    && f2.data.directionWall== f.data.directionWall) {
                                        state.allFiguresList.remove(f2);
                                    }
                                }
                                state.allFiguresList.add(f);

                                list.add(state);
                            }
                        }
                    }
                    case "yellow" -> {

                        boolean isEmpty = calc.yellowPotentialMoves.isEmpty();

                        if (!isEmpty) {
                            for (GlobalChessData data2 : calc.yellowPotentialMoves) {
                                BoardState state = new BoardState();

                                state.allFiguresList.addAll(b.allFiguresList);
                                state.allFiguresList.remove(b.allFiguresList.get(i));

                                ItemStack newItem;
                                if(figure.stack.getItem() == ModItems.START_YELLOW_PAWN){
                                    newItem = new ItemStack(ModItems.YELLOW_PAWN);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_YELLOW_KING){
                                    newItem = new ItemStack(ModItems.YELLOW_KING);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_YELLOW_TOWER){
                                    newItem = new ItemStack(ModItems.YELLOW_TOWER);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else{
                                    newItem = figure.stack;
                                }
                                
                                FigureOnBoard f = new FigureOnBoard(data2,newItem);
                                for( FigureOnBoard f2 :  b.allFiguresList){
                                    if(f2.data.pos.getX() == f.data.pos.getX() && f2.data.pos.getY() == f.data.pos.getY() && f2.data.pos.getZ() == f.data.pos.getZ()
                                            && f2.data.directionWall== f.data.directionWall) {
                                        state.allFiguresList.remove(f2);
                                    }
                                }
                                state.allFiguresList.add(f);

                                list.add(state);
                            }
                        }
                    }
                    case "pink" -> {

                        boolean isEmpty = calc.pinkPotentialMoves.isEmpty();

                        if (!isEmpty) {
                            for (GlobalChessData data2 : calc.pinkPotentialMoves) {
                                BoardState state = new BoardState();

                                state.allFiguresList.addAll(b.allFiguresList);
                                state.allFiguresList.remove(b.allFiguresList.get(i));

                                ItemStack newItem;
                                if(figure.stack.getItem() == ModItems.START_PINK_PAWN){
                                    newItem = new ItemStack(ModItems.PINK_PAWN);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_PINK_KING){
                                    newItem = new ItemStack(ModItems.PINK_KING);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else if(figure.stack.getItem() == ModItems.CASTLE_PINK_TOWER){
                                    newItem = new ItemStack(ModItems.PINK_TOWER);
                                    newItem.setNbt(figure.stack.getNbt());
                                }else{
                                    newItem = figure.stack;
                                }
                                
                                
                                FigureOnBoard f = new FigureOnBoard(data2, newItem);
                                for( FigureOnBoard f2 :  b.allFiguresList){
                                    if(f2.data.pos.getX() == f.data.pos.getX() && f2.data.pos.getY() == f.data.pos.getY() && f2.data.pos.getZ() == f.data.pos.getZ()
                                            && f2.data.directionWall== f.data.directionWall) {
                                        state.allFiguresList.remove(f2);
                                    }
                                }
                                state.allFiguresList.add(f);

                                list.add(state);
                            }
                        }

                    }
                }


                clearList("white", calc);
                clearList("black", calc);
                clearList("yellow", calc);
                clearList("pink", calc);
            }


        return list;

    }



    public boolean containsKing(String team){
        boolean containsKing = false;

        for(FigureOnBoard f : this.allFiguresList){
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            if(Chess.itemMap.get(f.stack.getItem()).equals(sb.append(team).append( " king").toString()) || Chess.itemMap.get(f.stack.getItem()).equals(sb2.append(team).append( " castle_king").toString())){
              containsKing = true;
            }

        }


        return containsKing;
    }





    private static void clearList(String colour, FigurePotentialMovesCalculations calc){

        switch (colour) {
            case "white" -> calc.whitePotentialMoves.clear();
            case "black" -> calc.blackPotentialMoves.clear();
            case "yellow" -> calc.yellowPotentialMoves.clear();
            case "pink" -> calc.pinkPotentialMoves.clear();
        }


    }

    public String getColourOfFigure(GlobalChessData data){

        for(FigureOnBoard f: allFiguresList){
            if(data.pos.getX() == f.data.pos.getX() &&  data.pos.getY() == f.data.pos.getY() && data.pos.getZ() == f.data.pos.getZ()
            && data.directionWall == f.data.directionWall){
                return Chess.ItemToColour(f.stack.getItem());
            }
        }
        return "";

    }













}
