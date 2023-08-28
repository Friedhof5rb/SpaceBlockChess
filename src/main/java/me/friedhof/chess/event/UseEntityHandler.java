package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.util.GlobalChessData;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UseEntityHandler implements UseEntityCallback {


    int figureDrawDistance = 7;
    int searchRadius = 20;
    private static Item[] whitePieces = {ModItems.START_WHITE_PAWN,ModItems.WHITE_BISHOP, ModItems.WHITE_KING, ModItems.WHITE_KNIGHT, ModItems.WHITE_PAWN, ModItems.WHITE_QUEEN, ModItems.WHITE_TOWER};

    private static Item[] blackPieces = {ModItems.START_BLACK_PAWN,ModItems.BLACK_BISHOP, ModItems.BLACK_KING, ModItems.BLACK_KNIGHT, ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER};
    private static Item[] whiteCapturePieces = {ModItems.START_CAPTURE_WHITE_PAWN,ModItems.CAPTURE_WHITE_BISHOP, ModItems.CAPTURE_WHITE_KING, ModItems.CAPTURE_WHITE_KNIGHT, ModItems.CAPTURE_WHITE_PAWN, ModItems.CAPTURE_WHITE_QUEEN, ModItems.CAPTURE_WHITE_TOWER};

    private static Item[] blackCapturePieces = {ModItems.START_CAPTURE_BLACK_PAWN,ModItems.CAPTURE_BLACK_BISHOP, ModItems.CAPTURE_BLACK_KING, ModItems.CAPTURE_BLACK_KNIGHT, ModItems.CAPTURE_BLACK_PAWN, ModItems.CAPTURE_BLACK_QUEEN, ModItems.CAPTURE_BLACK_TOWER};

    private static Item[] whiteSelectedPieces = {ModItems.START_SELECTED_WHITE_PAWN,ModItems.SELECTED_WHITE_BISHOP, ModItems.SELECTED_WHITE_KING, ModItems.SELECTED_WHITE_KNIGHT, ModItems.SELECTED_WHITE_PAWN, ModItems.SELECTED_WHITE_QUEEN, ModItems.SELECTED_WHITE_TOWER};

    private static Item[] blackSelectedPieces = {ModItems.START_SELECTED_BLACK_PAWN,ModItems.SELECTED_BLACK_BISHOP, ModItems.SELECTED_BLACK_KING, ModItems.SELECTED_BLACK_KNIGHT, ModItems.SELECTED_BLACK_PAWN, ModItems.SELECTED_BLACK_QUEEN, ModItems.SELECTED_BLACK_TOWER};


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        boolean whitesTurn = false;

        if (player.getInventory().getMainHandStack().getItem() != ModItems.WHITE_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.BLACK_ROD_OF_MOVING && player.getInventory().getMainHandStack().getItem() != ModItems.ROD_OF_ROTATION) {
            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem())
                        || Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem())
                        || frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER || Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem()) ||
                        Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem()) ) {
                    //Verhindere Rotation der Figuren durch andere Items als "Rod of Rotation"
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.PASS;
                }
            } else {
                return ActionResult.PASS;
            }
        }
        if (player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_ROTATION) {
            return ActionResult.PASS;
        }

        if (player.getInventory().getMainHandStack().getItem() == ModItems.WHITE_ROD_OF_MOVING) {
            whitesTurn = true;
        }

        if (whitesTurn) {

            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(whitePieces, frame.getHeldItemStack().getItem())) {
                    selectFigure(world, frame);
                    return ActionResult.SUCCESS;
                } else if (Chess.arrayContains(blackCapturePieces, frame.getHeldItemStack().getItem())) {
                    takeWithFigure(world, frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(whiteSelectedPieces, frame.getHeldItemStack().getItem())){
                    deselectFigure(world, frame);
                    return ActionResult.SUCCESS;
                }else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {
                    moveFigure(world, frame);
                    return ActionResult.SUCCESS;
                }
            }

        } else {

            if (entity instanceof ItemFrameEntity) {
                ItemFrameEntity frame = (ItemFrameEntity) entity;
                if (Chess.arrayContains(blackPieces, frame.getHeldItemStack().getItem())) {
                    selectFigure(world, frame);
                    return ActionResult.SUCCESS;
                } else if (Chess.arrayContains(whiteCapturePieces, frame.getHeldItemStack().getItem())) {
                    takeWithFigure(world,frame);
                    return ActionResult.SUCCESS;
                }else if(Chess.arrayContains(blackSelectedPieces, frame.getHeldItemStack().getItem())){
                    deselectFigure(world,frame);
                    return ActionResult.SUCCESS;

                } else if (frame.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {
                    moveFigure(world,frame);
                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    //Global Daten speichern: Ausgewählte Figur, markierte Felder (durch Koordinaten,orientierung des Items und richtung, untere blockhälfte = oben(weil zeigt nach oben))



    //Oberflächenbewegungskoordinaten -> einen nach vorne, rechts etc. entlang der Oberfläche ->
    // Resultat: neue Koordinate, neue Richtung für Oberfläche, neue Orientierung entlang der Oberfläche




    private void selectFigure(World w, ItemFrameEntity frame) {
        deselectFigure(w,frame);
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_TOWER){



            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            towerMoveScheme(w,forward,Direction.NORTH,true);
            towerMoveScheme(w,backward,Direction.SOUTH,true);
            towerMoveScheme(w,left,Direction.WEST,true);
            towerMoveScheme(w,right,Direction.EAST,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_TOWER);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_TOWER){


            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            towerMoveScheme(w,forward,Direction.NORTH,false);
            towerMoveScheme(w,backward,Direction.SOUTH,false);
            towerMoveScheme(w,left,Direction.WEST,false);
            towerMoveScheme(w,right,Direction.EAST,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_TOWER);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_BISHOP) {

            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_BISHOP);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);




        }

        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_BISHOP) {

            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            bishopMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            bishopMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            bishopMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            bishopMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_BISHOP);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);
        }

        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_KNIGHT) {

            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,true);
            knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,true);
            knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,true);
            knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,true);
            knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,true);
            knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,true);
            knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_KNIGHT);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_KNIGHT) {
            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            knightMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            knightMoveScheme(w,backward,Direction.SOUTH,Direction.EAST,false);
            knightMoveScheme(w,left,Direction.EAST,Direction.NORTH,false);
            knightMoveScheme(w,right,Direction.WEST,Direction.NORTH,false);
            knightMoveScheme(w,forward,Direction.NORTH,Direction.WEST,false);
            knightMoveScheme(w,backward,Direction.SOUTH,Direction.WEST,false);
            knightMoveScheme(w,left,Direction.EAST,Direction.SOUTH,false);
            knightMoveScheme(w,right,Direction.WEST,Direction.SOUTH,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_KNIGHT);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_QUEEN) {
            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,true);
            queenMoveScheme(w,left,Direction.EAST,Direction.UP,true);
            queenMoveScheme(w,right,Direction.WEST,Direction.UP,true);

            queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_QUEEN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_QUEEN) {
            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            queenMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            queenMoveScheme(w,backward,Direction.SOUTH,Direction.UP,false);
            queenMoveScheme(w,left,Direction.EAST,Direction.UP,false);
            queenMoveScheme(w,right,Direction.WEST,Direction.UP,false);

            queenMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            queenMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            queenMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            queenMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_QUEEN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_KING) {

            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,true);
            kingMoveScheme(w,left,Direction.EAST,Direction.UP,true);
            kingMoveScheme(w,right,Direction.WEST,Direction.UP,true);

            kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,true);
            kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,true);
            kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,true);
            kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_KING);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_KING) {

            GlobalChessData forward = figureToData(frame);
            GlobalChessData backward = figureToData(frame);
            GlobalChessData left = figureToData(frame);
            GlobalChessData right = figureToData(frame);

            kingMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            kingMoveScheme(w,backward,Direction.SOUTH,Direction.UP,false);
            kingMoveScheme(w,left,Direction.EAST,Direction.UP,false);
            kingMoveScheme(w,right,Direction.WEST,Direction.UP,false);

            kingMoveScheme(w,forward,Direction.NORTH,Direction.EAST,false);
            kingMoveScheme(w,backward,Direction.NORTH,Direction.WEST,false);
            kingMoveScheme(w,left,Direction.SOUTH,Direction.EAST,false);
            kingMoveScheme(w,right,Direction.SOUTH,Direction.WEST,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_KING);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);
        }
        if(frame.getHeldItemStack().getItem() == ModItems.WHITE_PAWN) {


            GlobalChessData forward = figureToData(frame);


            pawnMoveScheme(w,forward,Direction.NORTH,true);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_WHITE_PAWN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.BLACK_PAWN) {
            GlobalChessData forward = figureToData(frame);


            pawnMoveScheme(w,forward,Direction.NORTH,false);


            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.SELECTED_BLACK_PAWN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);

        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_WHITE_PAWN) {


            GlobalChessData forward = figureToData(frame);


            startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,true);
            startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,true);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_WHITE_PAWN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);


        }
        if(frame.getHeldItemStack().getItem() == ModItems.START_BLACK_PAWN) {
            GlobalChessData forward = figureToData(frame);


            startPawnMoveScheme(w,forward,Direction.NORTH,Direction.UP,false);
            startPawnMoveScheme(w,forward,Direction.NORTH,Direction.NORTH,false);

            ItemFrameEntity newFrame = new ItemFrameEntity(w,frame.getBlockPos(),frame.getHorizontalFacing());
            newFrame.setRotation(frame.getRotation());
            ItemStack stack = new ItemStack(ModItems.START_SELECTED_BLACK_PAWN);
            newFrame.setHeldItemStack(stack);

            frame.kill();
            w.spawnEntity(newFrame);

        }


    }

    private void deselectFigure(World w, ItemFrameEntity frame){
        int radius = searchRadius;
        GlobalChessData currentPosition = figureToData(frame);
        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-radius,currentPosition.pos.getY()-radius,currentPosition.pos.getZ()-radius,currentPosition.pos.getX()+radius,currentPosition.pos.getY()+radius,currentPosition.pos.getZ()+radius),EntityPredicates.VALID_ENTITY);
        for(int i = 0; i < list.size();i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }
                if(Chess.arrayContains(whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces,entity.getHeldItemStack().getItem()) ){
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(),true);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());

                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(),false);

                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());

                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }


            }


        }



    }



    private void moveFigure(World w, ItemFrameEntity frame) {
        GlobalChessData currentPosition = figureToData(frame);
        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-searchRadius,currentPosition.pos.getY()-searchRadius,currentPosition.pos.getZ()-searchRadius,currentPosition.pos.getX()+searchRadius,currentPosition.pos.getY()+searchRadius,currentPosition.pos.getZ()+searchRadius),EntityPredicates.VALID_ENTITY);
        for(int j = 0; j < list.size();j++) {
            if (list.get(j) instanceof ItemFrameEntity) {
                ItemFrameEntity entity = (ItemFrameEntity) list.get(j);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }
                if(Chess.arrayContains(whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces,entity.getHeldItemStack().getItem()) ){
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(),true);


                    ItemFrameEntity e = new ItemFrameEntity(w,entity.getBlockPos(),entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());

                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);

                }
                if(Chess.arrayContains(whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(),false);
                    if(item == ModItems.START_WHITE_PAWN ){
                        item = ModItems.WHITE_PAWN;
                    }
                    if(item == ModItems.START_BLACK_PAWN ){
                        item = ModItems.BLACK_PAWN;
                    }


                    ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(currentPosition.itemRotation);

                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }



            }
        }
    }

    private void takeWithFigure( World w, ItemFrameEntity frame) {

        int rotation = 0;
        GlobalChessData currentPosition = figureToData(frame);
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-searchRadius,currentPosition.pos.getY()-searchRadius,currentPosition.pos.getZ()-searchRadius,currentPosition.pos.getX()+searchRadius,currentPosition.pos.getY()+searchRadius,currentPosition.pos.getZ()+searchRadius),EntityPredicates.VALID_ENTITY);
        for(int j = 0; j < list.size();j++) {

                ItemFrameEntity entity = list.get(j);
                if(entity.getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){

                    w.getEntityById(entity.getId()).kill();
                }
                if(Chess.arrayContains(whiteCapturePieces,entity.getHeldItemStack().getItem()) || Chess.arrayContains(blackCapturePieces,entity.getHeldItemStack().getItem()) ) {
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(), true);




                    ItemFrameEntity e = new ItemFrameEntity(w, entity.getBlockPos(), entity.getHorizontalFacing());
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);
                    e.setRotation(entity.getRotation());

                    w.getEntityById(entity.getId()).kill();
                    if (!(e.getBlockPos().getX() == currentPosition.pos.getX() && e.getBlockPos().getY() == currentPosition.pos.getY() && e.getBlockPos().getZ() == currentPosition.pos.getZ() && e.getHorizontalFacing() == currentPosition.directionWall)){
                        w.spawnEntity(e);

                    }else{
                        rotation = entity.getHeldItemStack().getDamage();
                        if(e.getHeldItemStack().getItem() == ModItems.WHITE_KING){

                            MinecraftClient mc = MinecraftClient.getInstance();

                            mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("The White King is Dead!"), mc.player.getUuid());


                        }
                        if(e.getHeldItemStack().getItem() == ModItems.BLACK_KING){
                            MinecraftClient mc = MinecraftClient.getInstance();

                            mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("The Black King is Dead!"), mc.player.getUuid());
                        }
                    }



                }





        }
        for(int j = 0; j < list.size();j++) {

                ItemFrameEntity entity = list.get(j);
                if(Chess.arrayContains(whiteSelectedPieces,entity.getHeldItemStack().getItem() ) || Chess.arrayContains(blackSelectedPieces,entity.getHeldItemStack().getItem() )){
                    Item item = exchangeItems(entity.getHeldItemStack().getItem(),false);
                    if(item == ModItems.START_WHITE_PAWN ){
                        item = ModItems.WHITE_PAWN;
                    }
                    if(item == ModItems.START_BLACK_PAWN ){
                        item = ModItems.BLACK_PAWN;
                    }

                    ItemFrameEntity e = new ItemFrameEntity(w,currentPosition.pos,currentPosition.directionWall);
                    ItemStack stack = new ItemStack(item);
                    e.setHeldItemStack(stack);


                    e.setRotation(rotation-1);

                    w.getEntityById(entity.getId()).kill();
                    w.spawnEntity(e);
                }


        }
    }

