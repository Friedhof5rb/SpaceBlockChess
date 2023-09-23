package me.friedhof.chess.event;

import me.friedhof.chess.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModPlayerEventCopyFrom implements ServerPlayerEvents.CopyFrom{


    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IEntityDataSaver original = (IEntityDataSaver) oldPlayer;
        IEntityDataSaver player = (IEntityDataSaver) newPlayer;

        player.getPersistentData().putBoolean("canSeeChessNotation",original.getPersistentData().getBoolean("canSeeChessNotation"));
    }
}
