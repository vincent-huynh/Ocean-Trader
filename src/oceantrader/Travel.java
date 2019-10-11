package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Travel {

    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;

    protected static void confirmTravel() {

        Object value = RegionPanel.regionList.getSelectedValue();

        if (value == null) {
            JOptionPane.showMessageDialog(window, "No region selected.");
        } else if (value.equals(OceanTrader.player.getRegion().getName())) {
            JOptionPane.showMessageDialog(window, "You are at this region!");
        } else {
            travel();
        }
    }

    private static void travel() {

        Region region = OceanTrader.player.getRegion();
        int listSelected = RegionPanel.regionList.getSelectedIndex();

        Map.regions.replace(region, Map.CURR_POINT_COLOR, Map.DEF_POINT_COLOR);
        player.setRegion(Universe.regions.get(listSelected));
        String newRegion = player.getRegion().getName();
        JOptionPane.showMessageDialog(window, "Welcome to " + newRegion + "!");

        Universe.getInstance().sortRegions();
        RegionPanel.updateRegionList();
        RegionPanel.regionList.setSelectedIndex(0);
        RegionDisplay.map.updateMapTitle(player.getRegion());
        RegionDisplay.map.repaint();
    }

    private static int fuelCost() {
        return (int) (Region.calcDistance(player, Map.selected)
                * getDiffMultiplier() * getPilotSavings());
    }

    private static double getDiffMultiplier() {
        if (player.getDifficulty() == Difficulty.EASY) {
            return 1.0;
        } else if (player.getDifficulty() == Difficulty.MEDIUM) {
            return 1.5;
        }
        return 2.0;
    }

    private static double getPilotSavings() {
        return (100 - (player.getSkillLevel("Pilot") * 3.0)) / 100;
    }
}