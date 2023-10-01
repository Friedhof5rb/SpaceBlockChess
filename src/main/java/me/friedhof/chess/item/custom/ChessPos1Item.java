package me.friedhof.chess.item.custom;

import me.friedhof.chess.Chess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class ChessPos1Item extends Item {
    public ChessPos1Item(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        if(!context.getWorld().isClient()) {


            BlockPos pos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            Chess.pos1.put(player.getUuidAsString(), pos);
            player.sendMessage(Text.literal("Pos1: " + pos.getX() + ", " +pos.getY() + ", " + pos.getZ()), false);
        }
        return super.useOnBlock(context);
    }




}
