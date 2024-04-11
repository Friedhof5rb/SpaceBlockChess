package me.friedhof.chess.item;

import me.friedhof.chess.Chess;
import me.friedhof.chess.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static ItemGroup Figures = Registry.register(Registries.ITEM_GROUP,new Identifier(Chess.MOD_ID, "figures"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.chess.figures"))
                .icon(() -> new ItemStack(ModItems.WHITE_KING))
                .entries((displayContext, entries) -> {

                    entries.add(ModItems.WHITE_PAWN);
                    entries.add(ModItems.WHITE_TOWER);
                    entries.add(ModItems.WHITE_KNIGHT);
                    entries.add(ModItems.WHITE_BISHOP);
                    entries.add(ModItems.WHITE_QUEEN);
                    entries.add(ModItems.WHITE_KING);
                    entries.add(ModItems.START_WHITE_PAWN);
                    entries.add(ModItems.CASTLE_WHITE_KING);
                    entries.add(ModItems.CASTLE_WHITE_TOWER);

                    entries.add(ModItems.BLACK_PAWN);
                    entries.add(ModItems.BLACK_TOWER);
                    entries.add(ModItems.BLACK_KNIGHT);
                    entries.add(ModItems.BLACK_BISHOP);
                    entries.add(ModItems.BLACK_QUEEN);
                    entries.add(ModItems.BLACK_KING);
                    entries.add(ModItems.START_BLACK_PAWN);
                    entries.add(ModItems.CASTLE_BLACK_KING);
                    entries.add(ModItems.CASTLE_BLACK_TOWER);

                    entries.add(ModItems.YELLOW_PAWN);
                    entries.add(ModItems.YELLOW_TOWER);
                    entries.add(ModItems.YELLOW_KNIGHT);
                    entries.add(ModItems.YELLOW_BISHOP);
                    entries.add(ModItems.YELLOW_QUEEN);
                    entries.add(ModItems.YELLOW_KING);
                    entries.add(ModItems.START_YELLOW_PAWN);
                    entries.add(ModItems.CASTLE_YELLOW_KING);
                    entries.add(ModItems.CASTLE_YELLOW_TOWER);

                    entries.add(ModItems.PINK_PAWN);
                    entries.add(ModItems.PINK_TOWER);
                    entries.add(ModItems.PINK_KNIGHT);
                    entries.add(ModItems.PINK_BISHOP);
                    entries.add(ModItems.PINK_QUEEN);
                    entries.add(ModItems.PINK_KING);
                    entries.add(ModItems.START_PINK_PAWN);
                    entries.add(ModItems.CASTLE_PINK_KING);
                    entries.add(ModItems.CASTLE_PINK_TOWER);


                })
                .build());

    public static ItemGroup ChessTools = Registry.register(Registries.ITEM_GROUP,new Identifier(Chess.MOD_ID, "chess_tools"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.chess.chess_tools"))
                    .icon(() -> new ItemStack(ModItems.WHITE_ROD_OF_MOVING))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.WHITE_ROD_OF_MOVING);
                        entries.add(ModItems.BLACK_ROD_OF_MOVING);
                        entries.add(ModItems.YELLOW_ROD_OF_MOVING);
                        entries.add(ModItems.PINK_ROD_OF_MOVING);

                        entries.add(ModItems.UNIVERSAL_ROD_OF_CHESS);

                        entries.add(ModItems.ROD_OF_REMOVAL);
                        entries.add(ModItems.ROD_OF_ROTATION);
                        entries.add(ModItems.CHESS_POS1);
                        entries.add(ModItems.CHESS_POS2);

                        entries.add(ModItems.CHESS_CORE);

                        entries.add(ModItems.WHITE_TORCH);
                        entries.add(ModItems.BLACK_TORCH);
                        entries.add(ModItems.YELLOW_TORCH);
                        entries.add(ModItems.PINK_TORCH);

                        entries.add(ModItems.PAWN_TO_TOWER);
                        entries.add(ModItems.PAWN_TO_KNIGHT);
                        entries.add(ModItems.PAWN_TO_BISHOP);
                        entries.add(ModItems.PAWN_TO_QUEEN);
                    })
                    .build());


    public static void registerItemGroups(){

        Chess.LOGGER.info("Registering Itemgroups for " + Chess.MOD_ID);
    }






}
