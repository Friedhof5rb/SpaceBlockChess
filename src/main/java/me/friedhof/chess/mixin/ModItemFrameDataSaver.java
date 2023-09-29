package me.friedhof.chess.mixin;

import me.friedhof.chess.util.IEntityDataSaver;
import me.friedhof.chess.util.IItemFrameDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ModItemFrameDataSaver implements IItemFrameDataSaver {

    @Shadow
    private boolean fixed;

    @Override
    public boolean getFixed() {
        return fixed;
    }
    @Override
    public void setFixed(boolean fixed){
        this.fixed = fixed;
    }



}
