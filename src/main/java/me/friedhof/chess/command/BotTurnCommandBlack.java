package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.util.BoardState;
import me.friedhof.chess.util.Calculations.ClickFigureCalculations;
import me.friedhof.chess.util.Calculations.MovementCalculations;
import me.friedhof.chess.util.Calculations.checkCalculations;
import me.friedhof.chess.util.ChessBot.Bot;
import me.friedhof.chess.util.ChessBot.TreeNode;
import me.friedhof.chess.util.FigureOnBoard;
import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BotTurnCommandBlack {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("botTurn").then(CommandManager.literal("black").executes(BotTurnCommandBlack::run))));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        World w = context.getSource().getWorld();
        Vec3d vec = context.getSource().getPosition();
        BoardState current =  checkCalculations.getCurrentBoardState(w,new GlobalChessData(new BlockPos((int)vec.getX(),(int)vec.getY(),(int)vec.getZ()), Direction.UP,0,false));
        Bot bot = new Bot("black",w,current);
        context.getSource().getPlayer().sendMessage(Text.literal("Black Bot started Calculating..."));
        Chess.setStart_time();
        TreeNode t = bot.computeBestMove();
        Chess.setEnd_time();




        context.getSource().getPlayer().sendMessage(Text.literal("Black Bot finished Calculating. Now playing."));
        ArrayList<FigureOnBoard> list = checkCalculations.compareBoardStates(current,t.getState());
        ArrayList<FigureOnBoard> list2 = checkCalculations.compareBoardStates(t.getState(),current);
        int r = 50;
        List<ItemFrameEntity> EntityList = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(vec.getX()-r,vec.getY()-r,vec.getZ()-r,vec.getX()+r,vec.getY()+r,vec.getZ()+r), EntityPredicates.VALID_ENTITY);

        List<PlayerEntity> players = w.getEntitiesByType(EntityType.PLAYER,new Box(vec.getX()-r,vec.getY()-r,vec.getZ()-r,vec.getX()+r,vec.getY()+r,vec.getZ()+r),EntityPredicates.VALID_ENTITY);
        int wk2 = 0;
        int bk2 = 0;
        int yk2 = 0;
        int pk2 = 0;
        int wk1 = 0;
        int bk1 = 0;
        int yk1 = 0;
        int pk1 = 0;


        for(FigureOnBoard f : list2){
            GlobalChessData data = f.data;
            Item item = f.item;
            for(ItemFrameEntity e : EntityList){
                if(e.getBlockPos().getX() == data.pos.getX() && e.getBlockPos().getY() == data.pos.getY() &&
                        e.getBlockPos().getZ() == data.pos.getZ() && e.getHorizontalFacing() == data.directionWall){




                    w.getEntityById(e.getId()).kill();
                    for(PlayerEntity p : players) {
                        String uuid = p.getUuidAsString();

                        Chess.lastMoveFrom.put(uuid, data);


                    }
                }
            }
            if( item == ModItems.WHITE_KING || item == ModItems.CASTLE_WHITE_KING ) {
                wk2 +=1;
            }
            if( item == ModItems.BLACK_KING || item == ModItems.CASTLE_BLACK_KING ) {
                bk2 +=1;

            }
            if( item == ModItems.YELLOW_KING || item == ModItems.CASTLE_YELLOW_KING ) {
                yk2 +=1;
            }
            if( item == ModItems.PINK_KING || item == ModItems.CASTLE_PINK_KING ) {
                pk2 +=1;
            }

        }
        for(FigureOnBoard f : list) {
            GlobalChessData data = f.data;
            Item item = f.item;
            ItemFrameEntity e = MovementCalculations.dataToFigure(w,data,item);
            e.setInvisible(true);
            e.setInvulnerable(true);
            w.spawnEntity(e);
            for(PlayerEntity p : players) {
                String uuid = p.getUuidAsString();

                Chess.lastMoveTo.put(uuid, data);


            }
            if( item == ModItems.WHITE_KING || item == ModItems.CASTLE_WHITE_KING ) {
                wk1 +=1;
            }
            if( item == ModItems.BLACK_KING || item == ModItems.CASTLE_BLACK_KING ) {
                bk1 +=1;

            }
            if( item == ModItems.YELLOW_KING || item == ModItems.CASTLE_YELLOW_KING ) {
                yk1 +=1;
            }
            if( item == ModItems.PINK_KING || item == ModItems.CASTLE_PINK_KING ) {
                pk1 +=1;
            }



        }


        if(wk2 < wk1) {
                String dead = "The White King is defeated!";
                ClickFigureCalculations.sendMessageToClosePlayers(w, dead, 50, new BlockPos((int) vec.getX(), (int) vec.getY(), (int) vec.getZ()), true, false, Formatting.RED);
        }

        if(bk2 < bk1) {
            String dead = "The Black King is defeated!";
            ClickFigureCalculations.sendMessageToClosePlayers(w, dead, 50, new BlockPos((int) vec.getX(), (int) vec.getY(), (int) vec.getZ()), true, false, Formatting.RED);
        }

        if(yk2 < yk1) {
            String dead = "The Yellow King is defeated!";
            ClickFigureCalculations.sendMessageToClosePlayers(w, dead, 50, new BlockPos((int) vec.getX(), (int) vec.getY(), (int) vec.getZ()), true, false, Formatting.RED);
        }

        if(pk2 < pk1) {
            String dead = "The Pink King is defeated!";
            ClickFigureCalculations.sendMessageToClosePlayers(w, dead, 50, new BlockPos((int) vec.getX(), (int) vec.getY(), (int) vec.getZ()), true, false, Formatting.RED);
        }



        ClickFigureCalculations.checkForCheck(w,new GlobalChessData(new BlockPos((int)vec.getX(),(int)vec.getY(),(int)vec.getZ()), Direction.UP,0,false));
        context.getSource().getPlayer().sendMessage(Text.literal("Black Bot played it's move."));
        Chess.printDuration();


        return 1;
    }



}
