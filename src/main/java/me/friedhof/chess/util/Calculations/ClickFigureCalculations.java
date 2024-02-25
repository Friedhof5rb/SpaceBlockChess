package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.Chess;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.GlobalChessData;
import me.friedhof.chess.util.IEntityDataSaver;
import me.friedhof.chess.util.IItemFrameDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClickFigureCalculations {


    private static void spawnMoveHighlighter(World w, GlobalChessData currentPosition){

        ItemFrameEntity e = MovementCalculations.dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);
        e.setInvisible(true);
        IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
        saver.setFixed(true);
        if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            e.setInvulnerable(true);
        }
        w.spawnEntity(e);

    }

    private static void replaceOldFigure(World w,GlobalChessData currentPosition, String team){

        if(Chess.arrayContains(UseEntityHandler.whitePieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  team != "white"){
            replacementForColour(w,currentPosition,FigurePotentialMovesCalculations.exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));

        }
        if(Chess.arrayContains(UseEntityHandler.blackPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "black"){
            replacementForColour(w,currentPosition,FigurePotentialMovesCalculations.exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));
        }
        if(Chess.arrayContains(UseEntityHandler.yellowPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "yellow"){
            replacementForColour(w,currentPosition,FigurePotentialMovesCalculations.exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));

        }
        if(Chess.arrayContains(UseEntityHandler.pinkPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team != "pink"){
            replacementForColour(w,currentPosition,FigurePotentialMovesCalculations.exchangeItems(MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true));
        }



    }


    private static void replaceOldFigureForCastling(World w,GlobalChessData currentPosition, String team){

        if(Chess.arrayContains(UseEntityHandler.whitePieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  team == "white"){
            replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_WHITE_TOWER);

        }
        if(Chess.arrayContains(UseEntityHandler.blackPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "black"){
            replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_BLACK_TOWER);
        }
        if(Chess.arrayContains(UseEntityHandler.yellowPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "yellow"){
            replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_YELLOW_TOWER);

        }
        if(Chess.arrayContains(UseEntityHandler.pinkPieces,MovementCalculations.getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&   team == "pink"){
            replacementForColour(w,currentPosition,ModItems.CASTLE_SWITCH_PINK_TOWER);
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
        IItemFrameDataSaver saver = (IItemFrameDataSaver) newEntity;
        saver.setFixed(true);
        if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            newEntity.setInvulnerable(true);
        }
        w.getEntityById(id).kill();
        w.spawnEntity(newEntity);


    }

    private static ArrayList<GlobalChessData> listFromColour(String team, FigurePotentialMovesCalculations calc){

        ArrayList<GlobalChessData> data = new ArrayList<>();
        switch(team){

            case "white" -> {
                return calc.whitePotentialMoves;
            }
            case "black" -> {
                return calc.blackPotentialMoves;
            }
            case "yellow" -> {
                return calc.yellowPotentialMoves;
            }
            case "pink" -> {
                return calc.pinkPotentialMoves;
            }
        }
        return data;
    }







    private static void turnBoardToReality(World w, String team, FigurePotentialMovesCalculations calc,ItemFrameEntity frame, BoardState b ){
        Item item = frame.getHeldItemStack().getItem();
        for(GlobalChessData data2 : listFromColour(team,calc)){
            if(FigurePotentialMovesCalculations.isContainedInBoardState(data2,b)){
                if(!b.getColourOfFigure(data2).equals(team)) {
                    replaceOldFigure(w, data2, team);
                }else{
                    replaceOldFigureForCastling(w, data2, team);
                }
            }else{
                spawnMoveHighlighter(w,data2);
            }
        }


        ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
        newFrame.setRotation(frame.getRotation());
        ItemStack stack = new ItemStack(FigurePotentialMovesCalculations.exchangeItems(item,false));
        newFrame.setHeldItemStack(stack);
        newFrame.setInvisible(true);
        IItemFrameDataSaver saver = (IItemFrameDataSaver) newFrame;
        saver.setFixed(true);
        if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            newFrame.setInvulnerable(true);
        }
        frame.kill();
        w.spawnEntity(newFrame);


    }






    public static void selectFigure(World w, ItemFrameEntity frame, String team) {
        deselectFigure(w,frame);


        Item item = frame.getHeldItemStack().getItem();
        if(Chess.itemMap.get(item).equals(team + " tower")){

            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForTower(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);


        }


        if(Chess.itemMap.get(item).equals(team + " bishop")) {

            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForBishop(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);



        }


        if(Chess.itemMap.get(item).equals(team + " knight")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForKnight(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }

        if(Chess.itemMap.get(item).equals(team + " queen")) {

            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForQueen(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);

        }

        if(Chess.itemMap.get(item).equals(team + " king")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForKing(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }

        if(Chess.itemMap.get(item).equals(team + " pawn")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForPawn(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }



        if(Chess.itemMap.get(item).equals(team + " start_pawn")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForStartPawn(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }


            //Castling


        if(Chess.itemMap.get(item).equals(team + " castle_king")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForCastleKing(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }


        if(Chess.itemMap.get(item).equals(team + " castle_tower")) {


            FigurePotentialMovesCalculations calc = new FigurePotentialMovesCalculations();
            GlobalChessData data = MovementCalculations.figureToData(frame);
            BoardState b = checkCalculations.getCurrentBoardState(w,data);

            calc.calculateAllMovesForCastleTower(w,data,team,b);

            turnBoardToReality(w,team,calc,frame,b);
        }

        
    }

    public static void deselectFigure(World w, ItemFrameEntity frame){
        int radius = UseEntityHandler.searchRadius;
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-radius,currentPosition.pos.getY()-radius,currentPosition.pos.getZ()-radius,currentPosition.pos.getX()+radius,currentPosition.pos.getY()+radius,currentPosition.pos.getZ()+radius), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i < list.size();i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }

                if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem()) ){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }


            }


        }



    }



    public static GlobalChessData moveFigure(World w, ItemFrameEntity frame) {


        GlobalChessData result = MovementCalculations.figureToData(frame);
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()- UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);


        for(int j = 0; j < list.size();j++) {
                ItemFrameEntity entity = list.get(j);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }

                if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }

                if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem())  || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())  ){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);


                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) ||
                        Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
                    GlobalChessData oneStep = MovementCalculations.moveOneInDirection(w,MovementCalculations.figureToData(entity),Direction.NORTH);
                    GlobalChessData twoStep = MovementCalculations.moveOneInDirection(w,oneStep,Direction.NORTH);

                    if(item == ModItems.START_WHITE_PAWN ){
                        item = ModItems.WHITE_PAWN;

                    }
                    if(item == ModItems.START_BLACK_PAWN ){
                        item = ModItems.BLACK_PAWN;

                    }
                    if(item == ModItems.CASTLE_WHITE_KING){
                        item = ModItems.WHITE_KING;
                    }
                    if(item == ModItems.CASTLE_WHITE_TOWER){
                        item = ModItems.WHITE_TOWER;
                    }
                    if(item == ModItems.CASTLE_BLACK_KING){
                        item = ModItems.BLACK_KING;
                    }
                    if(item == ModItems.CASTLE_BLACK_TOWER){
                        item = ModItems.BLACK_TOWER;
                    }

                    if(item == ModItems.START_YELLOW_PAWN ){
                        item = ModItems.YELLOW_PAWN;

                    }
                    if(item == ModItems.START_PINK_PAWN ){
                        item = ModItems.PINK_PAWN;

                    }
                    if(item == ModItems.CASTLE_YELLOW_KING){
                        item = ModItems.YELLOW_KING;
                    }
                    if(item == ModItems.CASTLE_YELLOW_TOWER){
                        item = ModItems.YELLOW_TOWER;
                    }
                    if(item == ModItems.CASTLE_PINK_KING){
                        item = ModItems.PINK_KING;
                    }
                    if(item == ModItems.CASTLE_PINK_TOWER){
                        item = ModItems.PINK_TOWER;
                    }




                    result = currentPosition;
                    ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);



                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(currentPosition.itemRotation);
                    deleteFigureFromEnPassant(w, e);
                    removeEnPassantPermission(frame,w);




                    NbtCompound nbt = new NbtCompound();


                    boolean isPawn = item == ModItems.WHITE_PAWN || item == ModItems.BLACK_PAWN || item == ModItems.YELLOW_PAWN || item == ModItems.PINK_PAWN;
                    if(twoStep.pos.getX() == currentPosition.pos.getX() && twoStep.pos.getY() == currentPosition.pos.getY() && twoStep.pos.getZ() == currentPosition.pos.getZ()
                            && twoStep.directionWall== currentPosition.directionWall) {
                        if(isPawn) {
                            nbt.putBoolean("allowEnPassant", true);
                            stack.setNbt(nbt);
                        }
                    }


                    e.setHeldItemStack(stack);
                    e.setInvisible(true);
                    IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                    saver.setFixed(true);
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }




        }
        checkForCheck(w,currentPosition);
        return result;
    }


    public static GlobalChessData takeWithFigure( World w, ItemFrameEntity frame) {

        GlobalChessData result = MovementCalculations.figureToData(frame);
        int rotation = 0;
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        removeEnPassantPermission(frame,w);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                w.getEntityById(entity.getId()).kill();
            }
            if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())){
                Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);

                ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                e.setInvisible(true);
                IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                saver.setFixed(true);
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);

            }

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())) {
                Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(), true);


                if (w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {

                    ItemStack stack3 = new ItemStack(item);
                    ItemEntity e3 = new ItemEntity(w, currentPosition.pos.getX(), currentPosition.pos.getY(), currentPosition.pos.getZ(), stack3);
                    w.spawnEntity(e3);

                }
                ItemFrameEntity e = new ItemFrameEntity(w, entity.getBlockPos(), entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                e.setInvisible(true);
                IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                saver.setFixed(true);
                w.getEntityById(entity.getId()).kill();
                if (!(e.getBlockPos().getX() == currentPosition.pos.getX() && e.getBlockPos().getY() == currentPosition.pos.getY() && e.getBlockPos().getZ() == currentPosition.pos.getZ() && e.getHorizontalFacing() == currentPosition.directionWall)){
                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }

                    w.spawnEntity(e);

                }else{
                    rotation = entity.getHeldItemStack().getNbt().getInt("rotation");


                    if(e.getHeldItemStack().getItem() == ModItems.WHITE_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_WHITE_KING){


                        String dead = "The White King is defeated!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true,false,Formatting.RED);


                    }
                    if(e.getHeldItemStack().getItem() == ModItems.BLACK_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_BLACK_KING){
                        String dead = "The Black King is defeated!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true,false,Formatting.RED);
                    }


                    if(e.getHeldItemStack().getItem() == ModItems.YELLOW_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_YELLOW_KING){


                        String dead = "The Yellow King is defeated!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true,false,Formatting.RED);


                    }
                    if(e.getHeldItemStack().getItem() == ModItems.PINK_KING || e.getHeldItemStack().getItem() == ModItems.CASTLE_PINK_KING){
                        String dead = "The Pink King is defeated!";
                        sendMessageToClosePlayers(w,dead,50,currentPosition.pos,true,false,Formatting.RED);
                    }



                }

            }

        }
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) |
                    Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() )  || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() ) ){
                Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
                if(item == ModItems.START_WHITE_PAWN ){
                    item = ModItems.WHITE_PAWN;
                }
                if(item == ModItems.START_BLACK_PAWN ){
                    item = ModItems.BLACK_PAWN;
                }

                if(item == ModItems.CASTLE_WHITE_KING){
                    item = ModItems.WHITE_KING;
                }
                if(item == ModItems.CASTLE_WHITE_TOWER){
                    item = ModItems.WHITE_TOWER;
                }
                if(item == ModItems.CASTLE_BLACK_KING){
                    item = ModItems.BLACK_KING;
                }
                if(item == ModItems.CASTLE_BLACK_TOWER){
                    item = ModItems.BLACK_TOWER;
                }

                if(item == ModItems.START_YELLOW_PAWN ){
                    item = ModItems.YELLOW_PAWN;
                }
                if(item == ModItems.START_PINK_PAWN ){
                    item = ModItems.PINK_PAWN;
                }

                if(item == ModItems.CASTLE_YELLOW_KING){
                    item = ModItems.YELLOW_KING;
                }
                if(item == ModItems.CASTLE_YELLOW_TOWER){
                    item = ModItems.YELLOW_TOWER;
                }
                if(item == ModItems.CASTLE_PINK_KING){
                    item = ModItems.PINK_KING;
                }
                if(item == ModItems.CASTLE_PINK_TOWER){
                    item = ModItems.PINK_TOWER;
                }
                
                result = currentPosition;
                ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setInvisible(true);
                IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                saver.setFixed(true);
                e.setRotation(rotation);
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);
            }


        }
        checkForCheck(w,currentPosition);
        return result;
    }



    public static void sendMessageToClosePlayers(World w, String s, int radius, BlockPos currentPosition, boolean alsoActionbar,boolean ChessNotation, Formatting textcolour){


        List<PlayerEntity> list = w.getEntitiesByType(EntityType.PLAYER,new Box(currentPosition.getX()-radius,currentPosition.getY()-radius,currentPosition.getZ()-radius,currentPosition.getX()+radius,currentPosition.getY()+radius,currentPosition.getZ()+radius),EntityPredicates.VALID_ENTITY);

        for(PlayerEntity p : list){
            String uuid = p.getUuidAsString();
            IEntityDataSaver saver = (IEntityDataSaver) p;
            if(saver.getPersistentData().contains("canSeeChessNotation") && ChessNotation){
                if(!(saver.getPersistentData().getBoolean("canSeeChessNotation"))){
                    continue;
                }
            }

            p.sendMessage(Text.literal(s).formatted(textcolour),false);
            if(alsoActionbar){
                p.sendMessage(Text.literal(s),true);
            }
        }


    }





    public static GlobalChessData switchFigure( World w, ItemFrameEntity frame){


        GlobalChessData result1 = MovementCalculations.figureToData(frame);
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);
        removeEnPassantPermission(frame,w);
        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if (entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                w.getEntityById(entity.getId()).kill();
            }

            if(Chess.arrayContains(UseEntityHandler.whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.blackCapturePieces,entity.getHeldItemStack().getItem()) ||
                    Chess.arrayContains(UseEntityHandler.yellowCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(UseEntityHandler.pinkCapturePieces,entity.getHeldItemStack().getItem())){


                Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),true);


                ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                e.setInvisible(true);
                IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                saver.setFixed(true);
                if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                    e.setInvulnerable(true);
                }
                w.getEntityById(entity.getId()).kill();
                w.spawnEntity(e);

            }


            if(Chess.arrayContains(UseEntityHandler.switchPieces,entity.getHeldItemStack().getItem())) {
                Item item = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(), true);


                ItemFrameEntity e = new ItemFrameEntity(w, entity.getBlockPos(), entity.getHorizontalFacing());
                ItemStack stack = new ItemStack(item);
                e.setHeldItemStack(stack);
                e.setRotation(entity.getRotation());
                e.setInvisible(true);
                IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
                saver.setFixed(true);
                if(!(e.getBlockPos().getX() == currentPosition.pos.getX() && e.getBlockPos().getY() == currentPosition.pos.getY() && e.getBlockPos().getZ() == currentPosition.pos.getZ() && e.getHorizontalFacing() == currentPosition.directionWall)){

                    if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                        e.setInvulnerable(true);
                    }
                     w.getEntityById(entity.getId()).kill();
                     w.spawnEntity(e);
                }
            }



        }

        for(int j = 0; j < list.size();j++) {

            ItemFrameEntity entity = list.get(j);
            if(Chess.arrayContains(UseEntityHandler.whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.blackSelectedPieces,entity.getHeldItemStack().getItem() ) ||
                    Chess.arrayContains(UseEntityHandler.yellowSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(UseEntityHandler.pinkSelectedPieces,entity.getHeldItemStack().getItem() )){

            GlobalChessData selectedPiece = MovementCalculations.figureToData(entity);
            Direction result = Direction.UP;
            if(isSwitchPiece(w,Direction.NORTH,selectedPiece,currentPosition)){
                result = Direction.NORTH;
            }
            if(isSwitchPiece(w,Direction.SOUTH,selectedPiece,currentPosition)){
                    result = Direction.SOUTH;
            }
            if(isSwitchPiece(w,Direction.EAST,selectedPiece,currentPosition)){
                    result = Direction.EAST;
            }
            if(isSwitchPiece(w,Direction.WEST,selectedPiece,currentPosition)){
                    result = Direction.WEST;
            }
            GlobalChessData newSwitchPiece = MovementCalculations.moveOneInDirection(w,selectedPiece,result);
            GlobalChessData newPiece = MovementCalculations.moveOneInDirection(w,newSwitchPiece,result);

            w.getEntityById(frame.getId()).kill();
            w.getEntityById(entity.getId()).kill();


            Item item2 = FigurePotentialMovesCalculations.exchangeItems(entity.getHeldItemStack().getItem(),false);
            Item item = FigurePotentialMovesCalculations.exchangeItems(frame.getHeldItemStack().getItem(),false);

                if(item2 == ModItems.CASTLE_WHITE_KING){
                    item2 = ModItems.WHITE_KING;
                }
                if(item == ModItems.CASTLE_WHITE_TOWER){
                    item = ModItems.WHITE_TOWER;
                }
                if(item2 == ModItems.CASTLE_BLACK_KING){
                    item2 = ModItems.BLACK_KING;
                }
                if(item == ModItems.CASTLE_BLACK_TOWER){
                    item = ModItems.BLACK_TOWER;
                }

                if(item2 == ModItems.CASTLE_YELLOW_KING){
                    item2 = ModItems.YELLOW_KING;
                }
                if(item == ModItems.CASTLE_YELLOW_TOWER){
                    item = ModItems.YELLOW_TOWER;
                }
                if(item2 == ModItems.CASTLE_PINK_KING){
                    item2 = ModItems.PINK_KING;
                }
                if(item == ModItems.CASTLE_PINK_TOWER){
                    item = ModItems.PINK_TOWER;
                }

            ItemFrameEntity e = new ItemFrameEntity(w,newSwitchPiece.pos,newSwitchPiece.directionWall);
            ItemStack stack = new ItemStack(item);
            e.setHeldItemStack(stack);
            e.setRotation(newSwitchPiece.itemRotation);
            e.setInvisible(true);
            IItemFrameDataSaver saver = (IItemFrameDataSaver) e;
            saver.setFixed(true);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e.setInvulnerable(true);
            }
            w.spawnEntity(e);
            result1 = newPiece;
            ItemFrameEntity e2 = new ItemFrameEntity(w,newPiece.pos,newPiece.directionWall);
            ItemStack stack2 = new ItemStack(item2);
            e2.setHeldItemStack(stack2);
            e2.setRotation(newPiece.itemRotation);
            e2.setInvisible(true);
            IItemFrameDataSaver saver2 = (IItemFrameDataSaver) e2;
            saver2.setFixed(true);
            if(!w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
                e2.setInvulnerable(true);
            }
            w.spawnEntity(e2);



            }

        }
        checkForCheck(w,currentPosition);
    return result1;
    }

    private static boolean isSwitchPiece(World w, Direction direction,GlobalChessData selectedPiece, GlobalChessData switchPiece) {
        for (int i = 0; i < UseEntityHandler.figureDrawDistance; i++) {
            selectedPiece = MovementCalculations.moveOneInDirection(w, selectedPiece, direction);

            if(selectedPiece == null){
                return false;
            }
            if(selectedPiece.pos.getX() == switchPiece.pos.getX() && selectedPiece.pos.getY() == switchPiece.pos.getY() && selectedPiece.pos.getZ() == switchPiece.pos.getZ() && selectedPiece.directionWall == switchPiece.directionWall){
                return true;
            }


        }
        return false;
    }

    public static void checkForCheck(World w, GlobalChessData currentPosition){

        checkCheckForColour("white",w,currentPosition);
        checkCheckForColour("black",w,currentPosition);
        checkCheckForColour("yellow",w,currentPosition);
        checkCheckForColour("pink",w,currentPosition);

    }

    private static void checkCheckForColour(String team, World w, GlobalChessData currentPosition){


        boolean check = checkCalculations.isKingOfColourInCheck(w,team,checkCalculations.getCurrentBoardState(w,currentPosition));
        boolean potentialCheck = checkCalculations.isKingOfColourInPotentialCheck(w,team,checkCalculations.getCurrentBoardState(w,currentPosition));

        if(check && !potentialCheck){

            sendMessageToClosePlayers(w, team+" is in Check!!!",50,currentPosition.pos,false,false,Formatting.YELLOW);
        }
        if(check && potentialCheck){
            sendMessageToClosePlayers(w, team+" is in Checkmate!!!",50,currentPosition.pos,false,false,Formatting.RED);
        }
        if(!check && potentialCheck) {
            sendMessageToClosePlayers(w, team+" is in Stalemate!!!",50,currentPosition.pos,false,false,Formatting.BLUE);
        }


    }


    private static void removeEnPassantPermission(ItemFrameEntity frame, World w){
        GlobalChessData currentPosition = MovementCalculations.figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-UseEntityHandler.searchRadius,currentPosition.pos.getY()-UseEntityHandler.searchRadius,currentPosition.pos.getZ()-UseEntityHandler.searchRadius,currentPosition.pos.getX()+UseEntityHandler.searchRadius,currentPosition.pos.getY()+UseEntityHandler.searchRadius,currentPosition.pos.getZ()+UseEntityHandler.searchRadius),EntityPredicates.VALID_ENTITY);

        for(ItemFrameEntity e : list){

            ItemStack stack = e.getHeldItemStack();
            if(stack.hasNbt()){
                if(stack.getNbt().contains("allowEnPassant")){
                    stack.getNbt().remove("allowEnPassant");
                }
            }
            e.setHeldItemStack(stack);
        }
    }


    private static void deleteFigureFromEnPassant(World w, ItemFrameEntity frame){
        Item item = frame.getHeldItemStack().getItem();
        boolean isPawn = item == ModItems.WHITE_PAWN || item == ModItems.BLACK_PAWN || item == ModItems.YELLOW_PAWN || item == ModItems.PINK_PAWN;
        if(isPawn) {

            GlobalChessData backStep = MovementCalculations.moveOneInDirection(w, MovementCalculations.figureToData(frame), Direction.SOUTH);
            if (MovementCalculations.isItemFrame(w, backStep.pos, backStep.directionWall)) {

                ItemFrameEntity e2 = MovementCalculations.getItemFrame(w, backStep.pos, backStep.directionWall);
                ItemStack stack = e2.getHeldItemStack();
                if (stack.hasNbt()) {
                    NbtCompound nbt = stack.getNbt();
                    if (nbt.contains("allowEnPassant")) {
                        if (nbt.getBoolean("allowEnPassant")) {
                          w.getEntityById(e2.getId()).kill();

                        }
                    }
                }
            }
        }

    }
















}
