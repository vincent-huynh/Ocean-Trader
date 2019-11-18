package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Random;

public class Police implements NPC {

    private static JFrame window = OceanTrader.window;
    private static Random rand = new Random();

    @Override
    public void fightable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(PoliceEncounter.player.getSkillLevel("Fighter"))) {
            JOptionPane.showMessageDialog(window, "You won the fight!"
                    + " You safely traveled to your destination.");
            NPCEncounter.modifyKarma(1, "gained");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            PoliceEncounter.fleeSuccess = false;
            int fee = PoliceEncounter.player.getCurrency() != 0
                    ? rand.nextInt(PoliceEncounter.player.getCurrency() / 2) : 10;
            PoliceEncounter.player.getShip().getCargoList().remove(PoliceEncounter.itemPos);
            PoliceEncounter.player.setCurrency(PoliceEncounter.player.getCurrency() - fee);
            JOptionPane.showMessageDialog(window, "You lost the fight. Your ship was damaged,"
                    + " your " + PoliceEncounter.forbidden.getName() + " was confiscated and you "
                    + "were fined " + fee + " for fighting the police.");
            NPCEncounter.damageShip();
            if (PoliceEncounter.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
            NPCEncounter.modifyKarma(1, "gained");
        }
    }

    @Override
    public void avertable() {
        OceanTrader.encounterFrame.setVisible(false);
        if (NPCEncounter.getOutcome(PoliceEncounter.player.getSkillLevel("Pilot"))) {
            PoliceEncounter.fleeSuccess = true;
            JOptionPane.showMessageDialog(window, "You successfully fled from the police!");
            NPCEncounter.modifyKarma(1, "gained");
            Travel.updateFuel((int) Travel.getCost());
            Travel.travel();
        } else {
            PoliceEncounter.fleeSuccess = false;
            int fee = rand.nextInt(PoliceEncounter.player.getCurrency() / 4);
            PoliceEncounter.player.getShip().getCargoList().remove(PoliceEncounter.itemPos);
            PoliceEncounter.player.setCurrency(PoliceEncounter.player.getCurrency() - fee);
            JOptionPane.showMessageDialog(window, "You were unsuccessful in evading the police."
                    + " Your " + PoliceEncounter.forbidden.getName() + " was confiscated, your"
                    + " ship was damaged, and you were fined " + fee + " for trying to escape.");
            NPCEncounter.damageShip();
            if (PoliceEncounter.player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
            OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
            NPCEncounter.modifyKarma(1, "gained");
        }
    }

    @Override
    public void concedable() {
        OceanTrader.encounterFrame.setVisible(false);
        PoliceEncounter.player.getShip().getCargoList().remove(PoliceEncounter.itemPos);
        JOptionPane.showMessageDialog(window, "You forfeited your "
                + PoliceEncounter.forbidden.getName() + " and continued to your destination.");
        OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
        NPCEncounter.modifyKarma(-1, "lost");
        Travel.updateFuel((int) Travel.getCost());
        Travel.travel();
    }
}