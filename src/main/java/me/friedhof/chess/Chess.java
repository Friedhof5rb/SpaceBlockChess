package me.friedhof.chess;

import me.friedhof.chess.event.AttackBlockHandler;
import me.friedhof.chess.event.AttackEntityHandler;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;


public class Chess implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static final String MOD_ID = "chess";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static HashMap<String,ArrayList<ItemStack> > pool = new HashMap<>();

    public static HashMap<String, BlockPos> pos1 = new HashMap<>();
    public static HashMap<String, BlockPos> pos2 = new HashMap<>();


    @Override
    public void onInitialize() {

        ModMessages.registerC2SPackets();
        ModGamerules.registerGamerules();
        ModItems.registerModItems();
        AttackBlockCallback.EVENT.register(new AttackBlockHandler());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
        UseEntityCallback.EVENT.register(new UseEntityHandler());
        ModRegistries.registerModStuffs();
        LOGGER.info("spaceChessMod loaded.");
    }


    public static boolean arrayContains(Item[] list, Item element){
        for(int i = 0; i< list.length; i++){
            if(list[i] == element){
                return true;
            }
        }
        return false;
    }

    public static void printPool(PlayerEntity p){
        StringBuilder text = new StringBuilder();
        String uuid = p.getUuidAsString();
        for(int i = 0; i < Chess.pool.get(uuid).size();i++){
            text.append(Chess.pool.get(uuid).get(i).getCount()).append(" * ").append(Chess.pool.get(uuid).get(i).getName().getString()).append(",  \n");

        }
        p.sendMessage(new LiteralText(text.toString()),false);



    }
}
