package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.util.ChessBotCalculations.Boardstate;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class switchChessNotationVisbility {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("toggleNotationVisibility").executes(switchChessNotationVisbility::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        String uuid = context.getSource().getPlayer().getUuidAsString();
        World w = context.getSource().getWorld();
        if(!Chess.canSeeChessNotation.containsKey(uuid) ){
           Chess.canSeeChessNotation.put(uuid,false);
            context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to false"), false);
        }else{
            if(Chess.canSeeChessNotation.get(uuid)){
                Chess.canSeeChessNotation.put(uuid,false);
                context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to false"), false);

            }else{
                Chess.canSeeChessNotation.put(uuid,true);
                context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to true"), false);
            }
        }
        return 1;
    }
}
