package me.friedhof.chess.util.ChessBot;

import com.ibm.icu.impl.UResource;
import io.netty.channel.unix.UnixChannelUtil;
import me.friedhof.chess.Chess;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.Calculations.checkCalculations;
import me.friedhof.chess.util.FigureOnBoard;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TreeNode {

    BoardState state;
    ArrayList<TreeNode> possibleMoves;
    int value;

    boolean lookedAtAlready =  false;

    public TreeNode(BoardState state) {
        this.state = state;

    }

    int king = 900;
    int queen = 90;
    int tower = 50;
    int bishop = 30;
    int knight = 30;
    int pawn = 10;




    public void computePossibleMoves(World w, String team){

        possibleMoves = new ArrayList<>();

        ArrayList<BoardState> list = this.state.allPossibleMoves(w,team);
        for(BoardState b : list) {
            if(!checkCalculations.isKingOfColourInCheck(w,team,b)) {
                possibleMoves.add(new TreeNode(b));
            }
        }

    }

    public void computeTotalValue(String team) {
        switch (team) {
            case "white" -> this.value = computeValue("white")-computeValue("black")-computeValue("yellow")-computeValue("pink");
            case "black" -> this.value = computeValue("black")-computeValue("white")-computeValue("yellow")-computeValue("pink");
            case "yellow" -> this.value = computeValue("yellow")-computeValue("black")-computeValue("white")-computeValue("pink");
            case "pink" -> this.value = computeValue("pink")-computeValue("black")-computeValue("yellow")-computeValue("white");
        }
    }


    public boolean hasKing(String team) {
        boolean hasKing = false;
        for (FigureOnBoard f : state.allFiguresList) {
            StringBuilder sb = new StringBuilder();
            if (Chess.itemMap.get(f.item).equals(sb.append(team).append(" king").toString())) {
                hasKing = true;
            }
            sb = new StringBuilder();
            if (Chess.itemMap.get(f.item).equals(sb.append(team).append(" castle_king").toString())) {
                hasKing = true;
            }

        }
       return hasKing;
    }



    private int computeValue(String team){

        int v= 0;

        for(FigureOnBoard f : state.allFiguresList){
            StringBuilder sb = new StringBuilder();

            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " king").toString())){
                v+= 900;
            }
             sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " queen").toString())){
                v+= 90;
            }
             sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " bishop").toString())){
                v+= 30;
            }
             sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " tower").toString())){
                v+= 50;
            }
            sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " pawn").toString())){
                v+= 10;
            }
            sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " knight").toString())){
                v+= 30;
            }
            sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " castle_king").toString())){
                v+= 900;
            }
            sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " castle_tower").toString())){
                v+= 50;
            }
            sb = new StringBuilder();
            if(Chess.itemMap.get(f.item).equals(sb.append(team).append( " start_pawn").toString())){
                v+= 10;
            }

        }

        return v;

    }





    public BoardState getState() {
        return state;
    }

    public void setState(BoardState state) {
        this.state = state;
    }

    public ArrayList<TreeNode> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<TreeNode> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

}
