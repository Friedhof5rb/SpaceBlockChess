package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FigurePotentialMovesCalculationsForShow {


    public static ArrayList<GlobalChessData> whitePotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> blackPotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> yellowPotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> pinkPotentialMoves = new ArrayList<>();




    private static boolean isContainedInBoardState(GlobalChessData currentPosition, BoardState b){

        for(FigureOnBoard f : b.allFiguresList){
            if(currentPosition.pos.getX() == f.data.pos.getX() && currentPosition.pos.getY() == f.data.pos.getY() && currentPosition.pos.getZ() == f.data.pos.getZ() &&
                    currentPosition.directionWall == f.data.directionWall){
                return true;
            }
        }

        return false;
    }

    private static Item getItemFromBoard(GlobalChessData currentPosition, BoardState b){

        for(FigureOnBoard f : b.allFiguresList){
            if(currentPosition.pos.getX() == f.data.pos.getX() && currentPosition.pos.getY() == f.data.pos.getY() && currentPosition.pos.getZ() == f.data.pos.getZ() &&
                    currentPosition.directionWall == f.data.directionWall){
                return f.item;
            }
        }

        return Items.AIR;
    }





    public static void towerMoveScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team, BoardState b){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if( isContainedInBoardState(currentPosition,b)){

                //stop at enemy figure
                replaceOldFigure(currentPosition,team,b);
                break;

            }else{
                //go on
                switch(team){
                    case "white":{
                        whitePotentialMoves.add(currentPosition);

                    }
                    case "black":{
                        blackPotentialMoves.add(currentPosition);

                    }
                    case "yellow":{
                        yellowPotentialMoves.add(currentPosition);

                    }
                    case "pink":{
                        pinkPotentialMoves.add(currentPosition);

                    }
                }





            }
        }
    }

    public static void castlingScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team, BoardState b){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(isContainedInBoardState(currentPosition,b)){

                replaceOldFigureForCastling(currentPosition,team,b);
                break;

            }
        }


    }











    public static void bishopMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


            if(currentPosition == null){
                return;
            }

            if(isContainedInBoardState(currentPosition,b)){

                replaceOldFigure(currentPosition,team,b);
                break;

            }else{
                switch(team){
                    case "white":{
                        whitePotentialMoves.add(currentPosition);

                    }
                    case "black":{
                        blackPotentialMoves.add(currentPosition);

                    }
                    case "yellow":{
                        yellowPotentialMoves.add(currentPosition);

                    }
                    case "pink":{
                        pinkPotentialMoves.add(currentPosition);

                    }
                }
            }
        }
    }

    public static void knightMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


        if(currentPosition == null){
            return;
        }

        if(isContainedInBoardState(currentPosition,b)){

            replaceOldFigure(currentPosition,team,b);

        }else{
            switch(team){
                case "white":{
                    whitePotentialMoves.add(currentPosition);

                }
                case "black":{
                    blackPotentialMoves.add(currentPosition);

                }
                case "yellow":{
                    yellowPotentialMoves.add(currentPosition);

                }
                case "pink":{
                    pinkPotentialMoves.add(currentPosition);

                }
            }
        }

    }


    public static void queenMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
            if(relativeDirection2 != Direction.UP) {
                currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
            }


            if(currentPosition == null){
                return;
            }
            if(isContainedInBoardState(currentPosition,b)){

                replaceOldFigure(currentPosition,team,b);
                break;

            }else{
                switch(team){
                    case "white":{
                        whitePotentialMoves.add(currentPosition);

                    }
                    case "black":{
                        blackPotentialMoves.add(currentPosition);

                    }
                    case "yellow":{
                        yellowPotentialMoves.add(currentPosition);

                    }
                    case "pink":{
                        pinkPotentialMoves.add(currentPosition);

                    }
                }
            }
        }
    }



    public static void kingMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2,String team, BoardState b){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        if(relativeDirection2 != Direction.UP) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
        }

        if(currentPosition == null){
            return;
        }
        if(isContainedInBoardState(currentPosition,b)){

            replaceOldFigure(currentPosition,team,b);
        }else{
            switch(team){
                case "white":{
                    whitePotentialMoves.add(currentPosition);

                }
                case "black":{
                    blackPotentialMoves.add(currentPosition);

                }
                case "yellow":{
                    yellowPotentialMoves.add(currentPosition);

                }
                case "pink":{
                    pinkPotentialMoves.add(currentPosition);

                }
            }
        }

    }



    public static void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, String team, BoardState b) {


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);


        if (currentPosition != null) {
            if (!isContainedInBoardState(currentPosition,b)) {
                switch(team){
                    case "white":{
                        whitePotentialMoves.add(currentPosition);

                    }
                    case "black":{
                        blackPotentialMoves.add(currentPosition);

                    }
                    case "yellow":{
                        yellowPotentialMoves.add(currentPosition);

                    }
                    case "pink":{
                        pinkPotentialMoves.add(currentPosition);

                    }
                }
            }
        }


        //1
        if (takingPosition1 != null) {
            if (isContainedInBoardState(currentPosition,b)) {



                replaceOldFigure(takingPosition1,team,b);


            }
        }
        //2
        if(takingPosition2 != null) {
            if (isContainedInBoardState(currentPosition,b)) {



                replaceOldFigure(takingPosition2,team,b);


            }
        }






    }


    public static void startPawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1,Direction relativeDirection2, String team, BoardState b){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);

        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);
        if(relativeDirection2 != Direction.UP) {

            if(!isContainedInBoardState(currentPosition,b)) {
                currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
            }
        }




        if(currentPosition != null) {
            if (!isContainedInBoardState(currentPosition,b)) {
                switch(team){
                    case "white":{
                        whitePotentialMoves.add(currentPosition);

                    }
                    case "black":{
                        blackPotentialMoves.add(currentPosition);

                    }
                    case "yellow":{
                        yellowPotentialMoves.add(currentPosition);

                    }
                    case "pink":{
                        pinkPotentialMoves.add(currentPosition);

                    }
                }
            }
        }
        //1
        if(takingPosition1 != null) {
            if (isContainedInBoardState(currentPosition,b)) {

                replaceOldFigure(takingPosition1,team,b);

            }
        }
        //2
        if(takingPosition2 != null) {
            if (isContainedInBoardState(currentPosition,b)) {

                replaceOldFigure(takingPosition2,team,b);
            }
        }



    }

