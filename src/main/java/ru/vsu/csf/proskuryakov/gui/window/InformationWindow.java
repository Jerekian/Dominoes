package ru.vsu.csf.proskuryakov.gui.window;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InformationWindow extends Window  {

    public InformationWindow(String title) {
        super(title);
    }

    public InformationWindow(String titele, double minHeight, double minWidth) {
        this(titele);
        setMinSize(minHeight, minWidth);
    }

    public void display(String message){

        window.setScene(new Scene(new StackPane(new Label(message))));
        display();

    }

}
