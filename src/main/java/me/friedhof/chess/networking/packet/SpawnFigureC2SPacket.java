package me.friedhof.chess.networking.packet;

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

    private static Item[] items = {ModItems.BLACK_BISHOP,ModItems.BLACK_KING,ModItems.BLACK_KNIGHT,ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER,
            ModItems.WHITE_BISHOP,ModItems.WHITE_KING,ModItems.WHITE_KNIGHT,ModItems.WHITE_PAWN,ModItems.WHITE_QUEEN,ModItems.WHITE_TOWER,ModItems.START_WHITE_PAWN,ModItems.START_BLACK_PAWN,
    ModItems.CASTLE_BLACK_KING,ModItems.CASTLE_BLACK_TOWER,ModItems.CASTLE_WHITE_KING,ModItems.CASTLE_WHITE_TOWER};

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
        ItemStack stack = new ItemStack(items[figureIndex]);
        e.setHeldItemStack(stack);


        w.spawnEntity(e);

    }






}
