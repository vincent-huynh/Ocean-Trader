package oceantrader;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ConfirmationScreen {

    protected JPanel panel;
    protected Player player;
    protected JButton button;
    protected Object[][] list;

    protected ConfirmationScreen() {
        this.panel = new JPanel();
        this.button = new JButton();
    }

    protected void setPlayer(Player player) {
        this.player = player;
        this.list = retrieveData();
        addPlayerInfo();
    }

    private void addPlayerInfo() {
        for (int i = 0; i < list.length; i++) {
            if (list[i][0] != null) {
                panel.add(new JLabel(list[i][0] + ": " + list[i][1]));
            }
        }
        button = new JButton("Proceed");
        panel.add(button);
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