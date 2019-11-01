package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Travel {

    //Variables to help keep the code clean and easier to read.
    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;

    private static double cost = 0;
    private static StringBuilder str = new StringBuilder();

    /*
     * Basically checks to see whether or not the user has a valid selection
     * when hitting "travel". If so, then it does a bunch of checks with fuel
     * and all that to make sure the player can actually travel.
     */
    protected static void confirmTravel() {

        Object value = RegionPanel.regionList.getSelectedValue();

        if (value == null) {
            JOptionPane.showMessageDialog(window, "No region selected.");
        } else if (value.equals(player.getRegion().getName())) {
            JOptionPane.showMessageDialog(window, "You are at this region!");
        } else {
            addBaseCost();
            addDiffMultipler();
            addPilotSavings();

            int capacity = player.getShip().getFuelCapacity();
            if (cost > capacity) {
                String errorMsg = String.format("Not enough fuel!"
                                + "\nYour ship only has %d fuel."
                                + "\nTraveling to %s will cost %d fuel.",
                        capacity, Map.selected.getName(), (int) cost);
                JOptionPane.showMessageDialog(window, errorMsg);

            } else {
                int confirmPage = JOptionPane.showConfirmDialog(window,
                        str.toString(), "Price Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmPage == 0) {
                    String confirmMsg = String.format("Your ship currently has"
                                    + " %d fuel.\nTraveling to %s will use %d"
                                    + " fuel.\nConfirm Travel?",
                            capacity, Map.selected.getName(), (int) cost);
                    int yesOrNo = JOptionPane.showConfirmDialog(window,
                            confirmMsg, "Travel Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (yesOrNo == 0) {
                        //TO TEST YOUR CODE, UN-COMMENT THE LINE BELOW
                        NPCEncounter.initiateEncounter((int) cost);

                        //COMMENT THESE TWO LINES OUT
                        //updateFuel((int) cost);
                        //travel();
                    }
                }
            }
        }
    }

    /*
     * This method facilitates the travel.
     */
    protected static void travel() {

        // Variables to help keep the code clean and easier to read.
        Region region = player.getRegion();
        int listSelected = RegionPanel.regionList.getSelectedIndex();

        // Makes changes to the map, and also changes the player's region
        Map.regions.replace(region, Map.CURR_POINT_COLOR, Map.DEF_POINT_COLOR);
        player.setRegion(Universe.getInstance().regions.get(listSelected));
        String newRegion = player.getRegion().getName();
        JOptionPane.showMessageDialog(window, "Welcome to " + newRegion + "!");

        // Logistics to update the GUI list and map to properly reflect changes.
        Universe.getInstance().sortRegions();
        RegionPanel.updateRegionList();
        RegionPanel.regionList.setSelectedIndex(0);
        OceanTrader.regionDisplay.map.updateMapTitle(player.getRegion());
        OceanTrader.regionDisplay.map.repaint();
        OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
    }

    private static void addBaseCost() {
        cost = Region.calcDistance(player, Map.selected);
        str.setLength(0);
        str.append(String.format("Base Fuel Cost: %.1f Fuel\n\n", cost));
    }

    private static void addDiffMultipler() {
        double multipler = player.getDifficulty() == Difficulty.EASY ? 1.0
                : player.getDifficulty() == Difficulty.MEDIUM ? 1.5 : 2.0;
        cost *= multipler;
        str.append(String.format("Your Difficulty Multipler is %.1f%s\nNew cost"
                + " after multipler: %.1f Fuel\n\n", multipler,
                new String("x"), cost));
    }

    private static void addPilotSavings() {
        int points = player.getSkillLevel("Pilot");
        double savings = (100 - points * 3.0) / 100;
        cost *= savings;
        str.append(String.format("You have %d point(s) allocated to Seamanship."
                + "\nYour skill bonus discount is -%d%s.\n\nYour final cost is:"
                + " %.1f Fuel", points, points * 3, new String("%"), cost));
    }

    /**
     * This method updates the player's ship fuel after they have traveled.
     * @param cost The amount of fuel required to travel.
     */
    protected static void updateFuel(int cost) {
        Ship ship = player.getShip();
        ship.setFuelCapacity(ship.getFuelCapacity() - cost);
    }
}