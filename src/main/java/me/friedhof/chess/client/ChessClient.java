package me.friedhof.chess.client;

import me.friedhof.chess.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;

public class ChessClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
    }
}
