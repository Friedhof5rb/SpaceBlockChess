package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class clearChessCommand {


    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("clearFigures").executes(clearChessCommand::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (!context.getSource().hasPermissionLevel(3)) {
            context.getSource().getPlayer().sendMessage(Text.literal("You must have Permission Level 3."), false);
            return -1;
        }



        String uuid = context.getSource().getPlayer().getUuidAsString();


        if(!Chess.pos1.containsKey(uuid) || !Chess.pos2.containsKey(uuid)){
            context.getSource().getPlayer().sendMessage(Text.literal("Pos1 or Pos2 is not defined"), false);
            return -1;
        }

        World w = context.getSource().getWorld();
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(Chess.pos1.get(uuid),Chess.pos2.get(uuid)), EntityPredicates.VALID_ENTITY);
        for(int i = list.size()-1; i >= 0; --i){

            w.getEntityById(list.get(i).getId()).kill();
        }


        return 1;
    }


}
