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

import java.util.List;

public class FigureMovesCalculations {



    public static void towerMoveScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, String team){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                    replaceOldFigure(w,currentPosition,team);
                    break;
                }
            }else{
                ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.spawnEntity(e);
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
                ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.spawnEntity(e);
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
            ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
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
                ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.spawnEntity(e);
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
            ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
        }

    }



    public static void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, String team) {


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        GlobalChessData takingPosition1 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = MovementCalculations.moveOneInDirection(w, currentPosition, Direction.WEST);


        if (currentPosition != null) {
            if (!MovementCalculations.isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {
                ItemFrameEntity e = MovementCalculations.dataToFigure(w, currentPosition, ModItems.MOVE_HIGHLIGHTER);

                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.spawnEntity(e);
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
                ItemFrameEntity e = MovementCalculations.dataToFigure(w, currentPosition, ModItems.MOVE_HIGHLIGHTER);

                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.spawnEntity(e);
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

        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_TOWER &&  team != "white"){
            replacementForColour(w,currentPosition, ModItems.CASTLE_SWITCH_WHITE_TOWER);
        }
        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_TOWER && team != "black"){
           replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_BLACK_TOWER);
        }

        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_TOWER &&  team != "yellow"){
            replacementForColour(w,currentPosition, ModItems.CASTLE_SWITCH_YELLOW_TOWER);
        }
        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_PINK_TOWER &&  team != "pink"){
            replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_PINK_TOWER);
        }

    }






    private static void replaceOldFigure(World w,GlobalChessData currentPosition, String team){

        if(Chess.arrayContains(UseEntityHandler.whitePieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  team != "white"){
          replacementForColour(w,currentPosition,exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));

        }
        if(Chess.arrayContains(UseEntityHandler.blackPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "black"){
            replacementForColour(w,currentPosition,exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));
        }
        if(Chess.arrayContains(UseEntityHandler.yellowPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "yellow"){
            replacementForColour(w,currentPosition,exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));

        }
        if(Chess.arrayContains(UseEntityHandler.pinkPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "pink"){
            replacementForColour(w,currentPosition,exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));
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
        if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            newEntity.setInvulnerable(true);
        }
        w.getEntityById(id).kill();
        w.spawnEntity(newEntity);


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
