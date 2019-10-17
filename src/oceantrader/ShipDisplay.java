package oceantrader;

import javax.swing.*;
import java.awt.*;

import static oceantrader.OceanTrader.player;

public class ShipDisplay {

    private static GridBagLayout gbPanel;
    private static GridBagConstraints constraints;

    protected static JPanel panel;
    protected static JLabel typeLabel;
    protected static JLabel fuelLabel;
    protected static JLabel healthLabel;
    protected static JLabel cargoLabel;
    private static Ship ship = player.getShip();

    protected ShipDisplay() {
        panel = new JPanel();
        gbPanel = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets( 5, 10, 5, 10);
        panel.setLayout(gbPanel);
        //panel.setSize();
        displaySetup();
    }

    private static void displaySetup() {
        typeLabel = new JLabel( "Ship Type: " + ship.getType());
        changeConstraints(1,1,5,1,1,1);
        panel.add(typeLabel, constraints);

        healthLabel = new JLabel("Health: " + ship.getHealth() + " / "
                + ship.getMaxHealth());
        changeConstraints(1,2,5,1,1,1);
        panel.add(healthLabel, constraints);

        fuelLabel = new JLabel("Fuel Capacity: " + ship.getFuelCapacity
                + " out of " + ship.getMaxFuelCapacity());
        changeConstraints(1,3,5,1,1,1);
        panel.add(fuelLabel, constraints);

        cargoLabel = new JLabel( "Cargo Space: " + ship.getCargoSize()
                + " / " + ship.getMaxCargoSpace());
        changeConstraints(1,4,5,1,1,1);
        panel.add(cargoLabel,constraints);

    }

    private static void changeConstraints(int x, int y, int width, int height,
                                          int weightx, int weighty) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = GridBagConstraints.NORTH;
    }

    protected void updateShipDisplay(Ship ship) {
        typeLabel.setText("Ship Type: " + ship.getType());
        healthLabel.setText("Health: " + ship.getHealth()
                + " / " + ship.getMaxHealth());
        fuelLabel.setText("Fuel Capacity: " + ship.getFuelCapacity
                + " out of " + ship.getMaxFuelCapacity());
        cargoLabel.setText("Cargo Space: " + ship.getCargoSize()
                + " / " + ship.getMaxCargoSpace());
    }

}
