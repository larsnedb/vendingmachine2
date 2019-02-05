package vendingmachine;

public class Inventory {

    private int numberOfWaters;
    private int numberOfSodas;
    private int numberOfBeers;


    public int getNumberOfWaters() {
        return numberOfWaters;
    }

    public void addWater() {
        numberOfWaters++;
    }

    public void removeWater() {
        numberOfWaters--;
    }

    public int getNumberOfSodas() {
        return numberOfSodas;
    }

    public void addSoda() {
        numberOfSodas++;
    }

    public void removeSoda() {
        numberOfSodas--;
    }

    public int getNumberOfBeers() {
        return numberOfBeers;
    }

    public void addBeer() {
        numberOfBeers++;
    }

    public void removeBeer() {
        numberOfBeers--;
    }
}
