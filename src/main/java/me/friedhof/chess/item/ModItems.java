package me.friedhof.chess.item;

import me.friedhof.chess.Chess;
import me.friedhof.chess.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {


    //Utility
    public static final Item ROD_OF_REMOVAL = registerItem("rod_of_removal",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));
    public static final Item ROD_OF_ROTATION = registerItem("rod_of_rotation",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));
    public static final Item MOVE_HIGHLIGHTER = registerItem("move_highlighter",new Item(new FabricItemSettings()));

    public static final Item CHESS_CORE = registerItem("chess_core",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess)));
    public static final Item CHESS_POS1 = registerItem("chesspos1",new ChessPos1Item(new FabricItemSettings().group(ModItemGroup.SpaceChess)));
    public static final Item CHESS_POS2 = registerItem("chesspos2",new ChessPos2Item(new FabricItemSettings().group(ModItemGroup.SpaceChess)));

    public static final Item WHITE_ROD_OF_MOVING = registerItem("white_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));
    public static final Item BLACK_ROD_OF_MOVING = registerItem("black_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));

    public static final Item YELLOW_ROD_OF_MOVING = registerItem("yellow_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));
    public static final Item PINK_ROD_OF_MOVING = registerItem("pink_rod_of_moving",new Item(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(1)));





    //White
    public static final Item WHITE_PAWN = registerItem("white_pawn",new WhitePawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item WHITE_TOWER = registerItem("white_tower",new WhiteTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item WHITE_KNIGHT = registerItem("white_knight",new WhiteKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item WHITE_BISHOP = registerItem("white_bishop",new WhiteBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item WHITE_QUEEN = registerItem("white_queen",new WhiteQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item WHITE_KING= registerItem("white_king",new WhiteKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));

    public static final Item START_WHITE_PAWN = registerItem("start_white_pawn",new StartWhitePawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_WHITE_KING = registerItem("castle_white_king",new CastleWhiteKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_WHITE_TOWER = registerItem("castle_white_tower",new CastleWhiteTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item CAPTURE_WHITE_BISHOP = registerItem("capture_white_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_WHITE_KING= registerItem("capture_white_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_WHITE_KNIGHT = registerItem("capture_white_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_WHITE_PAWN = registerItem("capture_white_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_WHITE_QUEEN = registerItem("capture_white_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_WHITE_TOWER = registerItem("capture_white_tower",new Item(new FabricItemSettings().maxCount(64)));


    public static final Item START_CAPTURE_WHITE_PAWN = registerItem("start_capture_white_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_WHITE_KING = registerItem("castle_capture_white_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_WHITE_TOWER = registerItem("castle_capture_white_tower",new Item(new FabricItemSettings().maxCount(64)));



    public static final Item SELECTED_WHITE_BISHOP = registerItem("selected_white_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_WHITE_KING= registerItem("selected_white_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_WHITE_KNIGHT = registerItem("selected_white_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_WHITE_PAWN = registerItem("selected_white_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_WHITE_QUEEN = registerItem("selected_white_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_WHITE_TOWER = registerItem("selected_white_tower",new Item(new FabricItemSettings().maxCount(64)));

    public static final Item START_SELECTED_WHITE_PAWN = registerItem("start_selected_white_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_WHITE_KING = registerItem("castle_selected_white_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_WHITE_TOWER = registerItem("castle_selected_white_tower",new Item(new FabricItemSettings().maxCount(64)));



    //public static final Item CASTLE_SWITCH_WHITE_KING = registerItem("castle_switch_white_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SWITCH_WHITE_TOWER = registerItem("castle_switch_white_tower",new Item(new FabricItemSettings().maxCount(64)));







    //Black
    public static final Item BLACK_PAWN = registerItem("black_pawn",new BlackPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item BLACK_TOWER = registerItem("black_tower",new BlackTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item BLACK_KNIGHT = registerItem("black_knight",new BlackKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item BLACK_BISHOP = registerItem("black_bishop",new BlackBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item BLACK_QUEEN = registerItem("black_queen",new BlackQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item BLACK_KING= registerItem("black_king",new BlackKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item START_BLACK_PAWN = registerItem("start_black_pawn",new StartBlackPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_BLACK_KING = registerItem("castle_black_king",new CastleBlackKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_BLACK_TOWER = registerItem("castle_black_tower",new CastleBlackTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item CAPTURE_BLACK_BISHOP = registerItem("capture_black_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_BLACK_KING= registerItem("capture_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_BLACK_KNIGHT = registerItem("capture_black_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_BLACK_PAWN = registerItem("capture_black_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_BLACK_QUEEN = registerItem("capture_black_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_BLACK_TOWER = registerItem("capture_black_tower",new Item(new FabricItemSettings().maxCount(64)));



    public static final Item START_CAPTURE_BLACK_PAWN = registerItem("start_capture_black_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_BLACK_KING = registerItem("castle_capture_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_BLACK_TOWER = registerItem("castle_capture_black_tower",new Item(new FabricItemSettings().maxCount(64)));


    public static final Item SELECTED_BLACK_BISHOP = registerItem("selected_black_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_BLACK_KING= registerItem("selected_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_BLACK_KNIGHT = registerItem("selected_black_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_BLACK_PAWN = registerItem("selected_black_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_BLACK_QUEEN = registerItem("selected_black_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_BLACK_TOWER = registerItem("selected_black_tower",new Item(new FabricItemSettings().maxCount(64)));

    public static final Item START_SELECTED_BLACK_PAWN = registerItem("start_selected_black_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_BLACK_KING = registerItem("castle_selected_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_BLACK_TOWER = registerItem("castle_selected_black_tower",new Item(new FabricItemSettings().maxCount(64)));



    //public static final Item CASTLE_SWITCH_BLACK_KING = registerItem("castle_switch_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SWITCH_BLACK_TOWER = registerItem("castle_switch_black_tower",new Item(new FabricItemSettings().maxCount(64)));


    //Yellow
    public static final Item YELLOW_PAWN = registerItem("yellow_pawn",new YellowPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item YELLOW_TOWER = registerItem("yellow_tower",new YellowTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item YELLOW_KNIGHT = registerItem("yellow_knight",new YellowKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item YELLOW_BISHOP = registerItem("yellow_bishop",new YellowBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item YELLOW_QUEEN = registerItem("yellow_queen",new YellowQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item YELLOW_KING= registerItem("yellow_king",new YellowKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item START_YELLOW_PAWN = registerItem("start_yellow_pawn",new StartYellowPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_YELLOW_KING = registerItem("castle_yellow_king",new CastleYellowKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_YELLOW_TOWER = registerItem("castle_yellow_tower",new CastleYellowTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item CAPTURE_YELLOW_BISHOP = registerItem("capture_yellow_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_YELLOW_KING= registerItem("capture_yellow_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_YELLOW_KNIGHT = registerItem("capture_yellow_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_YELLOW_PAWN = registerItem("capture_yellow_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_YELLOW_QUEEN = registerItem("capture_yellow_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_YELLOW_TOWER = registerItem("capture_yellow_tower",new Item(new FabricItemSettings().maxCount(64)));



    public static final Item START_CAPTURE_YELLOW_PAWN = registerItem("start_capture_yellow_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_YELLOW_KING = registerItem("castle_capture_yellow_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_YELLOW_TOWER = registerItem("castle_capture_yellow_tower",new Item(new FabricItemSettings().maxCount(64)));


    public static final Item SELECTED_YELLOW_BISHOP = registerItem("selected_yellow_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_YELLOW_KING= registerItem("selected_yellow_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_YELLOW_KNIGHT = registerItem("selected_yellow_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_YELLOW_PAWN = registerItem("selected_yellow_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_YELLOW_QUEEN = registerItem("selected_yellow_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_YELLOW_TOWER = registerItem("selected_yellow_tower",new Item(new FabricItemSettings().maxCount(64)));

    public static final Item START_SELECTED_YELLOW_PAWN = registerItem("start_selected_yellow_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_YELLOW_KING = registerItem("castle_selected_yellow_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_YELLOW_TOWER = registerItem("castle_selected_yellow_tower",new Item(new FabricItemSettings().maxCount(64)));



    //public static final Item CASTLE_SWITCH_BLACK_KING = registerItem("castle_switch_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SWITCH_YELLOW_TOWER = registerItem("castle_switch_yellow_tower",new Item(new FabricItemSettings().maxCount(64)));


    //pink
    public static final Item PINK_PAWN = registerItem("pink_pawn",new PinkPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item PINK_TOWER = registerItem("pink_tower",new PinkTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item PINK_KNIGHT = registerItem("pink_knight",new PinkKnightItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item PINK_BISHOP = registerItem("pink_bishop",new PinkBishopItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item PINK_QUEEN = registerItem("pink_queen",new PinkQueenItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item PINK_KING= registerItem("pink_king",new PinkKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));




    public static final Item START_PINK_PAWN = registerItem("start_pink_pawn",new StartPinkPawnItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_PINK_KING = registerItem("castle_pink_king",new CastlePinkKingItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));
    public static final Item CASTLE_PINK_TOWER = registerItem("castle_pink_tower",new CastlePinkTowerItem(new FabricItemSettings().group(ModItemGroup.SpaceChess).maxCount(64)));



    public static final Item CAPTURE_PINK_BISHOP = registerItem("capture_pink_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_PINK_KING= registerItem("capture_pink_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_PINK_KNIGHT = registerItem("capture_pink_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_PINK_PAWN = registerItem("capture_pink_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_PINK_QUEEN = registerItem("capture_pink_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CAPTURE_PINK_TOWER = registerItem("capture_pink_tower",new Item(new FabricItemSettings().maxCount(64)));



    public static final Item START_CAPTURE_PINK_PAWN = registerItem("start_capture_pink_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_PINK_KING = registerItem("castle_capture_pink_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_CAPTURE_PINK_TOWER = registerItem("castle_capture_pink_tower",new Item(new FabricItemSettings().maxCount(64)));


    public static final Item SELECTED_PINK_BISHOP = registerItem("selected_pink_bishop",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_PINK_KING= registerItem("selected_pink_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_PINK_KNIGHT = registerItem("selected_pink_knight",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_PINK_PAWN = registerItem("selected_pink_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_PINK_QUEEN = registerItem("selected_pink_queen",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SELECTED_PINK_TOWER = registerItem("selected_pink_tower",new Item(new FabricItemSettings().maxCount(64)));

    public static final Item START_SELECTED_PINK_PAWN = registerItem("start_selected_pink_pawn",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_PINK_KING = registerItem("castle_selected_pink_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SELECTED_PINK_TOWER = registerItem("castle_selected_pink_tower",new Item(new FabricItemSettings().maxCount(64)));



    //public static final Item CASTLE_SWITCH_BLACK_KING = registerItem("castle_switch_black_king",new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CASTLE_SWITCH_PINK_TOWER = registerItem("castle_switch_pink_tower",new Item(new FabricItemSettings().maxCount(64)));



    //Methods

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM,new Identifier(Chess.MOD_ID,name),item);
    }

    public static void registerModItems(){
        Chess.LOGGER.info("Registering Items for " + Chess.MOD_ID);

    }



}
