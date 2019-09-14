package MainPackage;

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



}
