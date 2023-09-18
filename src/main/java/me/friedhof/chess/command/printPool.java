package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class printPool {
    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("printPool").executes(printPool::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(!context.getSource().getPlayer().isCreative()){
            context.getSource().getPlayer().sendMessage(Text.literal("You can only use this Command in Creative."), false);
            return -1;
        }

        if(Chess.pool.containsKey(context.getSource().getPlayer().getUuidAsString())) {
            ArrayList<ItemStack> list = Chess.pool.get(context.getSource().getPlayer().getUuidAsString());
            if (list.isEmpty()) {
                context.getSource().getPlayer().sendMessage(Text.literal("The Pool is Empty"), false);
                return -1;
            }
            Chess.printPool(context.getSource().getPlayer());
        }else{
            context.getSource().getPlayer().sendMessage(Text.literal("The Pool is Empty"), false);
            return -1;
        }
        return 1;
    }


}
