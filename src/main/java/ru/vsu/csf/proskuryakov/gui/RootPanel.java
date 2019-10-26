package ru.vsu.csf.proskuryakov.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.data.Bone;

import javax.swing.*;

public class RootPanel{

    BorderPane window = new BorderPane();

    VBox playerOneBox = new VBox();
    Label playerOneLable = new Label("Bone of the first player");

    VBox playerTwoBox = new VBox();
    Label playerTwoLable = new Label("Bone of the second player");

    VBox center = new VBox();
    Label playingFieldLable = new Label("Playing field");
    FlowPane playingField = new FlowPane();

    public RootPanel(GameState gameState) {

        playerOneBox.setMinSize(100, 100);
        playerTwoBox.setMinSize(100, 100);
        playingField.setMinSize(200,100);

        center.setAlignment(Pos.BASELINE_CENTER);
        center.getChildren().addAll(playingFieldLable, playingField);

        fillPlayerOneBox(gameState);
        fillPlayerTwoBox(gameState);
        fillPlayingField(gameState);

        window.setMinSize(400, 200);
        window.setLeft(playerOneBox);
        window.setRight(playerTwoBox);
        window.setCenter(center);
        window.setBottom(new StackPane(GUIApplication.nextMoveButton));
    }

    void fillPlayerOneBox(GameState gameState){
        playerOneBox.getChildren().clear();
        playerOneBox.getChildren().add(playerOneLable);

        for(Bone bone: gameState.getPlayers()[0].getAllBone()){
            playerOneBox.getChildren().add(new Label(bone.toString()));
        }

    }

    void fillPlayerTwoBox(GameState gameState){
        playerTwoBox.getChildren().clear();
        playerTwoBox.getChildren().add(playerTwoLable);


        for(Bone bone: gameState.getPlayers()[1].getAllBone()){
            playerTwoBox.getChildren().add(new Label(bone.toString()));
        }

    }

    void fillPlayingField(GameState gameState){
        playingField.getChildren().clear();

        for(Bone bone: gameState.getListPlayingField()){
            playingField.getChildren().add(new Label(bone.toString()));
        }

    }

    void nextMoveButton(GameState gameState){
        try {
            gameState.nextMove();
        }catch (Error e){
            InformationWindow.draw("Win", "Победитель: " + gameState.getWinnerName());
        }
        fillPlayerOneBox(gameState);
        fillPlayerTwoBox(gameState);
        fillPlayingField(gameState);
    }

}
