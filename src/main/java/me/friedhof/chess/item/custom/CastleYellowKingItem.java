package me.friedhof.chess.item.custom;

import me.friedhof.chess.networking.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CastleYellowKingItem extends Item {


    public CastleYellowKingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {


        World w =  context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction d = context.getSide();

        if(w.isClient()) {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeIntArray(new int[]{pos.getX(), pos.getY(), pos.getZ(), d.getId(),24});
            ClientPlayNetworking.send(ModMessages.SPAWN_FIGURE, buffer);
        }

        return super.useOnBlock(context);
    }
}
