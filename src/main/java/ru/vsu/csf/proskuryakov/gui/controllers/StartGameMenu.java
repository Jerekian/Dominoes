package ru.vsu.csf.proskuryakov.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.vsu.csf.proskuryakov.gui.Dominoes;

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
        Dominoes.app.startNewGame();
    }

    @FXML
    void initialize() {

    }
}
