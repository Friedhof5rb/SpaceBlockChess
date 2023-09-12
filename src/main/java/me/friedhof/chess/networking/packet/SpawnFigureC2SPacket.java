package me.friedhof.chess.networking.packet;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItemGroup;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SpawnFigureC2SPacket {



    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        World w = player.getWorld();

        if (w.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            return;
        }



        int[] data = buf.readIntArray();
        Direction d = Direction.byId(data[3]);
        BlockPos pos = new BlockPos(data[0], data[1], data[2]);
        int figureIndex = data[4];


        if (d == Direction.DOWN) {
            pos = new BlockPos(data[0], data[1] - 1, data[2]);
        }
        if (d == Direction.UP) {
            pos = new BlockPos(data[0], data[1] + 1, data[2]);
        }
        if (d == Direction.NORTH) {
            pos = new BlockPos(data[0], data[1], data[2] - 1);
        }
        if (d == Direction.SOUTH) {
            pos = new BlockPos(data[0], data[1], data[2] + 1);
        }
        if (d == Direction.WEST) {
            pos = new BlockPos(data[0] - 1, data[1], data[2]);
        }
        if (d == Direction.EAST) {
            pos = new BlockPos(data[0] + 1, data[1], data[2]);
        }

        ItemFrameEntity e = new ItemFrameEntity(w, pos, d);
        ItemStack stack = new ItemStack(Chess.poolAndPlace[figureIndex]);
        e.setHeldItemStack(stack);


        w.spawnEntity(e);

    }






}
