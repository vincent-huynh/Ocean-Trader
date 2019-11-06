package oceantrader;

import java.util.ArrayList;

public class Ship {

    private ShipType type;
    private ArrayList<Item> cargoList;
    private int fuelCapacity;
    private int health;

    private int maxCargoSpace;
    private int maxFuelCapacity;
    private int maxHealth;

    public Ship(ShipType type, int space, int fuel, int health) {
        this.type = type;
        this.fuelCapacity = fuel;
        this.health = health;

        this.maxCargoSpace = space;
        this.maxFuelCapacity = fuel;
        this.maxHealth = health;

        this.cargoList = new ArrayList();
    }

    /**
     * This method bypasses the constructor restrictions of having to have
     * all constructor chaining calls to be on the first line.
     *
     * @param type ShipType enum that will dictate the type of ship created.
     * @return A new ship.
     * Call like so: Ship SOME_NAME = newShip(YOUR_TYPE);
     */
    public static Ship newShip(ShipType type) {
        switch (type) {
        case WARSHIP:
            return new Ship(ShipType.WARSHIP, 5, 300, 2000);
        case MERCHANT:
            return new Ship(ShipType.MERCHANT, 45, 650, 1250);
        case EXPLORER:
            return new Ship(ShipType.EXPLORER, 15, 1000, 600);
        default:
            return new Ship(ShipType.DEFAULT, 5, 500, 1000);
        }
    }

    public ShipType getType() {
        return type;
    }

    public int getCargoSize() {
        return cargoList.size();
    }

    public ArrayList<Item> getCargoList() {
        return cargoList;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxCargoSpace() {
        return maxCargoSpace;
    }

    public void setMaxCargoSpace(int maxCargoSpace) {
        this.maxCargoSpace = maxCargoSpace;
    }

    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public void setMaxFuelCapacity(int maxFuelCapacity) {
        this.maxFuelCapacity = maxFuelCapacity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}