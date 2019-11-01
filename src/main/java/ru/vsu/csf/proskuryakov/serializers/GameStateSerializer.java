package ru.vsu.csf.proskuryakov.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.vsu.csf.proskuryakov.core.GameState;

import java.lang.reflect.Type;

public class GameStateSerializer implements JsonSerializer<GameState> {

    @Override
    public JsonElement serialize(GameState gameState, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();

        result.addProperty("Количество игроков", gameState.getNumberOfPlayers());


        return null;
    }

}
