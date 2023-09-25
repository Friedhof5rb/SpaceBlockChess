package me.friedhof.chess.util.ChessBot;

import me.friedhof.chess.Chess;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.Calculations.checkCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.world.World;

public class Bot {

    int f;
    String team;
    BoardState state;


    public Bot(String team, World w, BoardState b) {
        this.team = team;
        this.state = b;

    }


    

    public TreeNode computeBestDirectMove(World w){
           TreeNode current = new TreeNode(this.state);
           current.computePossibleMoves(w,this.team);
           for(TreeNode t : current.possibleMoves){
                t.computePossibleMoves(w,this.team);
           }
           int v = -100000000;
            int index = 0;
            for(int i = 0; i < current.possibleMoves.size(); i++){
                TreeNode t = current.possibleMoves.get(i);
                if(t.value > v){
                    v = t.value;
                    index = i;
                }
            }


            if(current.possibleMoves.size() > 0) {
                return current.possibleMoves.get(index);
            }else{
                return current;
            }
    }






}
