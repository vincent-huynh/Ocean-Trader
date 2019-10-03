package oceantrader;

public class Region implements Comparable<Region> {

    private String name;
    private TechLevel techLevel;

    private int xCoord;
    private int yCoord;
    private double distance;

    public Region(String name, TechLevel techLevel, int xCoord, int yCoord) {

        this.name = name;
        this.techLevel = techLevel;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
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

    protected double calcDistance(Player player, Region region) {
        return Math.sqrt(Math.pow((region.getyCoord()
                - player.getRegion().getyCoord()), 2)
                + Math.pow((region.getxCoord()
                - player.getRegion().getxCoord()), 2));
    }

    @Override
    public int compareTo(Region region) {
        this.setDistance(calcDistance(OceanTrader.player, this));
        region.setDistance(calcDistance(OceanTrader.player, region));

        if (this.distance < region.distance) {
            return -1;
        } else if (this.distance > region.distance) {
            return 1;
        }
        return -1;
    }
}