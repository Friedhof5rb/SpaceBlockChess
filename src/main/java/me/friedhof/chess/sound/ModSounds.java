package me.friedhof.chess.sound;

import me.friedhof.chess.Chess;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {



    public static SoundEvent move = registerSoundEvent("chess_move_sound");
    public static SoundEvent take = registerSoundEvent("chess_take_sound");




    private static SoundEvent registerSoundEvent (String name) {
        Identifier id = new Identifier(Chess.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT,id,SoundEvent.of(id));
    }

    public static void registerSoundEvents(){
        Chess.LOGGER.info("Registering Sounds for " + Chess.MOD_ID);

    }




}
