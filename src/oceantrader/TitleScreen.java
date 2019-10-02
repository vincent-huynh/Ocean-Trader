package oceantrader;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen {

    private JLabel label;
    private JPanel panelBox;

    protected JButton button;
    protected JPanel panelGridBag;

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
        panelGridBag.add(panelBox);
    }
}