package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class chessCubeCommand {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("cube").executes(chessCubeCommand::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (!context.getSource().hasPermissionLevel(3)) {
            context.getSource().getPlayer().sendMessage(Text.literal("You must have Permission Level 3."), false);
            return -1;
        }
        String uuid = context.getSource().getPlayer().getUuidAsString();
        World w = context.getSource().getWorld();

        if(!Chess.pos1.containsKey(uuid) || !Chess.pos2.containsKey(uuid)){
            context.getSource().getPlayer().sendMessage(Text.literal("Pos1 or Pos2 is not defined"), false);
            return -1;
        }

        int lowx = 0;
        int highx = 0;
        if(Chess.pos1.get(uuid).getX() > Chess.pos2.get(uuid).getX()){
            highx =   Chess.pos1.get(uuid).getX();
            lowx =  Chess.pos2.get(uuid).getX();

        }else{
            lowx =   Chess.pos1.get(uuid).getX();
            highx =  Chess.pos2.get(uuid).getX();
        }



        int lowy = 0;
        int highy = 0;
        if(Chess.pos1.get(uuid).getY() > Chess.pos2.get(uuid).getY()){
            highy =   Chess.pos1.get(uuid).getY();
            lowy =  Chess.pos2.get(uuid).getY();

        }else{
            lowy =   Chess.pos1.get(uuid).getY();
            highy =  Chess.pos2.get(uuid).getY();
        }



        int lowz = 0;
        int highz = 0;
        if(Chess.pos1.get(uuid).getZ() > Chess.pos2.get(uuid).getZ()){
            highz =   Chess.pos1.get(uuid).getZ();
            lowz =  Chess.pos2.get(uuid).getZ();

        }else{
            lowz =   Chess.pos1.get(uuid).getZ();
            highz =  Chess.pos2.get(uuid).getZ();
        }



        for(int x = lowx; x<= highx;x++){
            for(int y = lowy; y<= highy;y++){
                for(int z = lowz; z<= highz;z++){
                    BlockState state = getTileColour(x,y,z).getDefaultState();
                    w.setBlockState(new BlockPos(x,y,z),state);
                }
            }
        }
        return 1;
    }


    private static Block getTileColour(int x, int y, int z){

        boolean white = false;
        if(x%2 == 0 && y %2 == 0 && z%2 == 0){
            white = true;
        }

        if(x%2 != 0 && y %2 != 0 && z%2 == 0){
            white = true;
        }

        if(x%2 != 0 && y %2 == 0 && z%2 != 0){
            white = true;
        }

        if(x%2 == 0 && y %2 != 0 && z%2 != 0){
            white = true;
        }

        if(white){
            return  Blocks.WHITE_CONCRETE;
        }else{
            return  Blocks.BLACK_CONCRETE;
        }
    }



}
