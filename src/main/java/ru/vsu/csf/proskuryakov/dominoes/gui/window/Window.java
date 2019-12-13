package ru.vsu.csf.proskuryakov.dominoes.gui.window;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {

    Stage window;

    public Window(String title) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
    }

    public void display(){
        window.show();
    }

    public void close(){
        window.close();
    }

    public void setMinSize(double minHeight, double minWidth){
        window.setMinHeight(minHeight);
        window.setMinWidth(minWidth);
    }

    public void setMaxSize(double maxHeight, double maxWidth){
        window.setMaxHeight(maxHeight);
        window.setMaxWidth(maxWidth);
    }

}
