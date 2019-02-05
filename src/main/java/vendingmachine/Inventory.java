package vendingmachine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Inventory<T> {

    private Map<T, Integer> contents = new HashMap<>();

    void addBeverage(T beverage) {
        Integer count = contents.getOrDefault(beverage, 0);
        contents.put(beverage, count + 1);
    }

    int getNumberOfBeverages(T beverage) {
        Integer numberInStock = contents.get(beverage);
        return numberInStock != null ? numberInStock : 0;
    }

    void reduceBeverage(T beverage) {
        if (hasBeverageInStock(beverage)) {
            Integer count = contents.get(beverage);
            contents.put(beverage, count - 1);
        }
    }

    boolean hasBeverageInStock(T beverage) {
        Integer numberOfBeverages = contents.get(beverage);
        return numberOfBeverages != null && numberOfBeverages > 0;
    }

    void clear() {
        contents.clear();
    }

    Map<T, Integer> getContents() {
        return Collections.unmodifiableMap(contents);
    }
}