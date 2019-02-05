package vendingmachine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeverageMachineTest {

    private BeverageMachine machine;

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
}