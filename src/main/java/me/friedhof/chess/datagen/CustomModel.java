package me.friedhof.chess.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import org.spongepowered.include.com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class CustomModel extends Model {



    public CustomModel(Optional<Identifier> parent, Optional<String> variant, TextureKey... requiredTextureKeys) {
        super(parent, variant, requiredTextureKeys);
    }


    @Override
    public JsonObject createJson(Identifier id, Map<TextureKey, Identifier> textures) {

        JsonObject object =  super.createJson(id, textures);

        JsonArray array = new JsonArray(3);
        array.add(2);
        array.add(2);
        array.add(2);
        JsonObject object3 = new JsonObject();
        object3.add("scale",array);
        JsonObject object2 = new JsonObject();
        object2.add("fixed",object3);
        object.add("display",object2);

        return object;
    }
}
