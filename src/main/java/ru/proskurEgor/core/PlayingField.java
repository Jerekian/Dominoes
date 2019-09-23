package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

import java.util.LinkedList;

public class PlayingField{

    private LinkedList<Bone> playingField;

    public PlayingField() {
        this.playingField = new LinkedList<>();
    }

    public void addFirst(Bone bone){
        if(playingField.size() == 0 ||
                bone.getPipsOnSecondHalf() == playingField.getFirst().getPipsOnFirstHalf()){
            playingField.addFirst(bone);
        }else{
            playingField.addFirst(new Bone(bone.getPipsOnSecondHalf(), bone.getPipsOnFirstHalf()));
        }
    }

    public void addLast(Bone bone){
        if(playingField.size() == 0 ||
                bone.getPipsOnFirstHalf() == playingField.getLast().getPipsOnSecondHalf()){
            playingField.addLast(bone);
        }else{
            playingField.addLast(new Bone(bone.getPipsOnSecondHalf(), bone.getPipsOnFirstHalf()));
        }
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
