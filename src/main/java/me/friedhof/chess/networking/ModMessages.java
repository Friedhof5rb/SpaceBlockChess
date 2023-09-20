package me.friedhof.chess.networking;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.networking.packet.SpawnFigureC2SPacket;
import me.friedhof.chess.networking.packet.SpawnParticleS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModMessages {

    public static final Identifier SPAWN_FIGURE= new Identifier(Chess.MOD_ID,"spawn_figure");

    public static final Identifier SPAWN_PARTICLE= new Identifier(Chess.MOD_ID,"spawn_particle");
    public static void registerC2SPackets(){

        ServerPlayNetworking.registerGlobalReceiver(SPAWN_FIGURE, SpawnFigureC2SPacket::receive);
    }
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SPAWN_PARTICLE, SpawnParticleS2CPacket::receive);
    }


}


