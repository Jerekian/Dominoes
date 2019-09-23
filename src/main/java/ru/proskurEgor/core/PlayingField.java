package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

import java.util.LinkedList;

public class PlayingField{

    private LinkedList<Bone> playingField;

    public PlayingField() {
        this.playingField = new LinkedList<>();
    }

    public void addFirst(Bone bone){
        playingField.addFirst(bone);
    }

    public void addLast(Bone bone){
        playingField.addLast(bone);
    }

    public Bone getFirst(){
        return playingField.getFirst();
    }

    public Bone getLast(){
        return playingField.getLast();
    }

    public LinkedList<Bone> getPlayingField() {
        return playingField;
    }
}
