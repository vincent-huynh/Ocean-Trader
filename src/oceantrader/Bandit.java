package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Bandit implements NPC {

    //Variables to help keep the code clean and easier to read
    private static JFrame window = OceanTrader.window;

    /*
     * Method to process the player trying to fight off the bandit
     */
    @Override
    public void fightable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(BanditEncounter.player.getSkillLevel("Fighter"))) {
            int creditsGained = BanditEncounter.rand.nextInt(1000 - 300) + 300;
            BanditEncounter.player.setCurrency(creditsGained
                    + BanditEncounter.player.getCurrency());
            JOptionPane.showMessageDialog(window, "You have successfully defeated the bandit and"
                    + " received " + creditsGained + " of the bandit's coins as a reward.");
            NPCEncounter.modifyKarma(-1, "lost");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            BanditEncounter.player.setCurrency(0);
            JOptionPane.showMessageDialog(window, "You failed to fight off the bandit,"
                    + " so the bandit took all of your coins and damaged your ship.");
            NPCEncounter.damageShip();
            if (BanditEncounter.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
            NPCEncounter.modifyKarma(-1, "lost");
        }
        OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
        OceanTrader.regionDisplay.regionPanel.update();
    }

    /*
     * Method to process the player trying to flee back to the previous region
     */
    @Override
    public void avertable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(BanditEncounter.player.getSkillLevel("Pilot"))) {
            JOptionPane.showMessageDialog(window, "You have successfully fled!");
            NPCEncounter.modifyKarma(-1, "lost");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            BanditEncounter.player.setCurrency(0);
            JOptionPane.showMessageDialog(window, "You failed to flee, so the"
                    + " bandit took all of your coins and damaged your ship.");
            NPCEncounter.damageShip();
            if (BanditEncounter.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            NPCEncounter.modifyKarma(-1, "lost");
        }
    }

    /*
     * Method to process the player paying the bandit's demand and continuing
     *  to the desired destination
     */
    @Override
    public void concedable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (BanditEncounter.player.getCurrency() >= BanditEncounter.demand) {
            BanditEncounter.player.setCurrency(BanditEncounter.player.getCurrency()
                    - BanditEncounter.demand);
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            JOptionPane.showMessageDialog(window, "You paid "
                    + BanditEncounter.demand + " coins to the bandit.");
        } else if (BanditEncounter.playerInventory.size() >= 1) {
            BanditEncounter.playerInventory.clear();
            OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(BanditEncounter.player);
            OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
            JOptionPane.showMessageDialog(window, "You could not afford the"
                    + " bandit's demands, so he demanded your inventory.");
        } else {
            JOptionPane.showMessageDialog(window, "You didn't have any items,"
                    + " so the bandit damaged your ship.");
            NPCEncounter.damageShip();
            if (BanditEncounter.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
        }
        NPCEncounter.modifyKarma(-1, "lost");
        Travel.updateFuel((int) Travel.getCost());
        Travel.travel();
    }
}