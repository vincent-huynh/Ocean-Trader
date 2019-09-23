package oceantrader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ConfirmationScreen {

    protected JPanel panel;
    protected Player player;
    protected JButton button;
    protected Object[][] list;
    protected JPanel panelGridBag;

    protected ConfirmationScreen() {
        this.panel = new JPanel();
        this.panelGridBag = new JPanel(new GridBagLayout());
        this.button = new JButton();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
    }

    protected void setPlayer(Player player) {
        this.player = player;
        this.list = retrieveData();
        addPlayerInfo();
    }

    private void addPlayerInfo() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < list.length; i++) {
            if (list[i][0] != null) {
                JLabel label = new JLabel("<html>" + list[i][0] + ": "
                        + list[i][1] + "<br><br>" + "</html>");
                label.setFont(new Font("Tahoma", Font.PLAIN, 30));
                this.panel.add(label, constraints);
            }
        }
        button = new JButton("Proceed");
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
        panel.add(button);
        panelGridBag.add(panel);
    }

    private Object[][] retrieveData() {

        Object[][] list = new Object[10][2];
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

        return list;
    }
}