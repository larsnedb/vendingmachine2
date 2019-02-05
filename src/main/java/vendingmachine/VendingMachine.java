package vendingmachine;

import java.util.List;

public interface VendingMachine {

    void insertCoin(Coin coin);

    Inventory getInventory();

    List<Coin> completePurchaseAndComputeChange();

    List<Coin> cancelTransaction();

    void reset();
}