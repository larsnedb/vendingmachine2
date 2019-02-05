package vendingmachine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Inventory<T> {

    private Map<T, Integer> contents = new HashMap<>();

    void addItem(T item) {
        Integer count = contents.getOrDefault(item, 0);
        contents.put(item, count + 1);
    }

    void subtractItem(T item) {
        if (hasItemInStock(item)) {
            Integer count = contents.get(item);
            contents.put(item, count - 1);
        }
    }

    int getNumberOfItems(T item) {
        Integer numberOfItems = contents.get(item);
        return numberOfItems != null ? numberOfItems : 0;
    }

    boolean hasItemInStock(T item) {
        Integer numberOfItems = contents.get(item);
        return numberOfItems != null && numberOfItems > 0;
    }

    void clear() {
        contents.clear();
    }

    Map<T, Integer> getContents() {
        return Collections.unmodifiableMap(contents);
    }
}