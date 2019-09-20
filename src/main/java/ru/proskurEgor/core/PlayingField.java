package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;

public class PlayingField extends BoneSet{

    public void addFirst(Bone bone){
        bones.addFirst(bone);
    }

    public void addLast(Bone bone){
        bones.addLast(bone);
    }


}
