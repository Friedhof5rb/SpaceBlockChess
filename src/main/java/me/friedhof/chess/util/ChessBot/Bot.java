package me.friedhof.chess.util.ChessBot;

import com.sun.source.tree.Tree;
import me.friedhof.chess.Chess;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.Calculations.checkCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.world.World;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.Objects;

public class Bot {

    int f;
    String team;
    BoardState state;

    World w;

    public Bot(String team, World w, BoardState b) {
        this.team = team;
        this.state = b;
        this.w = w;

    }


    public int toNegative(int n){

        if(n > 0){
            return  n*-1;
        }
        return n;
    }






    public TreeNode computeBestMove(){
        
        TreeNode current = new TreeNode(this.state);

        current.computePossibleMoves(w,team);
        int v = -10000000;
        TreeNode t0 = null;
        for(TreeNode t: current.getPossibleMoves()){
            int score = maxi(2,t,-1000000000,1000000000);
            if(score > v){
                v = score;
                t0 = t;
            }
        }

        if(t0 == null){
            return current;
        }else{
            return t0;
        }

    }














    int maxi(int depth, TreeNode t , int alpha, int beta) {
        if ( depth == 0 ) {
           t.computeTotalValue(team);
           return t.value;
        }
        t.computePossibleMoves(w,team);
        for (TreeNode t2: t.possibleMoves) {
           int  score = mini( depth - 1,t2 ,  alpha,  beta);
            if( score >= beta ){
                return beta;
            }
            if(score > alpha){
                alpha = score;
            }

        }
        return alpha;
    }

    int mini( int depth, TreeNode t , int alpha, int beta) {
        if ( depth == 0 ) {
            t.computeTotalValue(nextColour(team));
            return t.value;
        }

        t.computePossibleMoves(w,nextColour(team));
        for (TreeNode t2: t.possibleMoves) {
            int score = maxi( depth - 1,t2 ,  alpha,  beta);
            if( score <= alpha ){
                return alpha;
            }
            if(score < beta){
                beta = score;
            }


        }
        return beta;
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


    








}
