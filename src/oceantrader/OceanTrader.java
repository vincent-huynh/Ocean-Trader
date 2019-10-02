package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;

public class OceanTrader {

    private static Player player;
    private static JFrame window;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static HashMap<String, Integer> diffMap;
    private static int currPoints = 0;
    protected static Universe universe;

    protected static void startGame() {

        window = new JFrame();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        universe = new Universe();
        TitleScreen titleScreen = new TitleScreen();
        ConfigurationScreen configScreen = new ConfigurationScreen();
        ConfirmationScreen confirmScreen = new ConfirmationScreen();
        RegionDisplay regionDisplay = new RegionDisplay();

        cardPanel.add(titleScreen.panelGridBag, "Title");
        cardPanel.add(configScreen.panel, "Config");
        cardPanel.add(regionDisplay.panel, "Main");

        diffMap = new HashMap<>(3);
        diffMap.put("Easy", 16);
        diffMap.put("Medium", 12);
        diffMap.put("Hard", 8);

        titleScreen.button.addActionListener(e -> {

            window.setMinimumSize(new Dimension(400, 500));
            window.setMaximumSize(new Dimension(400, 500));
            window.setPreferredSize(new Dimension(400, 500));
            window.setSize(400, 500);
            cardLayout.show(cardPanel, "Config");
        });

        configScreen.startButton.addActionListener(e -> {

            String name = configScreen.nameField.getText().trim();
            Object diff = configScreen.difficultyComboBox.getSelectedItem();

            int pilotPoints = ((Integer) configScreen.pilotSpinner
                    .getValue()).intValue();
            int fighterPoints = ((Integer) configScreen.fighterSpinner
                    .getValue()).intValue();
            int traderPoints = ((Integer) configScreen.traderSpinner
                    .getValue()).intValue();
            int engineerPoints = ((Integer) configScreen.engineerSpinner
                    .getValue()).intValue();

            int totalSkill = pilotPoints + fighterPoints + traderPoints
                    + engineerPoints;

            if (name.equals("") || diff == null) {
                JOptionPane.showMessageDialog(window,
                        "Please enter player info.");
            } else if (totalSkill != (diffMap.get((String) diff)).intValue()) {
                JOptionPane.showMessageDialog(window,
                        "Incorrect point allocation.\nExpected: "
                                + diffMap.get((String) diff).toString()
                                + "\nReceived: " + totalSkill);
            } else {
                Difficulty choice;
                if (diff.equals("Easy")) {
                    choice = Difficulty.EASY;
                } else if (diff.equals("Hard")) {
                    choice = Difficulty.HARD;
                } else {
                    choice = Difficulty.MEDIUM;
                }
                player = new Player(name, pilotPoints, fighterPoints,
                                    traderPoints, engineerPoints, choice);
                confirmScreen.setPlayer(player);
                cardPanel.add(confirmScreen.panelGridBag, "Confirm");
                window.setSize(new Dimension(1400, 1000));
                cardLayout.show(cardPanel, "Confirm");
            }
        });

        configScreen.difficultyComboBox.addActionListener(changeEvent -> {
            updateCurrPoints(configScreen.difficultyComboBox.getSelectedItem());
        });

        configScreen.pilotSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints(configScreen.difficultyComboBox.getSelectedItem());

        });

        configScreen.fighterSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints(configScreen.difficultyComboBox.getSelectedItem());
        });

        configScreen.traderSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints(configScreen.difficultyComboBox.getSelectedItem());
        });

        configScreen.engineerSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints(configScreen.difficultyComboBox.getSelectedItem());
        });

        confirmScreen.button.addActionListener(e -> {
            cardLayout.show(cardPanel, "Main");
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1400, 1000));
        window.add(cardPanel);
        window.setResizable(false);
        window.setVisible(true);
    }

    private static void updateCurrPoints(Object diff) {
        int pointsDiff;
        if (((String) diff).equals("Easy")) {
            pointsDiff = 16;
        } else if (((String) diff).equals("Hard")) {
            pointsDiff = 8;
        } else {
            pointsDiff = 12;
        }
        currPoints = (int) ConfigurationScreen.pilotSpinner.getValue()
                + (int) ConfigurationScreen.fighterSpinner.getValue()
                + (int) ConfigurationScreen.traderSpinner.getValue()
                + (int) ConfigurationScreen.engineerSpinner.getValue();
        ConfigurationScreen.pointsRemaining.setText((pointsDiff - currPoints)
                + " points remaining.");
    }
}