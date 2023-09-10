package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class clearChessCommand {


    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("clearChess").executes(clearChessCommand::run));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (!context.getSource().getPlayer().isCreative()) {
            context.getSource().getPlayer().sendMessage(new LiteralText("You can only use this Command in Creative."), false);
            return -1;
        }
        String uuid = context.getSource().getPlayer().getUuidAsString();
        World w = context.getSource().getWorld();
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(Chess.pos1.get(uuid),Chess.pos2.get(uuid)), EntityPredicates.VALID_ENTITY);
        for(int i = list.size()-1; i >= 0; --i){

            w.getEntityById(list.get(i).getId()).kill();
        }


        return 1;
    }


}
