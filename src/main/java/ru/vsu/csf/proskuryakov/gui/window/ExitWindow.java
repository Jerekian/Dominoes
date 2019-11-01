package ru.vsu.csf.proskuryakov.gui.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ExitWindow extends Window{

    private boolean answer;

    public ExitWindow() {
        super("ExitWindow");
    }


    public boolean displayAndGetAnswer(){
        setMinSize(100, 250);

        Button yesButton = new Button("Yes");
        yesButton.setAlignment(Pos.BASELINE_CENTER);
        yesButton.setOnAction(e -> {
            answer = true;
            close();
        });

        Button noButton = new Button("No");
        noButton.setAlignment(Pos.BASELINE_CENTER);
        noButton.setOnAction(e -> {
            answer = false;
            close();
        });

        HBox buttons = new HBox(50, yesButton, noButton);
        buttons.setAlignment(Pos.BASELINE_CENTER);

        Label message = new Label("Are you sure?");
        HBox messageBox = new HBox(message);
        messageBox.setAlignment(Pos.BASELINE_CENTER);

        BorderPane root = new BorderPane();

        root.setTop(messageBox);
        root.setCenter(new Separator());
        root.setBottom(buttons);

        window.setScene(new Scene(root));
        window.showAndWait();
        return answer;
    }

}
