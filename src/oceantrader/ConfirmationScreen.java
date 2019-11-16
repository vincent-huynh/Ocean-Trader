package oceantrader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ConfirmationScreen {

    protected JPanel panel;
    protected JButton button;
    protected JPanel panelGridBag;
    protected Object[][] list = new Object[10][2];

    protected ConfirmationScreen() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(TitleScreen.launchScreenColor);
        panelGridBag = new JPanel(new GridBagLayout());
        panelGridBag.setBackground(TitleScreen.launchScreenColor);
        button = new JButton("Proceed");
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
    }

    protected void setPlayer(Player player) {
        retrieveData(player);
        addPlayerInfo();
    }

    private void addPlayerInfo() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        for (Object[] objects : list) {
            if (objects[0] != null) {
                javax.swing.JLabel label = new javax.swing.JLabel("<html>" + objects[0] + ": "
                        + objects[1] + "<br><br>" + "</html>");
                label.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 30));
                this.panel.add(label, constraints);
            }
        }
        panel.add(button);
        panelGridBag.add(panel);
    }

    private void retrieveData(Player player) {
        list[0][0] = "Player Name";
        list[1][0] = "Difficulty";
        list[2][0] = "Currency";
        list[3][0] = "Seamanship";
        list[4][0] = "Battle Ability";
        list[5][0] = "Tradesmanship";
        list[6][0] = "Workmanship";

        list[0][1] = player.getName();
        list[1][1] = player.getDifficulty();
        list[2][1] = player.getCurrency();
        list[3][1] = player.getSkillLevel("Pilot");
        list[4][1] = player.getSkillLevel("Fighter");
        list[5][1] = player.getSkillLevel("Trader");
        list[6][1] = player.getSkillLevel("Engineer");
    }
}