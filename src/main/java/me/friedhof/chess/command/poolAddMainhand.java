package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class poolAddMainhand {


    private static Item[] items = {ModItems.WHITE_TOWER,ModItems.WHITE_KING,ModItems.CASTLE_WHITE_KING,ModItems.WHITE_QUEEN,ModItems.WHITE_KNIGHT,ModItems.WHITE_BISHOP,ModItems.WHITE_PAWN,ModItems.START_WHITE_PAWN, ModItems.CASTLE_WHITE_TOWER,
            ModItems.BLACK_TOWER,ModItems.BLACK_KING,ModItems.CASTLE_BLACK_KING,ModItems.BLACK_QUEEN,ModItems.BLACK_KNIGHT,ModItems.BLACK_BISHOP,ModItems.BLACK_PAWN,ModItems.START_BLACK_PAWN, ModItems.CASTLE_BLACK_TOWER};




    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("addToPool").executes(poolAddMainhand::run));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(!context.getSource().getPlayer().isCreative()){
            context.getSource().getPlayer().sendMessage(new LiteralText("You can only use this Command in Creative."), false);
            return -1;
        }

        String uuid = context.getSource().getPlayer().getUuidAsString();
        ItemStack current = context.getSource().getPlayer().getInventory().getMainHandStack();
        if(!Chess.arrayContains(items,current.getItem())){
            return -1;
        }

        ArrayList<ItemStack> items;
        if(Chess.pool.containsKey(uuid)) {
            items = Chess.pool.get(uuid);

            int index = -1;
            for(int i = 0 ; i< items.size();i++){
                if(items.get(i).getItem() == current.getItem()){
                    index = i;
                    break;
                }
            }


            if(index == -1) {
                items.add(current);
                Chess.pool.put(uuid, items);
            }else{
                ItemStack stack = items.get(index);
                int newCount = items.get(index).getCount()+current.getCount();


                while(newCount > 64){
                    newCount -= 64;
                    ItemStack stack2 = items.get(index).copy();
                    stack2.setCount(64);
                    items.add(stack2);
                }
                stack.setCount(newCount);
                items.set(index,stack);

                Chess.pool.put(uuid,items);
            }


        }else{
            items = new ArrayList<>();
            items.add(current);
            Chess.pool.put(uuid,items);
        }


        Chess.printPool(context.getSource().getPlayer());

        return 1;
    }







}
