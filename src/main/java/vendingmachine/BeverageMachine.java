package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class BeverageMachine implements VendingMachine {

    private State state;
    private Beverage selectedBeverage;
    private int balance;
    private Inventory<Beverage> inventory;

    BeverageMachine() {
        state = State.IDLE;
        inventory = new Inventory<>();
    }

    @Override
    public State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    @Override
    public Beverage getSelectedBeverage() {
        return selectedBeverage;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public Inventory<Beverage> getInventory() {
        return inventory;
    }

    @Override
    public void insertCoin(Coin coin) {
        balance += coin.getValue();
    }

    @Override
    public void reset() {
        state = State.IDLE;
        selectedBeverage = null;
        balance = 0;
        inventory.clear();
    }

    @Override
    public void chooseBeverage(Beverage beverage) {
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
    public List<Coin> cancel() {
        return computeChange();
    }

    private List<Coin> computeChange() {
        List<Coin> coins = new ArrayList<>();
        while (balance > 0) {
            if (balance >= 20) {
                coins.add(Coin.TWENTY);
                balance -= 20;
            }
            else if (balance >= 10) {
                coins.add(Coin.TEN);
                balance -= 10;
            }
            else if (balance >= 5) {
                coins.add(Coin.FIVE);
                balance -= 5;
            }
            else {
                coins.add(Coin.ONE);
                balance -= 1;
            }
        }
        return coins;
    }
}