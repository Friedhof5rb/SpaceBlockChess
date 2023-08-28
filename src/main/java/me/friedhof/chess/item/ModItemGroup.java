package me.friedhof.chess.item;

import me.friedhof.chess.Chess;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup SpaceChess = FabricItemGroupBuilder.build(new Identifier(Chess.MOD_ID,"space_chess"),
            () -> new ItemStack(ModItems.WHITE_KNIGHT));

}
