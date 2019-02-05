package vendingmachine;

import java.util.List;

public interface VendingMachine {

    State getState();

    void insertCoin(Coin coin);

    int getBalance();

    Beverage getSelectedBeverage();

    Inventory getInventory();

    void reset();

    void chooseBeverage(Beverage beverage);

    List<Coin> completePurchaseAndComputeChange();

    List<Coin> cancel();
}