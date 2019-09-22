import java.awt.*;
import javax.swing.*;

public class TitleScreen {
    public static void main(String[] args) {
        JFrame f = new JFrame("Ocean Traders");
        JPanel p = new JPanel(new GridBagLayout());
        JLabel l = new JLabel("Welcome to Ocean Traders!");
        l.setFont(l.getFont().deriveFont(60.0f));
        JLabel l2 = new JLabel(" ");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b = new JButton("Start");
        b.setFont(new Font("Tahoma", Font.PLAIN, 30));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        f.setLayout(new GridBagLayout());
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        p.add(l, constraints);
        p.add(l2, constraints);
        p.add(b, constraints);
        f.add(p);
        f.setSize(1400, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }
}