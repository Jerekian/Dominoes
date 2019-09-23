package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;
import ru.proskurEgor.data.OrderedBones;
import ru.proskurEgor.data.Player;

import java.util.LinkedList;

public class GameState {

    private int numberOfPlayers;
    private Market market;
    private Player[] players;
    private PlayingField playingField = new PlayingField();
    private int activePlayer;

    public GameState(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.market = new Market();
        this.players = new Player[numberOfPlayers];
        this.playingField = new PlayingField();
    }

    public void startGame(){

        for(int i = 0; i < players.length; i++){
            players[i] = new Player("Player_" + (i+1));
            for(int j = 0; j < 6; j++){
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
                    activePlayer = i;
                    playingField.addFirst(players[j].pollBone(bone));
                    return;
                }
            }
        }
    }


}
