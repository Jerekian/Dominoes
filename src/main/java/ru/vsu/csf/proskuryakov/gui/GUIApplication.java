package ru.vsu.csf.proskuryakov.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.vsu.csf.proskuryakov.data.command.Command;
import ru.vsu.csf.proskuryakov.data.command.CommandHistory;
import ru.vsu.csf.proskuryakov.data.command.NextMoveCommand;
import ru.vsu.csf.proskuryakov.core.GameState;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUIApplication extends Application {

    static Stage window;
    static RootPanel rootPanel = null;
    static Scene scene;
    static GameState gameState;

    static Button nextMoveButton = new Button("Next move");
    static Button undoButton = new Button("Undo");

    static CommandHistory commandHistory = new CommandHistory();

    public static void main(String[] args) {

        startNewGame();

        //nextMoveButton.setOnAction(e -> rootPanel.nextMoveButton(gameState));
        //nextMoveButton.setOnAction(e -> executeCommand(new NextMoveCommand(gameState, rootPanel)));
        nextMoveButton.setMinSize(100, 50);
        undoButton.setOnAction(e -> undo());
        undoButton.setMinSize(100, 50);
        rootPanel = new RootPanel(gameState);

        scene = new Scene(rootPanel.window);

        launch(args);
    }

    static void startNewGame() {
        gameState = new GameState(2);

        gameState.startGame();

        gameState.firstMove();

        gameState.checkMove();

        commandHistory = new CommandHistory();

        if(rootPanel != null){
            rootPanel.refillAllElement(gameState);
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

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String jsonGameState = gson.toJson(gameState);

        try(FileWriter writer = new FileWriter("jsonGameState.json", false))
        {
            writer.write(jsonGameState);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void openLastGame(){
        String jsonGameState = null;
        try {
            jsonGameState = new String(Files.readAllBytes(Paths.get("jsonGameState.json")));
        } catch (IOException e) {
            System.out.println(e);
        }

        Gson gson = new GsonBuilder().create();

        gameState = gson.fromJson(jsonGameState, GameState.class);

        commandHistory = new CommandHistory();

        rootPanel.refillAllElement(gameState);
    }

    public static void undo(){

        if(commandHistory.isEmpty()){
            System.out.println("История пуста");
            return;
        }

        Command command = commandHistory.pop();
        if(command != null){
            command.undo();
            gameState = command.getGameState();
            rootPanel.refillAllElement(gameState);
            System.out.println("Отмена команды: \"" + command.getCommandDescriptione() + "\"" );
        }

    }

    public static void executeCommand(Command command) {
        if (command.execute()) {
            commandHistory.push(command);
        }
    }

}
