package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BoneSet {

    protected Random rnd = new Random();
    protected LinkedList<Bone> bones = new LinkedList<>();

    public Bone pollLastBone(){
        return bones.pollLast();
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

    public void randomSort(){
        bones.sort((a, b) -> rnd.nextInt(2)*2-1);
    }

    public void ascendingSort(){
        bones.sort(Bone::compareTo);
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
