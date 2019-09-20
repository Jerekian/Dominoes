package ru.proskurEgor.core;

import ru.proskurEgor.data.Bone;
import ru.proskurEgor.data.OrderedBones;

import java.util.LinkedList;
import java.util.Random;

public class Market{

    private LinkedList<Bone> market;
    private Random rnd = new Random();

    public Market() {
        market = new LinkedList<>(OrderedBones.getAllBones());
        randomSort();
    }

    public Bone getRandomBone(){
        int index = rnd.nextInt(market.size());
        Bone bone = market.get(index);
        market.remove(index);
        return bone;
    }

    public Bone getBone(){
        return market.poll();
    }

    public boolean isEmpty(){
        return market.isEmpty();
    }

    public int size(){
        return market.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Bone bone: market){
            sb.append(bone.toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    public void randomSort(){
        market.sort((a, b) -> rnd.nextInt(2)*2-1);
    }

}
