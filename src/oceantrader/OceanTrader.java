package oceantrader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OceanTrader {

    static Player player;
    static JFrame window;
    static CardLayout cardLayout;
    static JPanel cardPanel;

    public static void startGame() {

        window = new JFrame();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        TitleScreen titleScreen = new TitleScreen();
        ConfigurationScreen configScreen = new ConfigurationScreen();
        final ConfirmScreen confirmScreen;

        cardPanel.add(titleScreen.jpanel, "Title");
        cardPanel.add(configScreen.jpanel, "Config");

        HashMap<String, Integer> map = new HashMap<>(3);
        map.put("Easy", 16);
        map.put("Medium", 12);
        map.put("Hard", 8);

        titleScreen.titleScreenButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Config");
        });

        configScreen.configScreenButton.addActionListener(e -> {
            String name = titleScreen.nameText.getText().trim();
            Object difficulty = configScreen.comboBox.getSelectedItem();
            int pilotPoints = ((Integer) configScreen.spinner1.getValue()).intValue();
            int fighterPoints = ((Integer) configScreen.spinner2.getValue()).intValue();
            int traderPoints = ((Integer) configScreen.spinner3.getValue()).intValue();
            int engineerPoints = ((Integer) configScreen.spinner4.getValue()).intValue();
            int totalSkill = pilotPoints + fighterPoints + traderPoints + engineerPoints;

            if (name.equals("") || difficulty == null) {
                JOptionPane.showMessageDialog(frame, "Please enter player info.");
            } else if (totalSkill != (map.get((String)difficulty)).intValue()) {
                JOptionPane.showMessageDialog(frame, "Incorrect point allocation.\nExpected: " + map.get((String)difficulty).toString() + "\nReceived: " + totalSkill);
            } else {
                player = new Player(name, pilotPoints, fighterPoints, traderPoints, engineerPoints, (String)difficulty);
                confirmScreen = new ConfirmScreen(player);
                cardPanel.add(confirmScreen.jpanel, "Confirm");
                cardLayout.show(cardPanel, "Confirm");
            }
        });

        confirmScreen.confirmScreenButton.addActionListener(e -> {
            //To be implemented later.
        });

        cardLayout.show(cardPanel, "Title");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 1000);
        window.add(cardPanel);
        window.setResizable(false);
        window.setVisible(true);
    }
}