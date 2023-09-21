package me.friedhof.chess.client;

import me.friedhof.chess.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ChessClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HudRenderCallback.EVENT.register(new ChessNotationHudOverlay());
    }
}
