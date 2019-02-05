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
        inventory.addItem(Beverage.WATER);

        assertFalse(inventory.hasItemInStock(Beverage.BEER));
        assertTrue(inventory.hasItemInStock(Beverage.WATER));
    }

    @Test
    public void shouldReturnFalseIfBeverageNotInStock() {
        assertFalse(inventory.hasItemInStock(Beverage.BEER));
    }

    @Test
    public void shouldReturnNumberOfBeveragesInStock() {
        inventory.addItem(Beverage.COKE);
        inventory.addItem(Beverage.COKE);
        inventory.addItem(Beverage.COKE);

        assertEquals(3, inventory.getNumberOfItems(Beverage.COKE));
    }

    @Test
    public void shouldReduceNumberOfBeverages() {
        Beverage beverage = Beverage.COKE;

        inventory.addItem(beverage);
        inventory.addItem(beverage);

        assertEquals(2, inventory.getNumberOfItems(beverage));

        inventory.subtractItem(beverage);
        assertEquals(1, inventory.getNumberOfItems(beverage));
    }

    @Test
    public void shouldClearInventory() {
        inventory.addItem(Beverage.WATER);
        inventory.addItem(Beverage.COKE);
        inventory.addItem(Beverage.COKE);
        inventory.addItem(Beverage.BEER);

        assertEquals(1, inventory.getNumberOfItems(Beverage.WATER));
        assertEquals(2, inventory.getNumberOfItems(Beverage.COKE));
        assertEquals(1, inventory.getNumberOfItems(Beverage.BEER));

        inventory.clear();

        assertTrue(inventory.getContents().isEmpty());
    }
}