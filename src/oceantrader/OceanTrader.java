package oceantrader;

import javax.swing.*;
import java.awt.*;

public class OceanTrader {

    public static void startGame() {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setSize(1400, 1000); //some random arbitrary size
        window.setPreferredSize(new Dimension(1400, 1000));
        final CardLayout cardLayout = new CardLayout();
        final JPanel cardPanel = new JPanel(cardLayout);

//        cardPanel.add(YOUR JPANEL HERE) for testing
        ConfigurationScreen test = new ConfigurationScreen();
        cardPanel.add(test.getPanel());
        window.setMinimumSize(new Dimension(400, 500));

        window.add(cardPanel);
        window.setVisible(true);
    }
}