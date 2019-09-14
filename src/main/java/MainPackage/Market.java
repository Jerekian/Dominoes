package MainPackage;

public class Market extends BoneSet {

    public Bone getRandomBone(){
        return bones.get(rnd.nextInt(bones.size()));
    }

}
