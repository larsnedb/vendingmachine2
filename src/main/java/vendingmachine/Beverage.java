package vendingmachine;

public enum Beverage {

    WATER("Water", 10),
    COKE("Coke", 25),
    BEER("Beer", 45);

    private final String name;
    private final int price;

    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}