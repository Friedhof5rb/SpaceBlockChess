package me.friedhof.chess.particle;

import me.friedhof.chess.Chess;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final DefaultParticleType CHESS_PARTICLE = FabricParticleTypes.simple();


    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE,new Identifier(Chess.MOD_ID,"chess_particle"),CHESS_PARTICLE);


    }




}
