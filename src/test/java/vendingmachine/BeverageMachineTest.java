package vendingmachine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BeverageMachineTest {

    private BeverageMachine machine;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        machine = new BeverageMachine();
    }

    @Test
    public void shouldAddInsertedCoinsToBalance() {
        machine.insertCoin(Coin.ONE);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TWENTY);

        assertEquals(36, machine.getBalance());
    }

    @Test
    public void shouldReturnNullWhenNoBeverageIsChosen() {
        assertNull(machine.getSelectedBeverage());
    }

    @Test
    public void shouldChooseBeverage() {
        machine.chooseBeverage(Beverage.BEER);

        assertEquals(Beverage.BEER, machine.getSelectedBeverage());
    }

    @Test
    public void shouldCompleteTransactionIfBalanceIsSufficient() {
        machine.insertCoin(Coin.TEN);
        machine.chooseBeverage(Beverage.WATER);

        assertEquals(0, machine.completePurchase());
    }

    @Test
    public void shouldThrowExceptionWhenNoBeverageIsChosen() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No beverage is chosen");

        machine.completePurchase();
    }

    @Test
    public void shouldThrowExceptionIfInsufficientBalance() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Insufficient funds, balance = 1, price of beverage = 10");

        machine.insertCoin(Coin.ONE);
        machine.chooseBeverage(Beverage.WATER);

        machine.completePurchase();
    }
}