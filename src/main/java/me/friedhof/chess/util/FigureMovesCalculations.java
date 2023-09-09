package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class FigureMovesCalculations {



    public static void towerMoveScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, boolean white){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                    replaceOldFigure(w,currentPosition,white);
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

    public static void castlingScheme(World w, GlobalChessData currentPosition, Direction relativeDirection, boolean white){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection);

            if(currentPosition == null){
                return;
            }


            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                    replaceOldFigureForCastling(w,currentPosition,white);
                    break;
                }
            }
        }


    }











    public static void bishopMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){

        for(int i = 0; i< UseEntityHandler.figureDrawDistance; i++) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


            if(currentPosition == null){
                return;
            }

            if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){

                    replaceOldFigure(w,currentPosition,white);
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

    public static void knightMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);


        if(currentPosition == null){
            return;
        }

        if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                replaceOldFigure(w,currentPosition,white);

            }
        }else{
            ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
        }

    }


    public static void queenMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){

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


                    replaceOldFigure(w,currentPosition,white);
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



    public static void kingMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){


        currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection1);
        if(relativeDirection2 != Direction.UP) {
            currentPosition = MovementCalculations.moveOneInDirection(w, currentPosition, relativeDirection2);
        }

        if(currentPosition == null){
            return;
        }
        if(MovementCalculations.isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(!(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)){


                replaceOldFigure(w,currentPosition,white);


            }
        }else{
            ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);

            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
        }

    }



    public static void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, boolean white) {


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


                    replaceOldFigure(w,takingPosition1,white);

                }
            }
        }
        //2
        if(takingPosition2 != null) {
            if (MovementCalculations.isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition2,white);

                }
            }
        }






    }


    public static void startPawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1,Direction relativeDirection2, boolean white){


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


                    replaceOldFigure(w,takingPosition1,white);

                }
            }
        }
        //2
        if(takingPosition2 != null) {
            if (MovementCalculations.isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
                if (!(MovementCalculations.getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER)) {


                    replaceOldFigure(w,takingPosition2,white);

                }
            }
        }



    }

////////////////////////////////////////////////////////

    private static void replaceOldFigureForCastling(World w,GlobalChessData currentPosition, boolean white){

        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_TOWER &&  white){
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
            ItemFrameEntity newEntity = MovementCalculations.dataToFigureWithDamage(w,currentPosition, ModItems.CASTLE_SWITCH_WHITE_TOWER,rotation );
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newEntity.setInvulnerable(true);
            }
            w.getEntityById(id).kill();
            w.spawnEntity(newEntity);

        }
        if(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_TOWER &&  !white){
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
            ItemFrameEntity newEntity = MovementCalculations.dataToFigureWithDamage(w,currentPosition, ModItems.CASTLE_SWITCH_BLACK_TOWER,rotation );
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newEntity.setInvulnerable(true);
            }
            w.getEntityById(id).kill();
            w.spawnEntity(newEntity);
        }




    }






    private static void replaceOldFigure(World w,GlobalChessData currentPosition, boolean white){

        if(Chess.arrayContains(UseEntityHandler.whitePieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
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
            ItemFrameEntity newEntity = MovementCalculations.dataToFigureWithDamage(w,currentPosition, exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newEntity.setInvulnerable(true);
            }
            w.getEntityById(id).kill();
            w.spawnEntity(newEntity);

        }
        if(Chess.arrayContains(UseEntityHandler.blackPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
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
            ItemFrameEntity newEntity = MovementCalculations.dataToFigureWithDamage(w,currentPosition, exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                newEntity.setInvulnerable(true);
            }
            w.getEntityById(id).kill();
            w.spawnEntity(newEntity);
        }




    }




    public static Item exchangeItems(Item input, boolean capture){


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
        }else if(input == ModItems.CASTLE_SWITCH_BLACK_KING){
            return ModItems.CASTLE_BLACK_KING;
        }else if(input == ModItems.CASTLE_SWITCH_BLACK_TOWER){
            return ModItems.CASTLE_BLACK_TOWER;
        }else if(input == ModItems.CASTLE_SWITCH_WHITE_KING){
            return ModItems.CASTLE_WHITE_KING;
        }else if(input == ModItems.CASTLE_SWITCH_WHITE_TOWER){
            return ModItems.CASTLE_WHITE_TOWER;
        }





        return input;

    }


















}
