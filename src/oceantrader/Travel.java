package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Travel {


    //Variables to help keep the code clean and easier to read.
    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;

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
            int cost = fuelCost();
            int capacity = player.getShip().getFuelCapacity();
            if (cost > capacity) {
                String errorMsg = String.format("Not enough fuel!"
                                + "\nYour ship only has %d fuel."
                                + "\nTraveling to %s will cost %d fuel.",
                        capacity, Map.selected.getName(), cost);
                JOptionPane.showMessageDialog(window, errorMsg);
            } else {
                String confirmMsg = String.format("Your ship currently has"
                        + " %d fuel.\nTraveling to %s will use %d fuel."
                        + "\nConfirm Travel?",
                        capacity, Map.selected.getName(), cost);
                int yesOrNo = JOptionPane.showConfirmDialog(window, confirmMsg,
                        "Travel Confirmation", JOptionPane.YES_NO_OPTION);
                if (yesOrNo == 0) {
                    updateFuel(cost);
                    travel();
                }
            }
        }
    }

    /*
     * This method facilitates the travel.
     */
    private static void travel() {

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
        RegionDisplay.map.updateMapTitle(player.getRegion());
        RegionDisplay.map.repaint();
    }

    /**
     * Calculates the fuel cost to travel.
     * @return the fuel cost, as an int.
     */
    private static int fuelCost() {
        return (int) (Region.calcDistance(player, Map.selected)
                * getDiffMultiplier() * getPilotSavings());
    }

    /**
     * @return The difficulty multipler to be used in fuelCost(). Easy pays
     * x1 amount, medium pays x1.5 amount, and hard pays x2 amount.
     */
    private static double getDiffMultiplier() {
        return player.getDifficulty() == Difficulty.EASY ? 1.0
                : player.getDifficulty() == Difficulty.MEDIUM ? 1.5 : 2.0;
    }

    /**
     * For every point the player has allocated to pilot, the cost will be -3%.
     * @return The amount of fuel the player saves as a result of pilot points.
     */
    private static double getPilotSavings() {
        return (100 - (player.getSkillLevel("Pilot") * 3.0)) / 100;
    }

    /**
     * This method updates the player's ship fuel after they have traveled.
     * @param cost The amount of fuel required to travel.
     */
    private static void updateFuel(int cost) {
        Ship ship = player.getShip();
        ship.setFuelCapacity(ship.getFuelCapacity() - cost);
    }
}