package me.friedhof.chess;

import me.friedhof.chess.event.AttackBlockHandler;
import me.friedhof.chess.event.AttackEntityHandler;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Chess implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static final String MOD_ID = "chess";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModMessages.registerC2SPackets();
        ModItems.registerModItems();
        AttackBlockCallback.EVENT.register(new AttackBlockHandler());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
        UseEntityCallback.EVENT.register(new UseEntityHandler());
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
}
