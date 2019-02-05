package vendingmachine;

public enum Beverage {

    WATER(10),
    COKE(25),
    BEER(45);

    private final int price;

    Beverage(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}