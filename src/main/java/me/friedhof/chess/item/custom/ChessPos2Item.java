package me.friedhof.chess.item.custom;

import me.friedhof.chess.Chess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class ChessPos2Item extends Item {
    public ChessPos2Item(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos pos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            Chess.pos2.put(player.getUuidAsString(), pos);
            player.sendMessage(Text.literal("Pos2: " + player.getBlockPos().getX() + ", " + player.getBlockPos().getY() + ", " + player.getBlockPos().getZ()), false);
        }
        return super.useOnBlock(context);
    }
}
