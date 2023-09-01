package me.friedhof.chess.networking;

import me.friedhof.chess.Chess;
import me.friedhof.chess.networking.packet.SendChatC2SPacket;
import me.friedhof.chess.networking.packet.SpawnFigureC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {

    public static final Identifier SPAWN_FIGURE= new Identifier(Chess.MOD_ID,"spawn_figure");
    public static final Identifier SEND_CHAT= new Identifier(Chess.MOD_ID,"send_chat");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SPAWN_FIGURE, SpawnFigureC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SEND_CHAT, SendChatC2SPacket::receive);
    }
    public static void registerS2CPackets() {

    }


}

