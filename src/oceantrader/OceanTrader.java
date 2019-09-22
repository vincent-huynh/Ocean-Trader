package oceantrader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class OceanTrader {

    private static Player player;
    private static JFrame window;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static int currPoints = 0;

    protected static void startGame() {

        window = new JFrame();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        TitleScreen titleScreen = new TitleScreen();
        ConfigurationScreen configScreen = new ConfigurationScreen();
        ConfirmationScreen confirmScreen = new ConfirmationScreen();

        cardPanel.add(titleScreen.panelGridBag, "Title");
        cardPanel.add(configScreen.panel, "Config");

        HashMap<String, Integer> map = new HashMap<>(3);
        map.put("Easy", 16);
        map.put("Medium", 12);
        map.put("Hard", 8);

        titleScreen.button.addActionListener(e -> {
            window.setMinimumSize(new Dimension(400, 500));
            window.setMaximumSize(new Dimension(400, 500));
            cardLayout.show(cardPanel, "Config");
        });

        configScreen.startButton.addActionListener(e -> {
            String name = configScreen.nameField.getText().trim();
            Object difficulty = configScreen.difficultyComboBox.getSelectedItem();
            int pilotPoints = ((Integer) configScreen.pilotSpinner.getValue()).intValue();
            int fighterPoints = ((Integer) configScreen.fighterSpinner.getValue()).intValue();
            int traderPoints = ((Integer) configScreen.traderSpinner.getValue()).intValue();
            int engineerPoints = ((Integer) configScreen.engineerSpinner.getValue()).intValue();
            int totalSkill = pilotPoints + fighterPoints + traderPoints + engineerPoints;

            if (name.equals("") || difficulty == null) {
                JOptionPane.showMessageDialog(window, "Please enter player info.");
            } else if (totalSkill != (map.get((String)difficulty)).intValue()) {
                JOptionPane.showMessageDialog(window, "Incorrect point allocation.\nExpected: " + map.get((String)difficulty).toString() + "\nReceived: " + totalSkill);
            } else {
                Difficulty choice;
                if (difficulty.equals("Easy")) {
                    choice = Difficulty.EASY;
                } else if (difficulty.equals("Hard")) {
                    choice = Difficulty.HARD;
                } else {
                    choice = Difficulty.MEDIUM;
                }
                player = new Player(name, pilotPoints, fighterPoints, traderPoints, engineerPoints, choice);
                confirmScreen.setPlayer(player);
                cardPanel.add(confirmScreen.panel, "Confirm");
                window.setMinimumSize(new Dimension(1400, 1000));
                cardLayout.show(cardPanel, "Confirm");
            }
        });

        configScreen.pilotSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints();
        });

        configScreen.fighterSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints();
        });

        configScreen.traderSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints();
        });

        configScreen.engineerSpinner.addChangeListener(changeEvent -> {
            updateCurrPoints();
        });

        confirmScreen.button.addActionListener(e2 -> {
            //TO BE IMPLEMENTED LATER
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1400, 1000));
        window.add(cardPanel);
        window.setResizable(false);
        window.setVisible(true);
    }

    private static void updateCurrPoints() {
        currPoints = (int) ConfigurationScreen.pilotSpinner.getValue()
                + (int) ConfigurationScreen.fighterSpinner.getValue()
                + (int) ConfigurationScreen.traderSpinner.getValue()
                + (int) ConfigurationScreen.engineerSpinner.getValue();
        ConfigurationScreen.pointsRemaining.setText((16 - currPoints) + " points remaining");
    }
}