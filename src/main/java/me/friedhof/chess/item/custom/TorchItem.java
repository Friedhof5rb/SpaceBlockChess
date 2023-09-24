package me.friedhof.chess.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TorchItem extends Item {

    public boolean justShowCheck = false;


    public TorchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient()) {
            if (!this.justShowCheck) {
                this.justShowCheck = true;
                user.sendMessage(Text.literal("Mode: safe squares."));
            } else {
                this.justShowCheck = false;
                user.sendMessage(Text.literal("Mode: all squares."));
            }
        }
        return super.use(world, user, hand);
    }


}

