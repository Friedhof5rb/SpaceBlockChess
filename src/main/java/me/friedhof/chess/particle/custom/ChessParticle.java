package me.friedhof.chess.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class ChessParticle extends SpriteBillboardParticle {


    protected ChessParticle(ClientWorld clientWorld, double d, double e, double f,SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(clientWorld, d, e, f,xd,yd,zd);

        this.velocityMultiplier = 0.6f;
        this.x = d;
        this.y = e;
        this.z = f;
        this.scale *= 0.5F;
        this.maxAge = 20;
        this.setSprite(spriteSet);
        this.red = 1f;
        this.green= 1f;
        this.blue = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {

            return new ChessParticle(level, x, y, z, this.sprites,dx,dy,dz);
        }
    }



}
