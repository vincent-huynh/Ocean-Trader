package oceantrader;

import java.util.ArrayList;
import java.util.Arrays;

public enum TechLevel {

    PREAG("Pre-Agriculture", new Item[]{
        new Item("Fish", 50, "Food"),
        new Item("Deer", 100, "Food"),
        new Item("Chicken", 50, "Food"),
        new Item("Stone", 10, "Material"),
        new Item("Crab", 50, "Food"),
        new Item("Berry", 20, "Food"),
        new Item("Flint", 100, "Material"),
        new Item("Wood", 200, "Material"),
        new Item("Fur", 200, "Material"),
        new Item("Water", 10, "Drink")}),

    AGRICULTURE("Agriculture", new Item[]{
        new Item("Carrot", 20, "Food"),
        new Item("Apple", 20, "Food"),
        new Item("Orange", 20, "Food"),
        new Item("Vegetable", 20, "Food")}, PREAG),

    MEDIEVAL("Medieval", new Item[]{
        new Item("Sword", 1000, "Combat"),
        new Item("Shield", 1000, "Combat"),
        new Item("Wine", 100, "Drink")}, AGRICULTURE),

    RENAISSANCE("Renaissance", new Item[]{
        new Item("Mona Lisa", 8000, "Cute"),
        new Item("Clock", 500, "Utility"),
        new Item("Compass", 500, "Utility"),
        new Item("Gunpowder", 200, "Material")}, MEDIEVAL),

    INDUSTRIAL("Industrial", new Item[]{
        new Item("Cannon", 7000, "Combat"),
        new Item("Gun", 5000, "Combat"),
        new Item("Narcotic", 1000, "Drug")}, RENAISSANCE),

    MODERN("Modern", new Item[]{
        new Item("Oil", 1000, "Material"),
        new Item("Phone", 2000, "Tech"),
        new Item("Nuclear Missile", 8000, "Combat")}, INDUSTRIAL),

    FUTURISTIC("Futuristic", new Item[]{
        new Item("Laser Gun", 10000, "Combat"),
        new Item("Anti-Gravity Machine", 10000, "Tech"),
        new Item("Time Machine", 10000, "Tech"),
        new Item("The Flying Dutchman", 10000, "Ship")}, MODERN);

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