package oceantrader;

import javax.swing.JOptionPane;

public class Travel {

    protected static void confirmTravel() {
        if (RegionPanel.regionList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(OceanTrader.window, "No region selected.");
        } else if (RegionPanel.regionList.getSelectedValue().equals(OceanTrader.player.getRegion().getName())) {
            JOptionPane.showMessageDialog(OceanTrader.window, "You are already at this region!");
        } else {
            OceanTrader.player.setRegion(Universe.regions.get(RegionPanel.regionList.getSelectedIndex()));
            JOptionPane.showMessageDialog(OceanTrader.window, "Welcome to " + OceanTrader.player.getRegion().getName() + "!");
            Universe.getInstance().sortRegions();
            RegionPanel.updateRegionList();
        }
    }
}