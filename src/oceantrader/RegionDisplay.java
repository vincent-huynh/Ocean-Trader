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

    protected RegionPanel regionPanel;
    protected RegionDisplay() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        regionPanel = new RegionPanel();


        leftPanel = regionPanel.panel;
        leftPanel.setPreferredSize(new Dimension(350, 1000));

        //leftPanel.setBackground(Color.orange);

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(350, 1000));
        rightPanel.setBackground(Color.orange);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.gray);

        bottomPanel = new JPanel();
        travelButton = new JButton("Travel");
        travelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setPreferredSize(new Dimension(50, 50));
        bottomPanel.add(travelButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(leftPanel, BorderLayout.LINE_START);
        panel.add(rightPanel, BorderLayout.LINE_END);
        panel.add(bottomPanel, BorderLayout.PAGE_END);
    }
}