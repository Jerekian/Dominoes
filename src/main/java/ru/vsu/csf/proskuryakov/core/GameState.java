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
    private boolean isMoveDone;
    private boolean haveWinner = false;
    private Player winner = null;

    public GameState(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.market = new Market();
        this.players = new Player[numberOfPlayers];
        this.playingField = new PlayingField();
    }

    //создаем игроков и заполняем их руки костяшками
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

    //начинаем игру с поиска первой по приоритету костяшки, выкладываем ее на поле и запоминаем начавшего игрока
    public void firstMove(){
        for(int i = 0; i < OrderedBones.size(); i++){
            Bone bone = OrderedBones.getBone(i);
            for(int j = 0; j < players.length; j++){
                if(players[j].isContains(bone)){
                    activePlayer = j;
                    playingField.addFirst(players[j].pollBone(bone));
                    isMoveDone = true;
                    return;
                }
            }
        }
    }

    //если базар не пустой или у игроков есть что положить, значит продолжаем игру
    public void checkMove(){
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
        if(player.getAllBone().isEmpty()){
            haveWinner = true;
            winner = player;
        }
    }

    public void game(){

        try {
            checkMove();
            while(true) nextMove();
        }catch (Error error){

        }

    }


    public void nextMove() throws Error{

        if(haveMove && !haveWinner){
            if(isMoveDone){
                activePlayer++;
                activePlayer = activePlayer%numberOfPlayers;
                isMoveDone = false;
            }

            nextMove(players[activePlayer]);
            checkMove();
            checkWinner(players[activePlayer]);
        }else{
            if(winner == null) checkWinner();
            throw new Error("Хода нет");
        }

    }

    private void nextMove(Player player){
        Bone bone = player.boneSearch(playingField.getLast().getPipsOnSecondHalf());
        if (bone != null) {
            playingField.addLast(bone);
            isMoveDone = true;
            return;
        }

        bone = player.boneSearch(playingField.getFirst().getPipsOnFirstHalf());
        if (bone != null){
            playingField.addFirst(bone);
            isMoveDone = true;
            return;
        }

        if(market.size() == 0){
            haveMove = false;
            isMoveDone = false;
            return;
        }

        player.addBone(market.getBone());
        isMoveDone = false;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
