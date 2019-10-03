package oceantrader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Universe {

    private static Universe singleInstance = null;
    protected static ArrayList<Region> regions;

    private Universe() {

        Random rand = new Random();
        regions = new ArrayList<Region>();
        HashMap<Integer, Integer> coords = new HashMap<Integer, Integer>();

        ArrayList<String> regionNames = new ArrayList<String>();
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

        TechLevel[] techLevels = {TechLevel.PREAG, TechLevel.AGRICULTURE,
            TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL,
            TechLevel.MODERN, TechLevel.FUTURISTIC};

        for (int i = 0; i < 10; ++i) {
            boolean newCoords = true;
            while (newCoords) {
                boolean stop = false;
                int x = rand.nextInt(201) * (rand.nextBoolean() ? -1 : 1);
                int y = rand.nextInt(201) * (rand.nextBoolean() ? -1 : 1);
                for (Map.Entry<Integer, Integer> entry : coords.entrySet()) {
                    if (distBetween((double) x, (double) y,
                            entry.getKey().doubleValue(),
                            entry.getValue().doubleValue()) < 5) {
                        stop = true;
                        break;
                    }
                }
                if (!stop) {
                    coords.put(new Integer(x), new Integer(y));
                    regions.add(new Region(regionNames
                            .remove(rand.nextInt(regionNames.size())),
                            techLevels[rand.nextInt(7)], x, y));
                    newCoords = false;
                }
            }
        }
    }

    protected double distBetween(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    protected static Universe getInstance() {
        if (singleInstance == null) {
            singleInstance = new Universe();
        }
        return singleInstance;
    }

    protected String[] getRegionArray() {
        String[] regionArr = new String[regions.size()];
        for (int i = 0; i < regions.size(); ++i) {
            regionArr[i] = regions.get(i).getName();
        }
        return regionArr;
    }

    protected void sortRegions() {
        Collections.sort(regions);
    }

    public String toString() {
        return regions.toString();
    }
}