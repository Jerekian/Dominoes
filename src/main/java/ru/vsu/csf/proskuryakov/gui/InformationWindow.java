package ru.vsu.csf.proskuryakov.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InformationWindow {

    public static void draw(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(100);
        window.setMinWidth(255);

        window.setScene(new Scene(new StackPane(new Label(message))));
        window.show();


    }

}
