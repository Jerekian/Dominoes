package ru.vsu.csf.proskuryakov.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.data.command.Command;
import ru.vsu.csf.proskuryakov.data.command.NextMoveCommand;
import ru.vsu.csf.proskuryakov.gui.controllers.Game;
import ru.vsu.csf.proskuryakov.gui.window.ExitWindow;
import ru.vsu.csf.proskuryakov.gui.window.InformationWindow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameController {

    public static final File GAME_STATE_JSON_FILE = new File(ClassLoader.getSystemClassLoader().getResource("json/jsonGameState.json").getFile());


    public static String nextMove(GameState gameState){

        String move = "";

        try {
            move = gameState.nextMove();
        }catch (Error e){
            System.out.println("НЕ МОГУ СЛЕДУЮЩИЙ ХОД СДЕЛАТЬ");
        }

        if(gameState.isHaveWinner()){
            new InformationWindow("Win",
                    100, 250).display("Победитель: " + gameState.getWinnerName());
        }

        return move;
    }

    public static void undo(){
        if(Dominoes.app.getCommandHistory().isEmpty()){
            System.out.println("История пуста");
            return;
        }

        Command command = Dominoes.app.getCommandHistory().pop();
        if(command != null){
            String undoCommandDescriptione = "Отмена команды: \"" + command.getCommandDescriptione() + "\"";
            Command undoCommand = new NextMoveCommand(command.getGameState(),
                    command.getJsonString(), undoCommandDescriptione);

            Dominoes.app.getUndoCommandHistory().push(undoCommand);

            command.undo();
            Game.game.setGameState(command.getGameState());

        }
    }

    public static void closeProgram() {

        if(new ExitWindow().displayAndGetAnswer()){
            Dominoes.app.close();
        }

    }

    public static void saveGameState(GameState gameState){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String jsonGameState = gson.toJson(gameState);

        try(FileWriter writer = new FileWriter(GAME_STATE_JSON_FILE))
        {
            writer.write(jsonGameState);
            System.out.println("В файл записано");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static GameState openLastGame(){

        String jsonGameState = null;
        try {
            jsonGameState = new String(Files.readAllBytes(Paths.get(GAME_STATE_JSON_FILE.toURI())));
            System.out.println("Игра загружена");
        } catch (IOException e) {
            System.out.println(e);
        }

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(jsonGameState, GameState.class);

    }

}
