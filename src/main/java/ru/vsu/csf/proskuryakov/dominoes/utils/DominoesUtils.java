package ru.vsu.csf.proskuryakov.dominoes.utils;

/*
в этом классе будут реализованы утилиты
в частности утилита которая будет искать первоходящего игрока
             используем класс с отсортироваными костяшками
             и набор играков
             по ним ищем у кого самая приоритетная и даем тому преимущество в ходу
 */

import ru.vsu.csf.proskuryakov.dominoes.data.essence.Bone;
import ru.vsu.csf.proskuryakov.dominoes.data.essence.BoneNumbers;

import java.util.List;

public class DominoesUtils {

    public static BoneNumbers getSimilarNumber(int number){
        switch (number){
            case 0: return BoneNumbers.ZERO;
            case 1: return BoneNumbers.ONE;
            case 2: return BoneNumbers.TWO;
            case 3: return BoneNumbers.THREE;
            case 4: return BoneNumbers.FOUR;
            case 5: return BoneNumbers.FIVE;
            case 6: return BoneNumbers.SIX;
        }
        return null;
    }

    public static String listToString(List<Bone> list){
        StringBuilder sb = new StringBuilder();
        for(Bone bone: list){
            sb.append(bone.toString());
            sb.append(" ");
        }

        return sb.toString();
    }

}
