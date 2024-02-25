package me.friedhof.chess.networking.packet;

import me.friedhof.chess.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ChangeNotationVisibilityC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {


        IEntityDataSaver saver = (IEntityDataSaver) player;


        if(!saver.getPersistentData().contains("canSeeChessNotation")){
            saver.getPersistentData().putBoolean("canSeeChessNotation", false);
            player.sendMessage(Text.literal("Toggled Visibility to false"), false);
        }else{
            if(saver.getPersistentData().getBoolean("canSeeChessNotation")){
                saver.getPersistentData().putBoolean("canSeeChessNotation", false);
                player.sendMessage(Text.literal("Toggled Visibility to false"), false);
            }else{
                saver.getPersistentData().putBoolean("canSeeChessNotation", true);
                player.sendMessage(Text.literal("Toggled Visibility to true"), false);
            }
        }

    }
}
