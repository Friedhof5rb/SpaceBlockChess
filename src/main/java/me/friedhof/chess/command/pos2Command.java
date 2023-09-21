package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class pos2Command {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("pos2").executes(pos2Command::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (!context.getSource().hasPermissionLevel(3)) {
            context.getSource().getPlayer().sendMessage(Text.literal("You must have Permission Level 3."), false);
            return -1;
        }

        PlayerEntity p = context.getSource().getPlayer();
        Chess.pos2.put(p.getUuidAsString(),p.getBlockPos());
        p.sendMessage(Text.literal("Pos2: "+ p.getBlockPos().getX() + ", " + p.getBlockPos().getY()+ ", " + p.getBlockPos().getZ()),false);
        return 1;
    }



}
