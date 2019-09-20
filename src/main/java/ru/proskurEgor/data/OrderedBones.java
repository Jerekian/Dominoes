package ru.proskurEgor.data;

import java.util.LinkedList;
import java.util.List;

public class OrderedBones {

    private List<Bone> allBones = new LinkedList<>();

    {
        for(int i = 1; i <= 6; i++){
            allBones.add(new Bone(i, i));
        }
        allBones.add(new Bone(0,0));
        for(int i = 6; i >=0; i--){
            for(int j = i-1; j >= 0; j--){
                allBones.add(new Bone(i,j));
            }
        }
    }

    public List<Bone> getAllBones(){
        return allBones;
    }


}
