package me.friedhof.chess.datagen;

import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ChessModelGenerator extends FabricModelProvider {
    public ChessModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.YELLOW_ROD_OF_MOVING, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_ROD_OF_MOVING, Models.GENERATED);


        itemModelGenerator.register(ModItems.PINK_BISHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_KNIGHT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_PAWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_QUEEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_BISHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_KNIGHT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_PAWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_QUEEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_BISHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_KNIGHT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_PAWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_QUEEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SELECTED_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_PINK_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CASTLE_SWITCH_PINK_TOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.START_PINK_PAWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.START_CAPTURE_PINK_PAWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.START_SELECTED_PINK_PAWN, Models.GENERATED);
    }
}
