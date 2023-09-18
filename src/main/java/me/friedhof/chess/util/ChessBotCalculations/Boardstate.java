package me.friedhof.chess.util.ChessBotCalculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.item.custom.WhiteKnightItem;
import me.friedhof.chess.util.Calculations.ClickFigureCalculations;
import me.friedhof.chess.util.Calculations.FigureMovesCalculations;
import me.friedhof.chess.util.Calculations.MovementCalculations;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Boardstate {




    public HashMap<Item,Integer> valuesOfChesspieces = new HashMap<>();
    public ArrayList<GlobalChessData> positionsByID = new ArrayList<>();
    public ArrayList<Item> chessPieceById = new ArrayList<>();

    int piecesOnBoard = 0;

    public Boardstate() {
        setPieceValues();

    }


    public Boardstate getCurrentPosition(World w, BlockPos start){

        int r = 50;
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(start.getX()-r,start.getY()-r, start.getZ()-r, start.getX()+r, start.getY()+r, start.getZ()+r), EntityPredicates.VALID_ENTITY);
        Boardstate b = new Boardstate();
        for(int i = 0; i < list.size(); i++){
            GlobalChessData position = MovementCalculations.figureToData(list.get(i));
            Item item = list.get(i).getHeldItemStack().getItem();
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,item) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,item) ||
                    Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,item) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,item) ||
                   Chess.arrayContains(UseEntityHandler.whiteCapturePieces,item) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,item) ||
                    Chess.arrayContains(UseEntityHandler.yellowCapturePieces,item) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,item) ||
                    Chess.arrayContains(UseEntityHandler.switchPieces,item)){
                item = FigureMovesCalculations.exchangeItems(item,false);
            }
            b.positionsByID.add(position);
            b.chessPieceById.add(item);

        }
        b.piecesOnBoard = list.size();
        return b;
    }


    public Boardstate justOneColour(Boardstate b, String colour)  {

        Boardstate newBoard = new Boardstate();


        switch(colour){
            case "white":
                for(Item e : UseEntityHandler.whitePieces){
                 b.valuesOfChesspieces.put(e,b.valuesOfChesspieces.get(e)*(-1));
                }
                break;
            case "black":
                for(Item e : UseEntityHandler.blackPieces){
                    b.valuesOfChesspieces.put(e,b.valuesOfChesspieces.get(e)*(-1));
                }
                break;
            case "yellow":
                for(Item e : UseEntityHandler.yellowPieces){
                    b.valuesOfChesspieces.put(e,b.valuesOfChesspieces.get(e)*(-1));
                }
                break;
            case "pink":
                for(Item e : UseEntityHandler.pinkPieces){
                    b.valuesOfChesspieces.put(e,b.valuesOfChesspieces.get(e)*(-1));
                }
                break;
            default:
                break;

        }
        return b;

    }

    public boolean containsPosition(GlobalChessData data){
        for(int i = 0; i<positionsByID.size(); i++){
            if(data.pos.getX() == positionsByID.get(i).pos.getX() && data.pos.getY() == positionsByID.get(i).pos.getY()  && data.pos.getZ() == positionsByID.get(i).pos.getZ() && data.directionWall == positionsByID.get(i).directionWall  ){
                return true;
            }
        }
        return false;



    }

   public Boardstate copyOf(){
       Boardstate b = new Boardstate();
       b.positionsByID.addAll(this.positionsByID);
       b.chessPieceById.addAll(this.chessPieceById);
       b.piecesOnBoard = this.piecesOnBoard;

       return b;
   }












    private void setPieceValues(){
        valuesOfChesspieces.put(ModItems.WHITE_PAWN,-10);
        valuesOfChesspieces.put(ModItems.WHITE_KNIGHT,-30);
        valuesOfChesspieces.put(ModItems.WHITE_BISHOP,-30);
        valuesOfChesspieces.put(ModItems.WHITE_TOWER,-50);
        valuesOfChesspieces.put(ModItems.WHITE_QUEEN,-90);
        valuesOfChesspieces.put(ModItems.WHITE_KING,-900);
        valuesOfChesspieces.put(ModItems.START_WHITE_PAWN,-10);
        valuesOfChesspieces.put(ModItems.CASTLE_WHITE_TOWER,-50);
        valuesOfChesspieces.put(ModItems.CASTLE_WHITE_KING,-900);

        valuesOfChesspieces.put(ModItems.BLACK_PAWN,-10);
        valuesOfChesspieces.put(ModItems.BLACK_KNIGHT,-30);
        valuesOfChesspieces.put(ModItems.BLACK_BISHOP,-30);
        valuesOfChesspieces.put(ModItems.BLACK_TOWER,-50);
        valuesOfChesspieces.put(ModItems.BLACK_QUEEN,-90);
        valuesOfChesspieces.put(ModItems.BLACK_KING,-900);
        valuesOfChesspieces.put(ModItems.START_BLACK_PAWN,-10);
        valuesOfChesspieces.put(ModItems.CASTLE_BLACK_TOWER,-50);
        valuesOfChesspieces.put(ModItems.CASTLE_BLACK_KING,-900);

        valuesOfChesspieces.put(ModItems.YELLOW_PAWN,-10);
        valuesOfChesspieces.put(ModItems.YELLOW_KNIGHT,-30);
        valuesOfChesspieces.put(ModItems.YELLOW_BISHOP,-30);
        valuesOfChesspieces.put(ModItems.YELLOW_TOWER,-50);
        valuesOfChesspieces.put(ModItems.YELLOW_QUEEN,-90);
        valuesOfChesspieces.put(ModItems.YELLOW_KING,-900);
        valuesOfChesspieces.put(ModItems.START_YELLOW_PAWN,-10);
        valuesOfChesspieces.put(ModItems.CASTLE_YELLOW_TOWER,-50);
        valuesOfChesspieces.put(ModItems.CASTLE_YELLOW_KING,-900);

        valuesOfChesspieces.put(ModItems.PINK_PAWN,-10);
        valuesOfChesspieces.put(ModItems.PINK_KNIGHT,-30);
        valuesOfChesspieces.put(ModItems.PINK_BISHOP,-30);
        valuesOfChesspieces.put(ModItems.PINK_TOWER,-50);
        valuesOfChesspieces.put(ModItems.PINK_QUEEN,-90);
        valuesOfChesspieces.put(ModItems.PINK_KING,-900);
        valuesOfChesspieces.put(ModItems.START_PINK_PAWN,-10);
        valuesOfChesspieces.put(ModItems.CASTLE_PINK_TOWER,-50);
        valuesOfChesspieces.put(ModItems.CASTLE_PINK_KING,-900);

    }










}
