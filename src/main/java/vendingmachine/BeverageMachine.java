package vendingmachine;

import java.util.List;

public class BeverageMachine implements VendingMachine {

    private State state;
    private Beverage selectedBeverage;
    private int balance;
    private Inventory inventory;

    BeverageMachine() {
        state = State.IDLE;
        inventory = new Inventory();
    }

    @Override
    public State getState() {
        return state;
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
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void insertCoin(Coin coin) {
        balance += coin.getValue();
    }

    @Override
    public List<Coin> returnChange() {
        return null;
    }

    @Override
    public void reset() {

    }

    @Override
    public void chooseBeverage(Beverage beverage) {
        selectedBeverage = beverage;
    }

    @Override
    public boolean completePurchase() {
        return selectedBeverage != null && balance >= selectedBeverage.getPrice();
    }
}