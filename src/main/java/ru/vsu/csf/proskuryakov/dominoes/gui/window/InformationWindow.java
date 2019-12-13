package ru.vsu.csf.proskuryakov.dominoes.gui.window;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

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
