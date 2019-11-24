package oceantrader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class TitleScreen {

    //Variables to help keep the code clean and easier to read.
    private JLabel label;
    private JPanel panelBox;
    protected static Color launchScreenColor = new Color(211, 243, 255);

    protected JButton button;
    protected JPanel panelGridBag;

    /*
     * Constructor to display the title screen of the game
     */
    protected TitleScreen() {

        panelBox = new JPanel();
        panelGridBag = new JPanel(new GridBagLayout());
        label = new JLabel("Welcome to Ocean Traders!");

        label.setFont(label.getFont().deriveFont(60.0f));
        JLabel label2 = new JLabel(" ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        button = new JButton("Start");
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.PAGE_AXIS));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;

        panelBox.add(label, constraints);
        panelBox.add(label2, constraints);
        panelBox.add(button, constraints);
        panelBox.setBackground(launchScreenColor);
        panelGridBag.add(panelBox);
        panelGridBag.setBackground(launchScreenColor);
    }
}