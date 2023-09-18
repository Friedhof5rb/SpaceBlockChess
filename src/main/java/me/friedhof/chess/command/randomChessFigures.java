package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomChessFigures {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("chess").then(CommandManager.literal("randomFigures").executes(randomChessFigures::run)));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (!context.getSource().getPlayer().isCreative()) {
            context.getSource().getPlayer().sendMessage(Text.literal("You can only use this Command in Creative."), false);
            return -1;
        }

        String uuid = context.getSource().getPlayer().getUuidAsString();
        World w = context.getSource().getWorld();

        ArrayList<ItemStack> list = Chess.pool.get(uuid);

        for(int i = list.size()-1; i >= 0; --i){

            while(list.get(i).getCount() > 1){
                spawnItemFrame(w,uuid,list.get(i).getItem(),0);
                list.get(i).setCount(list.get(i).getCount()-1);
            }
            spawnItemFrame(w,uuid,list.get(i).getItem(),0);
            list.remove(list.get(i));
        }
        Chess.pool.remove(uuid);
        return 1;
    }

    private static void spawnItemFrame(World w, String uuid, Item item, int counter){

        if(counter > 100){
            return;
        }

        BlockPos pos = randomPos(uuid);
        if(!w.getBlockState(pos).isAir()  && !(w.getBlockState(pos).getBlock() instanceof GlassBlock) && w.getBlockState(pos).getFluidState() == Fluids.EMPTY.getDefaultState() ){

            ArrayList<BlockPos> sides = new ArrayList<>();
            ArrayList<Direction> direc = new ArrayList<>();
            BlockPos Up = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
            BlockPos Down = new BlockPos(pos.getX(),pos.getY()-1,pos.getZ());
            BlockPos North = new BlockPos(pos.getX(),pos.getY(),pos.getZ()-1);
            BlockPos South = new BlockPos(pos.getX(),pos.getY(),pos.getZ()+1);
            BlockPos East = new BlockPos(pos.getX()+1,pos.getY(),pos.getZ());
            BlockPos West = new BlockPos(pos.getX()-1,pos.getY(),pos.getZ());


            if(w.getBlockState(Up).isAir() && isValidForItemframe(w,Up,Direction.UP) ){
                sides.add(Up);
                direc.add(Direction.UP);
            }
            if(w.getBlockState(Down).isAir() && isValidForItemframe(w,Down,Direction.DOWN) ){
                sides.add(Down);
                direc.add(Direction.DOWN);
            }
            if(w.getBlockState(North).isAir() && isValidForItemframe(w,North,Direction.NORTH) ){
                sides.add(North);
                direc.add(Direction.NORTH);

            }
            if(w.getBlockState(South).isAir() && isValidForItemframe(w,South,Direction.SOUTH) ){
                sides.add(South);
                direc.add(Direction.SOUTH);
            }
            if(w.getBlockState(East).isAir() && isValidForItemframe(w,East,Direction.EAST) ){
                sides.add(East);
                direc.add(Direction.EAST);
            }
            if(w.getBlockState(West).isAir() && isValidForItemframe(w,West,Direction.WEST) ){
                sides.add(West);
                direc.add(Direction.WEST);

            }

            if(sides.isEmpty()){
                spawnItemFrame(w, uuid, item,counter+1);
                return;
            }

            Random r = new Random();
            int result = r.nextInt(sides.size());

            Random r2 = new Random();
            int rotation = r2.nextInt(3);

            ItemFrameEntity e = new ItemFrameEntity(w,sides.get(result),direc.get(result));
            ItemStack stack = new ItemStack(item);
            e.setHeldItemStack(stack);
            e.setRotation(rotation*2);
            e.setInvisible(true);
            e.setInvulnerable(true);
            w.spawnEntity(e);

            return;
        }

        spawnItemFrame(w, uuid, item, counter+1);


    }









    private static boolean isValidForItemframe(World w,BlockPos pos, Direction facing){

        List<ItemFrameEntity> list = w.getEntitiesByType(EntityType.ITEM_FRAME,new Box(pos.getX()-1,pos.getY()-1,pos.getZ()-1,pos.getX()+1,pos.getY()+1,pos.getZ()+1), EntityPredicates.VALID_ENTITY);
        for(ItemFrameEntity e : list){
            if(e.getBlockPos().getX() == pos.getX() && e.getBlockPos().getY() == pos.getY() && e.getBlockPos().getZ() == pos.getZ() && e.getHorizontalFacing() == facing){
                return false;
            }
        }
        return true;
    }




    private static BlockPos randomPos(String uuid){

        Random r = new Random();
        int low = 0;
        int high = 0;
        if(Chess.pos1.get(uuid).getX() > Chess.pos2.get(uuid).getX()){
            high =   Chess.pos1.get(uuid).getX();
            low =  Chess.pos2.get(uuid).getX();

        }else{
            low =   Chess.pos1.get(uuid).getX();
            high =  Chess.pos2.get(uuid).getX();
        }
        int rx = r.nextInt(high-low) + low;

        r = new Random();
        low = 0;
        high = 0;
        if(Chess.pos1.get(uuid).getY() > Chess.pos2.get(uuid).getY()){
            high =   Chess.pos1.get(uuid).getY();
            low =  Chess.pos2.get(uuid).getY();

        }else{
            low =   Chess.pos1.get(uuid).getY();
            high =  Chess.pos2.get(uuid).getY();
        }
        int ry = r.nextInt(high-low) + low;

        r = new Random();
        low = 0;
        high = 0;
        if(Chess.pos1.get(uuid).getZ() > Chess.pos2.get(uuid).getZ()){
            high =   Chess.pos1.get(uuid).getZ();
            low =  Chess.pos2.get(uuid).getZ();

        }else{
            low =   Chess.pos1.get(uuid).getZ();
            high =  Chess.pos2.get(uuid).getZ();
        }
        int rz = r.nextInt(high-low) + low;

        return new BlockPos(rx,ry,rz);

    }




}
