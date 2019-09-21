package oceantrader;

import sun.security.krb5.Config;

import javax.security.auth.login.Configuration;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OceanTrader {

    static Player test1;
    private static int currPoints = 0;
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

        ConfigurationScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                configPlayer();
                System.out.println(test1.toString());
            }
        });

        ConfigurationScreen.getPilotSpinner().addChangeListener(changeEvent -> {
            updateCurrPoints();
            ConfigurationScreen.pointsRemaining.setText((16 - currPoints) + " points remaining");
        });

        ConfigurationScreen.getFighterSpinner().addChangeListener(changeEvent -> {
            updateCurrPoints();
            ConfigurationScreen.pointsRemaining.setText((16 - currPoints) + " points remaining");
        });

        ConfigurationScreen.getTraderSpinner().addChangeListener(changeEvent -> {
            updateCurrPoints();
            ConfigurationScreen.pointsRemaining.setText((16 - currPoints) + " points remaining");
        });

        ConfigurationScreen.getEngineerSpinner().addChangeListener(changeEvent -> {
            updateCurrPoints();
            ConfigurationScreen.pointsRemaining.setText((16 - currPoints) + " points remaining");
        });

        window.add(cardPanel);
        window.setVisible(true);
    }

    private static void updateCurrPoints() {
        currPoints = (int) ConfigurationScreen.getPilotSpinner().getValue()
                + (int) ConfigurationScreen.getFighterSpinner().getValue()
                + (int) ConfigurationScreen.getTraderSpinner().getValue()
                + (int) ConfigurationScreen.getEngineerSpinner().getValue();
    }
    private static void configPlayer() {
        test1 = ConfigurationScreen.grabInfo();
    }
}