package oceantrader;

import javax.swing.*;
import java.awt.*;

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

        cardLayout.show(cardPanel, "Title");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 1000);
        window.add(cardPanel);
        window.setVisible(true);
    }
}