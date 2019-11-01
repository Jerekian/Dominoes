package ru.vsu.csf.proskuryakov.core;

import ru.vsu.csf.proskuryakov.data.*;

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

    public GameState(int numberOfPlayers, Market market, Player[] players,
                     PlayingField playingField, int activePlayer, boolean haveMove,
                     boolean isMoveDone, boolean haveWinner, Player winner) {
        this.numberOfPlayers = numberOfPlayers;
        this.market = market;
        this.players = players;
        this.playingField = playingField;
        this.activePlayer = activePlayer;
        this.haveMove = haveMove;
        this.isMoveDone = isMoveDone;
        this.haveWinner = haveWinner;
        this.winner = winner;
    }

    public GameState() {
    }

    //создаем игроков и заполняем их руки костяшками
    public void startGame(){

        for(int i = 0; i < players.length; i++){
            players[i] = new Player("Player_" + (i+1), market.getStartBonePack());
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

    public String getWinnerName(){
        if(winner == null) checkWinner();
        return winner.getName();
    }

    public List<Bone> getPlayingFieldList(){
        return playingField.getPlayingField();
    }

    public List<Bone> getMarketList(){
        return market.getMarketList();
    }

    //getter and setters

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public PlayingField getPlayingField() {
        return playingField;
    }

    public void setPlayingField(PlayingField playingField) {
        this.playingField = playingField;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }

    public boolean isHaveMove() {
        return haveMove;
    }

    public void setHaveMove(boolean haveMove) {
        this.haveMove = haveMove;
    }

    public boolean isMoveDone() {
        return isMoveDone;
    }

    public void setMoveDone(boolean moveDone) {
        isMoveDone = moveDone;
    }

    public boolean isHaveWinner() {
        return haveWinner;
    }

    public void setHaveWinner(boolean haveWinner) {
        this.haveWinner = haveWinner;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

}
