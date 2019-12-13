package ru.vsu.csf.proskuryakov.dominoes.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class StartGameMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void runSavedGame(ActionEvent event) {

    }

    @FXML
    void startNewGame(ActionEvent event) {
        Game.game.startNewGame(event);
    }

    @FXML
    void initialize() {

    }
}
