package ru.vsu.csf.proskuryakov.core;

import ru.vsu.csf.proskuryakov.data.OrderedBones;
import ru.vsu.csf.proskuryakov.utils.DominoesUtils;

public class MainJustForTest {

    public static void main(String[] args) {

        Market market = new Market();

        System.out.println(DominoesUtils.listToString(OrderedBones.getAllBones()));
        System.out.println(market.toString());
        market.randomSort();
        System.out.println(market.toString());
        System.out.println("----------");
        for(int i = market.size(); i > 0; i = market.size()){
            System.out.println(market.getBone() + "  " + market.size());
        }


    }


}
