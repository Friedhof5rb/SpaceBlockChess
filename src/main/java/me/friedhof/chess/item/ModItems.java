package me.friedhof.chess.item;

import me.friedhof.chess.Chess;
import me.friedhof.chess.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ROD_OF_REMOVAL = registerItem("rod_of_removal",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess)));
    public static final Item ROD_OF_ROTATION = registerItem("rod_of_rotation",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess)));
    public static final Item MOVE_HIGHLIGHTER = registerItem("move_highlighter",new Item(new FabricItemSettings()));





    public static final Item WHITE_BISHOP = registerItem("white_bishop",new WhiteBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_KING= registerItem("white_king",new WhiteKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_KNIGHT = registerItem("white_knight",new WhiteKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_PAWN = registerItem("white_pawn",new WhitePawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_QUEEN = registerItem("white_queen",new WhiteQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_ROD_OF_MOVING = registerItem("white_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item WHITE_TOWER = registerItem("white_tower",new WhiteTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));


    public static final Item BLACK_BISHOP = registerItem("black_bishop",new BlackBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_KING= registerItem("black_king",new BlackKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_KNIGHT = registerItem("black_knight",new BlackKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_PAWN = registerItem("black_pawn",new BlackPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_QUEEN = registerItem("black_queen",new BlackQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_ROD_OF_MOVING = registerItem("black_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item BLACK_TOWER = registerItem("black_tower",new BlackTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));




    public static final Item CAPTURE_WHITE_BISHOP = registerItem("capture_white_bishop",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_WHITE_KING= registerItem("capture_white_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_WHITE_KNIGHT = registerItem("capture_white_knight",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_WHITE_PAWN = registerItem("capture_white_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_WHITE_QUEEN = registerItem("capture_white_queen",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_WHITE_TOWER = registerItem("capture_white_tower",new Item(new FabricItemSettings().maxDamage(16)));


    public static final Item CAPTURE_BLACK_BISHOP = registerItem("capture_black_bishop",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_BLACK_KING= registerItem("capture_black_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_BLACK_KNIGHT = registerItem("capture_black_knight",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_BLACK_PAWN = registerItem("capture_black_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_BLACK_QUEEN = registerItem("capture_black_queen",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CAPTURE_BLACK_TOWER = registerItem("capture_black_tower",new Item(new FabricItemSettings().maxDamage(16)));



    public static final Item SELECTED_WHITE_BISHOP = registerItem("selected_white_bishop",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_WHITE_KING= registerItem("selected_white_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_WHITE_KNIGHT = registerItem("selected_white_knight",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_WHITE_PAWN = registerItem("selected_white_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_WHITE_QUEEN = registerItem("selected_white_queen",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_WHITE_TOWER = registerItem("selected_white_tower",new Item(new FabricItemSettings().maxDamage(16)));


    public static final Item SELECTED_BLACK_BISHOP = registerItem("selected_black_bishop",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_BLACK_KING= registerItem("selected_black_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_BLACK_KNIGHT = registerItem("selected_black_knight",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_BLACK_PAWN = registerItem("selected_black_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_BLACK_QUEEN = registerItem("selected_black_queen",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item SELECTED_BLACK_TOWER = registerItem("selected_black_tower",new Item(new FabricItemSettings().maxDamage(16)));


    public static final Item START_BLACK_PAWN = registerItem("start_black_pawn",new StartBlackPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));

    public static final Item START_WHITE_PAWN = registerItem("start_white_pawn",new StartWhitePawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));

    public static final Item START_CAPTURE_BLACK_PAWN = registerItem("start_capture_black_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item START_CAPTURE_WHITE_PAWN = registerItem("start_capture_white_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item START_SELECTED_BLACK_PAWN = registerItem("start_selected_black_pawn",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item START_SELECTED_WHITE_PAWN = registerItem("start_selected_white_pawn",new Item(new FabricItemSettings().maxDamage(16)));



    public static final Item CASTLE_BLACK_KING = registerItem("castle_black_king",new CastleBlackKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item CASTLE_BLACK_TOWER = registerItem("castle_black_tower",new CastleBlackTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item CASTLE_CAPTURE_BLACK_KING = registerItem("castle_capture_black_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_CAPTURE_BLACK_TOWER = registerItem("castle_capture_black_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_CAPTURE_WHITE_KING = registerItem("castle_capture_white_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_CAPTURE_WHITE_TOWER = registerItem("castle_capture_white_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SELECTED_BLACK_KING = registerItem("castle_selected_black_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SELECTED_BLACK_TOWER = registerItem("castle_selected_black_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SELECTED_WHITE_KING = registerItem("castle_selected_white_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SELECTED_WHITE_TOWER = registerItem("castle_selected_white_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SWITCH_BLACK_KING = registerItem("castle_switch_black_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SWITCH_BLACK_TOWER = registerItem("castle_switch_black_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SWITCH_WHITE_KING = registerItem("castle_switch_white_king",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_SWITCH_WHITE_TOWER = registerItem("castle_switch_white_tower",new Item(new FabricItemSettings().maxDamage(16)));
    public static final Item CASTLE_WHITE_KING = registerItem("castle_white_king",new CastleWhiteKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));
    public static final Item CASTLE_WHITE_TOWER = registerItem("castle_white_tower",new CastleWhiteTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxDamage(16)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM,new Identifier(Chess.MOD_ID,name),item);
    }

    public static void registerModItems(){
        Chess.LOGGER.info("Registering Items for " + Chess.MOD_ID);

    }



}
