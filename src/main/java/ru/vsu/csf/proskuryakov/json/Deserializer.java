package ru.vsu.csf.proskuryakov.json;

import com.google.gson.*;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.data.essence.Bone;
import ru.vsu.csf.proskuryakov.data.essence.BoneNumbers;
import ru.vsu.csf.proskuryakov.data.essence.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deserializer {


    public static class MarketDeserializer implements JsonDeserializer<LinkedList<Bone>> {

        @Override
        public LinkedList<Bone> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonArray array = jsonElement.getAsJsonArray();

            return (LinkedList<Bone>) getBoneList(array, jsonDeserializationContext);
        }
    }

//
//    public static class MarketDeserializer implements JsonDeserializer<Market> {
//        @Override
//        public Market deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//            JsonArray array = jsonObject.getAsJsonArray("Bone on Market");
//
//            return new Market(getBoneList(array, jsonDeserializationContext));
//        }
//
//    }
//

    public static class PlayingFieldDeserializer implements JsonDeserializer<LinkedList<Bone>> {


        @Override
        public LinkedList<Bone> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {


            JsonArray array = jsonElement.getAsJsonArray();

            return  (LinkedList<Bone>) getBoneList(array, jsonDeserializationContext);
        }
    }
//
//    public static class PlayingFieldDeserializer implements JsonDeserializer<PlayingField> {
//
//        @Override
//        public PlayingField deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//            JsonArray array = jsonObject.getAsJsonArray("Bone on playing field");
//
//            return new PlayingField(getBoneList(array, jsonDeserializationContext));
//        }
//    }

    private static List<Bone> getBoneList(JsonArray array, JsonDeserializationContext jsonDeserializationContext){
        List<Bone> boneList = new LinkedList<>();
        for(JsonElement element: array){
            boneList.add(jsonDeserializationContext.deserialize(element, Bone.class));
        }
        return boneList;
    }

    public static class BoneDeserializer implements JsonDeserializer<Bone> {
        @Override
        public Bone deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();

            return new Bone(BoneNumbers.valueOf(jsonObject.get("Left").getAsString()),
                    BoneNumbers.valueOf(jsonObject.get("Right").getAsString()));

        }
    }


    public static class GameStateDeserializer implements JsonDeserializer<GameState> {
        @Override
        public GameState deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();

            GameState gameState = new GameState();

            int numberOfPlayers = jsonObject.get("Number of players").getAsInt();

            gameState.setNumberOfPlayers(numberOfPlayers);

            gameState.setActivePlayer(jsonObject.get("Active player").getAsInt());

            gameState.setHaveMove(jsonObject.get("haveMove").getAsBoolean());

            gameState.setHaveWinner(jsonObject.get("haveWinner").getAsBoolean());

            gameState.setMoveDone(jsonObject.get("isMoveDone").getAsBoolean());

            gameState.setWinner(context.deserialize(jsonObject.get("Winner"), Player.class));
//---------------
            gameState.setMarketList(context.deserialize(jsonObject.get("Market"), LinkedList.class));

            gameState.setPlayingFieldList(context.deserialize(jsonObject.get("PlayingField"),  LinkedList.class));
//--------------
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

}