////////////////////////////////////////////////////////

    private static void replaceOldFigureForCastling(GlobalChessData currentPosition, String team, BoardState b){

        if(getItemFromBoard(currentPosition,b) == ModItems.CASTLE_WHITE_TOWER && Objects.equals(team, "white")){
            whitePotentialMoves.add(currentPosition);
        }
        if(getItemFromBoard(currentPosition,b)  == ModItems.CASTLE_BLACK_TOWER && Objects.equals(team, "black")){
            blackPotentialMoves.add(currentPosition);
        }

        if(getItemFromBoard(currentPosition,b)  == ModItems.CASTLE_YELLOW_TOWER && Objects.equals(team, "yellow")){
            yellowPotentialMoves.add(currentPosition);
        }
        if(getItemFromBoard(currentPosition,b)  == ModItems.CASTLE_PINK_TOWER && Objects.equals(team, "pink")){
            pinkPotentialMoves.add(currentPosition);
        }

    }






    private static void replaceOldFigure( GlobalChessData currentPosition, String team, BoardState b){

        if(!Chess.arrayContains(UseEntityHandler.whitePieces,getItemFromBoard(currentPosition,b) ) && Objects.equals(team, "white")){
            whitePotentialMoves.add(currentPosition);

        }
        if(!Chess.arrayContains(UseEntityHandler.blackPieces,getItemFromBoard(currentPosition,b) ) && Objects.equals(team, "black")){
            blackPotentialMoves.add(currentPosition);

        }
        if(!Chess.arrayContains(UseEntityHandler.yellowPieces,getItemFromBoard(currentPosition,b) ) && Objects.equals(team, "yellow")){
            yellowPotentialMoves.add(currentPosition);

        }
        if(!Chess.arrayContains(UseEntityHandler.pinkPieces,getItemFromBoard(currentPosition,b) ) && Objects.equals(team, "pink")){
            pinkPotentialMoves.add(currentPosition);

        }

    }

    public static void calculateAllMovesForColour(World w, String colour,  BoardState b){

        switch (colour) {
            case "white" -> whitePotentialMoves.clear();
            case "black" -> blackPotentialMoves.clear();
            case "yellow" -> yellowPotentialMoves.clear();
            case "pink" -> pinkPotentialMoves.clear();
        }


        for (FigureOnBoard figure : b.allFiguresList) {
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
                        calculateAllMovesForTower(w, data, colour, b);
                case "white bishop", "black bishop", "yellow bishop", "pink bishop" ->
                        calculateAllMovesForBishop(w, data, colour, b);
                case "white king", "black king", "yellow king", "pink king" ->
                        calculateAllMovesForKing(w, data, colour, b);
                case "white queen", "black queen", "yellow queen", "pink queen" ->
                        calculateAllMovesForQueen(w, data, colour, b);
                case "white knight", "black knight", "yellow knight", "pink knight" ->
                        calculateAllMovesForKnight(w, data, colour, b);
                case "white pawn", "black pawn", "yellow pawn", "pink pawn" ->
                        calculateAllMovesForPawn(w, data, colour, b);
                case "white start_pawn", "black start_pawn", "yellow start_pawn", "pink start_pawn" ->
                        calculateAllMovesForStartPawn(w, data, colour, b);
                case "white castle_king", "black castle_king", "yellow castle_king", "pink castle_king" ->
                        calculateAllMovesForCastleKing(w, data, colour, b);
                case "white castle_tower", "black castle_tower", "yellow castle_tower", "pink castle_tower" ->
                        calculateAllMovesForCastleTower(w, data, colour, b);
            }


        }

    }

    public static void calculateAllMovesForTower(World w, GlobalChessData data, String team,  BoardState b){

        towerMoveScheme(w, data, Direction.NORTH, team,b);
        towerMoveScheme(w, data, Direction.SOUTH, team,b);
        towerMoveScheme(w, data, Direction.WEST, team,b);
        towerMoveScheme(w, data, Direction.EAST, team,b);




    }

    public static void calculateAllMovesForBishop(World w, GlobalChessData data, String team,  BoardState b) {

        bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);



    }

    public static void calculateAllMovesForKnight(World w, GlobalChessData data, String team,  BoardState b) {

        knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, team,b);
        knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, team,b);
        knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);
        knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, team,b);
        knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, team,b);

    }

    public static void calculateAllMovesForQueen(World w, GlobalChessData data, String team,  BoardState b) {
        queenMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.EAST, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.WEST, Direction.UP, team,b);

        queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);

    }

    public static void calculateAllMovesForKing(World w, GlobalChessData data, String team,  BoardState b) {
        kingMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.EAST, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.WEST, Direction.UP, team,b);

        kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team, b);

    }

    public static void calculateAllMovesForPawn(World w, GlobalChessData data, String team,  BoardState b) {
        pawnMoveScheme(w, data, Direction.NORTH, team,b);

    }

    public static void calculateAllMovesForStartPawn(World w, GlobalChessData data, String team,  BoardState b) {

        startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, team,b);

    }


    public static void calculateAllMovesForCastleKing(World w, GlobalChessData data, String team,  BoardState b) {

        kingMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.EAST, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.WEST, Direction.UP, team,b);

        kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);


        castlingScheme(w, data, Direction.NORTH, team,b);
        castlingScheme(w, data, Direction.SOUTH, team,b);
        castlingScheme(w, data, Direction.EAST, team,b);
        castlingScheme(w, data, Direction.WEST, team,b);



    }

    public static void calculateAllMovesForCastleTower(World w, GlobalChessData data, String team,  BoardState b) {
        towerMoveScheme(w, data, Direction.NORTH, team,b);
        towerMoveScheme(w, data, Direction.SOUTH, team,b);
        towerMoveScheme(w, data, Direction.WEST, team,b);
        towerMoveScheme(w, data, Direction.EAST, team,b);
    }

































}
