package ru.vsu.csf.proskuryakov.gui;

import javafx.scene.control.*;
import ru.vsu.csf.proskuryakov.gui.window.ExitWindow;

public class DominoesMenu {

    private MenuBar menuBar;

    public DominoesMenu() {

        menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");
        fillGameMenu(gameMenu);

        Menu settingMenu = new Menu("Settings");

        Menu numberOfPlayersMenu = new Menu("Number of players");
        fillNumberOfPlayersMenu(numberOfPlayersMenu);

        settingMenu.getItems().addAll(numberOfPlayersMenu);

        menuBar.getMenus().addAll(gameMenu, settingMenu);

    }

    private void fillGameMenu(Menu gameMenu){
        MenuItem startNewGameMenuItem = new MenuItem("Start new game");
        startNewGameMenuItem.setOnAction(e -> {
            GUIApplication.startNewGame();
            System.out.println("Start new game");
        });

        MenuItem saveGameMenuItem = new MenuItem("Save game state");
        saveGameMenuItem.setOnAction(e -> {
            System.out.println("Save game state");
            GUIApplication.saveGameState();
        });

        MenuItem openGameMenuItem = new MenuItem("Open last game");
        openGameMenuItem.setOnAction(e -> System.out.println("Open last game"));

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> {
            RootPanel.closeProgram();
        });

        gameMenu.getItems().addAll(
                startNewGameMenuItem,
                saveGameMenuItem,
                openGameMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem
        );
    }

    private void fillNumberOfPlayersMenu(Menu numberOfPlayersMenu){
        ToggleGroup numberOfPlayersGroup = new ToggleGroup();
        RadioMenuItem twoPlayerItem = new RadioMenuItem("2 Players");
        twoPlayerItem.setSelected(true);
        RadioMenuItem threePlayerItem = new RadioMenuItem("3 Players");
        RadioMenuItem fourPlayerItem = new RadioMenuItem("4 Players");
        twoPlayerItem.setToggleGroup(numberOfPlayersGroup);
        threePlayerItem.setToggleGroup(numberOfPlayersGroup);
        fourPlayerItem.setToggleGroup(numberOfPlayersGroup);
        numberOfPlayersMenu.getItems().addAll(
                twoPlayerItem,
                threePlayerItem,
                fourPlayerItem
        );
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
