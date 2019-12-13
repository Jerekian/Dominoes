package ru.vsu.csf.proskuryakov.dominoes.data.essence;

public enum  BoneNumbers {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX;

    public static BoneNumbers getSimilarNumber(int number){
        switch (number){
            case 0: return ZERO;
            case 1: return ONE;
            case 2: return TWO;
            case 3: return THREE;
            case 4: return FOUR;
            case 5: return FIVE;
            case 6: return SIX;
        }
        return null;
    }

}
