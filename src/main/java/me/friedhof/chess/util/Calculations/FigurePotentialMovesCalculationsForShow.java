package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FigurePotentialMovesCalculationsForShow {


    public static ArrayList<GlobalChessData> whitePotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> blackPotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> yellowPotentialMoves = new ArrayList<>();
    public static ArrayList<GlobalChessData> pinkPotentialMoves = new ArrayList<>();




    public static void towerMoveScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){

                //stop at enemy figure
                replaceOldFigure(w,currentPosition,team);
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

    public static void castlingScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                    replaceOldFigureForCastling(w,currentPosition,team);
                    break;
                }
            }
        }


    }











    public static void bishopMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


            if(currentPosition == null){
                return;
            }

            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){

                    replaceOldFigure(w,currentPosition,team);
                    break;
                }
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

    public static void knightMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


        if(currentPosition == null){
            return;
        }

        if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                replaceOldFigure(w,currentPosition,team);

            }
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


    public static void queenMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, String team){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
            if(relativeDirection2 != Direction.UP) {
                currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
            }


            if(currentPosition == null){
                return;
            }
            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                    replaceOldFigure(w,currentPosition,team);
                    break;
                }
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



    public static void kingMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2,String team){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        if(relativeDirection2 != Direction.UP) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
        }

        if(currentPosition == null){
            return;
        }
        if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                replaceOldFigure(w,currentPosition,team);


            }
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



    public static void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, String team) {


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);


        if (currentPosition != null) {
            if (!MovementCalculations.isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {
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
            if (MovementCalculations.isItemFrame(w, takingPosition1.pos, takingPosition1.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition1,team);

                }
            }
        }
        //2
        if(takingPosition2 != null) {
            if (MovementCalculations.isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition2,team);

                }
            }
        }






    }


    public static void startPawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1,Direction relativeDirection2, String team){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);

        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);
        if(relativeDirection2 != Direction.UP) {

            if(!MovementCalculations.isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {

                currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
            }else{
                if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                    currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
                }

            }
        }




        if(currentPosition != null) {
            if (!MovementCalculations.isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {
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
            if (MovementCalculations.isItemFrame(w, takingPosition1.pos, takingPosition1.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition1,team);

                }
            }
        }
        //2
        if(takingPosition2 != null) {
            if (MovementCalculations.isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition2,team);

                }
            }
        }



    }

////////////////////////////////////////////////////////

    private static void replaceOldFigureForCastling(World w,GlobalChessData currentPosition, String team){

        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_TOWER &&  team == "white"){
            whitePotentialMoves.add(currentPosition);
        }
        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_TOWER && team == "black"){
            blackPotentialMoves.add(currentPosition);
        }

        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_TOWER &&  team == "yellow"){
            yellowPotentialMoves.add(currentPosition);
        }
        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_PINK_TOWER &&  team == "pink"){
            pinkPotentialMoves.add(currentPosition);
        }

    }






    private static void replaceOldFigure(World w,GlobalChessData currentPosition, String team){

        if(!Chess.arrayContains(UseEntityHandler.whitePieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  team == "white"){
            whitePotentialMoves.add(currentPosition);

        }
        if(!Chess.arrayContains(UseEntityHandler.blackPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "black"){
           blackPotentialMoves.add(currentPosition);

        }
        if(!Chess.arrayContains(UseEntityHandler.yellowPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "yellow"){
            yellowPotentialMoves.add(currentPosition);


        }
        if(!Chess.arrayContains(UseEntityHandler.pinkPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "pink"){
           pinkPotentialMoves.add(currentPosition);

        }



    }

    private static void replacementForColour(World w, GlobalChessData currentPosition, Item newItem){

        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



        ItemFrameEntity old = MovementCalculations.dataToFigure(w,currentPosition,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
        int rotation = currentPosition.itemRotation;
        int id = 0;
        for(int j = 0; j < list.size();j++){
            if(list.get(j) instanceof ItemFrameEntity){
                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                if(old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                        old2.getBlockPos().getX() == old.getBlockPos().getX() &&  old2.getBlockPos().getY() == old.getBlockPos().getY() &&  old2.getBlockPos().getZ() == old.getBlockPos().getZ()  ){
                    currentPosition.itemRotation = old2.getRotation();
                    id = old2.getId();
                    break;
                }
            }

        }
        ItemFrameEntity newEntity = MovementCalculations.dataToFigureWithDamage(w,currentPosition, newItem,rotation );
        newEntity.setInvisible(true);
        if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            newEntity.setInvulnerable(true);
        }
        w.getEntityById(id).kill();
        w.spawnEntity(newEntity);


    }

































}
