package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Random;

public class OceanTrader {

    //Important variables that facilitate the entire GUI.
    protected static Player player;
    protected static JFrame window;
    //GUI Screens
    protected static TitleScreen titleScreen;
    protected static ConfigurationScreen configScreen;
    protected static ConfirmationScreen confirmScreen;
    protected static RegionDisplay regionDisplay;
    protected static EncounterFrame encounterFrame;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    //Random variables that we need.
    private static int currPoints = 0;
    private static Random rand = new Random();
    private static HashMap<String, Integer> diffMap;

    /*
    The first method that is called when the game first begins.
     */
    protected static void startGame() {
        // Initializes all the important GUI variables.
        window = new JFrame("Ocean Trader");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        encounterFrame = new EncounterFrame();

        // Creates instances of all the beautiful GUIs you guys made.
        titleScreen = new TitleScreen();
        configScreen = new ConfigurationScreen();
        confirmScreen = new ConfirmationScreen();
        regionDisplay = new RegionDisplay();

        /*
        Adds all your GUIs into a card panel, this allows me to switch
        between the panels easily.
         */
        cardPanel.add(titleScreen.panelGridBag, "Title");
        cardPanel.add(ConfigurationScreen.panel, "Config");
        cardPanel.add(regionDisplay.panel, "Main");

        //Hashmap to faciliate with logic because O(1) is gr8.
        diffMap = new HashMap<>(3);
        diffMap.put("Easy", 16);
        diffMap.put("Medium", 12);
        diffMap.put("Hard", 8);

        /*
        When the first button is clicked on the title screen that Rashmi made,
        we adjust the GUI size to better fit Vincent's page.
        The cardLayout.show() is what transitions the GUIs.
         */
        titleScreen.button.addActionListener(e -> {
            window.setMinimumSize(new Dimension(400, 500));
            window.setMaximumSize(new Dimension(400, 500));
            window.setPreferredSize(new Dimension(400, 500));
            window.setSize(400, 500);
            cardLayout.show(cardPanel, "Config");
        });

        /*
        This code is a bit complicated to understand at first, but stick with me
        So after the player supposedly has entered their information in
        Vincent's page, and clicks the embark button, we have to take all of the
        information that was inputted and create a player object out of it.
        After that, we have to send that player to Nick's GUI to display all the
        information on the confirmation screen. Below I break down the steps.
         */
        ConfigurationScreen.startButton.addActionListener(e -> {

            // Grabs the name field and the difficulty field
            String name = ConfigurationScreen.nameField.getText().trim();
            Object diff = ConfigurationScreen.difficultyComboBox.getSelectedItem();

            // Grabs the values the user entered for their point allocations.
            int pilot = ((Integer) ConfigurationScreen.pilotSpinner.getValue());
            int fighter = ((Integer) ConfigurationScreen.fighterSpinner.getValue());
            int trader = ((Integer) ConfigurationScreen.traderSpinner.getValue());
            int engineer = ((Integer) ConfigurationScreen.engineerSpinner.getValue());

            // The sum of all the points. To be used in calculation below.
            int totalSkill = pilot + fighter + trader + engineer;

            /*
            Below is the parameter check.
            If the user has not entered a name of difficulty level,
             the first if-statement runs.
            If the user has a name but did not allocate the correct amount of
            points based on the level they chose, the second if-statement runs.
            Otherwise, if everything checks out, the player object is created!
             */
            if (name.isEmpty() || diff == null) {
                JOptionPane.showMessageDialog(window, "Please enter name.");
            } else if (totalSkill != (diffMap.get(diff)).intValue()) {
                JOptionPane.showMessageDialog(window, "Incorrect point allocation.\nExpected: "
                                + diffMap.get(diff).toString() + "\nReceived: " + totalSkill);
            } else {
                Difficulty df = diff.equals("Easy") ? Difficulty.EASY
                        : diff.equals("Hard") ? Difficulty.HARD : Difficulty.MEDIUM;
                player = new Player(name, pilot, fighter, trader, engineer, df);
                /*
                After the player object is created, we pass it onto Nick's GUI
                for the info to be displayed. We reset the GUI size back to
                what it was, and do cardLayout.show() to show the next page.
                 */
                confirmScreen.setPlayer(player);
                cardPanel.add(confirmScreen.panelGridBag, "Confirm");
                window.setSize(new Dimension(1400, 1000));
                cardLayout.show(cardPanel, "Confirm");
            }
        });

        /*
        No need to worry about these. All of these below are just action
        listeners that updates points as the user clicks through the spinners.
         */
        ConfigurationScreen.difficultyComboBox.addActionListener(changeEvent ->
            updateCurrPoints(oceantrader.ConfigurationScreen.difficultyComboBox.getSelectedItem()));
        ConfigurationScreen.pilotSpinner.addChangeListener(changeEvent ->
            updateCurrPoints(oceantrader.ConfigurationScreen.difficultyComboBox.getSelectedItem()));
        ConfigurationScreen.fighterSpinner.addChangeListener(changeEvent ->
            updateCurrPoints(oceantrader.ConfigurationScreen.difficultyComboBox.getSelectedItem()));
        ConfigurationScreen.traderSpinner.addChangeListener(changeEvent ->
            updateCurrPoints(oceantrader.ConfigurationScreen.difficultyComboBox.getSelectedItem()));
        ConfigurationScreen.engineerSpinner.addChangeListener(changeEvent ->
            updateCurrPoints(oceantrader.ConfigurationScreen.difficultyComboBox.getSelectedItem()));

        /*
        After the user has viewed the confirmation page and clicks the button,
        The map and lists of the next GUI are updated before it is displayed.
         */
        confirmScreen.button.addActionListener(e -> {
            player.setRegion(Universe.getInstance().regions.get(rand.nextInt(10)));
            Universe.getInstance().sortRegions();
            regionDisplay.regionPanel.updateRegionList();
            regionDisplay.regionPanel.update();
            regionDisplay.map.updateMapTitle(player.getRegion());
            cardLayout.show(cardPanel, "Main");
            regionDisplay.invMarketDisplay.updateInventory();
            regionDisplay.invMarketDisplay.updateMarket();
            regionDisplay.shipDisplay.updateShipDisplay(player);
            regionDisplay.invMarketDisplay.updateCurrencyDisplay();
        });

        /*
        When the user clicks the travel button, the travel class is called
        to facilitate with all the traveling.
         */
        regionDisplay.travelButton.addActionListener(e -> oceantrader.Travel.confirmTravel());

        // Basic window stuff that we need. Can ignore.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(1400, 1000));
        window.add(cardPanel);
        window.setResizable(false);
        window.setVisible(true);
    }

    /*
    Helper method that updates the points of the spinner as the user clicks
    through and allocates their points.
     */
    private static void updateCurrPoints(Object diff) {
        int pointsDiff;
        JTextField pointsRemain = ConfigurationScreen.pointsRemaining;
        pointsDiff = diff.equals("Easy") ? 16 : diff.equals("Hard") ? 8 : 12;
        currPoints = (int) ConfigurationScreen.pilotSpinner.getValue()
                + (int) ConfigurationScreen.fighterSpinner.getValue()
                + (int) ConfigurationScreen.traderSpinner.getValue()
                + (int) ConfigurationScreen.engineerSpinner.getValue();
        pointsRemain.setText((pointsDiff - currPoints) + " points remaining.");
    }

    protected static void endGame(int value) {
        new EndGame(value);
    }
}