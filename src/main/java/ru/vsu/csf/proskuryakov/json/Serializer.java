package ru.vsu.csf.proskuryakov.json;

import com.google.gson.*;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.core.Market;
import ru.vsu.csf.proskuryakov.core.PlayingField;
import ru.vsu.csf.proskuryakov.data.essence.Bone;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Serializer {

//    public static class MarketSerializer implements JsonSerializer<Market> {
//        @Override
//        public JsonElement serialize(Market market, Type type, JsonSerializationContext context) {
//
//            JsonArray array = new JsonArray();
//
//            for(Bone bone: market.getMarketList()){
//                array.add(context.serialize(bone));
//            }
//
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.add("Bone on Market", array);
//
//            return jsonObject;
//
//        }
//    }

    public static class MarketSerializer implements JsonSerializer<ArrayList<Bone>> {

        @Override
        public JsonElement serialize(ArrayList<Bone> bones, Type type, JsonSerializationContext jsonSerializationContext) {

            JsonArray array = new JsonArray();

            for(Bone bone: bones){
                array.add(jsonSerializationContext.serialize(bone));
            }

            return array;

        }
    }



//
//    public static class PlayingFieldSerializer implements JsonSerializer<PlayingField> {
//
//        @Override
//        public JsonElement serialize(PlayingField playingField, Type type, JsonSerializationContext context) {
//
//            JsonArray array = new JsonArray();
//
//            for(Bone bone: playingField.getPlayingField()){
//                array.add(context.serialize(bone));
//            }
//
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.add("Bone on playing field", array);
//
//            return jsonObject;
//        }
//    }

    public static class PlayingFieldSerializer implements JsonSerializer<ArrayList<Bone>>{

        @Override
        public JsonElement serialize(ArrayList<Bone> bones, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonArray array = new JsonArray();

            for(Bone bone: bones){
                array.add(jsonSerializationContext.serialize(bone));
            }

            return array;
        }
    }

        public static class BoneSerializer implements JsonSerializer<Bone> {
        @Override
        public JsonElement serialize(Bone bone, Type type, JsonSerializationContext jsonSerializationContext) {

            JsonObject result = new JsonObject();

            result.addProperty("Left", String.valueOf(bone.getPipsOnFirstHalf()));
            result.addProperty("Right", String.valueOf(bone.getPipsOnSecondHalf()));

            return result;
        }
    }

    public static class GameStateSerializer implements JsonSerializer<GameState> {

        @Override
        public JsonElement serialize(GameState gameState, Type type, JsonSerializationContext jsonSerializationContext) {

            try {

                JsonObject result = new JsonObject();

                result.addProperty("Number of players", gameState.getNumberOfPlayers());

                result.add("Players", jsonSerializationContext.serialize(gameState.getPlayers()));

                result.add("Market", jsonSerializationContext.serialize(gameState.getMarket().getMarketList()));

                result.add("PlayingField", jsonSerializationContext.serialize(gameState.getPlayingField().getPlayingField()));

                result.addProperty("Active player", gameState.getActivePlayer());

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

}
