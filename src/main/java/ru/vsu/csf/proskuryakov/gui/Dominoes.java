package ru.vsu.csf.proskuryakov.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.data.command.Command;
import ru.vsu.csf.proskuryakov.data.command.CommandHistory;
import ru.vsu.csf.proskuryakov.gui.controllers.Game;

import java.io.IOException;

public class Dominoes extends Application {

    public static Dominoes app;

    private Stage stage;
    private Scene startGameMenu;
    private Scene game;

    GameState gameState;
    CommandHistory commandHistory = new CommandHistory();
    CommandHistory undoCommandHistory = new CommandHistory();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;
        app = this;

        initScenes();

        stage.setOnCloseRequest(e -> {
            e.consume();
            GameController.closeProgram();
        });

        stage.setTitle("Dominoes");
        setScene(startGameMenu);
        stage.show();

    }

    private void initScenes() throws IOException {
        startGameMenu = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml\\StartGameMenu.fxml")));
        game = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml\\Game.fxml")));
    }

    private void setScene(Scene scene){
        stage.setScene(scene);
    }

    public void setScene(SceneType gameScene){

        switch (gameScene){
            case GAME:
                setScene(game);
                break;
            case START_GAME_MENU:
                setScene(startGameMenu);
                break;
        }

    }

    public void startNewGame(){
        startNewGame(2);
    }

    public void startNewGame(int numberOfPlayers) {
        gameState = new GameState(numberOfPlayers);

        gameState.startGame();

        gameState.firstMove();

        gameState.checkMove();

        commandHistory = new CommandHistory();

        Game.game.setGameState(gameState);

        setScene(game);
    }

    public GameState getGameState(){
        return gameState;
    }

    public void executeCommand(Command command) {
        if (command.execute()) {
            commandHistory.push(command);
        }
    }

    public void executeUndoCommand(Command command) {
        if (command.execute()) {
            undoCommandHistory.push(command);
        }
    }

    public void executeRedoCommand(Command command) {
        if (command.execute()) {
            System.out.println("норм");
        }
    }

    public void close(){
        stage.close();
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public CommandHistory getUndoCommandHistory() {
        return undoCommandHistory;
    }
}
