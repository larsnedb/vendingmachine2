package vendingmachine;

import java.util.List;

public interface VendingMachine {

    State getState();

    void insertCoin(Coin coin);

    int getBalance();

    Beverage getSelectedBeverage();

    Inventory getInventory();

    List<Coin> returnChange();

    void reset();

    void chooseBeverage(Beverage beverage);

    boolean completePurchase();
}