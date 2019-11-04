package ru.vsu.csf.proskuryakov.gui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class ExitWindow extends Window{

    private boolean answer;

    public ExitWindow() {
        super("ExitWindow");
    }


    public boolean displayAndGetAnswer(){

        setMinSize(100, 250);
        setMaxSize(100, 250);

        Label text = new Label("Are you sure you want to exit the game?");
        text.setTextFill(Paint.valueOf("WHITE"));

        Button yesButton = new Button("Yes");
        yesButton.setMinSize(50,25);
        yesButton.setStyle("-fx-background-color: #87CEEB;");
        yesButton.setOnAction(e -> {
            answer = true;
            close();
        });

        Button noButton = new Button("No");
        noButton.setMinSize(50,25);
        noButton.setStyle("-fx-background-color: #87CEEB;");
        noButton.setOnAction(e -> {
            answer = false;
            close();
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(yesButton, noButton);


        VBox root = new VBox(5);
        root.setStyle("-fx-background-color: #000000;");
        root.setMargin(root, new Insets(5, 5, 5, 5));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(text, new Separator(), buttonBox);


        window.setScene(new Scene(root));
        window.showAndWait();
        return answer;

    }

}
