package ru.vsu.csf.proskuryakov.data;

public class PlayersCircularLinkedList {



    PlayersListNode first = null;
    int count;

    public PlayersCircularLinkedList() {

    }

    public PlayersCircularLinkedList(int count, Player player1, Player player2) {
        this.count = count;
        first = new PlayersListNode(player1, null);
        first.setNextPlayer(new PlayersListNode(player2, first));
    }

    public PlayersCircularLinkedList(int count, Player player1, Player player2, Player player3) {
        this.count = count;
        first = new PlayersListNode(player1, null);
        first.setNextPlayer(new PlayersListNode(player2, new PlayersListNode(player3, first)));
    }

    public PlayersCircularLinkedList(int count, Player player1, Player player2, Player player3, Player player4) {
        this.count = count;
        first = new PlayersListNode(player1, null);
        first.setNextPlayer(new PlayersListNode(player2, new PlayersListNode(player3, new PlayersListNode(player4, first))));
    }

    public void add(Player player){
        if(first == null)
            first = new PlayersListNode(player, null);
        else{
            PlayersListNode last = getLast();
            last.setNextPlayer(new PlayersListNode(player, null));
        }
    }

    private PlayersListNode getLast(){
        PlayersListNode p = first;
        while ( p.getNextPlayer() != null){
            p = p.getNextPlayer();
        }
        return p;
    }

    public void makeCyclic(){
        getLast().setNextPlayer(first);
    }

    public PlayersListNode getFirst() {
        return first;
    }

    public void setFirst(PlayersListNode first) {
        this.first = first;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
