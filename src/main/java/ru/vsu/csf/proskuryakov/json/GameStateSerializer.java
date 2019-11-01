package ru.vsu.csf.proskuryakov.json;

import com.google.gson.*;
import ru.vsu.csf.proskuryakov.core.GameState;

import java.lang.reflect.Type;

public class GameStateSerializer implements JsonSerializer<GameState> {

    @Override
    public JsonElement serialize(GameState gameState, Type type, JsonSerializationContext jsonSerializationContext) {

        try {

            JsonObject result = new JsonObject();

            result.addProperty("NumberOfPlayers", gameState.getNumberOfPlayers());

            result.add("Players", jsonSerializationContext.serialize(gameState.getPlayers()));

            result.add("Market", jsonSerializationContext.serialize(gameState.getMarket()));

            result.add("PlayingField", jsonSerializationContext.serialize(gameState.getPlayingField()));

            result.addProperty("ActivePlayer", gameState.getActivePlayer());

            result.addProperty("haveMove", gameState.isHaveMove());

            result.addProperty("isMoveDone", gameState.isMoveDone());

            result.addProperty("haveWinner", gameState.isHaveWinner());

            result.add("Winner", jsonSerializationContext.serialize(gameState.getWinner()));

            return result;
        }catch (Exception e){
            return null;
        }
    }

}
