package oceantrader;

import java.util.ArrayList;

public class Region implements Comparable<Region> {

    private String name;
    private int tax;
    private TechLevel techLevel;
    private ArrayList<Item> marketItems;

    private int xCoord;
    private int yCoord;
    private double distance;

    public Region(String name, TechLevel techLevel, int xCoord, int yCoord,
                  int tax) {

        this.name = name;
        this.techLevel = techLevel;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.tax = tax;
        this.distance = 0;
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getTax() {
        return tax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public void setTax() {
        this.tax = tax;
    }

    public ArrayList getMarketItems() {
        return marketItems;
    }

    public void setMarketItems(ArrayList marketItems) {
        this.marketItems = marketItems;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Calculates the distance between the player and a region.
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

    @Override
    public int compareTo(Region region) {
        this.setDistance(calcDistance(OceanTrader.player, this));
        region.setDistance(calcDistance(OceanTrader.player, region));
        return this.distance < region.distance ? -1 : 1;
    }
}