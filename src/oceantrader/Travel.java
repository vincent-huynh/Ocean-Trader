package oceantrader;

import javax.swing.JOptionPane;
import java.awt.Color;

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
            updateCurrentDot(Map.CURRENT_POINT_COLOR, Map.DEFAULT_POINT_COLOR);
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
    }

    private static void updateCurrentDot(Color oldVal, Color newVal) {
        Map.regions.replace(OceanTrader.player.getRegion(), oldVal, newVal);
    }
}