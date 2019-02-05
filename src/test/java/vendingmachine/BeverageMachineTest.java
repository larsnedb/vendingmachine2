package vendingmachine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
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

        assertEquals(Collections.emptyList(), machine.completePurchaseAndComputeChange());
    }

    @Test
    public void shouldThrowExceptionWhenNoBeverageIsChosen() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No beverage is chosen");

        machine.completePurchaseAndComputeChange();
    }

    @Test
    public void shouldThrowExceptionIfInsufficientBalance() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Insufficient funds, balance = 1, price of beverage = 10");

        machine.insertCoin(Coin.ONE);
        machine.chooseBeverage(Beverage.WATER);

        machine.completePurchaseAndComputeChange();
    }

    @Test
    public void shouldReturnEmptyListOfCoinsIfBalanceEqualsPrice() {
        machine.chooseBeverage(Beverage.WATER);
        machine.insertCoin(Coin.TEN);

        List<Coin> change = machine.completePurchaseAndComputeChange();

        assertTrue(change.isEmpty());
    }

    @Test
    public void shouldComputeChangeInCoinsGreedy() {
        machine.chooseBeverage(Beverage.COKE);

        machine.insertCoin(Coin.TWENTY);
        machine.insertCoin(Coin.FIVE);

        machine.insertCoin(Coin.TWENTY);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.ONE);
        machine.insertCoin(Coin.ONE);

        List<Coin> coins = machine.completePurchaseAndComputeChange();

        List<Coin> expected = asList(Coin.TWENTY, Coin.TEN, Coin.FIVE, Coin.ONE, Coin.ONE);
        assertEquals(expected, coins);
    }

    @Test
    public void shouldResetMachineState() {
        machine.chooseBeverage(Beverage.BEER);
        machine.insertCoin(Coin.TEN);
        machine.getInventory().addItem(Beverage.COKE);
        machine.setState(State.ONGOING_TRANSACTION);

        machine.reset();

        assertNull(machine.getSelectedBeverage());
        assertEquals(0, machine.getBalance());
        assertEquals(Collections.emptyMap(), machine.getInventory().getContents());
        assertEquals(State.IDLE, machine.getState());
    }


    @Test
    public void shouldReturnEmptyListIfCancelAndNoBalance() {
        List<Coin> change = machine.cancel();

        assertEquals(change, Collections.emptyList());
    }

    @Test
    public void shouldRefundBalanceIfTransactionCancelled() {
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.FIVE);

        List<Coin> change = machine.cancel();

        assertEquals(asList(Coin.TEN, Coin.FIVE), change);
    }
}