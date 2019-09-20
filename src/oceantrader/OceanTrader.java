package oceantrader;

import javax.swing.*;
import java.awt.*;

public class OceanTrader {

    public static void startGame() {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 1000); //some random arbitrary size

        final CardLayout cardLayout = new CardLayout();
        final JPanel cardPanel = new JPanel(cardLayout);

//        cardPanel.add(YOUR JPANEL HERE) for testing
        ConfigurationScreen test = new ConfigurationScreen();
        cardPanel.add(test.getPanel());

        window.add(cardPanel);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        startGame();
    }
}