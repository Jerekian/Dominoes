package ru.vsu.csf.proskuryakov.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import ru.vsu.csf.proskuryakov.core.GameState;
import ru.vsu.csf.proskuryakov.data.essence.Bone;
import ru.vsu.csf.proskuryakov.gui.window.ExitWindow;
import ru.vsu.csf.proskuryakov.gui.window.InformationWindow;

public class RootPanel{

    BorderPane window = new BorderPane();

    VBox playerOneBox = new VBox();
    Label playerOneLable = new Label("Bone of the first player");

    VBox playerTwoBox = new VBox();
    Label playerTwoLable = new Label("Bone of the second player");

    VBox center = new VBox();
    Label playingFieldLable = new Label("Playing field");
    FlowPane playingField = new FlowPane();
    Label marketLable = new Label("Market");
    FlowPane market = new FlowPane();


    DominoesMenu dominoesMenu = new DominoesMenu();

    public RootPanel(GameState gameState) {

        playerOneBox.setMinSize(100, 200);
        playerTwoBox.setMinSize(100, 200);
        center.setMinSize(200,200);

        playingField.setAlignment(Pos.BASELINE_CENTER);


        market.setAlignment(Pos.BASELINE_CENTER);

        VBox topOfCenter = new VBox(playingFieldLable, playingField);
        topOfCenter.setAlignment(Pos.CENTER);

        VBox bottomOfCenter = new VBox(marketLable,market);
        bottomOfCenter.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(GUIApplication.nextMoveButton, GUIApplication.undoButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(25);

        center.getChildren().addAll(
                topOfCenter,
                new Separator(),
                bottomOfCenter
        );

        refillAllElement(gameState);

        window.setMinSize(400, 200);
        window.setTop(dominoesMenu.getMenuBar());
        window.setLeft(playerOneBox);
        window.setRight(playerTwoBox);
        window.setCenter(center);
        window.setBottom(buttonBox);
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

        for(Bone bone: gameState.getPlayingFieldList()){
            playingField.getChildren().add(new Label(bone.toString()));
        }

    }

    void fillMarket(GameState gameState){
        market.getChildren().clear();

        for(Bone bone: gameState.getMarketList()){
            market.getChildren().add(new Label(bone.toString()));
        }

    }

    public String nextMoveButton(GameState gameState){

        String move = "";

        try {
            move = gameState.nextMove();
        }catch (Error e){
            System.out.println("НЕ МОГУ СЛЕДУЮЩИЙ ХОД СДЕЛАТЬ");
        }

        refillAllElement(gameState);

        if(gameState.isHaveWinner()){
            new InformationWindow("Win",
                    100, 250).display("Победитель: " + gameState.getWinnerName());
        }

        return move;
    }

    public void refillAllElement(GameState gameState){
        fillPlayerOneBox(gameState);
        fillPlayerTwoBox(gameState);
        fillPlayingField(gameState);
        fillMarket(gameState);
    }

    static void closeProgram() {

        if(new ExitWindow().displayAndGetAnswer()){
            GUIApplication.window.close();
        }

    }

}
