package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BotTurnCommandPink {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("botTurn").then(CommandManager.literal("pink").executes(BotTurnCommandPink::run))));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {



        World w = context.getSource().getWorld();
        Vec3d vec = context.getSource().getPosition();
        BoardState current =  checkCalculations.getCurrentBoardState(w,new GlobalChessData(new BlockPos((int)vec.getX(),(int)vec.getY(),(int)vec.getZ()), Direction.UP,0,false));
        Bot bot = new Bot("pink",w,current);
        context.getSource().getPlayer().sendMessage(Text.literal("Bot started Calculating..."));
        Chess.setStart_time();
        TreeNode t = bot.computeBestMove();
        Chess.setEnd_time();
        context.getSource().getPlayer().sendMessage(Text.literal("Bot finished Calculating. Now playing."));
        ArrayList<FigureOnBoard> list = checkCalculations.compareBoardStates(current,t.getState());
        ArrayList<FigureOnBoard> list2 = checkCalculations.compareBoardStates(t.getState(),current);
        int r = 50;
        List<ItemFrameEntity> EntityList = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(vec.getX()-r,vec.getY()-r,vec.getZ()-r,vec.getX()+r,vec.getY()+r,vec.getZ()+r), EntityPredicates.VALID_ENTITY);
        List<PlayerEntity> players = w.getEntitiesByType(EntityType.PLAYER,new Box(vec.getX()-r,vec.getY()-r,vec.getZ()-r,vec.getX()+r,vec.getY()+r,vec.getZ()+r),EntityPredicates.VALID_ENTITY);

        for(FigureOnBoard f : list2){
            GlobalChessData data = f.data;
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
        }
        ClickFigureCalculations.checkForCheck(w,new GlobalChessData(new BlockPos((int)vec.getX(),(int)vec.getY(),(int)vec.getZ()), Direction.UP,0,false));
        context.getSource().getPlayer().sendMessage(Text.literal("Bot played it's move."));
        Chess.printDuration();
        return 1;
    }



}
