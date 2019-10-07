package oceantrader;

import javax.swing.JOptionPane;

public class Travel {

    protected static void confirmTravel() {

        if (RegionPanel.regionList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(OceanTrader.window,
                    "No region selected.");

        } else if (RegionPanel.regionList.getSelectedValue()
                .equals(OceanTrader.player.getRegion().getName())) {
            JOptionPane.showMessageDialog(OceanTrader.window,
                    "You are already at this region!");

        } else {
            travel();
        }
    }

    protected static void travel() {
        Map.regions.replace(OceanTrader.player.getRegion(),
                Map.CURRENT_POINT_COLOR, Map.DEFAULT_POINT_COLOR);
        OceanTrader.player.setRegion(Universe.regions
                .get(RegionPanel.regionList.getSelectedIndex()));
        JOptionPane.showMessageDialog(OceanTrader.window, "Welcome to "
                + OceanTrader.player.getRegion().getName() + "!");
        Universe.getInstance().sortRegions();
        RegionPanel.updateRegionList();
        RegionPanel.regionList.setSelectedIndex(0);
        RegionDisplay.map.updateMapTitle(OceanTrader.player.getRegion());
        RegionDisplay.map.repaint();
    }

    protected static int fuelCalc() {
        return 0;
    }
}