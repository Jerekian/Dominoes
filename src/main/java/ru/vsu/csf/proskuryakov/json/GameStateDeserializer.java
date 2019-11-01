package ru.vsu.csf.proskuryakov.json;

import com.google.gson.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.core.Market;
import ru.vsu.csf.proskuryakov.core.PlayingField;
import ru.vsu.csf.proskuryakov.data.Player;

import java.lang.reflect.Type;

public class GameStateDeserializer implements JsonDeserializer<GameState> {
    @Override
    public GameState deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        GameState gameState = new GameState();

        int numberOfPlayers = jsonObject.get("NumberOfPlayers").getAsInt();

        gameState.setNumberOfPlayers(numberOfPlayers);

        gameState.setActivePlayer(jsonObject.get("ActivePlayer").getAsInt());

        gameState.setHaveMove(jsonObject.get("haveMove").getAsBoolean());

        gameState.setHaveWinner(jsonObject.get("haveWinner").getAsBoolean());

        gameState.setMoveDone(jsonObject.get("isMoveDone").getAsBoolean());

        gameState.setWinner(context.deserialize(jsonObject.get("Winner"), Player.class));

        gameState.setMarket(context.deserialize(jsonObject.get("Market").getAsJsonArray(), Market.class));

        gameState.setPlayingField(context.deserialize(jsonObject.get("PlayingField").getAsJsonArray(), PlayingField.class));

        JsonArray jsonPlayers = jsonObject.getAsJsonArray("Players");

        Player[] players = new Player[numberOfPlayers];
        int i = 0;

        for(JsonElement jsonPlayer : jsonPlayers) {
            players[i] = context.deserialize(jsonPlayer, Player.class);
            i++;
        }

        gameState.setPlayers(players);

        return gameState;
    }
}
