package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.item.ModItems;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;


import java.util.ArrayList;

public class poolAddMainhand{




    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("addToPool").executes(poolAddMainhand::run)));
    }




    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (!context.getSource().hasPermissionLevel(3)) {
            context.getSource().getPlayer().sendMessage(Text.literal("You must have Permission Level 3."), false);
            return -1;
        }

        String uuid = context.getSource().getPlayer().getUuidAsString();
        ItemStack current = context.getSource().getPlayer().getInventory().getMainHandStack().copy();
        if(!Chess.arrayContains(Chess.poolAndPlace,current.getItem())){
            context.getSource().getPlayer().sendMessage(Text.literal("You can't add this Item to the Pool."), false);
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
