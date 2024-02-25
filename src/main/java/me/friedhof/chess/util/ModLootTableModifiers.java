package me.friedhof.chess.util;

import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier SIMPLE_DUNGEON_ID = new Identifier("minecraft","chests/simple_dungeon");

    private static final Identifier ZOMBIE_ID = new Identifier("minecraft","entities/zombie");


    public static void modifyLootTables(){

        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
           if(SIMPLE_DUNGEON_ID.equals(id)){
               LootPool.Builder poolBuilder = LootPool.builder()
                       .rolls(ConstantLootNumberProvider.create(1))
                       .conditionally(RandomChanceLootCondition.builder(0.1f))
                       .with(ItemEntry.builder(ModItems.PAWN_TO_BISHOP))
                       .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());

               LootPool.Builder poolBuilder2 = LootPool.builder()
                       .rolls(ConstantLootNumberProvider.create(1))
                       .conditionally(RandomChanceLootCondition.builder(0.1f))
                       .with(ItemEntry.builder(ModItems.PAWN_TO_TOWER))
                       .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());

               LootPool.Builder poolBuilder3 = LootPool.builder()
                       .rolls(ConstantLootNumberProvider.create(1))
                       .conditionally(RandomChanceLootCondition.builder(0.1f))
                       .with(ItemEntry.builder(ModItems.PAWN_TO_KNIGHT))
                       .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());

               LootPool.Builder poolBuilder4 = LootPool.builder()
                       .rolls(ConstantLootNumberProvider.create(1))
                       .conditionally(RandomChanceLootCondition.builder(0.05f))
                       .with(ItemEntry.builder(ModItems.PAWN_TO_QUEEN))
                       .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());


               tableBuilder.pool(poolBuilder.build());
               tableBuilder.pool(poolBuilder2.build());
               tableBuilder.pool(poolBuilder3.build());
               tableBuilder.pool(poolBuilder4.build());
           }
            if(ZOMBIE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create (1))
                        .conditionally (RandomChanceLootCondition.builder( 1f))
                        .with(ItemEntry.builder (ModItems.CHESS_CORE))
                        .apply(SetCountLootFunction.builder (UniformLootNumberProvider.create(1.0f, 1.0f)).build ());
                tableBuilder.pool (poolBuilder.build());

            }

        }));



    }





}
