package me.friedhof.chess.client;

import me.friedhof.chess.Chess;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.particle.ModParticles;
import me.friedhof.chess.particle.custom.ChessParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.registry.Registry;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class ChessClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HudRenderCallback.EVENT.register(new ChessNotationHudOverlay());

        ParticleFactoryRegistry.getInstance().register(ModParticles.CHESS_PARTICLE, ChessParticle.Factory::new);
    }
}
