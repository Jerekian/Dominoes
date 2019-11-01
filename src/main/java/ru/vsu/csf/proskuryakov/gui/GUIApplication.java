package ru.vsu.csf.proskuryakov.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.core.PlayingField;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GUIApplication extends Application {

    static Stage window;
    static RootPanel rootPanel = null;
    static Scene scene;
    static GameState gameState;

    static Button nextMoveButton = new Button("Next move");

    public static void main(String[] args) {

        startNewGame();

        nextMoveButton.setOnAction(e -> rootPanel.nextMoveButton(gameState));

        rootPanel = new RootPanel(gameState);

        scene = new Scene(rootPanel.window);

        launch(args);
    }

    static void startNewGame() {
        gameState = new GameState(2);

        gameState.startGame();

        gameState.firstMove();

        gameState.checkMove();

        if(rootPanel != null){
            rootPanel.fillPlayerOneBox(gameState);
            rootPanel.fillPlayerTwoBox(gameState);
            rootPanel.fillPlayingField(gameState);
            rootPanel.fillMarket(gameState);
        }


    }

    @Override
    public void start(Stage primaryStage){
        window = primaryStage;

        window.setOnCloseRequest(e -> {
            e.consume();
            rootPanel.closeProgram();
        });

        primaryStage.setTitle("Dominoes");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void saveGameState(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonGameState = gson.toJson(gameState);
        String jsonRootPanel = gson.toJson(rootPanel);

        try(FileWriter writer = new FileWriter("jsonGameState.json", false))
        {
            writer.write(jsonGameState);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter("jsonRootPanel.json", false))
        {
            writer.write(jsonRootPanel);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void openLastGame(){

    }




}
