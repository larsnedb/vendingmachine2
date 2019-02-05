package vendingmachine;

import java.util.List;

public class BeverageMachine implements VendingMachine {

    private State state;
    private Beverage selectedBeverage;
    private int balance;
    private Inventory inventory;

    @Override
    public State getState() {
        return null;
    }

    @Override
    public Beverage getSelectedBeverage() {
        return null;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public void insertCoin() {

    }

    @Override
    public List<Coin> returnChange() {
        return null;
    }

    @Override
    public void reset() {

    }
}