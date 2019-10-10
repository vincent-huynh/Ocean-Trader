package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Travel {

    protected static void confirmTravel() {

        JFrame window = OceanTrader.window;
        Object value = RegionPanel.regionList.getSelectedValue();

        if (value == null) {
            JOptionPane.showMessageDialog(window, "No region selected.");
        } else if (value.equals(OceanTrader.player.getRegion().getName())) {
            JOptionPane.showMessageDialog(window, "You are at this region!");
        } else {
            System.out.println(fuelCost());
            travel();
        }
    }

    private static void travel() {
        Player player = OceanTrader.player;
        JFrame window = OceanTrader.window;
        Region region = OceanTrader.player.getRegion();
        int listSelected = RegionPanel.regionList.getSelectedIndex();

        Map.regions.replace(region, Map.CURR_POINT_COLOR, Map.DEF_POINT_COLOR);
        player.setRegion(Universe.regions.get(listSelected));
        String newRegion = player.getRegion().getName();
        JOptionPane.showMessageDialog(window, "Welcome to " + newRegion + "!");

        Universe.getInstance().sortRegions();
        RegionPanel.updateRegionList();
        RegionPanel.regionList.setSelectedIndex(0);
        RegionDisplay.map.updateMapTitle(OceanTrader.player.getRegion());
        RegionDisplay.map.repaint();
    }

    private static int fuelCost() {
        return (int) (Region.calcDistance(OceanTrader.player, Map.selected)
                * getDiffMultiplier() * getPilotSavings());
    }

    private static double getDiffMultiplier() {
        if (OceanTrader.player.getDifficulty() == Difficulty.EASY) {
            return 1.0;
        } else if (OceanTrader.player.getDifficulty() == Difficulty.MEDIUM) {
            return 1.5;
        }
        return 2.0;
    }

    private static double getPilotSavings() {
        return (100 - (OceanTrader.player.getSkillLevel("Pilot") * 3.0)) / 100;
    }
}