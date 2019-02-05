package vendingmachine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {

    private Inventory<Beverage> inventory;

    @Before
    public void setUp() {
        inventory = new Inventory<>();
    }

    @Test
    public void shouldReturnTrueIfBeverageInStock() {
        inventory.addBeverage(Beverage.WATER);

        assertFalse(inventory.hasBeverageInStock(Beverage.BEER));
        assertTrue(inventory.hasBeverageInStock(Beverage.WATER));
    }

    @Test
    public void shouldReturnFalseIfBeverageNotInStock() {
        assertFalse(inventory.hasBeverageInStock(Beverage.BEER));
    }

    @Test
    public void shouldReturnNumberOfBeveragesInStock() {
        inventory.addBeverage(Beverage.COKE);
        inventory.addBeverage(Beverage.COKE);
        inventory.addBeverage(Beverage.COKE);

        assertEquals(3, inventory.getNumberOfBeverages(Beverage.COKE));
    }

    @Test
    public void shouldReduceNumberOfBeverages() {
        Beverage beverage = Beverage.COKE;

        inventory.addBeverage(beverage);
        inventory.addBeverage(beverage);

        assertEquals(2, inventory.getNumberOfBeverages(beverage));

        inventory.reduceBeverage(beverage);
        assertEquals(1, inventory.getNumberOfBeverages(beverage));
    }

    @Test
    public void shouldClearInventory() {
        inventory.addBeverage(Beverage.WATER);
        inventory.addBeverage(Beverage.COKE);
        inventory.addBeverage(Beverage.COKE);
        inventory.addBeverage(Beverage.BEER);

        assertEquals(1, inventory.getNumberOfBeverages(Beverage.WATER));
        assertEquals(2, inventory.getNumberOfBeverages(Beverage.COKE));
        assertEquals(1, inventory.getNumberOfBeverages(Beverage.BEER));

        inventory.clear();

        assertEquals(0, inventory.getNumberOfBeverages(Beverage.WATER));
        assertEquals(0, inventory.getNumberOfBeverages(Beverage.COKE));
        assertEquals(0, inventory.getNumberOfBeverages(Beverage.BEER));
    }
}