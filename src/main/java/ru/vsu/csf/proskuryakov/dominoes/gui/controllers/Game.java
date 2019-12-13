package ru.vsu.csf.proskuryakov.dominoes.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.vsu.csf.proskuryakov.dominoes.core.GameState;
import ru.vsu.csf.proskuryakov.dominoes.data.command.Command;
import ru.vsu.csf.proskuryakov.dominoes.data.command.NextMoveCommand;
import ru.vsu.csf.proskuryakov.dominoes.data.command.RedoCommand;
import ru.vsu.csf.proskuryakov.dominoes.data.command.UndoCommand;
import ru.vsu.csf.proskuryakov.dominoes.gui.Dominoes;
import ru.vsu.csf.proskuryakov.dominoes.gui.GameController;
import ru.vsu.csf.proskuryakov.dominoes.utils.GUIUtils;

public class Game {

    public static Game game;

    @FXML
    private SplitPane rightSplitPane;

    @FXML
    private SplitPane leftSplitPane;

    @FXML
    private RadioMenuItem isTwoPlayers;

    @FXML
    private RadioMenuItem isThreePlayers;

    @FXML
    private RadioMenuItem isFourPlayers;

    @FXML
    private HBox marketBox;

    @FXML
    private VBox bonePlayer1;

    @FXML
    private VBox bonePlayer3;

    @FXML
    private HBox playingFieldBox;

    @FXML
    private VBox bonePlayer2;

    @FXML
    private VBox bonePlayer4;

    @FXML
    private ListView<Command> listOfTeams;

    @FXML
    private ScrollPane playingFieldScrollPane;

    @FXML
    private GridPane playingFieldGridPane;

    private VBox[] playersBoxArray;
    private int numberOfPlayers;
    private GameState gameState;

    @FXML
    void exit(ActionEvent event) {
        GameController.closeProgram();
    }

    @FXML
    void nextMoveAction(ActionEvent event) {
        Command command = new NextMoveCommand(gameState);
        Dominoes.app.executeCommand(command);

        listOfTeams.getItems().add(command);
        listOfTeams.scrollTo(listOfTeams.getItems().size() - 1);

        refillAll();
    }

    @FXML
    void undo(ActionEvent event) {
        Command command = new UndoCommand(gameState);
        Dominoes.app.executeUndoCommand(command);

        listOfTeams.getItems().add(command);

        refillAll();
    }

    @FXML
    void redo(ActionEvent event) {
        Command command = new RedoCommand(gameState);

        //Dominoes.app.executeRedoCommand(command);
        Dominoes.app.executeCommand(command);

        listOfTeams.getItems().add(command);

        refillAll();
    }

    @FXML
    void saveGame(ActionEvent event) {
        GameController.saveGameState(gameState);
    }

    @FXML
    void openGame(ActionEvent event) {
        gameState = GameController.openLastGame();
        Dominoes.app.getUndoCommandHistory().clear();
        Dominoes.app.getCommandHistory().clear();
        clearAll();
        listOfTeams.getItems().clear();
        refillAll();
    }

    @FXML
    void restartThisGame(ActionEvent event) {

    }

    @FXML
    void startNewGame(ActionEvent event) {
        numberOfPlayers = getNumberOfPlayersFromGUI();
        Dominoes.app.startNewGame(numberOfPlayers);
        clearAll();
        refillAll();
        listOfTeams.getItems().clear();

        switch (numberOfPlayers){
            case 2:
                leftSplitPane.setDividerPosition(0, 1);
                rightSplitPane.setDividerPosition(0, 1);
                break;
            case 3:
                leftSplitPane.setDividerPosition(0, 0.5);
                rightSplitPane.setDividerPosition(0, 1);
                break;
            case 4:
                leftSplitPane.setDividerPosition(0, 0.5);
                rightSplitPane.setDividerPosition(0, 0.5);
                break;
        }

    }

    @FXML
    void initialize() {
        game = this;
        numberOfPlayers = 2;
        playersBoxArray = new VBox[] {bonePlayer1, bonePlayer2, bonePlayer3, bonePlayer4};
    }


    public void refillAll(){

        for(int i = 0; i < numberOfPlayers; i++){
            playersBoxArray[i].getChildren().clear();
            playersBoxArray[i].getChildren().addAll(GUIUtils.getDrawBoneList(gameState.getPlayers()[i].getAllBone()));
        }

        marketBox.getChildren().clear();
        marketBox.getChildren().addAll(GUIUtils.getDrawBoneList(gameState.getMarketList()));

        playingFieldBox.getChildren().clear();
        playingFieldBox.getChildren().addAll(GUIUtils.getDrawBoneList(gameState.getPlayingFieldList()));

    }

    public void clearAll(){
        for(int i = 0; i < 4; i++){
            playersBoxArray[i].getChildren().clear();
        }
        marketBox.getChildren().clear();
        playingFieldBox.getChildren().clear();
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        switch (numberOfPlayers){
            case 2:
                isTwoPlayers.setSelected(true);
                break;
            case 3:
                isThreePlayers.setSelected(true);
                break;
            case 4:
                isFourPlayers.setSelected(true);
                break;
        }
    }

    public int getNumberOfPlayersFromGUI(){
        if (isTwoPlayers.isSelected()) return 2;
        if (isThreePlayers.isSelected()) return 3;
        if (isFourPlayers.isSelected()) return 4;
        return 0;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
        refillAll();
    }

    public ListView<Command> getListOfTeams() {
        return listOfTeams;
    }
}

