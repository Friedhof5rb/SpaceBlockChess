package me.friedhof.chess;

import me.friedhof.chess.event.*;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItemGroup;
import me.friedhof.chess.item.ModItems;
import me.friedhof.chess.networking.ModMessages;
import me.friedhof.chess.particle.ModParticles;
import me.friedhof.chess.sound.ModSounds;
import me.friedhof.chess.util.GlobalChessData;
import me.friedhof.chess.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.client.report.ReporterEnvironment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


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

    public static HashMap<String,GlobalChessData> lastMoveFrom = new HashMap<>();
    public static HashMap<String,GlobalChessData> lastMoveTo = new HashMap<>();

    public static HashMap<Item, String> itemMap = new HashMap<>();

    public static String[] turnOrder = {"white","black"};






    public static long start_time = 0;
    public static long end_time = 0;
    public static void setStart_time(){
        start_time = System.nanoTime();;
    }
    public static void setEnd_time(){
        end_time = System.nanoTime();;
    }

    public static void printDuration(){

        long nanoseconds = end_time-start_time;
        double milliseconds = nanoseconds/1000000.0;
        System.out.println("nanoseconds: " + nanoseconds);
        System.out.println("milliseconds: " + milliseconds);

    }




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
        ModRegistries.registerModStuffs();
        ModParticles.registerParticles();
        ModSounds.registerSoundEvents();
        initializeList();
        LOGGER.info("Escher's Gambit loaded.");

        
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
        items1.addAll(Arrays.asList(UseEntityHandler.whitePieces));
        items1.addAll(Arrays.asList(UseEntityHandler.blackPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.yellowPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.pinkPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.whiteCapturePieces));
        items1.addAll(Arrays.asList(UseEntityHandler.blackCapturePieces));
        items1.addAll(Arrays.asList(UseEntityHandler.yellowCapturePieces));
        items1.addAll(Arrays.asList(UseEntityHandler.pinkCapturePieces));
        items1.addAll(Arrays.asList(UseEntityHandler.whiteSelectedPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.blackSelectedPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.yellowSelectedPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.pinkSelectedPieces));
        items1.addAll(Arrays.asList(UseEntityHandler.switchPieces));
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

    public static void initializeList(){

        itemMap.put(ModItems.WHITE_KING, "white king");
        itemMap.put(ModItems.WHITE_QUEEN, "white queen");
        itemMap.put(ModItems.WHITE_KNIGHT, "white knight");
        itemMap.put(ModItems.WHITE_BISHOP, "white bishop");
        itemMap.put(ModItems.WHITE_TOWER, "white tower");
        itemMap.put(ModItems.WHITE_PAWN, "white pawn");
        itemMap.put(ModItems.START_WHITE_PAWN, "white start_pawn");
        itemMap.put(ModItems.CASTLE_WHITE_KING, "white castle_king");
        itemMap.put(ModItems.CASTLE_WHITE_TOWER, "white castle_tower");

        itemMap.put(ModItems.BLACK_KING, "black king");
        itemMap.put(ModItems.BLACK_QUEEN, "black queen");
        itemMap.put(ModItems.BLACK_KNIGHT, "black knight");
        itemMap.put(ModItems.BLACK_BISHOP, "black bishop");
        itemMap.put(ModItems.BLACK_TOWER, "black tower");
        itemMap.put(ModItems.BLACK_PAWN, "black pawn");
        itemMap.put(ModItems.START_BLACK_PAWN, "black start_pawn");
        itemMap.put(ModItems.CASTLE_BLACK_KING, "black castle_king");
        itemMap.put(ModItems.CASTLE_BLACK_TOWER, "black castle_tower");

        itemMap.put(ModItems.YELLOW_KING, "yellow king");
        itemMap.put(ModItems.YELLOW_QUEEN, "yellow queen");
        itemMap.put(ModItems.YELLOW_KNIGHT, "yellow knight");
        itemMap.put(ModItems.YELLOW_BISHOP, "yellow bishop");
        itemMap.put(ModItems.YELLOW_TOWER, "yellow tower");
        itemMap.put(ModItems.YELLOW_PAWN, "yellow pawn");
        itemMap.put(ModItems.START_YELLOW_PAWN, "yellow start_pawn");
        itemMap.put(ModItems.CASTLE_YELLOW_KING, "yellow castle_king");
        itemMap.put(ModItems.CASTLE_YELLOW_TOWER, "yellow castle_tower");

        itemMap.put(ModItems.PINK_KING, "pink king");
        itemMap.put(ModItems.PINK_QUEEN, "pink queen");
        itemMap.put(ModItems.PINK_KNIGHT, "pink knight");
        itemMap.put(ModItems.PINK_BISHOP, "pink bishop");
        itemMap.put(ModItems.PINK_TOWER, "pink tower");
        itemMap.put(ModItems.PINK_PAWN, "pink pawn");
        itemMap.put(ModItems.START_PINK_PAWN, "pink start_pawn");
        itemMap.put(ModItems.CASTLE_PINK_KING, "pink castle_king");
        itemMap.put(ModItems.CASTLE_PINK_TOWER, "pink castle_tower");



    }











}
