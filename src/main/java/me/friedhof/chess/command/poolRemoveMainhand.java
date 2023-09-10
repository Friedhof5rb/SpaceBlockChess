package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class poolRemoveMainhand {

    private static Item[] items = {ModItems.WHITE_TOWER,ModItems.WHITE_KING,ModItems.CASTLE_WHITE_KING,ModItems.WHITE_QUEEN,ModItems.WHITE_KNIGHT,ModItems.WHITE_BISHOP,ModItems.WHITE_PAWN,ModItems.START_WHITE_PAWN, ModItems.CASTLE_WHITE_TOWER,
            ModItems.BLACK_TOWER,ModItems.BLACK_KING,ModItems.CASTLE_BLACK_KING,ModItems.BLACK_QUEEN,ModItems.BLACK_KNIGHT,ModItems.BLACK_BISHOP,ModItems.BLACK_PAWN,ModItems.START_BLACK_PAWN, ModItems.CASTLE_BLACK_TOWER};




    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("removeFromPool").executes(poolRemoveMainhand::run));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(!context.getSource().getPlayer().isCreative()){
            context.getSource().getPlayer().sendMessage(new LiteralText("You can only use this Command in Creative."), false);
            return -1;
        }

        String uuid = context.getSource().getPlayer().getUuidAsString();
        ItemStack current = context.getSource().getPlayer().getInventory().getMainHandStack().copy();

        if(!Chess.arrayContains(items,current.getItem())){
            return -1;
        }
        if(!Chess.pool.containsKey(uuid)) {
            return -1;
        }
        boolean valid = false;
        for(int i = 0; i< Chess.pool.get(uuid).size();i++){
            if(Chess.pool.get(uuid).get(i).getItem() == current.getItem()){
                valid = true;
            }
        }
        if(!valid){
            return -1;
        }
        ArrayList<ItemStack>  items = Chess.pool.get(uuid);

        if(current.getCount() < 64) {
            int index = -1;
            for(int i = 0 ; i < items.size();i++){
                if(items.get(i).getItem() == current.getItem()){
                    index = i;
                    break;
                }
            }
            if(current.getCount() < items.get(index).getCount() ){
                ItemStack stack = current.copy();
                stack.setCount(items.get(index).getCount()- current.getCount());
                items.set(index,stack);
            }else{
                ItemStack stack = current.copy();
                stack.setCount(64-(current.getCount()-items.get(index).getCount()));
                items.set(index,stack);
                int index2 = -1;
                for(int i = index+1 ; i < items.size();i++){
                    if(items.get(i).getItem() == current.getItem()){
                        index2 = i;
                        break;
                    }
                }
                items.remove(index2);

            }


        }else {

            int index = -1;
            for (int i = items.size() - 1; i >= 0; i--) {
                if (items.get(i).getItem() == current.getItem()) {
                    index = i;
                    break;
                }
            }
            items.remove(index);

        }
        Chess.pool.put(uuid,items);
        Chess.printPool(context.getSource().getPlayer());

        return 1;
    }
}
