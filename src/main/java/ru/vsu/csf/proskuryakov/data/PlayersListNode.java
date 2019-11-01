package ru.vsu.csf.proskuryakov.data;

public class PlayersListNode {
    private Player player;
    private PlayersListNode nextPlayer;

    public PlayersListNode(Player player, PlayersListNode nextPlayer) {
        this.player = player;
        this.nextPlayer = nextPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayersListNode getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(PlayersListNode nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

}
