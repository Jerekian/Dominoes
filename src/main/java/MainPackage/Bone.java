package MainPackage;

public class Bone implements Comparable<Bone> {
    private int pipsOnFirstHalf;
    private int pipsOnSecondHalf;
    private boolean isDouble;

    public Bone(int pipsOnFirstHalf, int pipsOnSecondHalf) {
        this.pipsOnFirstHalf = pipsOnFirstHalf;
        this.pipsOnSecondHalf = pipsOnSecondHalf;
        this.isDouble = pipsOnFirstHalf==pipsOnSecondHalf;
    }

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

    public int getPipsOnFirstHalf() {
        return pipsOnFirstHalf;
    }

    public void setPipsOnFirstHalf(int pipsOnFirstHalf) {
        this.pipsOnFirstHalf = pipsOnFirstHalf;
    }

    public int getPipsOnSecondHalf() {
        return pipsOnSecondHalf;
    }

    public void setPipsOnSecondHalf(int pipsOnSecondHalf) {
        this.pipsOnSecondHalf = pipsOnSecondHalf;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    @Override
    public String toString() {
        return " " + pipsOnFirstHalf + " " + pipsOnSecondHalf;
    }
}
