package vendingmachine;

import java.util.List;

public interface VendingMachine {

    State getState();

    void insertCoin();

    int getBalance();

    Beverage getSelectedBeverage();

    Inventory getInventory();

    List<Coin> returnChange();

    void reset();
}