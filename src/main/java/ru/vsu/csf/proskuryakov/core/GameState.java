package ru.vsu.csf.proskuryakov.core;

import ru.vsu.csf.proskuryakov.data.Bone;
import ru.vsu.csf.proskuryakov.data.OrderedBones;
import ru.vsu.csf.proskuryakov.data.Player;
import ru.vsu.csf.proskuryakov.utils.DominoesUtils;

import java.util.LinkedList;
import java.util.List;

public class GameState {

    private int numberOfPlayers;
    private Market market;
    private Player[] players;
    private PlayingField playingField;
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
            players[i] = new Player("Player_" + (i+1), market.getStartBonePack());
        }

    }

    public List<Bone> getListPlayingField(){
        return playingField.getPlayingField();
    }

    public PlayingField getPlayingField(){
        return playingField;
    }

    public void firstMove(){
        for(int i = 0; i < OrderedBones.size(); i++){
            Bone bone = OrderedBones.getBone(i);
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

        do{
            activePlayer++;
            activePlayer = activePlayer%numberOfPlayers;

            do{
                System.out.println(players[activePlayer].getName() + ": ");
                System.out.println(DominoesUtils.listToString(players[activePlayer].getAllBone()));
            }
            while(!nextMove(players[activePlayer]));

            System.out.println("Playing Field: ");
            System.out.println(DominoesUtils.listToString(playingField.getPlayingField()));
            System.out.println();
            checkMove();
            checkWinner(players[activePlayer]);
        }
        while(haveMove && !haveWinner);

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

    private boolean nextMove(Player player){
        Bone bone = player.boneSearch(playingField.getLast().getPipsOnSecondHalf());
        if (bone != null) {
            playingField.addLast(bone);
            return true;
        }

        bone = player.boneSearch(playingField.getFirst().getPipsOnFirstHalf());
        if (bone != null){
            playingField.addFirst(bone);
            return true;
        }

        if(market.size() == 0){
            haveMove = false;
            return false;
        }
        player.addBone(market.getBone());
        return false;
    }

}
