package oceantrader;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class RegionDisplay {

    protected JPanel panel;
    protected JPanel leftPanel;
    protected JPanel rightPanel;
    protected JPanel centerPanel;
    protected JPanel bottomPanel;

    protected JButton travelButton;

    //GUI Screens
    protected RegionPanel regionPanel;
    protected InvMarketDisplay invMarketDisplay;
    protected Map map;

    protected RegionDisplay() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        regionPanel = new RegionPanel();
        invMarketDisplay = new InvMarketDisplay();
        map = new Map();

        Color panelColor = new Color(196, 196, 196);

        leftPanel = regionPanel.panel;
        leftPanel.setPreferredSize(new Dimension(350, 1000));
        leftPanel.setBackground(panelColor);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(invMarketDisplay.panel, BorderLayout.NORTH);
        rightPanel.setPreferredSize(new Dimension(350, 1000));
        rightPanel.setBackground(panelColor);

        centerPanel = map;

        bottomPanel = new JPanel();
        travelButton = new JButton("Travel");
        travelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        bottomPanel.setPreferredSize(new Dimension(50, 60));
        bottomPanel.add(travelButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(leftPanel, BorderLayout.LINE_START);
        panel.add(rightPanel, BorderLayout.LINE_END);
        panel.add(bottomPanel, BorderLayout.PAGE_END);
    }
}