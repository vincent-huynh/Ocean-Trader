package oceantrader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OceanTrader {

    Player player;

    public static void startGame() {

        JFrame window = new JFrame();
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        TitleScreen titleScreen = new TitleScreen();
        ConfigurationScreen configScreen = new ConfigurationScreen();
        ConfirmationScreen confirmScreen = null;

        cardPanel.add(titleScreen.jpanel, "Title");
        cardPanel.add(configScreen.jpanel, "Config");
        cardPanel.add(confirmScreen.jpanel, "Confirm");

        titleScreen.titleScreenButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Config");
        });

        configScreen.configScreenButton.addActionListener(e -> {
            //grab name
            //grab skill 1
            //grab skill 2
            //grab skill 3
            //grab skill 4
            //grab difficulty

            //if valid inputs, then:
            cardLayout.show(cardPanel, "Confirm");
        });

        confirmScreen.confirmScreenButton.addActionListener(e -> {
        });

        cardLayout.show(cardPanel, "Title");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 1000);
        window.add(cardPanel);
        window.setVisible(true);
    }
}