/////////////////////////////////////////////////////////////////////////////////////
private void towerMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection, boolean white){

    for(int i = 0; i< figureDrawDistance; i++) {
        currentPosition = moveOneInDirection(w, currentPosition, relativeDirection);

        if(currentPosition == null){
            return;
        }


        if(isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                continue;
            }else {
                if(Chess.arrayContains(whitePieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                    ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );



                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);

                }
                if(Chess.arrayContains(blackPieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                    ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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

                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);
                }
                break;
            }
        }else{
            ItemFrameEntity e = dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);


            w.spawnEntity(e);
        }
    }
}



    private void bishopMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){

        for(int i = 0; i< figureDrawDistance; i++) {
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection2);


            if(currentPosition == null){
                return;
            }

            if(isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                    continue;
                }else {
                    if(Chess.arrayContains(whitePieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if(Chess.arrayContains(blackPieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }
                    break;
                }
            }else{
                ItemFrameEntity e = dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);


                w.spawnEntity(e);
            }
        }
    }

    private void knightMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){


            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection2);


        if(currentPosition == null){
            return;
        }

            if(isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                    return;
                }else {
                    if(Chess.arrayContains(whitePieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if(Chess.arrayContains(blackPieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );


                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }
                    return;
                }
            }else{
                ItemFrameEntity e = dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);


                w.spawnEntity(e);
            }

    }


    private void queenMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){

        for(int i = 0; i< figureDrawDistance; i++) {
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
            if(relativeDirection2 != Direction.UP) {
                currentPosition = moveOneInDirection(w, currentPosition, relativeDirection2);
            }


            if(currentPosition == null){
                return;
            }
            if(isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
                if(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                    continue;
                }else {
                    if(Chess.arrayContains(whitePieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );


                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if(Chess.arrayContains(blackPieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                        ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }
                    break;
                }
            }else{
                ItemFrameEntity e = dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);


                w.spawnEntity(e);
            }
        }
    }



    private void kingMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, Direction relativeDirection2, boolean white){


        currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
        if(relativeDirection2 != Direction.UP) {
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection2);
        }

        if(currentPosition == null){
            return;
        }
        if(isItemFrame(w,currentPosition.pos,currentPosition.directionWall)){
            if(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER){
                return;
            }else {
                if(Chess.arrayContains(whitePieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  !white){
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                    ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);

                }
                if(Chess.arrayContains(blackPieces,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem()) &&  white){
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(currentPosition.pos.getX()-1,currentPosition.pos.getY()-1,currentPosition.pos.getZ()-1,currentPosition.pos.getX()+1,currentPosition.pos.getY()+1,currentPosition.pos.getZ()+1),EntityPredicates.VALID_ENTITY);



                    ItemFrameEntity old = dataToFigure(w,currentPosition,getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem());
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
                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,currentPosition, exchangeItems(getItemFrame(w,currentPosition.pos,currentPosition.directionWall).getHeldItemStack().getItem(),true),rotation );

                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);
                }
                return;
            }
        }else{
            ItemFrameEntity e = dataToFigure(w,currentPosition, ModItems.MOVE_HIGHLIGHTER);


            w.spawnEntity(e);
        }

    }



    private void pawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1, boolean white) {


        currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
        GlobalChessData takingPosition1 = moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = moveOneInDirection(w, currentPosition, Direction.WEST);


        if (currentPosition != null) {
            if (!isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {
                ItemFrameEntity e = dataToFigure(w, currentPosition, ModItems.MOVE_HIGHLIGHTER);


                w.spawnEntity(e);
            }
        }


        //1
        if (takingPosition1 != null) {
            if (isItemFrame(w, takingPosition1.pos, takingPosition1.directionWall)) {
                if (getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                } else {
                    if (Chess.arrayContains(whitePieces, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem()) && !white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition1.pos.getX() - 1, takingPosition1.pos.getY() - 1, takingPosition1.pos.getZ() - 1, takingPosition1.pos.getX() + 1, takingPosition1.pos.getY() + 1, takingPosition1.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition1, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem());
                        int rotation = currentPosition.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition1.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition1, exchangeItems(getItemFrame(w,takingPosition1.pos,takingPosition1.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if (Chess.arrayContains(blackPieces, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem()) && white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition1.pos.getX() - 1, takingPosition1.pos.getY() - 1, takingPosition1.pos.getZ() - 1, takingPosition1.pos.getX() + 1, takingPosition1.pos.getY() + 1, takingPosition1.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition1, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem());
                        int rotation = takingPosition1.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition1.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition1, exchangeItems(getItemFrame(w,takingPosition1.pos,takingPosition1.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }

                }
            }
        }
        //2
        if(takingPosition2 != null) {
        if (isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
            if (getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

            } else {
                if (Chess.arrayContains(whitePieces, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem()) && !white) {
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition2.pos.getX() - 1, takingPosition2.pos.getY() - 1, takingPosition2.pos.getZ() - 1, takingPosition2.pos.getX() + 1, takingPosition2.pos.getY() + 1, takingPosition2.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                    ItemFrameEntity old = dataToFigure(w, takingPosition2, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem());
                    int rotation = takingPosition2.itemRotation;
                    int id = 0;
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j) instanceof ItemFrameEntity) {
                            ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                            if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                    old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                takingPosition2.itemRotation = old2.getRotation();
                                id = old2.getId();
                                break;
                            }
                        }

                    }
                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition2, exchangeItems(getItemFrame(w,takingPosition2.pos,takingPosition2.directionWall).getHeldItemStack().getItem(),true),rotation );

                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);

                }
                if (Chess.arrayContains(blackPieces, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem()) && white) {
                    List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition2.pos.getX() - 1, takingPosition2.pos.getY() - 1, takingPosition2.pos.getZ() - 1, takingPosition2.pos.getX() + 1, takingPosition2.pos.getY() + 1, takingPosition2.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                    ItemFrameEntity old = dataToFigure(w, takingPosition2, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem());
                    int rotation = takingPosition2.itemRotation;
                    int id = 0;
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j) instanceof ItemFrameEntity) {
                            ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                            if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                    old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                takingPosition2.itemRotation = old2.getRotation();
                                id = old2.getId();
                                break;
                            }
                        }

                    }
                    ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition2, exchangeItems(getItemFrame(w,takingPosition2.pos,takingPosition2.directionWall).getHeldItemStack().getItem(),true),rotation );

                    w.getEntityById(id).kill();
                    w.spawnEntity(newEntity);
                }

            }
        }
    }






    }


    private void startPawnMoveScheme(World w, GlobalChessData currentPosition,Direction relativeDirection1,Direction relativeDirection2, boolean white){


        currentPosition = moveOneInDirection(w, currentPosition, relativeDirection1);
        if(relativeDirection2 != Direction.UP) {
            currentPosition = moveOneInDirection(w, currentPosition, relativeDirection2);
        }
        GlobalChessData takingPosition1 = moveOneInDirection(w, currentPosition, Direction.EAST);
        GlobalChessData takingPosition2 = moveOneInDirection(w, currentPosition, Direction.WEST);



        if(currentPosition != null) {
            if (!isItemFrame(w, currentPosition.pos, currentPosition.directionWall)) {
                ItemFrameEntity e = dataToFigure(w, currentPosition, ModItems.MOVE_HIGHLIGHTER);


                w.spawnEntity(e);
            }
        }
        //1
        if(takingPosition1 != null) {
            if (isItemFrame(w, takingPosition1.pos, takingPosition1.directionWall)) {
                if (getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                } else {
                    if (Chess.arrayContains(whitePieces, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem()) && !white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition1.pos.getX() - 1, takingPosition1.pos.getY() - 1, takingPosition1.pos.getZ() - 1, takingPosition1.pos.getX() + 1, takingPosition1.pos.getY() + 1, takingPosition1.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition1, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem());
                        int rotation = takingPosition1.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition1.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition1, exchangeItems(getItemFrame(w,takingPosition1.pos,takingPosition1.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if (Chess.arrayContains(blackPieces, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem()) && white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition1.pos.getX() - 1, takingPosition1.pos.getY() - 1, takingPosition1.pos.getZ() - 1, takingPosition1.pos.getX() + 1, takingPosition1.pos.getY() + 1, takingPosition1.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition1, getItemFrame(w, takingPosition1.pos, takingPosition1.directionWall).getHeldItemStack().getItem());
                        int rotation = takingPosition1.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition1.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition1, exchangeItems(getItemFrame(w,takingPosition1.pos,takingPosition1.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }

                }
            }
        }
        //2
        if(takingPosition2 != null) {
            if (isItemFrame(w, takingPosition2.pos, takingPosition2.directionWall)) {
                if (getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem() == ModItems.MOVE_HIGHLIGHTER) {

                } else {
                    if (Chess.arrayContains(whitePieces, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem()) && !white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition2.pos.getX() - 1, takingPosition2.pos.getY() - 1, takingPosition2.pos.getZ() - 1, takingPosition2.pos.getX() + 1, takingPosition2.pos.getY() + 1, takingPosition2.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition2, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem());
                        int rotation = takingPosition2.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition2.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition2, exchangeItems(getItemFrame(w,takingPosition2.pos,takingPosition2.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);

                    }
                    if (Chess.arrayContains(blackPieces, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem()) && white) {
                        List list = w.getEntitiesByType(EntityType.ITEM_FRAME, new Box(takingPosition2.pos.getX() - 1, takingPosition2.pos.getY() - 1, takingPosition2.pos.getZ() - 1, takingPosition2.pos.getX() + 1, takingPosition2.pos.getY() + 1, takingPosition2.pos.getZ() + 1), EntityPredicates.VALID_ENTITY);


                        ItemFrameEntity old = dataToFigure(w, takingPosition2, getItemFrame(w, takingPosition2.pos, takingPosition2.directionWall).getHeldItemStack().getItem());
                        int rotation = takingPosition2.itemRotation;
                        int id = 0;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j) instanceof ItemFrameEntity) {
                                ItemFrameEntity old2 = (ItemFrameEntity) list.get(j);
                                if (old2.getHorizontalFacing() == old.getHorizontalFacing() &&
                                        old2.getBlockPos().getX() == old.getBlockPos().getX() && old2.getBlockPos().getY() == old.getBlockPos().getY() && old2.getBlockPos().getZ() == old.getBlockPos().getZ()) {
                                    takingPosition2.itemRotation = old2.getRotation();
                                    id = old2.getId();
                                    break;
                                }
                            }

                        }
                        ItemFrameEntity newEntity = dataToFigureWithDamage(w,takingPosition2, exchangeItems(getItemFrame(w,takingPosition2.pos,takingPosition2.directionWall).getHeldItemStack().getItem(),true),rotation );

                        w.getEntityById(id).kill();
                        w.spawnEntity(newEntity);
                    }

                }
            }
        }






    }

