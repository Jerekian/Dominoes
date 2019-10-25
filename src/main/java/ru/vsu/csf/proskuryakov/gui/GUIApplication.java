package ru.vsu.csf.proskuryakov.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.core.PlayingField;

import java.util.List;

public class GUIApplication extends Application {

    static RootPanel rootPanel;
    static Scene scene;
    GameState gameState;

    static Button nextMoveButton = new Button("Next move");

    public static void main(String[] args) {

        GameState gameState = new GameState(2);

        gameState.startGame();

        gameState.firstMove();

        gameState.checkMove();

        nextMoveButton.setOnAction(e -> rootPanel.nextMoveButton(gameState));

        rootPanel = new RootPanel(gameState);

        scene = new Scene(rootPanel.window);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Dominoes");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
