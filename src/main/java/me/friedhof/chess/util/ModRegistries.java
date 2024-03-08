package me.friedhof.chess.util;

import me.friedhof.chess.Chess;
import me.friedhof.chess.command.*;
import me.friedhof.chess.event.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class ModRegistries {

    public static void registerModStuffs(){

        registerEvents();
        registerCommands();
        Chess.LOGGER.info("Registering ModStuff for " + Chess.MOD_ID);
    }

    private static void registerCommands(){

        CommandRegistrationCallback.EVENT.register(switchChessNotationVisbility::register);
        CommandRegistrationCallback.EVENT.register(pos1Command::register);
        CommandRegistrationCallback.EVENT.register(pos2Command::register);
        CommandRegistrationCallback.EVENT.register(chessCubeCommand::register);
        CommandRegistrationCallback.EVENT.register(poolAddMainhand::register);
        CommandRegistrationCallback.EVENT.register(poolRemoveMainhand::register);
        CommandRegistrationCallback.EVENT.register(printPool::register);
        CommandRegistrationCallback.EVENT.register(randomChessFigures::register);
        CommandRegistrationCallback.EVENT.register(clearChessCommand::register);

    }

    private static void registerEvents() {
        AttackBlockCallback.EVENT.register(new AttackBlockHandler());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
        UseEntityCallback.EVENT.register(new UseEntityHandler());
        ServerTickEvents.START_SERVER_TICK.register(new StartServerTickHandler());
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }



    private static void  registerModels(){


    }


}