////////////////////////////////////////////////////////


    private Item exchangeItems(Item input, boolean capture){


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
        }
        return input;

    }







    private boolean isItemFrame(World w, BlockPos pos, Direction direction){
        ItemFrameEntity frame = new ItemFrameEntity(w,pos,direction);
        List list = w.getEntitiesByType(frame.getType(),new Box(pos.getX()-1,pos.getY()-1,pos.getZ()-1,pos.getX()+1, pos.getY()+1, pos.getZ()+1), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getBlockPos().getX() == pos.getX() && entity.getBlockPos().getY() == pos.getY() && entity.getBlockPos().getZ() == pos.getZ() && entity.getHorizontalFacing() == direction){
                    return true;
                }
            }

        }
        return false;
    }

    private ItemFrameEntity getItemFrame(World w, BlockPos pos, Direction direction){
        ItemFrameEntity frame = new ItemFrameEntity(w,pos,direction);
        List list = w.getEntitiesByType(frame.getType(),new Box(pos.getX()-1,pos.getY()-1,pos.getZ()-1,pos.getX()+1, pos.getY()+1, pos.getZ()+1), EntityPredicates.VALID_ENTITY);
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) instanceof ItemFrameEntity){
                ItemFrameEntity entity = (ItemFrameEntity) list.get(i);
                if(entity.getBlockPos().getX() == pos.getX() && entity.getBlockPos().getY() == pos.getY() && entity.getBlockPos().getZ() == pos.getZ() && entity.getHorizontalFacing() == direction){
                    return entity;
                }
            }

        }
        return frame;
    }

    private GlobalChessData figureToData(ItemFrameEntity frame){

        GlobalChessData data = new GlobalChessData(frame.getBlockPos(),frame.getHorizontalFacing(),frame.getRotation(),false);

        return data;
    }

    private ItemFrameEntity dataToFigure(World w, GlobalChessData data, Item item){

        ItemFrameEntity frame = new ItemFrameEntity(w,data.pos,data.directionWall);
        ItemStack stack = new ItemStack(item);
        frame.setHeldItemStack(stack);
        frame.setRotation(data.itemRotation);
        return frame;
    }
    private ItemFrameEntity dataToFigureWithDamage(World w, GlobalChessData data, Item item, int damage){

        ItemFrameEntity frame = new ItemFrameEntity(w,data.pos,data.directionWall);
        ItemStack stack = new ItemStack(item);
        stack.setDamage(damage+1);
        frame.setHeldItemStack(stack);
        frame.setRotation(data.itemRotation);
        return frame;
    }








    private GlobalChessData moveOneInDirection(World w,GlobalChessData data, Direction forwardNorth) {

        if(data == null){
            return null;
        }


        Direction absolute = relativeToAbsolute(data, forwardNorth);
        BlockPos sameBlock = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ());
        BlockPos attachedBlock = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ()).offset(data.directionWall.getOpposite(), 1);
        BlockPos nextTo = new BlockPos(data.pos.getX(), data.pos.getY(), data.pos.getZ()).offset(absolute,1);
        BlockPos diagonal = new BlockPos(attachedBlock.getX(), attachedBlock.getY(), attachedBlock.getZ()).offset(absolute,1);

        //innenkanten
        if(w.getBlockState(nextTo).getMaterial() != Material.AIR){

            if(w.getBlockState(nextTo).getMaterial() == Material.WATER || w.getBlockState(nextTo).getMaterial() == Material.LAVA || w.getBlockState(nextTo).getMaterial() == Material.GLASS){
                return null;
            }

            int newItemRotation = correctRotations(data.itemRotation,data.directionWall,absolute);
            if(data.directionWall == Direction.UP || data.directionWall == Direction.DOWN){
                newItemRotation = (newItemRotation + 4) % 8;
            }

            GlobalChessData newPosition = new GlobalChessData(sameBlock,absolute.getOpposite(),newItemRotation,false);

            return newPosition;

            //glatt
        }else if(w.getBlockState(diagonal).getMaterial() != Material.AIR){
            if(w.getBlockState(diagonal).getMaterial() == Material.WATER || w.getBlockState(diagonal).getMaterial() == Material.LAVA|| w.getBlockState(diagonal).getMaterial() == Material.GLASS){
                return null;
            }
            GlobalChessData newPosition = new GlobalChessData(nextTo,data.directionWall, data.itemRotation, false);

            return newPosition;

            //außenkanten
        }else{



            GlobalChessData newPosition = new GlobalChessData(diagonal,absolute,correctRotations(data.itemRotation,data.directionWall, absolute),false);

            return newPosition;
        }





    }



    private int correctRotations(int itemRotation, Direction from, Direction to){

        return (itemRotation + correctRotationsToAdd(from, to)) % 8;

    }




    //für außenkanten
    private int correctRotationsToAdd(Direction from, Direction to){

       int[][] mapping = new int[6][6];

        mapping[0][2] = 4;
        mapping[2][0] = 4;
        mapping[0][3]= 0;
        mapping[3][0] = 0;
        mapping[1][2] = 4;
        mapping[2][1]= 4;
        mapping[1][3]= 0;
        mapping[3][1] = 0;

        mapping[2][5] = 0;
        mapping[5][2] = 0;
        mapping[2][4]= 0;
        mapping[4][2] = 0;
        mapping[3][4] = 0;
        mapping[4][3] = 0;
        mapping[3][5] = 0;
        mapping[5][3]= 0;

        mapping[5][0] = 6;
        mapping[0][5] = 2;
        mapping[5][1] = 6;
        mapping[1][5] = 2;

        mapping[4][0] = 2;
        mapping[0][4] = 6;
        mapping[4][1] = 2;
        mapping[1][4] = 6;

        return mapping[from.getId()][to.getId()];

    }



    private Direction relativeToAbsolute(GlobalChessData data, Direction forwardNorth){
        Direction n = Direction.NORTH;
        switch (data.directionWall) {
            case UP:

                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                break;
            case DOWN:

                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.SOUTH;
                        break;
                    case SOUTH:
                        n = Direction.NORTH;
                        break;
                    case WEST:
                        n = Direction.EAST;
                        break;
                    case EAST:
                        n = Direction.WEST;
                        break;
                    default:
                        break;


                }




                break;
            case NORTH:
                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.EAST;
                        break;
                    case EAST:
                        n = Direction.WEST;
                        break;
                    default:
                        break;


                }


                break;
            case SOUTH:
                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.WEST;
                        break;
                    case EAST:
                        n = Direction.EAST;
                        break;
                    default:
                        break;


                }



                break;
            case EAST:
                n = alignRotationToItem(forwardNorth, data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.SOUTH;
                        break;
                    case EAST:
                        n = Direction.NORTH;
                        break;
                    default:
                        break;


                }



                break;
            case WEST:
                n = alignRotationToItem(forwardNorth, data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.NORTH;
                        break;
                    case EAST:
                        n = Direction.SOUTH;
                        break;
                    default:
                        break;

                }

                break;
            default:
                System.out.println("Direction of Chesspiece not defined");

        }
        return n;
    }



    private Direction alignRotationToItem(Direction n, int itemRotation, boolean clockwise) {

    if(clockwise) {
        for (int i = 0; i < itemRotation / 2; i++) {
            n = n.rotateClockwise(Direction.Axis.Y);
        }
    }else{
        for (int i = 0; i < itemRotation / 2; i++) {
            n = n.rotateCounterclockwise(Direction.Axis.Y);
        }
        }


        return n;
    }









}
