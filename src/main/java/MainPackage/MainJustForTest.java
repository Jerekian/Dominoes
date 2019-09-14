package MainPackage;

public class MainJustForTest {

    public static void main(String[] args) {

        Market market = new Market();

        market.fillMarket();

        System.out.println(market.toString());
        market.randomSort();
        System.out.println(market.toString());
        market.ascendingSort();
        System.out.println(market.toString());
    }


}
