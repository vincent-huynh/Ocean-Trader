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
            int skill1 = ((Integer) configScreen.spinner1.getValue()).intValue();
            int skill2 = ((Integer) configScreen.spinner2.getValue()).intValue();
            int skill3 = ((Integer) configScreen.spinner3.getValue()).intValue();
            int skill4 = ((Integer) configScreen.spinner4.getValue()).intValue();
            int totalSkill = skill1 + skill2 + skill3 + skill4;

            if (name.equals("") || difficulty == null) {
                JOptionPane.showMessageDialog(frame, "Please enter player info.");
            } else if (totalSkill != (map.get((String)difficulty)).intValue()) {
                JOptionPane.showMessageDialog(frame, "Incorrect point allocation.");
            } else {
                player = new Player(name, skill1, skill2, skill3, skill4, (String)difficulty);
                final ConfirmScreen confirmScreen = new ConfirmScreen(player);
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
        window.setVisible(true);
    }
}