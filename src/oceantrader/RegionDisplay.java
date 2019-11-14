package oceantrader;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class RegionDisplay {

    protected static CostDisplay costDisplay;
    protected static Color mainGUIColor = new Color(196, 196, 196);
    protected JPanel panel;
    protected JPanel leftPanel;
    protected JPanel rightPanel;
    protected JPanel centerPanel;
    protected JPanel bottomPanel;
    protected JButton travelButton;
    //GUI Screens
    protected RegionPanel regionPanel;
    protected InvMarketDisplay invMarketDisplay;
    protected ShipDisplay shipDisplay;
    protected Map map;

    protected RegionDisplay() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        regionPanel = new RegionPanel();
        invMarketDisplay = new InvMarketDisplay();
        shipDisplay = new ShipDisplay();
        costDisplay = new CostDisplay();
        map = new Map();

        leftPanel = regionPanel;
        leftPanel.setPreferredSize(new Dimension(350, 1000));
        leftPanel.setBackground(mainGUIColor);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(invMarketDisplay.panel, BorderLayout.NORTH);
        rightPanel.add(costDisplay.panel, BorderLayout.CENTER);
        rightPanel.add(ShipDisplay.panel, BorderLayout.SOUTH);
        rightPanel.setPreferredSize(new Dimension(350, 1000));
        rightPanel.setBackground(mainGUIColor);

        centerPanel = map;

        bottomPanel = new JPanel(new BorderLayout());
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