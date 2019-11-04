package ru.vsu.csf.proskuryakov.data;
/*
набор остортированных костяшек
 */
import ru.vsu.csf.proskuryakov.data.essence.Bone;
import ru.vsu.csf.proskuryakov.data.essence.BoneNumbers;

import java.util.LinkedList;

public class OrderedBones {

    private static LinkedList<Bone> allBones = fillMarker();

    private static LinkedList<Bone> fillMarker(){
        LinkedList<Bone> bones = new LinkedList<>();

        for(int i = 1; i <= 6; i++){
            bones.add(new Bone(BoneNumbers.getSimilarNumber(i), BoneNumbers.getSimilarNumber(i)));
        }
        bones.add(new Bone(BoneNumbers.ZERO, BoneNumbers.ZERO));
        for(int i = 6; i >=0; i--){
            for(int j = i-1; j >= 0; j--){
                bones.add(new Bone(BoneNumbers.getSimilarNumber(i), BoneNumbers.getSimilarNumber(j)));
            }
        }
        return bones;
    }

    public static LinkedList<Bone> getAllBones(){
        return allBones;
    }
    public static Bone getBone(int index){
        return allBones.get(index);
    }
    public static int size(){
        return allBones.size();
    }

}
