package oceantrader;

import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class TitleScreen {

//    protected JFrame frame;
    protected JPanel panel;
    protected JLabel label;
    protected JButton button;

    public TitleScreen() {

//        frame = new JFrame("Ocean Traders");
        panel = new JPanel(new GridBagLayout());
        label = new JLabel("Welcome to Ocean Traders!");

        label.setFont(label.getFont().deriveFont(60.0f));
        JLabel label2 = new JLabel(" ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        button = new JButton("Start");
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

//        frame.setLayout(new GridBagLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;

        panel.add(label, constraints);
        panel.add(label2, constraints);
        panel.add(button, constraints);

//        frame.add(panel);
//        frame.setSize(1400, 1000);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(false);
//        frame.setResizable(false);
    }
}