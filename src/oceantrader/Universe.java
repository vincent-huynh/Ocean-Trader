package oceantrader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Universe {

    private static Universe singleInstance = null;
    protected ArrayList<Region> regions;
    protected Item endGame;
    protected Region endGameRegion;

    private Universe() {

        Random rand = new Random();
        regions = new ArrayList<>();
        endGame = new Item("Infinity Gauntlet", 12000, "Endgame");
        HashMap<Integer, Integer> coords = new HashMap<>();
        /*
        This array list holds all the region names.
        For use in region generation below.
         */
        ArrayList<String> regionNames = new ArrayList<>();
        regionNames.add("Crystal Cove");
        regionNames.add("Sandy Shores");
        regionNames.add("Skull Island");
        regionNames.add("Buccaneer Bay");
        regionNames.add("Pirate’s Peak");
        regionNames.add("Boomer’s Cannon");
        regionNames.add("Caribbean Coast");
        regionNames.add("Shipwreck Harbor");
        regionNames.add("Plunder's Paradise");
        regionNames.add("Mermaid Mediterranean");

        /*
        This array holds all the tech level enums.
        For use in region generation below.
         */
        TechLevel[] techLevels = {TechLevel.PREAG, TechLevel.AGRICULTURE,
            TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL,
            TechLevel.MODERN, TechLevel.FUTURISTIC};

        /*
        When the Universe object is first created, this for-loop will create
        10 distinct regions with random coordinates that are no closer than
         5 units in any direction.
         */
        for (int i = 0; i < 10; ++i) {
            boolean newCoords = true;
            while (newCoords) {
                boolean stop = false;
                int x = rand.nextInt(201) * (rand.nextBoolean() ? -1 : 1);
                int y = rand.nextInt(201) * (rand.nextBoolean() ? -1 : 1);
                for (Map.Entry<Integer, Integer> entry : coords.entrySet()) {
                    if (distBetween((double) x, (double) y, entry.getKey().doubleValue(),
                            entry.getValue().doubleValue()) < 5) {
                        stop = true;
                        break;
                    }
                }
                if (!stop) {
                    coords.put(x, y);
                    regions.add(new Region(regionNames.remove(rand.nextInt(regionNames.size())),
                            techLevels[rand.nextInt(7)], x, y,
                            (int) Math.round(rand.nextDouble() * 7)));
                    newCoords = false;
                }
            }
        }
        /*
        After this for loop finishes, 10 regions will have been generated
        and added to the regions variable above.
         */

        /*
         * This portion of the code is is set to generate
         */
    }

    /**
     * Universe is a singleton class. This method ensures that only one
     * Universe object exists.
     *
     * @return The one and only Universe object.
     */
    protected static Universe getInstance() {
        if (singleInstance == null) {
            singleInstance = new Universe();
        }
        return singleInstance;
    }

    /**
     * Basically this is the distance formula.
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return The distance between two coordinates.
     */
    protected double distBetween(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * @return An array holding all the region names.
     */
    protected String[] getRegionArray() {
        String[] regionArr = new String[regions.size()];
        for (int i = 0; i < regions.size(); ++i) {
            regionArr[i] = regions.get(i).getName();
        }
        return regionArr;
    }

    /*
     * Sorts all the regions.
     */
    protected void sortRegions() {
        Collections.sort(regions);
    }

    public String toString() {
        return regions.toString();
    }
}