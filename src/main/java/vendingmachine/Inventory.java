package vendingmachine;

import java.util.HashMap;
import java.util.Map;

class Inventory<T> {

    private Map<T, Integer> inventory = new HashMap<>();

    void addBeverage(T beverage) {
        Integer count = inventory.getOrDefault(beverage, 0);
        inventory.put(beverage, count + 1);
    }

    int getNumberOfBeverages(T beverage) {
        Integer numberInStock = inventory.get(beverage);
        return numberInStock != null ? numberInStock : 0;
    }

    void reduceBeverage(T beverage) {
        if (hasBeverageInStock(beverage)) {
            Integer count = inventory.get(beverage);
            inventory.put(beverage, count - 1);
        }
    }

    boolean hasBeverageInStock(T beverage) {
        Integer numberOfBeverages = inventory.get(beverage);
        return numberOfBeverages != null && numberOfBeverages > 0;
    }

    void clear() {
        inventory.clear();
    }
}