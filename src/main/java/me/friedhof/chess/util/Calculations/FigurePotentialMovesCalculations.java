package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FigurePotentialMovesCalculations {


    public  ArrayList<GlobalChessData> whitePotentialMoves = new ArrayList<>();
    public  ArrayList<GlobalChessData> blackPotentialMoves = new ArrayList<>();
    public  ArrayList<GlobalChessData> yellowPotentialMoves = new ArrayList<>();
    public  ArrayList<GlobalChessData> pinkPotentialMoves = new ArrayList<>();


    public static boolean isEnPassanable(GlobalChessData currentPosition, BoardState b){

        if(currentPosition == null){
            return false;
        }

        for(FigureOnBoard f : b.allFiguresList){
            if(currentPosition.pos.getX() == f.data.pos.getX() && currentPosition.pos.getY() == f.data.pos.getY() && currentPosition.pos.getZ() == f.data.pos.getZ() &&
                    currentPosition.directionWall == f.data.directionWall ){

                ItemStack stack = f.stack;
                if(stack.hasNbt()) {
                    NbtCompound nbt = stack.getNbt();
                    if (nbt.contains("allowEnPassant")) {
                        if (nbt.getBoolean("allowEnPassant")) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;


    }




    public static boolean isContainedInBoardState(GlobalChessData currentPosition, BoardState b){

        if(currentPosition == null){
            return false;
        }


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
                return f.stack.getItem();
            }
        }

        return Items.AIR;
    }





    public void towerMoveScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team, BoardState b){

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

    public void castlingScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team, BoardState b){

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











    public void bishopMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){

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

    public void knightMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){


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


    public void queenMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team, BoardState b){

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



    public void kingMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2,String team, BoardState b){


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



    public void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, String team, BoardState b) {


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);

        GlobalChessData enPassant1 = MovementCalculations.moveOneInDirection(w, takingPosition1, Direction.SOUTH);
        GlobalChessData enPassant2 = MovementCalculations.moveOneInDirection(w, takingPosition2, Direction.SOUTH);


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
            if (isContainedInBoardState(takingPosition1,b)) {

                    replaceOldFigure(takingPosition1,team,b);

            }else{
                if(isEnPassanable(enPassant1,b)){


                    switch(team){
                        case "white":{
                            whitePotentialMoves.add(takingPosition1);

                        }
                        case "black":{
                            blackPotentialMoves.add(takingPosition1);

                        }
                        case "yellow":{
                            yellowPotentialMoves.add(takingPosition1);

                        }
                        case "pink":{
                            pinkPotentialMoves.add(takingPosition1);

                        }
                    }
                }
            }

        }
        //2
        if(takingPosition2 != null) {
            if (isContainedInBoardState(takingPosition2,b)) {

                    replaceOldFigure(takingPosition2,team,b);
            }else{
                if(isEnPassanable(enPassant2,b)){

                    switch(team){
                        case "white":{
                            whitePotentialMoves.add(takingPosition2);

                        }
                        case "black":{
                            blackPotentialMoves.add(takingPosition2);

                        }
                        case "yellow":{
                            yellowPotentialMoves.add(takingPosition2);

                        }
                        case "pink":{
                            pinkPotentialMoves.add(takingPosition2);

                        }
                    }
                }
            }
        }

    }


    public void startPawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1,Direction relativeDirection2, String team, BoardState b){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);

        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);

        GlobalChessData enPassant1 = MovementCalculations.moveOneInDirection(w, takingPosition1, Direction.SOUTH);
        GlobalChessData enPassant2 = MovementCalculations.moveOneInDirection(w, takingPosition2, Direction.SOUTH);

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
        if (takingPosition1 != null) {
            if (isContainedInBoardState(takingPosition1,b)) {

                replaceOldFigure(takingPosition1,team,b);

            }else{
                if(isEnPassanable(enPassant1,b)){
                    switch(team){
                        case "white":{
                            whitePotentialMoves.add(takingPosition1);

                        }
                        case "black":{
                            blackPotentialMoves.add(takingPosition1);

                        }
                        case "yellow":{
                            yellowPotentialMoves.add(takingPosition1);

                        }
                        case "pink":{
                            pinkPotentialMoves.add(takingPosition1);

                        }
                    }
                }
            }

        }
        //2
        if(takingPosition2 != null) {
            if (isContainedInBoardState(takingPosition2,b)) {

                replaceOldFigure(takingPosition2,team,b);
            }else{
                if(isEnPassanable(enPassant2,b)){

                    switch(team){
                        case "white":{
                            whitePotentialMoves.add(takingPosition2);

                        }
                        case "black":{
                            blackPotentialMoves.add(takingPosition2);

                        }
                        case "yellow":{
                            yellowPotentialMoves.add(takingPosition2);

                        }
                        case "pink":{
                            pinkPotentialMoves.add(takingPosition2);

                        }
                    }
                }
            }
        }

    }

