package ru.proskurEgor.data;
/*
набор остортированных костяшек
 */

import ru.proskurEgor.utils.DominoesUtils;

import java.util.LinkedList;

public class OrderedBones {

    private static LinkedList<Bone> allBones = fillMarker();

    private static LinkedList<Bone> fillMarker(){
        LinkedList<Bone> bones = new LinkedList<>();

        for(int i = 1; i <= 6; i++){
            bones.add(new Bone(DominoesUtils.getSimilarNumber(i), DominoesUtils.getSimilarNumber(i)));
        }
        bones.add(new Bone(BoneNumbers.ZERO, BoneNumbers.ZERO));
        for(int i = 6; i >=0; i--){
            for(int j = i-1; j >= 0; j--){
                bones.add(new Bone(DominoesUtils.getSimilarNumber(i), DominoesUtils.getSimilarNumber(j)));
            }
        }
        return bones;
    }

    public static LinkedList<Bone> getAllBones(){
        return allBones;
    }

}
