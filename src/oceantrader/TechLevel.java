package oceantrader;

import java.util.ArrayList;
import java.util.Arrays;

public enum TechLevel {

    PREAG("Pre-Agriculture", new Item[] {new Item("Fish"), new Item("Deer"),
        new Item("Chicken"), new Item("Stone"), new Item("Crab"),
        new Item("Berry"), new Item("Flint"), new Item("Wood"), new Item("Fur"),
        new Item("Water")}),

    AGRICULTURE("Agriculture", new Item[] {new Item("Carrot"),
        new Item("Apple"), new Item("Orange"), new Item("Vegetable")}, PREAG),

    MEDIEVAL("Medieval", new Item[] {new Item("Sword"), new Item("Shield"),
        new Item("Wine")}, AGRICULTURE),

    RENAISSANCE("Renaissance", new Item[] {new Item("Mono Lisa"),
        new Item("Gold"), new Item("Clock"), new Item("Compass"),
        new Item("Gunpowder")}, MEDIEVAL),

    INDUSTRIAL("Industrial", new Item[] {new Item("Cannon"), new Item("Gun"),
        new Item("Narcotic")}, RENAISSANCE),

    MODERN("Modern", new Item[] {new Item("Oil"), new Item("Phone"),
        new Item("Nuclear Missile")}, INDUSTRIAL),

    FUTURISTIC("Futuristic", new Item[] {new Item("Laser Gun"),
        new Item("Anti-Gravity Machine"), new Item("Time Machine")}, MODERN);

    private String name;
    private ArrayList<Item> items;
    private ArrayList<Item> formerItems;

    TechLevel(String name, Item[] items) {
        this.name = name;
        this.items = new ArrayList<>(Arrays.asList(items));
        this.formerItems = new ArrayList<>();
    }

    TechLevel(String name, Item[] items, TechLevel formerTech) {
        this(name, items);
        this.formerItems.addAll(formerTech.getItems());
        this.formerItems.addAll(formerTech.getFormerItem());
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<Item> getFormerItem() {
        return formerItems;
    }

    public String toString() {
        return this.name;
    }
}