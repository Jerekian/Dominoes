package ru.vsu.csf.proskuryakov.data.essence;
/*
класс описывающий одну костяшку
 */
public class Bone {

    private BoneNumbers pipsOnFirstHalf;
    private BoneNumbers pipsOnSecondHalf;
    private boolean isDouble;

    public Bone(BoneNumbers pipsOnFirstHalf, BoneNumbers pipsOnSecondHalf) {
        this.pipsOnFirstHalf = pipsOnFirstHalf;
        this.pipsOnSecondHalf = pipsOnSecondHalf;
        this.isDouble = pipsOnFirstHalf == pipsOnSecondHalf;
    }


    @Override
    public String toString() {
        return "(" + pipsOnFirstHalf +
                "|" + pipsOnSecondHalf + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bone)) return false;

        Bone bone = (Bone) o;

        if (isDouble() != bone.isDouble()) return false;
        if (getPipsOnFirstHalf() != bone.getPipsOnFirstHalf()) return false;
        return getPipsOnSecondHalf() == bone.getPipsOnSecondHalf();

    }

    @Override
    public int hashCode() {
        int result = getPipsOnFirstHalf().hashCode();
        result = 31 * result + getPipsOnSecondHalf().hashCode();
        result = 31 * result + (isDouble() ? 1 : 0);
        return result;
    }

    public BoneNumbers getPipsOnFirstHalf() {
        return pipsOnFirstHalf;
    }

    public void setPipsOnFirstHalf(BoneNumbers pipsOnFirstHalf) {
        this.pipsOnFirstHalf = pipsOnFirstHalf;
    }

    public BoneNumbers getPipsOnSecondHalf() {
        return pipsOnSecondHalf;
    }

    public void setPipsOnSecondHalf(BoneNumbers pipsOnSecondHalf) {
        this.pipsOnSecondHalf = pipsOnSecondHalf;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    /*
    //первая больше чем вторая. Здешняя больше чем о
    public int compareTo(Bone o) {

        if(isDouble && o.isDouble()){
            if(pipsOnFirstHalf != 0 && o.getPipsOnFirstHalf() != 0){
                return o.getPipsOnFirstHalf() - pipsOnFirstHalf;
            }else if(pipsOnFirstHalf == 0){
                return -1;
            }else{
                return 1;
            }
        }else if(isDouble && !o.isDouble()){
            return 1;
        }else if(!isDouble && o.isDouble()){
            return -1;
        }else if(pipsOnFirstHalf == o.getPipsOnFirstHalf()){
            return pipsOnSecondHalf-o.getPipsOnSecondHalf();
        }else if(pipsOnSecondHalf == o.getPipsOnSecondHalf()){
           return  pipsOnFirstHalf-o.getPipsOnFirstHalf();
        }else{
            return Math.max(pipsOnFirstHalf,pipsOnSecondHalf) -
                    Math.max(o.getPipsOnFirstHalf(), o.getPipsOnSecondHalf());
        }
    }
 */

}
