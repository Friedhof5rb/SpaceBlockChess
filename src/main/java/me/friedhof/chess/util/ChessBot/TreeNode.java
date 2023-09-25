package me.friedhof.chess.util.ChessBot;

import com.ibm.icu.impl.UResource;
import me.friedhof.chess.Chess;
import me.friedhof.chess.util.BoardState;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TreeNode {

    BoardState state;
    ArrayList<TreeNode> possibleMoves;
    int value;


    public TreeNode(BoardState state) {
        this.state = state;

    }
    private String nextColour(String team){

        for(int i = 0; i< Chess.turnOrder.length; i++){
            if(i != Chess.turnOrder.length-1) {
                if (Chess.turnOrder[i].equals(team)) {
                    return Chess.turnOrder[i+1];
                }
            }else{
                if (Chess.turnOrder[i].equals(team)) {
                    return Chess.turnOrder[0];
                }
            }
        }
        return "";
    }

    public void computePossibleMoves(World w, String team){

        possibleMoves = new ArrayList<>();
        ArrayList<BoardState> list = BoardState.allPossibleMoves(w,this.state,team, false);
        for(BoardState b : list) {
            possibleMoves.add(new TreeNode(b));
        }
        this.value = possibleMoves.size()-BoardState.allPossibleMoves(w,this.state,nextColour(team), false).size();;

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
