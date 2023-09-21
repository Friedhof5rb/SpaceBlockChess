package me.friedhof.chess.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.AirBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class ChessNotationHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            x = width/3;
            y = height/12;

            HitResult hit = client.player.raycast(5, 0, false);

            BlockHitResult b = (BlockHitResult) hit;




        if(!(client.world.getBlockState(b.getBlockPos()).getBlock() instanceof AirBlock)) {
            drawContext.drawText(client.textRenderer, "[" + getHitPos(b).getX() + ", " + getHitPos(b).getY() + ", " + getHitPos(b).getZ() + ", " + b.getSide() + "]", x, y, 0xffffff, false);
        }
        }

    }

    private BlockPos getHitPos(BlockHitResult b){

        int x = b.getBlockPos().getX();
        int y = b.getBlockPos().getY();
        int z = b.getBlockPos().getZ();
        switch(b.getSide()){

            case UP -> y = y+1;
            case DOWN -> y= y-1;
            case NORTH -> z=z-1;
            case SOUTH -> z= z+1;
            case EAST -> x=x+1;
            case WEST -> x=x-1;
        }

        return new BlockPos(x,y,z);


    }







}
