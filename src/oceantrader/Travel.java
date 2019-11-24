package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Travel {

    //Variables to help keep the code clean and easier to read.
    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;

    private static double cost = 0;
    private static StringBuilder str = new StringBuilder();

    private static final boolean END_GAME_TEST = false;

    /*
     * Basically checks to see whether or not the user has a valid selection
     * when hitting "travel". If so, then it does a bunch of checks with fuel
     * and all that to make sure the player can actually travel.
     */
    protected static void confirmTravel() {

        Object value = OceanTrader.regionDisplay.regionPanel.regionList.getSelectedValue();

        if (value == null) {
            JOptionPane.showMessageDialog(window, "No region selected.");
        } else if (value.equals(player.getRegion().getName())) {
            JOptionPane.showMessageDialog(window, "You are at this region!");
        } else {
            addBaseCost();
            addDiffMultiplier();
            addPilotSavings();
            addKarmaCost();
            int capacity = player.getShip().getFuelCapacity();
            if (cost > capacity) {
                String errorMsg = String.format("Not enough fuel!"
                                + "\nYour ship only has %d fuel."
                                + "\nTraveling to %s will cost %d fuel.",
                        capacity, Map.selected.getName(), (int) cost);
                JOptionPane.showMessageDialog(window, errorMsg);
            } else {
                int confirmPage = JOptionPane.showConfirmDialog(window,
                        str.toString(), "Price Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmPage == 0) {
                    String confirmMsg = String.format("Your ship currently has"
                                    + " %d fuel.\nTraveling to %s will use %d"
                                    + " fuel.\nConfirm Travel?",
                            capacity, Map.selected.getName(), (int) cost);
                    int yesOrNo = JOptionPane.showConfirmDialog(window,
                            confirmMsg, "Travel Confirmation", JOptionPane.YES_NO_OPTION);
                    if (yesOrNo == 0) {
                        NPCEncounter.initiateEncounter((int) cost);
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
        int listSelected = OceanTrader.regionDisplay.regionPanel.regionList.getSelectedIndex();

        Universe universe = Universe.getInstance();
        if (END_GAME_TEST) {
            if (universe.endGameRegion == null) {
                universe.endGameRegion = universe.regions.get((int) (Math.random()
                    * universe.regions.size()));
                universe.endGameRegion.getMarketItems().add(universe.endGame);
                universe.endGame.setPrice(0);
                universe.endGame.setSellPrice(0);
//                System.out.println("You are testing item Universe located at "
//                        + universe.endGameRegion.getName());
            }
        } else {
            //The value 10 is used to increase the rarity of the endGame item, the higher the rarer
            //the smaller the more common
            int endGameIndex = (int) (Math.random() * (universe.regions.size() + 10));
            if (universe.endGameRegion != null) {
                universe.endGameRegion.getMarketItems().remove(universe.endGame);
            }
            if (endGameIndex < universe.regions.size()) {
                universe.endGameRegion = universe.regions.get(endGameIndex);
                universe.endGameRegion.getMarketItems().add(universe.endGame);
                //System.out.println(universe.endGameRegion.getName());
            } else {
                universe.endGameRegion = null;
                //System.out.println("Item currently not in a region");
            }
        }

        // Makes changes to the map, and also changes the player's region
        Map.regions.replace(region, Map.CURR_POINT_COLOR, Map.DEFAULT_POINT_COLOR);
        player.setRegion(Universe.getInstance().regions.get(listSelected));
        String newRegion = player.getRegion().getName();
        JOptionPane.showMessageDialog(window, "Welcome to " + newRegion + "!");

        // Logistics to update the GUI list and map to properly reflect changes.
        Universe.getInstance().sortRegions();
        OceanTrader.regionDisplay.regionPanel.update();
        OceanTrader.regionDisplay.regionPanel.updateRegionList();
        OceanTrader.regionDisplay.regionPanel.regionList.setSelectedIndex(0);
        OceanTrader.regionDisplay.map.updateMapTitle(player.getRegion());
        OceanTrader.regionDisplay.map.repaint();
        OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
        OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
        OceanTrader.regionDisplay.invMarketDisplay.updateMarket();
    }

    private static void addBaseCost() {
        cost = Region.calcDistance(player, Map.selected);
        str.setLength(0);
        str.append(String.format("Base Fuel Cost: %.1f Fuel\n\n", cost));
    }

    private static void addDiffMultiplier() {
        double multiplier = player.getDifficulty() == Difficulty.EASY ? 1.0
                : player.getDifficulty() == Difficulty.MEDIUM ? 1.5 : 2.0;
        cost *= multiplier;
        str.append(String.format("Your Difficulty Multipler is %.1f%s\nNew cost"
                        + " after multipler: %.1f Fuel\n\n", multiplier, "x", cost));
    }

    private static void addPilotSavings() {
        int points = player.getSkillLevel("Pilot");
        double savings = (100 - points * 3.0) / 100;
        cost *= savings;
        str.append(String.format("You have %d point(s) allocated to Seamanship."
                + "\nYour skill bonus discount is -%d%s.\nNew cost after discount: %.1f Fuel\n\n",
                points, points * 3, "%", cost));
    }

    private static void addKarmaCost() {
        int multiplier = 10 * player.getKarma();
        cost *= ((100.0 + multiplier) / 100.0);
        str.append(String.format("You have %d negative karma.\nYour fuel cost is increased by %d%s."
                + "\n\nYour final cost is: %.1f Fuel", player.getKarma(), multiplier, "%", cost));
    }

    /**
     * This method updates the player's ship fuel after they have traveled.
     *
     * @param cost The amount of fuel required to travel.
     */
    protected static void updateFuel(int cost) {
        Ship ship = player.getShip();
        ship.setFuelCapacity(ship.getFuelCapacity() - cost);
    }

    protected static double getCost() {
        return cost;
    }
}