package ru.vsu.csf.proskuryakov.data.command;

import com.google.gson.GsonBuilder;
import ru.vsu.csf.proskuryakov.core.GameState;

public abstract class Command{

    protected GameState gameState;
    protected String jsonString = null;
    protected String commandDescriptione;

    public Command(GameState gameState) {
        this.gameState = gameState;
    }

    public Command(GameState gameState, String jsonString, String commandDescriptione){
        this.gameState = gameState;
        this.jsonString = jsonString;
        this.commandDescriptione = commandDescriptione;
    }

    public void backup(){
        jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(gameState);
    }

    public void undo(){
        gameState = new GsonBuilder().create().fromJson(jsonString, GameState.class);
    }

    public abstract boolean execute();

    public GameState getGameState(){
        return gameState;
    }

    public String getJsonString() {
        return jsonString;
    }

    public String getCommandDescriptione() {
        return commandDescriptione;
    }

    public void setCommandDescriptione(String commandDescriptione) {
        this.commandDescriptione = commandDescriptione;
    }

    public String toString(){
        return commandDescriptione;
    }

}