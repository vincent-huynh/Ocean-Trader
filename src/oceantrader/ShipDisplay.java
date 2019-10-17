package oceantrader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ShipDisplay {

    private static GridBagLayout gbPanel;
    private static GridBagConstraints constraints;

    protected static JPanel panel;
    private static JLabel typeLabel;
    private static JLabel fuelLabel;
    private static JLabel healthLabel;
    private static JLabel cargoLabel;
    private static Ship ship;

    protected ShipDisplay() {
        panel = new JPanel();
        gbPanel = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 10, 2, 10);
        panel.setLayout(gbPanel);
        displaySetup();
    }

    private static void displaySetup() {

        typeLabel = new JLabel("Ship Type: ");
        changeConstraints(1, 1, 5, 1, 1, 1);
        jLabelEdit(typeLabel);
        panel.add(typeLabel, constraints);

        healthLabel = new JLabel("Health: ");
        changeConstraints(1, 2, 5, 1, 1, 1);
        jLabelEdit(healthLabel);
        panel.add(healthLabel, constraints);

        fuelLabel = new JLabel("Fuel Capacity: ");
        changeConstraints(1, 3, 5, 1, 1, 1);
        jLabelEdit(fuelLabel);
        panel.add(fuelLabel, constraints);

        cargoLabel = new JLabel("Cargo Space: ");
        changeConstraints(1, 4, 5, 1, 1, 1);
        jLabelEdit(cargoLabel);
        panel.add(cargoLabel, constraints);

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

    protected void updateShipDisplay(Player player) {
        ship = player.getShip();
        typeLabel.setText("Ship Type: " + ship.getType());
        healthLabel.setText("Health: " + ship.getHealth()
                + " / " + ship.getMaxHealth());
        fuelLabel.setText("Fuel Capacity: " + ship.getFuelCapacity()
                + " out of " + ship.getMaxFuelCapacity());
        cargoLabel.setText("Cargo Space: " + ship.getCargoSize()
                + " / " + ship.getMaxCargoSpace());
    }

    private static void jLabelEdit(JLabel jLabel) {
        jLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }
}