package me.friedhof.chess;

import me.friedhof.chess.event.AttackBlockHandler;
import me.friedhof.chess.event.AttackEntityHandler;
import me.friedhof.chess.event.UseEntityHandler;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItemGroup;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.util.GlobalChessData;
import me.friedhof.chess.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.report.ReporterEnvironment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
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
    //test 1.20.1
    public static HashMap<String,ArrayList<ItemStack> > pool = new HashMap<>();

    public static HashMap<String, BlockPos> pos1 = new HashMap<>();
    public static HashMap<String, BlockPos> pos2 = new HashMap<>();

    public static HashMap<String, Boolean> canSeeChessNotation = new HashMap<>();


    public static GlobalChessData lastMove;

    String[] turnOrder = {"white","black"};




    //the order of Items matters
    public static Item[] poolAndPlace = {ModItems.BLACK_BISHOP,ModItems.BLACK_KING,ModItems.BLACK_KNIGHT,ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER,
            ModItems.WHITE_BISHOP,ModItems.WHITE_KING,ModItems.WHITE_KNIGHT,ModItems.WHITE_PAWN,ModItems.WHITE_QUEEN,ModItems.WHITE_TOWER,ModItems.START_WHITE_PAWN,ModItems.START_BLACK_PAWN,
            ModItems.CASTLE_BLACK_KING,ModItems.CASTLE_BLACK_TOWER,ModItems.CASTLE_WHITE_KING,ModItems.CASTLE_WHITE_TOWER,
            ModItems.YELLOW_BISHOP,ModItems.YELLOW_KING,ModItems.YELLOW_KNIGHT,ModItems.YELLOW_PAWN,ModItems.YELLOW_QUEEN, ModItems.YELLOW_TOWER, ModItems.CASTLE_YELLOW_KING,ModItems.CASTLE_YELLOW_TOWER,ModItems.START_YELLOW_PAWN,
            ModItems.PINK_BISHOP, ModItems.PINK_KING, ModItems.PINK_KNIGHT, ModItems.PINK_PAWN, ModItems.PINK_QUEEN, ModItems.PINK_TOWER, ModItems.CASTLE_PINK_KING, ModItems.CASTLE_PINK_TOWER, ModItems.START_PINK_PAWN };




    @Override
    public void onInitialize() {

        ModMessages.registerC2SPackets();
        ModGamerules.registerGamerules();
        ModItems.registerModItems();
        ModItemGroup.registerItemGroups();
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
            text.append(Chess.pool.get(uuid).get(i).getCount()).append(" * ").append(Text.translatable(Chess.pool.get(uuid).get(i).getTranslationKey()).getString()).append(",  \n");

        }

        p.sendMessage(Text.literal(text.toString()),false);


    }

    public static Item[] combineLists(){
       ArrayList<Item> items1 = new ArrayList<>();
       for(Item i : UseEntityHandler.whitePieces){
           items1.add(i);
       }
        for(Item i : UseEntityHandler.blackPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.yellowPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.pinkPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.whiteCapturePieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.blackCapturePieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.yellowCapturePieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.pinkCapturePieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.whiteSelectedPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.blackSelectedPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.yellowSelectedPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.pinkSelectedPieces){
            items1.add(i);
        }
        for(Item i : UseEntityHandler.switchPieces){
            items1.add(i);
        }
        Item[] list = new Item[items1.size()];
        for(int i = 0; i< items1.size();i++){
            list[i] = items1.get(i);
        }
        return list;

    }

    public static String ItemToColour(Item item){

        if(arrayContains(UseEntityHandler.whitePieces,item) || arrayContains(UseEntityHandler.whiteCapturePieces,item) ||arrayContains(UseEntityHandler.whiteSelectedPieces,item) ) {
            return "white";
        }
        if(arrayContains(UseEntityHandler.blackPieces,item) || arrayContains(UseEntityHandler.blackCapturePieces,item) ||arrayContains(UseEntityHandler.blackSelectedPieces,item) ){
            return "black";
        }
        if(arrayContains(UseEntityHandler.yellowPieces,item) || arrayContains(UseEntityHandler.yellowCapturePieces,item) ||arrayContains(UseEntityHandler.yellowSelectedPieces,item) ){
            return "yellow";
        }
        if(arrayContains(UseEntityHandler.pinkPieces,item) || arrayContains(UseEntityHandler.pinkCapturePieces,item) ||arrayContains(UseEntityHandler.pinkSelectedPieces,item) ) {
            return "pink";
        }

        if(item == ModItems.CASTLE_SWITCH_WHITE_TOWER){
            return "white";
        }
        if(item == ModItems.CASTLE_SWITCH_BLACK_TOWER){
            return "black";
        }
        if(item == ModItems.CASTLE_SWITCH_YELLOW_TOWER){
            return "yellow";
        }
        if(item == ModItems.CASTLE_SWITCH_PINK_TOWER){
            return "pink";
        }

        return "";

    }



}
