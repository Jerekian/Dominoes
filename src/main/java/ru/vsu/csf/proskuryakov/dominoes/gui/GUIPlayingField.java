package ru.vsu.csf.proskuryakov.dominoes.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GUIPlayingField {


    public static void createPlayingField(GridPane playingField){
        playingField.setAlignment(Pos.CENTER);
        playingField.setPadding(new Insets(0));
        playingField.setHgap(1);
        playingField.setVgap(1);
        playingField.setGridLinesVisible(false);

        for (ColumnConstraints columnConstraints: playingField.getColumnConstraints()){
            columnConstraints.setMinWidth(30);
            columnConstraints.setMaxWidth(60);
        }

        for(RowConstraints rowConstraints: playingField.getRowConstraints()){
            rowConstraints.setMinHeight(30);
            rowConstraints.setMaxHeight(60);
        }

        playingField.add(new Label("x"), 0, 0);


    }

}
