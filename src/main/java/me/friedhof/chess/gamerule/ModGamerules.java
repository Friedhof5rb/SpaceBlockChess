package me.friedhof.chess.gamerule;

import me.friedhof.chess.Chess;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGamerules {

    public static final GameRules.Key<GameRules.BooleanRule> isChessSurvivalOptimized = GameRuleRegistry.register("isChessSurvivalOptimized", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.BooleanRule> canUseChessTorches = GameRuleRegistry.register("canUseChessTorches", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static void registerGamerules(){
        Chess.LOGGER.info("Registering Gamerules for " + Chess.MOD_ID);

    }

}
