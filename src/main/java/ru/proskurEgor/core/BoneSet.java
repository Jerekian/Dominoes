package ru.proskurEgor.core;
/*
набор костящек игрока
 */
import ru.proskurEgor.data.Bone;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BoneSet {

    protected LinkedList<Bone> bones = new LinkedList<>();

    public Bone pollLastBone(){
        return bones.pollLast();
    }

    public Bone pollBone(int index){
        Bone bone = bones.get(index);
        bones.remove(index);
        return bone;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bones.size(); i++){
            sb.append(i != 0  ? (", ") : "");
            sb.append(bones.get(i).toString());

        }
        return sb.toString();
    }
}