////////////////////////////////////////////////////////

    private void replaceOldFigureForCastling(GlobalChessData currentPosition, String team, BoardState b){

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






    private void replaceOldFigure( GlobalChessData currentPosition, String team, BoardState b){

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








///////////////////////////////////////////////////
    public void calculateAllMovesForColour(World w, String colour, BoardState b){

        switch (colour) {
            case "white" -> whitePotentialMoves.clear();
            case "black" -> blackPotentialMoves.clear();
            case "yellow" -> yellowPotentialMoves.clear();
            case "pink" -> pinkPotentialMoves.clear();
        }


        for (FigureOnBoard figure : b.allFiguresList) {
            Item item2 = figure.stack.getItem();
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

    public void calculateAllMovesForTower(World w, GlobalChessData data, String team,  BoardState b){

        towerMoveScheme(w, data, Direction.NORTH, team,b);
        towerMoveScheme(w, data, Direction.SOUTH, team,b);
        towerMoveScheme(w, data, Direction.WEST, team,b);
        towerMoveScheme(w, data, Direction.EAST, team,b);




    }

    public void calculateAllMovesForBishop(World w, GlobalChessData data, String team,  BoardState b) {

        bishopMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        bishopMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        bishopMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        bishopMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);



    }

    public void calculateAllMovesForKnight(World w, GlobalChessData data, String team,  BoardState b) {

        knightMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        knightMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        knightMoveScheme(w, data, Direction.EAST, Direction.NORTH, team,b);
        knightMoveScheme(w, data, Direction.WEST, Direction.NORTH, team,b);
        knightMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        knightMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);
        knightMoveScheme(w, data, Direction.EAST, Direction.SOUTH, team,b);
        knightMoveScheme(w, data, Direction.WEST, Direction.SOUTH, team,b);

    }

    public void calculateAllMovesForQueen(World w, GlobalChessData data, String team,  BoardState b) {
        queenMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.EAST, Direction.UP, team,b);
        queenMoveScheme(w, data, Direction.WEST, Direction.UP, team,b);

        queenMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        queenMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        queenMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team,b);

    }

    public void calculateAllMovesForKing(World w, GlobalChessData data, String team,  BoardState b) {
        kingMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.EAST, Direction.UP, team,b);
        kingMoveScheme(w, data, Direction.WEST, Direction.UP, team,b);

        kingMoveScheme(w, data, Direction.NORTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.NORTH, Direction.WEST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.EAST, team,b);
        kingMoveScheme(w, data, Direction.SOUTH, Direction.WEST, team, b);

    }

    public void calculateAllMovesForPawn(World w, GlobalChessData data, String team,  BoardState b) {
        pawnMoveScheme(w, data, Direction.NORTH, team,b);

    }

    public void calculateAllMovesForStartPawn(World w, GlobalChessData data, String team,  BoardState b) {

        startPawnMoveScheme(w, data, Direction.NORTH, Direction.UP, team,b);
        startPawnMoveScheme(w, data, Direction.NORTH, Direction.NORTH, team,b);

    }


    public void calculateAllMovesForCastleKing(World w, GlobalChessData data, String team,  BoardState b) {

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

    public void calculateAllMovesForCastleTower(World w, GlobalChessData data, String team,  BoardState b) {
        towerMoveScheme(w, data, Direction.NORTH, team,b);
        towerMoveScheme(w, data, Direction.SOUTH, team,b);
        towerMoveScheme(w, data, Direction.WEST, team,b);
        towerMoveScheme(w, data, Direction.EAST, team,b);

    }

    public static Item exchangeItems(Item input, boolean capture){

        //Black and White
        if(input == ModItems.BLACK_BISHOP){
            if(capture){
                return ModItems.CAPTURE_BLACK_BISHOP ;
            }else{
                return ModItems.SELECTED_BLACK_BISHOP ;
            }
        }else if(input == ModItems.BLACK_TOWER){
            if(capture){
                return ModItems.CAPTURE_BLACK_TOWER;
            }else{
                return ModItems.SELECTED_BLACK_TOWER;
            }
        }else if(input == ModItems.BLACK_KING){
            if(capture){
                return ModItems.CAPTURE_BLACK_KING ;
            }else{
                return ModItems.SELECTED_BLACK_KING;
            }
        }else if(input == ModItems.BLACK_KNIGHT){
            if(capture){
                return ModItems.CAPTURE_BLACK_KNIGHT ;
            }else{
                return ModItems.SELECTED_BLACK_KNIGHT ;
            }
        }else if(input == ModItems.BLACK_PAWN){
            if(capture){
                return ModItems.CAPTURE_BLACK_PAWN;
            }else{
                return ModItems.SELECTED_BLACK_PAWN;
            }
        }else if(input == ModItems.BLACK_QUEEN){
            if(capture){
                return ModItems.CAPTURE_BLACK_QUEEN;
            }else{
                return ModItems.SELECTED_BLACK_QUEEN ;
            }
        }else if(input == ModItems.WHITE_BISHOP){
            if(capture){
                return ModItems.CAPTURE_WHITE_BISHOP ;
            }else{
                return ModItems.SELECTED_WHITE_BISHOP ;
            }
        }else if(input == ModItems.WHITE_TOWER){
            if(capture){
                return ModItems.CAPTURE_WHITE_TOWER;
            }else{
                return ModItems.SELECTED_WHITE_TOWER ;
            }
        }else if(input == ModItems.WHITE_KING){
            if(capture){
                return ModItems.CAPTURE_WHITE_KING;
            }else{
                return ModItems.SELECTED_WHITE_KING ;
            }
        }else if(input == ModItems.WHITE_KNIGHT){
            if(capture){
                return ModItems.CAPTURE_WHITE_KNIGHT;
            }else{
                return ModItems.SELECTED_WHITE_KNIGHT;
            }
        }else if(input == ModItems.WHITE_PAWN){
            if(capture){
                return ModItems.CAPTURE_WHITE_PAWN;
            }else{
                return ModItems.SELECTED_WHITE_PAWN;
            }
        }else if(input == ModItems.WHITE_QUEEN){
            if(capture){
                return ModItems.CAPTURE_WHITE_QUEEN ;
            }else{
                return ModItems.SELECTED_WHITE_QUEEN ;
            }
        } else if(input == ModItems.CAPTURE_BLACK_BISHOP){
            return ModItems.BLACK_BISHOP;
        }else if(input == ModItems.CAPTURE_BLACK_TOWER){
            return ModItems.BLACK_TOWER;
        }else if(input == ModItems.CAPTURE_BLACK_KING){
            return ModItems.BLACK_KING;
        }else if(input == ModItems.CAPTURE_BLACK_KNIGHT){
            return ModItems.BLACK_KNIGHT;
        }else if(input == ModItems.CAPTURE_BLACK_PAWN){
            return ModItems.BLACK_PAWN;
        }else if(input == ModItems.CAPTURE_BLACK_QUEEN){
            return ModItems.BLACK_QUEEN;
        }else if(input == ModItems.CAPTURE_WHITE_BISHOP){
            return ModItems.WHITE_BISHOP;
        }else if(input == ModItems.CAPTURE_WHITE_TOWER){
            return ModItems.WHITE_TOWER;
        }else if(input == ModItems.CAPTURE_WHITE_KING){
            return ModItems.WHITE_KING;
        }else if(input == ModItems.CAPTURE_WHITE_KNIGHT){
            return ModItems.WHITE_KNIGHT;
        }else if(input == ModItems.CAPTURE_WHITE_PAWN){
            return ModItems.WHITE_PAWN;
        }else if(input == ModItems.CAPTURE_WHITE_QUEEN){
            return ModItems.WHITE_QUEEN;
        } else if(input == ModItems.SELECTED_BLACK_BISHOP){
            return ModItems.BLACK_BISHOP;
        }else if(input == ModItems.SELECTED_BLACK_TOWER){
            return ModItems.BLACK_TOWER;
        }else if(input == ModItems.SELECTED_BLACK_KING){
            return ModItems.BLACK_KING;
        }else if(input == ModItems.SELECTED_BLACK_KNIGHT){
            return ModItems.BLACK_KNIGHT;
        }else if(input == ModItems.SELECTED_BLACK_PAWN){
            return ModItems.BLACK_PAWN;
        }else if(input == ModItems.SELECTED_BLACK_QUEEN){
            return ModItems.BLACK_QUEEN;
        }else if(input == ModItems.SELECTED_WHITE_BISHOP){
            return ModItems.WHITE_BISHOP;
        }else if(input == ModItems.SELECTED_WHITE_TOWER){
            return ModItems.WHITE_TOWER;
        }else if(input == ModItems.SELECTED_WHITE_KING){
            return ModItems.WHITE_KING;
        }else if(input == ModItems.SELECTED_WHITE_KNIGHT){
            return ModItems.WHITE_KNIGHT;
        }else if(input == ModItems.SELECTED_WHITE_PAWN){
            return ModItems.WHITE_PAWN;
        }else if(input == ModItems.SELECTED_WHITE_QUEEN){
            return ModItems.WHITE_QUEEN;
        }else if(input == ModItems.START_BLACK_PAWN){
            if(capture){
                return ModItems.START_CAPTURE_BLACK_PAWN;
            }else{
                return ModItems.START_SELECTED_BLACK_PAWN;
            }
        }else if(input == ModItems.START_WHITE_PAWN){
            if(capture){
                return ModItems.START_CAPTURE_WHITE_PAWN;
            }else{
                return ModItems.START_SELECTED_WHITE_PAWN;
            }
        }else if(input == ModItems.START_SELECTED_BLACK_PAWN){
            return ModItems.START_BLACK_PAWN;
        }else if(input == ModItems.START_SELECTED_WHITE_PAWN){
            return ModItems.START_WHITE_PAWN;
        }else if(input == ModItems.START_CAPTURE_BLACK_PAWN){
            return ModItems.START_BLACK_PAWN;
        }else if(input == ModItems.START_CAPTURE_WHITE_PAWN){
            return ModItems.START_WHITE_PAWN;
        }else if(input == ModItems.CASTLE_BLACK_KING){

            if(capture){
                return ModItems.CASTLE_CAPTURE_BLACK_KING;
            }else{
                return ModItems.CASTLE_SELECTED_BLACK_KING;
            }

        }else if(input == ModItems.CASTLE_BLACK_TOWER){

            if(capture){
                return ModItems.CASTLE_CAPTURE_BLACK_TOWER;
            }else{
                return ModItems.CASTLE_SELECTED_BLACK_TOWER;
            }

        }else if(input == ModItems.CASTLE_WHITE_KING){

            if(capture){
                return ModItems.CASTLE_CAPTURE_WHITE_KING;
            }else{
                return ModItems.CASTLE_SELECTED_WHITE_KING;
            }

        }else if(input == ModItems.CASTLE_WHITE_TOWER){

            if(capture){
                return ModItems.CASTLE_CAPTURE_WHITE_TOWER;
            }else{
                return ModItems.CASTLE_SELECTED_WHITE_TOWER;
            }

        }else if(input == ModItems.CASTLE_CAPTURE_BLACK_KING){
            return ModItems.CASTLE_BLACK_KING;
        }else if(input == ModItems.CASTLE_CAPTURE_BLACK_TOWER){
            return ModItems.CASTLE_BLACK_TOWER;
        }else if(input == ModItems.CASTLE_CAPTURE_WHITE_KING){
            return ModItems.CASTLE_WHITE_KING;
        }else if(input == ModItems.CASTLE_CAPTURE_WHITE_TOWER){
            return ModItems.CASTLE_WHITE_TOWER;
        }else if(input == ModItems.CASTLE_SELECTED_BLACK_KING){
            return ModItems.CASTLE_BLACK_KING;
        }else if(input == ModItems.CASTLE_SELECTED_BLACK_TOWER){
            return ModItems.CASTLE_BLACK_TOWER;
        }else if(input == ModItems.CASTLE_SELECTED_WHITE_KING){
            return ModItems.CASTLE_WHITE_KING;
        }else if(input == ModItems.CASTLE_SELECTED_WHITE_TOWER){
            return ModItems.CASTLE_WHITE_TOWER;
        }else if(input == ModItems.CASTLE_SWITCH_BLACK_TOWER){
            return ModItems.CASTLE_BLACK_TOWER;
        }else if(input == ModItems.CASTLE_SWITCH_WHITE_TOWER){
            return ModItems.CASTLE_WHITE_TOWER;
        }


        //Yellow and Pink
        if(input == ModItems.YELLOW_BISHOP){
            if(capture){
                return ModItems.CAPTURE_YELLOW_BISHOP ;
            }else{
                return ModItems.SELECTED_YELLOW_BISHOP ;
            }
        }else if(input == ModItems.YELLOW_TOWER){
            if(capture){
                return ModItems.CAPTURE_YELLOW_TOWER;
            }else{
                return ModItems.SELECTED_YELLOW_TOWER;
            }
        }else if(input == ModItems.YELLOW_KING){
            if(capture){
                return ModItems.CAPTURE_YELLOW_KING ;
            }else{
                return ModItems.SELECTED_YELLOW_KING;
            }
        }else if(input == ModItems.YELLOW_KNIGHT){
            if(capture){
                return ModItems.CAPTURE_YELLOW_KNIGHT ;
            }else{
                return ModItems.SELECTED_YELLOW_KNIGHT ;
            }
        }else if(input == ModItems.YELLOW_PAWN){
            if(capture){
                return ModItems.CAPTURE_YELLOW_PAWN;
            }else{
                return ModItems.SELECTED_YELLOW_PAWN;
            }
        }else if(input == ModItems.YELLOW_QUEEN){
            if(capture){
                return ModItems.CAPTURE_YELLOW_QUEEN;
            }else{
                return ModItems.SELECTED_YELLOW_QUEEN ;
            }
        }else if(input == ModItems.PINK_BISHOP){
            if(capture){
                return ModItems.CAPTURE_PINK_BISHOP ;
            }else{
                return ModItems.SELECTED_PINK_BISHOP ;
            }
        }else if(input == ModItems.PINK_TOWER){
            if(capture){
                return ModItems.CAPTURE_PINK_TOWER;
            }else{
                return ModItems.SELECTED_PINK_TOWER ;
            }
        }else if(input == ModItems.PINK_KING){
            if(capture){
                return ModItems.CAPTURE_PINK_KING;
            }else{
                return ModItems.SELECTED_PINK_KING ;
            }
        }else if(input == ModItems.PINK_KNIGHT){
            if(capture){
                return ModItems.CAPTURE_PINK_KNIGHT;
            }else{
                return ModItems.SELECTED_PINK_KNIGHT;
            }
        }else if(input == ModItems.PINK_PAWN){
            if(capture){
                return ModItems.CAPTURE_PINK_PAWN;
            }else{
                return ModItems.SELECTED_PINK_PAWN;
            }
        }else if(input == ModItems.PINK_QUEEN){
            if(capture){
                return ModItems.CAPTURE_PINK_QUEEN ;
            }else{
                return ModItems.SELECTED_PINK_QUEEN ;
            }
        } else if(input == ModItems.CAPTURE_YELLOW_BISHOP){
            return ModItems.YELLOW_BISHOP;
        }else if(input == ModItems.CAPTURE_YELLOW_TOWER){
            return ModItems.YELLOW_TOWER;
        }else if(input == ModItems.CAPTURE_YELLOW_KING){
            return ModItems.YELLOW_KING;
        }else if(input == ModItems.CAPTURE_YELLOW_KNIGHT){
            return ModItems.YELLOW_KNIGHT;
        }else if(input == ModItems.CAPTURE_YELLOW_PAWN){
            return ModItems.YELLOW_PAWN;
        }else if(input == ModItems.CAPTURE_YELLOW_QUEEN){
            return ModItems.YELLOW_QUEEN;
        }else if(input == ModItems.CAPTURE_PINK_BISHOP){
            return ModItems.PINK_BISHOP;
        }else if(input == ModItems.CAPTURE_PINK_TOWER){
            return ModItems.PINK_TOWER;
        }else if(input == ModItems.CAPTURE_PINK_KING){
            return ModItems.PINK_KING;
        }else if(input == ModItems.CAPTURE_PINK_KNIGHT){
            return ModItems.PINK_KNIGHT;
        }else if(input == ModItems.CAPTURE_PINK_PAWN){
            return ModItems.PINK_PAWN;
        }else if(input == ModItems.CAPTURE_PINK_QUEEN){
            return ModItems.PINK_QUEEN;
        } else if(input == ModItems.SELECTED_YELLOW_BISHOP){
            return ModItems.YELLOW_BISHOP;
        }else if(input == ModItems.SELECTED_YELLOW_TOWER){
            return ModItems.YELLOW_TOWER;
        }else if(input == ModItems.SELECTED_YELLOW_KING){
            return ModItems.YELLOW_KING;
        }else if(input == ModItems.SELECTED_YELLOW_KNIGHT){
            return ModItems.YELLOW_KNIGHT;
        }else if(input == ModItems.SELECTED_YELLOW_PAWN){
            return ModItems.YELLOW_PAWN;
        }else if(input == ModItems.SELECTED_YELLOW_QUEEN){
            return ModItems.YELLOW_QUEEN;
        }else if(input == ModItems.SELECTED_PINK_BISHOP){
            return ModItems.PINK_BISHOP;
        }else if(input == ModItems.SELECTED_PINK_TOWER){
            return ModItems.PINK_TOWER;
        }else if(input == ModItems.SELECTED_PINK_KING){
            return ModItems.PINK_KING;
        }else if(input == ModItems.SELECTED_PINK_KNIGHT){
            return ModItems.PINK_KNIGHT;
        }else if(input == ModItems.SELECTED_PINK_PAWN){
            return ModItems.PINK_PAWN;
        }else if(input == ModItems.SELECTED_PINK_QUEEN){
            return ModItems.PINK_QUEEN;
        }else if(input == ModItems.START_YELLOW_PAWN){
            if(capture){
                return ModItems.START_CAPTURE_YELLOW_PAWN;
            }else{
                return ModItems.START_SELECTED_YELLOW_PAWN;
            }
        }else if(input == ModItems.START_PINK_PAWN){
            if(capture){
                return ModItems.START_CAPTURE_PINK_PAWN;
            }else{
                return ModItems.START_SELECTED_PINK_PAWN;
            }
        }else if(input == ModItems.START_SELECTED_YELLOW_PAWN){
            return ModItems.START_YELLOW_PAWN;
        }else if(input == ModItems.START_SELECTED_PINK_PAWN){
            return ModItems.START_PINK_PAWN;
        }else if(input == ModItems.START_CAPTURE_YELLOW_PAWN){
            return ModItems.START_YELLOW_PAWN;
        }else if(input == ModItems.START_CAPTURE_PINK_PAWN){
            return ModItems.START_PINK_PAWN;
        }else if(input == ModItems.CASTLE_YELLOW_KING){

            if(capture){
                return ModItems.CASTLE_CAPTURE_YELLOW_KING;
            }else{
                return ModItems.CASTLE_SELECTED_YELLOW_KING;
            }

        }else if(input == ModItems.CASTLE_YELLOW_TOWER){

            if(capture){
                return ModItems.CASTLE_CAPTURE_YELLOW_TOWER;
            }else{
                return ModItems.CASTLE_SELECTED_YELLOW_TOWER;
            }

        }else if(input == ModItems.CASTLE_PINK_KING){

            if(capture){
                return ModItems.CASTLE_CAPTURE_PINK_KING;
            }else{
                return ModItems.CASTLE_SELECTED_PINK_KING;
            }

        }else if(input == ModItems.CASTLE_PINK_TOWER){

            if(capture){
                return ModItems.CASTLE_CAPTURE_PINK_TOWER;
            }else{
                return ModItems.CASTLE_SELECTED_PINK_TOWER;
            }

        }else if(input == ModItems.CASTLE_CAPTURE_YELLOW_KING){
            return ModItems.CASTLE_YELLOW_KING;
        }else if(input == ModItems.CASTLE_CAPTURE_YELLOW_TOWER){
            return ModItems.CASTLE_YELLOW_TOWER;
        }else if(input == ModItems.CASTLE_CAPTURE_PINK_KING){
            return ModItems.CASTLE_PINK_KING;
        }else if(input == ModItems.CASTLE_CAPTURE_PINK_TOWER){
            return ModItems.CASTLE_PINK_TOWER;
        }else if(input == ModItems.CASTLE_SELECTED_YELLOW_KING){
            return ModItems.CASTLE_YELLOW_KING;
        }else if(input == ModItems.CASTLE_SELECTED_YELLOW_TOWER){
            return ModItems.CASTLE_YELLOW_TOWER;
        }else if(input == ModItems.CASTLE_SELECTED_PINK_KING){
            return ModItems.CASTLE_PINK_KING;
        }else if(input == ModItems.CASTLE_SELECTED_PINK_TOWER){
            return ModItems.CASTLE_PINK_TOWER;
        }else if(input == ModItems.CASTLE_SWITCH_YELLOW_TOWER){
            return ModItems.CASTLE_YELLOW_TOWER;
        }else if(input == ModItems.CASTLE_SWITCH_PINK_TOWER){
            return ModItems.CASTLE_PINK_TOWER;
        }



        return input;

    }








}
