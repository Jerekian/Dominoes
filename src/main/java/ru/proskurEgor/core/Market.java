package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

public class Market extends BoneSet {

    public Market() {
        fillMarket();
    }

    public Bone getRandomBone(){
        return bones.get(rnd.nextInt(bones.size()));
    }

    public void fillMarket(){
        for(int i = 0; i <= 6; i++){
            for(int j = i; j <= 6; j++){
                bones.add(new Bone(i, j));
            }
        }
    }

}
