package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

import java.util.LinkedList;

public class PlayingField{

    private LinkedList<Bone> playingField = new LinkedList<>();

    public void addFirst(Bone bone){
        playingField.addFirst(bone);
    }

    public void addLast(Bone bone){
        playingField.addLast(bone);
    }


}
