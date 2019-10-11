package oceantrader;

import sun.security.krb5.Config;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Random;

public class OceanTrader {

    protected static Player player;
    protected static JFrame window;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static HashMap<String, Integer> diffMap;
    private static int currPoints = 0;
    private static Random rand = new Random();

    protected static void startGame() {

        window = new JFrame();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

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

            int pilot = ((Integer) configScreen.pilotSpinner.getValue());
            int fighter = ((Integer) configScreen.fighterSpinner.getValue());
            int trader = ((Integer) configScreen.traderSpinner.getValue());
            int engineer = ((Integer) configScreen.engineerSpinner.getValue());

            int totalSkill = pilot + fighter + trader + engineer;

            if (name.isEmpty() || diff == null) {
                JOptionPane.showMessageDialog(window, "Please enter name.");
            } else if (totalSkill != (diffMap.get((String) diff)).intValue()) {
                JOptionPane.showMessageDialog(window,
                        "Incorrect point allocation.\nExpected: "
                                + diffMap.get((String) diff).toString()
                                + "\nReceived: " + totalSkill);
            } else {
                Difficulty choice = diff.equals("Easy") ? Difficulty.EASY
                                  : diff.equals("Hard") ? Difficulty.HARD
                                                        : Difficulty.MEDIUM;
                player = new Player(name, pilot, fighter, trader, engineer,
                                                                    choice);
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
            player.setRegion(Universe.regions.get(rand.nextInt(10)));
            Universe.getInstance().sortRegions();
            RegionPanel.updateRegionList();
            RegionDisplay.map.updateMapTitle(player.getRegion());
            cardLayout.show(cardPanel, "Main");
        });

        regionDisplay.travelButton.addActionListener(e -> {
            Travel.confirmTravel();
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1400, 1000));
        window.add(cardPanel);
        window.setResizable(false);
        window.setVisible(true);
    }

    private static void updateCurrPoints(Object diff) {
        int pointsDiff;
        JTextField pointsRemain = ConfigurationScreen.pointsRemaining;
        pointsDiff = diff.equals("Easy") ? 16 : diff.equals("Hard") ? 8 : 12;
        currPoints = (int) ConfigurationScreen.pilotSpinner.getValue()
                   + (int) ConfigurationScreen.fighterSpinner.getValue()
                   + (int) ConfigurationScreen.traderSpinner.getValue()
                   + (int) ConfigurationScreen.engineerSpinner.getValue();
        pointsRemain.setText((pointsDiff - currPoints) + " points remaining.");
    }
}