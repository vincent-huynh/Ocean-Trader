package oceantrader;

import java.util.ArrayList;

public class Region implements Comparable<Region> {

    private String name;
    private TechLevel techLevel;
    private ArrayList<Item> marketItems;

    private int tax;
    private int xCoord;
    private int yCoord;
    private double distance;

    protected Region(String name, TechLevel techLevel, int xCoord, int yCoord, int tax) {
        this.name = name;
        this.techLevel = techLevel;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.tax = tax;
        this.distance = 0;
        this.marketItems = new ArrayList<>();
        generateMarket();
    }

    /**
     * Calculates the distance between the player and a region.
     *
     * @param player The player.
     * @param region the region to compare with.
     * @return the distance between the player and the region (as a double).
     */
    protected static double calcDistance(Player player, Region region) {
        return Math.sqrt(Math.pow((region.getyCoord()
                - player.getRegion().getyCoord()), 2)
                + Math.pow((region.getxCoord()
                - player.getRegion().getxCoord()), 2));
    }

    private void generateMarket() {
        ArrayList<Item> currentTech = new ArrayList<>(techLevel.getItems());
        ArrayList<Item> formerTech = new ArrayList<>(techLevel.getFormerItem());
        marketItems.add(new Item(currentTech.remove((int) (Math.random() * currentTech.size()))));
        //This implementation relies on there being at least 10 possible items
        // generated for a region
        for (int i = 0; i < 9; ++i) {
            if (currentTech.size() == 0) {
                marketItems.add(new Item(formerTech.remove((int) (Math.random()
                        * formerTech.size()))));
            } else if (formerTech.size() == 0) {
                marketItems.add(new Item(currentTech.remove((int) (Math.random()
                        * currentTech.size()))));
            } else if (Math.random() > 0.5) {
                marketItems.add(new Item(currentTech.remove((int) (Math.random()
                        * currentTech.size()))));
            } else {
                marketItems.add(new Item(formerTech.remove((int) (Math.random()
                        * formerTech.size()))));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public ArrayList<Item> getMarketItems() {
        return marketItems;
    }

    public void setMarketItems(ArrayList<Item> marketItems) {
        this.marketItems = marketItems;
    }

    protected void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Region region) {
        this.setDistance(calcDistance(OceanTrader.player, this));
        region.setDistance(calcDistance(OceanTrader.player, region));
        return this.distance < region.distance ? -1 : 1;
    }
}