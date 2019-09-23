package ru.proskurEgor.data;

import ru.proskurEgor.core.BoneSet;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private int score;
    private String name;
    private LinkedList<Bone> bones;

    public Player(String name) {
        this.name = name;
        this.bones = new LinkedList<>();
    }

    public Bone pollLastBone(){
        return bones.pollLast();
    }

    public Bone pollBone(int index){
        Bone bone = bones.get(index);
        bones.remove(index);
        return bone;
    }

    public Bone pollBone(Bone bone){
        return pollBone(bones.indexOf(bone));
    }

    public void addBone(Bone bone){
        bones.add(bone);
    }

    public Bone getBone(int index){
        return bones.get(index);
    }

    public List<Bone> getAllBone(){
        return bones;
    }

    public boolean isContains(Bone bone){
        return bones.contains(bone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bones.size(); i++){
            sb.append(i != 0  ? (", ") : "");
            sb.append(bones.get(i).toString());

        }
        return sb.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
