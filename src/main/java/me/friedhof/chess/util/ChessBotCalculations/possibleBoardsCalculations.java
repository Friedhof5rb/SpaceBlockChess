package me.friedhof.chess.util.ChessBotCalculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.Calculations.MovementCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;

public class possibleBoardsCalculations {

    ArrayList<Boardstate> possibleBoards = new ArrayList<>();


    public possibleBoardsCalculations(World w, String colour, BlockPos start, Boardstate b) {


        for(int i = 0; i < b.piecesOnBoard; i++ ){
            //check if it's the right colour (the colour that moves)
            if(Chess.ItemToColour(b.chessPieceById.get(i)).equals(colour)){
                Item item = b.chessPieceById.get(i);
                GlobalChessData position = b.positionsByID.get(i);
                addPossibleMovesForItemToList(w, b,item,position);

            }
        }

    }



    private void addPossibleMovesForItemToList(World w,Boardstate b, Item item, GlobalChessData position){

        if(item == ModItems.WHITE_TOWER || item == ModItems.BLACK_TOWER || item == ModItems.YELLOW_TOWER || item == ModItems.PINK_TOWER){


            GlobalChessData toNorth = MovementCalculations.moveOneInDirection(w,position, Direction.NORTH);;
            GlobalChessData toSouth = MovementCalculations.moveOneInDirection(w,position, Direction.SOUTH);;
            GlobalChessData toEast = MovementCalculations.moveOneInDirection(w,position, Direction.EAST);;
            GlobalChessData toWest = MovementCalculations.moveOneInDirection(w,position, Direction.WEST);;


           for(int i = 0; i < UseEntityHandler.figureDrawDistance-1; i++){


               if (!b.containsPosition(toNorth)) {
                   toNorth = MovementCalculations.moveOneInDirection(w, toNorth, Direction.NORTH);
               }
               int oldPosition = -1;
               Boardstate board = b.copyOf();
               for(int j = 0; j<board.positionsByID.size(); j++){
                   if(position.pos.getX() == board.positionsByID.get(j).pos.getX() && position.pos.getY() == board.positionsByID.get(j).pos.getY()  && position.pos.getZ() == board.positionsByID.get(j).pos.getZ() && position.directionWall == board.positionsByID.get(j).directionWall  ){
                      oldPosition = j;
                       break;
                   }
               }
               board.positionsByID.remove(oldPosition);
               board.positionsByID.add( oldPosition, toNorth);
               possibleBoards.add(board);
                ///////////////////////////////////////////

               if (!b.containsPosition(toSouth)) {
                   toSouth = MovementCalculations.moveOneInDirection(w, toSouth, Direction.SOUTH);
               }
                oldPosition = -1;
                board = b.copyOf();
               for(int j = 0; j<board.positionsByID.size(); j++){
                   if(position.pos.getX() == board.positionsByID.get(j).pos.getX() && position.pos.getY() == board.positionsByID.get(j).pos.getY()  && position.pos.getZ() == board.positionsByID.get(j).pos.getZ() && position.directionWall == board.positionsByID.get(j).directionWall  ){
                       oldPosition = j;
                       break;
                   }
               }
               board.positionsByID.remove(oldPosition);
               board.positionsByID.add(oldPosition,toSouth);
               possibleBoards.add(board);
               ///////////////////////////////////////////
               if (!b.containsPosition(toEast)) {
                   toEast = MovementCalculations.moveOneInDirection(w, toEast, Direction.EAST);
               }
                oldPosition = -1;
                board = b.copyOf();
               for(int j = 0; j<board.positionsByID.size(); j++){
                   if(position.pos.getX() == board.positionsByID.get(j).pos.getX() && position.pos.getY() == board.positionsByID.get(j).pos.getY()  && position.pos.getZ() == board.positionsByID.get(j).pos.getZ() && position.directionWall == board.positionsByID.get(j).directionWall  ){
                       oldPosition = j;
                       break;
                   }
               }
               board.positionsByID.remove(oldPosition);
               board.positionsByID.add(oldPosition,toEast);
               possibleBoards.add(board);
               ///////////////////////////////////////////
               if (!b.containsPosition(toWest)) {
                   toWest = MovementCalculations.moveOneInDirection(w, toWest, Direction.WEST);
               }
                oldPosition = -1;
                board = b.copyOf();
               for(int j = 0; j<board.positionsByID.size(); j++){
                   if(position.pos.getX() == board.positionsByID.get(j).pos.getX() && position.pos.getY() == board.positionsByID.get(j).pos.getY()  && position.pos.getZ() == board.positionsByID.get(j).pos.getZ() && position.directionWall == board.positionsByID.get(j).directionWall  ){
                       oldPosition = j;
                       break;
                   }
               }
               board.positionsByID.remove(oldPosition);
               board.positionsByID.add(oldPosition,toWest);
               possibleBoards.add(board);
               ///////////////////////////////////////////
           }

        }

    }









}
