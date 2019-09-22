package oceantrader;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Class in Charge of Displaying the Confirmation Screen
 * @author Nicholas Shi
 * @version 1.0
 */
public class ConfirmationScreen {

    protected JPanel panel;
    protected Player player;
    protected JButton button;

    /**
     * Constructor that initializes the loading windows
     * @param player The player object that is passed into the game
     */
    public ConfirmationScreen(Player player) {

        this.player = player;
        this.panel = new JPanel();
        Object[][] list = RetrieveData();

        for (int i = 0; i < list.length; i++) {
            if (list[i][0] != null) {
                panel.add(new JLabel(list[i][0] + ": " + list[i][1]));
            }
        }

        button = new JButton("Proceed");
        panel.add(button);
    }

    /**
     * Retrieves player data in order of Name, Difficulty, and Points (specific valus yet to be decided)
     * @return Returns the player setting values
     */
    public Object[][] RetrieveData() {
        //The possible fields
        Object[][] list = new Object[10][2];
        list[0][0] = "Player Name";
        list[1][0] = "Difficulty";
        list[2][0] = "Currency";
        list[3][0] = "Skill1";
        list[4][0] = "Skill2";
        list[5][0] = "Skill3";
        list[6][0] = "Skill4";

        //Retrieving values from the player
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