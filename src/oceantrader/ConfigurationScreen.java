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
        constraints.insets = new Insets(5, 10, 5, 10);
        panel.setLayout(gbPanel);
        panel.setSize(800, 600);
        screenSetup();
    }

    private static void screenSetup() {
        explorerName();
        difficultyChoice();
        skillPoints();
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

    private static void difficultyChoice() {
        JLabel difficultyLabel = new JLabel("Difficulty:");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 7;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(difficultyLabel, constraints );
        panel.add(difficultyLabel);

        String[] difficultyComboData = { "Easy", "Medium", "Hard" };
        JComboBox difficultyComboBox = new JComboBox(difficultyComboData);
        constraints.gridx = 8;
        constraints.gridy = 2;
        constraints.gridwidth = 11;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(difficultyComboBox, constraints );
        panel.add(difficultyComboBox);
    }

    private static void skillPoints() {
        skillPointsLabel();
        pilotPointsSection();
        fighterPointsSection();
        traderPointsSection();
        engineerPointsSection();
        startButton();
    }

    private static void skillPointsLabel() {
        JLabel skillPointLabel = new JLabel("Skill Points:");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 7;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(skillPointLabel, constraints );
        panel.add(skillPointLabel);
    }

    private static void pilotPointsSection() {
        JLabel pilotLabel = new JLabel("Seamanship:");
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(pilotLabel, constraints );
        panel.add(pilotLabel);

        JSpinner pilotSpinner = new JSpinner();
        constraints.gridx = 7;
        constraints.gridy = 6;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(pilotSpinner, constraints );
        panel.add(pilotSpinner);
    }

    private static void fighterPointsSection() {
        JLabel fighterLabel = new JLabel( "Battle Ability:"  );
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints( fighterLabel, constraints );
        panel.add(fighterLabel);

        JSpinner fighterSpinner = new JSpinner( );
        constraints.gridx = 7;
        constraints.gridy = 8;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(fighterSpinner, constraints);
        panel.add(fighterSpinner);
    }

    private static void traderPointsSection() {
        JLabel traderLabel = new JLabel( "Tradesmanship:"  );
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints( traderLabel, constraints );
        panel.add(traderLabel);

        JSpinner traderSpinner = new JSpinner( );
        constraints.gridx = 7;
        constraints.gridy = 10;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(traderSpinner, constraints);
        panel.add(traderSpinner);
    }

    private static void engineerPointsSection() {
        JLabel engineerLabel = new JLabel( "Workmanship:"  );
        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(engineerLabel, constraints );
        panel.add(engineerLabel);

        JSpinner engineerSpinner = new JSpinner( );
        constraints.gridx = 7;
        constraints.gridy = 12;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        gbPanel.setConstraints(engineerSpinner, constraints);
        panel.add(engineerSpinner);
    }

    private static void startButton() {
        JButton startButton = new JButton( "Embark"  );
        constraints.gridx = 7;
        constraints.gridy = 15;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.SOUTH;
        gbPanel.setConstraints(startButton, constraints);
        panel.add(startButton);
    }

    public JPanel getPanel() {
        return panel;
    }
}

