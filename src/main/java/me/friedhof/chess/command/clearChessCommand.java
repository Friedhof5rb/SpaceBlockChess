package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class clearChessCommand {


    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("clearFigures").executes(clearChessCommand::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (!context.getSource().hasPermissionLevel(3)) {
            context.getSource().getPlayer().sendMessage(Text.literal("You must have Permission Level 3."), false);
            return -1;
        }



        String uuid = context.getSource().getPlayer().getUuidAsString();


        if(!Chess.pos1.containsKey(uuid) || !Chess.pos2.containsKey(uuid)){
            context.getSource().getPlayer().sendMessage(Text.literal("Pos1 or Pos2 is not defined"), false);
            return -1;
        }
        int count = 0;

        World w = context.getSource().getWorld();
        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(moveCornerOut(Chess.pos1.get(uuid),Chess.pos2.get(uuid),true),moveCornerOut(Chess.pos1.get(uuid),Chess.pos2.get(uuid),false)), EntityPredicates.VALID_ENTITY);
        for(int i = list.size()-1; i >= 0; --i){

            w.getEntityById(list.get(i).getId()).kill();
            count++;
        }

        context.getSource().getPlayer().sendMessage(Text.literal("Cleared "+ count + " Chess Figures."), false);
        return 1;
    }


    private static BlockPos moveCornerOut(BlockPos pos1, BlockPos pos2, boolean firstCorner ){

        BlockPos corner1  = new BlockPos(pos1.getX(),pos1.getY(),pos1.getZ());
        BlockPos corner2  = new BlockPos(pos2.getX(),pos2.getY(),pos2.getZ());
        if(pos1.getX() > pos2.getX()){
            corner1 = corner1.offset(Direction.Axis.X,2);
            corner2  =corner2.offset(Direction.Axis.X,-2);
        }else {
            corner1 = corner1.offset(Direction.Axis.X,-2);
            corner2  =corner2.offset(Direction.Axis.X,2);

        }
        if(pos1.getY() > pos2.getY()){
            corner1 = corner1.offset(Direction.Axis.Y,2);
            corner2  =corner2.offset(Direction.Axis.Y,-2);
        }else {
            corner1 = corner1.offset(Direction.Axis.Y,-2);
            corner2  =corner2.offset(Direction.Axis.Y,2);

        }
        if(pos1.getZ() > pos2.getZ()){
            corner1 = corner1.offset(Direction.Axis.Z,2);
            corner2  =corner2.offset(Direction.Axis.Z,-2);
        }else {
            corner1 = corner1.offset(Direction.Axis.Z,-2);
            corner2  =corner2.offset(Direction.Axis.Z,2);

        }

        if(firstCorner){
            return corner1;
        }else{
            return corner2;
        }
    }




}
