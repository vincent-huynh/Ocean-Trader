import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Class in Charge of Displaying the Confirmation Screen
 * @author Nicholas Shi
 * @version 1.0
 */
public class ConfirmationScreen {
    /**
     * Main method for debugging logic, remove/alter as needed
     * @param args The input given if ran from console
     */
    public static void main (String[] args) {
        LoadConfirmation(null);
        //TestRetriever();
    }

    
    /**
     * Tester method for retriving player data
     */
    public static void TestRetriever() {
        Object[][] list = RetrieveData();
        for (int i = 0; i <list.length; i++) {
            System.out.print(list[i][0] + " ");
            System.out.println(list[i][1]);
        }
    }
    

    /**
     * Primary method for loading the confirmation UI
     * @param frame The parent frame that the confirmation dialog is loading from
     */
    public static void LoadConfirmation(JFrame frame) {
        //Populates the list with setting values
        Object[][] list = RetrieveData();

        //Creates the confirmation message to be displayed
        String message = "";
        for (int i = 0; i < list.length; i++) {
            if (list[i][0] != null) {
                message += list[i][0] + ": " + list[i][1] + "\n";
            }
        }

        //"frame" represents the parent frame from Screen 2
        int confirmation = JOptionPane.showConfirmDialog(frame, message, "Confirmation Screen", JOptionPane.DEFAULT_OPTION);

        //Modify logic below to determine event if not confirmed
        if (confirmation == 0) {
            //confirm pressed
        } else {
            //confirm not pressed
        }
    }


    /**
     * Retrieves player data in order of Name, Difficulty, and Points (specific valus yet to be decided)
     * @return Returns the player setting values
     */
    public static Object[][] RetrieveData() {
        //Puts all of settings selected into the confirmation window, still needs to add the getters and setters

        //The values below are created only for testing purposes
        Object[][] list = new Object[10][2];
        list[0][0] = "PlayerName";
        list[0][1] = "Harry";
        list[1][0] = "Difficulty";
        list[1][1] = "Hard";
        list[2][0] = "Points Allocated";
        list[2][1] = 10;
        return list;
    }
}