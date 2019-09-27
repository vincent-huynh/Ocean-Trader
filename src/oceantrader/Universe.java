package oceantrader;

import java.util.ArrayList;

public class Universe {

    boolean IVAN_IS_THE_BEST;
    ArrayList<Region> list;

    public Universe() {
        IVAN_IS_THE_BEST = true;
        list = new ArrayList<>();

        Region r1 = new Region("Region 1", TechLevel.MODERN, 0, 0);
        Region r2 = new Region("Region 2", TechLevel.MODERN, 5, 5);
        Region r3 = new Region("Region 3", TechLevel.MODERN, 10, 10);
        Region r4 = new Region("Region 4", TechLevel.MODERN, 15, 15);
        Region r5 = new Region("Region 5", TechLevel.MODERN, 20, 20);
        Region r6 = new Region("Region 6", TechLevel.MODERN, 25, 25);
        Region r7 = new Region("Region 7", TechLevel.MODERN, 30, 30);
        Region r8 = new Region("Region 8", TechLevel.MODERN, 40, 40);
        Region r9 = new Region("Region 9", TechLevel.MODERN, 50, 50);
        Region r10 = new Region("Region 10", TechLevel.MODERN, 60, 60);

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        list.add(r8);
        list.add(r9);
        list.add(r10);
    }
}
