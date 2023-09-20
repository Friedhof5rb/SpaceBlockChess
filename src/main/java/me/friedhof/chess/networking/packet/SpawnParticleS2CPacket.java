package me.friedhof.chess.networking.packet;

import me.friedhof.chess.util.GlobalChessData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.Particle;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;


public class SpawnParticleS2CPacket {



    public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {


        int[] array = packetByteBuf.readIntArray();
        int colour = array[3];
        Direction d = Direction.byId(array[4]);
        Vec3d vec = calculateParticleOffset(new GlobalChessData(new BlockPos(array[0],array[1],array[2]),d,0,false));

        if(colour == 0){

            assert minecraftClient.world != null;
            minecraftClient.world.addParticle(ParticleTypes.CLOUD,array[0]+vec.x,array[1]+vec.y,array[2]+vec.z,0,0,0);
        }
        if(colour == 1){


            assert minecraftClient.world != null;
            minecraftClient.world.addParticle(ParticleTypes.SQUID_INK,array[0]+vec.x,array[1]+vec.y,array[2]+vec.z,0,0,0);

        }
        if(colour == 2){


            assert minecraftClient.world != null;
            minecraftClient.world.addParticle(ParticleTypes.ANGRY_VILLAGER,array[0]+vec.x,array[1]+vec.y,array[2]+vec.z,0,0,0);

        }
        if(colour == 3){

            assert minecraftClient.world != null;
            minecraftClient.world.addParticle(ParticleTypes.HEART,array[0]+vec.x,array[1]+vec.y,array[2]+vec.z,0,0,0);


        }




    }

    private static Vec3d calculateParticleOffset(GlobalChessData data){
        float xOffset = 0;
        float yOffset = 0;
        float zOffset = 0;

        switch(data.directionWall){
            case UP -> {
                xOffset = 0.5f;
                yOffset = 0;
                zOffset = 0.5f;
                break;
            }
            case DOWN -> {
                xOffset = 0.5f;
                yOffset = 1;
                zOffset = 0.5f;
                break;
            }
            case NORTH -> {
                xOffset = 0.5f;
                yOffset = 0.5f;
                zOffset = 1f;
                break;
            }
            case SOUTH -> {
                xOffset = 0.5f;
                yOffset = 0.5f;
                zOffset = 0f;
                break;
            }
            case EAST -> {
                xOffset = 0f;
                yOffset = 0.5f;
                zOffset = 0.5f;
                break;
            }
            case WEST -> {
                xOffset = 1f;
                yOffset = 0.5f;
                zOffset = 0.5f;
                break;
            }


        }

        return new Vec3d(xOffset,yOffset,zOffset);




    }

}
