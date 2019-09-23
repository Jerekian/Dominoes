package ru.proskurEgor.core;

import ru.proskurEgor.utils.DominoesUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Введите количество игроков:");
        GameState gameState = new GameState(sc.nextInt());

        gameState.startGame();

        gameState.firstMove();
        System.out.println(DominoesUtils.listToString(gameState.getPlayingField()));

        gameState.game();
    }

}
