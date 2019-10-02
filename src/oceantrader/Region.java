package oceantrader;

public class Region {

    private String name;
    private TechLevel techLevel;
    private int xCoord;
    private int yCoord;

    public Region(String name, TechLevel techLevel, int xCoord, int yCoord) {

        this.name = name;
        this.techLevel = techLevel;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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
}