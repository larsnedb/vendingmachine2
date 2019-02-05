package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class BeverageMachine implements VendingMachine {

    private Beverage selectedBeverage;
    private int balance;
    private Inventory<Beverage> inventory;

    BeverageMachine() {
        inventory = new Inventory<>();
    }

    Beverage getSelectedBeverage() {
        return selectedBeverage;
    }

    int getBalance() {
        return balance;
    }

    @Override
    public void insertCoin(Coin coin) {
        balance += coin.getValue();
    }

    @Override
    public Inventory<Beverage> getInventory() {
        return inventory;
    }

    void setSelectedBeverage(Beverage beverage) {
        selectedBeverage = beverage;
    }

    @Override
    public List<Coin> completePurchaseAndComputeChange() {
        if (selectedBeverage == null) {
            throw new IllegalArgumentException("No beverage is chosen");
        }
        if (balance < selectedBeverage.getPrice()) {
            throw new IllegalArgumentException(
                    String.format("Insufficient funds, balance = %d, price of beverage = %d",
                            balance, selectedBeverage.getPrice()));
        }
        balance -= selectedBeverage.getPrice();
        return computeChange();
    }

    @Override
    public void reset() {
        selectedBeverage = null;
        balance = 0;
        inventory.clear();
    }

    @Override
    public List<Coin> cancelTransaction() {
        return computeChange();
    }

    private List<Coin> computeChange() {
        List<Coin> coins = new ArrayList<>();
        while (balance > 0) {
            if (balance >= 20) {
                coins.add(Coin.TWENTY);
                balance -= 20;
            } else if (balance >= 10) {
                coins.add(Coin.TEN);
                balance -= 10;
            } else if (balance >= 5) {
                coins.add(Coin.FIVE);
                balance -= 5;
            } else {
                coins.add(Coin.ONE);
                balance -= 1;
            }
        }
        return coins;
    }
}