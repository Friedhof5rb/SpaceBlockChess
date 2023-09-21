package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.util.IEntityDataSaver;
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

        IEntityDataSaver saver = (IEntityDataSaver) context.getSource().getPlayer();


        if(!saver.getPersistentData().contains("canSeeChessNotation")){
            saver.getPersistentData().putBoolean("canSeeChessNotation", false);
            context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to false"), false);
        }else{
            if(saver.getPersistentData().getBoolean("canSeeChessNotation")){
                saver.getPersistentData().putBoolean("canSeeChessNotation", false);
                context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to false"), false);
            }else{
                saver.getPersistentData().putBoolean("canSeeChessNotation", true);
                context.getSource().getPlayer().sendMessage(Text.literal("Toggled Visibility to true"), false);
            }
        }
        return 1;
    }
}
