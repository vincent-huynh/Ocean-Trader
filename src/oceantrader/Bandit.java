package oceantrader;

import javax.swing.JOptionPane;

public class Bandit implements NPC {

    @Override
    public void fightable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Fighter"))) {
            int creditsGained = BanditEncounter.rand.nextInt(1000 - 300) + 300;
            OceanTrader.player.setCurrency(creditsGained + OceanTrader.player.getCurrency());
            JOptionPane.showMessageDialog(OceanTrader.window, "You have " +
                    "successfully"
                    + " defeated the bandit and received " + creditsGained
                    + " of the bandit's coins as a reward.");
            NPCEncounter.modifyKarma(-1, "lost");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            OceanTrader.player.setCurrency(0);
            JOptionPane.showMessageDialog(OceanTrader.window, "You failed to " +
                    "fight off the bandit,"
                    + " so the bandit took all of your coins and damaged your ship.");
            NPCEncounter.damageShip();
            if (OceanTrader.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame();
                return;
            }
            NPCEncounter.modifyKarma(-1, "lost");
        }
        OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
        OceanTrader.regionDisplay.regionPanel.update();
    }

    @Override
    public void avertable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Pilot")))
        {
            JOptionPane.showMessageDialog(OceanTrader.window, "You " +
                    "have successfully fled!");
            NPCEncounter.modifyKarma(-1, "lost");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            OceanTrader.player.setCurrency(0);
            JOptionPane.showMessageDialog(OceanTrader.window, "You " +
                    "failed to flee,"
                    + " so the bandit took all of your coins and damaged " +
                    "your ship.");
            NPCEncounter.damageShip();
            if (OceanTrader.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame();
                return;
            }
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            NPCEncounter.modifyKarma(-1, "lost");
        }
    }

    @Override
    public void concedable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (OceanTrader.player.getCurrency() >= BanditEncounter.demand) {
            OceanTrader.player.setCurrency(OceanTrader.player.getCurrency() -
                    BanditEncounter.demand);
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            JOptionPane.showMessageDialog(OceanTrader.window, "You " +
                    "paid " + BanditEncounter.demand + " coins to the bandit.");
        } else if (BanditEncounter.playerInventory.size() >= 1) {
            BanditEncounter.playerInventory.clear();
            OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(OceanTrader
                    .player);
            OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
            JOptionPane.showMessageDialog(OceanTrader.window, "You " +
                    "could not afford the bandit's demands, so he demanded " +
                    "your inventory.");
        } else {
            JOptionPane.showMessageDialog(OceanTrader.window, "You " +
                    "didn't have any items, so the bandit damaged your ship.");
            NPCEncounter.damageShip();
            if (OceanTrader.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame();
                return;
            }
        }
        NPCEncounter.modifyKarma(-1, "lost");
        Travel.updateFuel((int) Travel.getCost());
        Travel.travel();
    }
}
