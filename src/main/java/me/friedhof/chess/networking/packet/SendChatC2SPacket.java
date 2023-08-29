package me.friedhof.chess.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SendChatC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

        server.getCommandManager().execute(server.getCommandSource(),"say "+ buf.readString());


    }
}
