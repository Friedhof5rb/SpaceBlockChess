package me.friedhof.chess.datagen;

import me.friedhof.chess.item.ModItems;


import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import org.spongepowered.include.com.google.gson.JsonObject;

import java.util.Optional;

public class ChessModelGenerator extends FabricModelProvider {
    public ChessModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.WHITE_ROD_OF_MOVING, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_ROD_OF_MOVING, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_ROD_OF_MOVING, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_ROD_OF_MOVING, Models.GENERATED);

        itemModelGenerator.register(ModItems.WHITE_TORCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACK_TORCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_TORCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_TORCH, Models.GENERATED);




        CustomModel customModel =  item("generated", TextureKey.LAYER0);;

        itemModelGenerator.register(ModItems.WHITE_BISHOP, customModel  );
        itemModelGenerator.register(ModItems.WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.WHITE_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.WHITE_PAWN, customModel);
        itemModelGenerator.register(ModItems.WHITE_QUEEN,customModel);
        itemModelGenerator.register(ModItems.WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_BISHOP, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_PAWN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_QUEEN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_BISHOP, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_PAWN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_QUEEN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_WHITE_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SWITCH_WHITE_TOWER, customModel);
        itemModelGenerator.register(ModItems.START_WHITE_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_CAPTURE_WHITE_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_SELECTED_WHITE_PAWN, customModel);

        itemModelGenerator.register(ModItems.BLACK_BISHOP, customModel  );
        itemModelGenerator.register(ModItems.BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.BLACK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.BLACK_PAWN, customModel);
        itemModelGenerator.register(ModItems.BLACK_QUEEN,customModel);
        itemModelGenerator.register(ModItems.BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_BISHOP, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_PAWN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_QUEEN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_BISHOP, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_PAWN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_QUEEN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_BLACK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SWITCH_BLACK_TOWER, customModel);
        itemModelGenerator.register(ModItems.START_BLACK_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_CAPTURE_BLACK_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_SELECTED_BLACK_PAWN, customModel);
        
        itemModelGenerator.register(ModItems.YELLOW_BISHOP, customModel  );
        itemModelGenerator.register(ModItems.YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.YELLOW_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.YELLOW_PAWN, customModel);
        itemModelGenerator.register(ModItems.YELLOW_QUEEN,customModel);
        itemModelGenerator.register(ModItems.YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_BISHOP, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_PAWN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_QUEEN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_BISHOP, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_PAWN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_QUEEN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_YELLOW_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SWITCH_YELLOW_TOWER, customModel);
        itemModelGenerator.register(ModItems.START_YELLOW_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_CAPTURE_YELLOW_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_SELECTED_YELLOW_PAWN, customModel);

        itemModelGenerator.register(ModItems.PINK_BISHOP, customModel  );
        itemModelGenerator.register(ModItems.PINK_KING, customModel);
        itemModelGenerator.register(ModItems.PINK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.PINK_PAWN, customModel);
        itemModelGenerator.register(ModItems.PINK_QUEEN,customModel);
        itemModelGenerator.register(ModItems.PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_BISHOP, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_KING, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_PAWN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_QUEEN, customModel);
        itemModelGenerator.register(ModItems.CAPTURE_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_BISHOP, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_KING, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_KNIGHT, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_PAWN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_QUEEN, customModel);
        itemModelGenerator.register(ModItems.SELECTED_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_PINK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_PINK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_CAPTURE_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_PINK_KING, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SELECTED_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.CASTLE_SWITCH_PINK_TOWER, customModel);
        itemModelGenerator.register(ModItems.START_PINK_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_CAPTURE_PINK_PAWN, customModel);
        itemModelGenerator.register(ModItems.START_SELECTED_PINK_PAWN, customModel);


    }

    private static CustomModel item(String parent, TextureKey... requiredTextureKeys) {
        return new CustomModel(Optional.of(new Identifier("minecraft", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }


}
