package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.command.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;


public class ModRegistries {

    public static void registerModStuffs(){

        registerCommands();
        Chess.LOGGER.info("Registering ModStuff for " + Chess.MOD_ID);
    }

    private static void registerCommands(){

        CommandRegistrationCallback.EVENT.register(poolAddMainhand::register);
        CommandRegistrationCallback.EVENT.register(poolRemoveMainhand::register);
        CommandRegistrationCallback.EVENT.register(printPool::register);
        CommandRegistrationCallback.EVENT.register(pos1Command::register);
        CommandRegistrationCallback.EVENT.register(pos2Command::register);
        CommandRegistrationCallback.EVENT.register(randomChessFigures::register);
        CommandRegistrationCallback.EVENT.register(clearChessCommand::register);
        CommandRegistrationCallback.EVENT.register(chessCubeCommand::register);
        CommandRegistrationCallback.EVENT.register(switchChessNotationVisbility::register);
    }



}
