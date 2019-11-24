package oceantrader;

import java.util.Objects;

public class Item {

    private String name;
    private String type;
    private int price;
    private int sellPrice;

    public Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.sellPrice = 0;
    }

    public Item(String name, int price) {
        this(name, price, "DEFAULT_VALUE");
    }

    public Item(String name) {
        this(name, 0, "DEFAULT_VALUE");
    }

    /*
    Copy Constructor
     */
    public Item(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.type = item.getType();
        this.sellPrice = item.getSellPrice();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return price == item.getPrice() && Objects.equals(name, item.getName())
                && Objects.equals(type, item.getType());
    }

    @Override
    public String toString() {
        return String.format("Item Name: %s\nItem Price: %d\nItem Type: %s",
                name, price, type);
    }

    protected Object[] tableizer() {
        return new Object[]{name, price, type, this};
    }
}