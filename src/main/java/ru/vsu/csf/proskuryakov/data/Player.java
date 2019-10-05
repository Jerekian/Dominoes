package ru.vsu.csf.proskuryakov.data;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private int score;
    private String name;
    private LinkedList<Bone> bones;

    public Player(String name, List<Bone> list) {
        this.name = name;
        this.bones = new LinkedList<>(list);
    }

    public Player(String name) {
        this.name = name;
        this.bones = new LinkedList<>();
    }

    public Bone pollLastBone() {
        return bones.pollLast();
    }

    public Bone pollBone(int index) {
        Bone bone = bones.get(index);
        bones.remove(index);
        return bone;
    }

    public Bone pollBone(Bone bone) {
        return pollBone(bones.indexOf(bone));
    }

    public void addBone(Bone bone) {
        bones.add(bone);
    }

    public void addStartBonePack(List bones){
        bones.addAll(bones);
    }

    public boolean checkBoneForMove(BoneNumbers boneNumbers) {
        for (int i = 0; i < bones.size(); i++) {
            if (bones.get(i).getPipsOnFirstHalf() == boneNumbers ||
                    bones.get(i).getPipsOnSecondHalf() == boneNumbers) {
                return true;
            }
        }
        return false;
    }

    public Bone getBone(int index) {
        return bones.get(index);
    }

    public List<Bone> getAllBone() {
        return bones;
    }

    public boolean isContains(Bone bone) {
        return bones.contains(bone);
    }

    public Bone boneSearch(BoneNumbers boneNumbers) {
        for (int i = 0; i < bones.size(); i++) {
            if(bones.get(i).getPipsOnFirstHalf() == boneNumbers ||
                    bones.get(i).getPipsOnSecondHalf() == boneNumbers){
                return pollBone(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bones.size(); i++) {
            sb.append(i != 0 ? (", ") : "");
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
