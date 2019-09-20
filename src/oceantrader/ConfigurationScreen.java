package oceantrader;

import javax.swing.*;
import java.awt.*;


public class ConfigurationScreen {
    static JPanel panel;
    static GridBagLayout gbPanel;
    static GridBagConstraints constraints;
    public ConfigurationScreen() {
        panel = new JPanel();
        gbPanel = new GridBagLayout();
        constraints = new GridBagConstraints();
        screenSetup();
    }

    private static void screenSetup() {
        explorerName();
    }

    private static void explorerName() {
        JLabel nameLabel = new JLabel("Explorer Name:");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 9;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(nameLabel, constraints );
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        constraints.gridx = 10;
        constraints.gridy = 1;
        constraints.gridwidth = 9;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(nameField, constraints );
        panel.add(nameField);
    }

    public JPanel getPanel() {
        return panel;
    }
}

