package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;
import ru.proskurEgor.data.OrderedBones;
import ru.proskurEgor.data.Player;
import ru.proskurEgor.utils.DominoesUtils;

import java.util.LinkedList;

public class GameState {

    private int numberOfPlayers;
    private Market market;
    private Player[] players;
    private PlayingField playingField = new PlayingField();
    private int activePlayer = -1;
    private boolean haveMove;
    private boolean haveWinner = false;
    private Player winner = null;

    public GameState(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.market = new Market();
        this.players = new Player[numberOfPlayers];
        this.playingField = new PlayingField();
    }

    public void startGame(){

        for(int i = 0; i < players.length; i++){
            players[i] = new Player("Player_" + (i+1));
            for(int j = 0; j < 7; j++){
                players[i].addBone(market.getBone());
            }
        }
    }

    public LinkedList<Bone> getPlayingField(){
        return playingField.getPlayingField();
    }

    public void firstMove(){
        for(int i = 0; i < OrderedBones.getAllBones().size(); i++){
            Bone bone = OrderedBones.getAllBones().get(i);
            for(int j = 0; j < players.length; j++){
                if(players[j].isContains(bone)){
                    activePlayer = j;
                    playingField.addFirst(players[j].pollBone(bone));
                    return;
                }
            }
        }
    }

    private void checkMove(){
        if(market.size() > 0){
            haveMove = true;
            return;
        }
        else{
            for(int i = 0; i < players.length; i++){
                if(players[i].checkBoneForMove(playingField.getFirst().getPipsOnFirstHalf()) ||
                        players[i].checkBoneForMove(playingField.getLast().getPipsOnSecondHalf())){
                    haveMove = true;
                    return;
                }
            }
        }
        haveMove = false;
    }

    private void checkWinner(){
        if(market.size() != 0){
            for(Player player: players){
                checkWinner(player);
            }
        }else if(!haveMove){
            int min = players[0].getAllBone().size(), minIndex = 0;
            for(int i = 1; i < players.length; i++){
                if(min > players[i].getAllBone().size()){
                    min = players[i].getAllBone().size();
                    minIndex = i;
                }
            }
            winner = players[minIndex];
        }
    }

    private void checkWinner(Player player){
        if(player.getAllBone().size() == 0){
            haveWinner = true;
            winner = player;
        }
    }

    public void game(){
        checkMove();
        for(int i = (activePlayer++)/numberOfPlayers; haveMove && !haveWinner; i = i==numberOfPlayers-1?0:i+1) {

            nextMove(players[i]);
            System.out.println(DominoesUtils.listToString(playingField.getPlayingField()));
            checkMove();
            checkWinner(players[i]);
        }

        if(winner == null) checkWinner();

        System.out.println("Игра окончена");
        System.out.println("Победитель: " + winner.getName());
        System.out.println(DominoesUtils.listToString(playingField.getPlayingField()));
        for (int i = 0; i < players.length; i++){
            System.out.println(players[i].getName());
            System.out.println(DominoesUtils.listToString(players[i].getAllBone()));
            System.out.println("------------");
        }
    }

    private void nextMove(Player player){
        int index = player.boneSearch(playingField.getLast().getPipsOnSecondHalf());
        if (index != -1) {
            playingField.addLast(player.pollBone(index));
            return;
        }

        index = player.boneSearch(playingField.getFirst().getPipsOnFirstHalf());
        if (index != -1){
            playingField.addFirst(player.pollBone(index));
            return;
        }

        if(market.size() == 0){
            haveMove = false;
            return;
        }
        player.addBone(market.getBone());
        nextMove(player);
    }

}
