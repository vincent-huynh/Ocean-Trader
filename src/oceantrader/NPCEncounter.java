package oceantrader;

import java.util.Random;
import java.util.HashMap;
import javax.swing.JOptionPane;

public abstract class NPCEncounter {

    private static Random rand = new Random();
    private static Player player = OceanTrader.player;
    private static HashMap<Difficulty, Integer> chances = null;

    protected static void initiateEncounter(int fuelCost) {

        int forceEncounter = -1; //0 -> Bandit, 1 -> Trader, 2 -> Police

        if (forceEncounter != -1) {
            callNPC(forceEncounter);
        } else if (rand.nextInt(100) + 1 <= getEncounterChance(player.getDifficulty())) {
            int chance = player.getShip().getCargoSize() > 0 ? 3 : 2;
            callNPC(rand.nextInt(chance));
        } else {
            Travel.updateFuel(fuelCost);
            Travel.travel();
        }
    }

    private static int getEncounterChance(Difficulty diff) {
        if (chances == null) {
            chances = new HashMap<>();
            chances.put(Difficulty.EASY, 40);
            chances.put(Difficulty.MEDIUM, 55);
            chances.put(Difficulty.HARD, 70);
        }
        return chances.getOrDefault(diff, 100);
    }

    private static void callNPC(int id) {
        OceanTrader.encounterFrame = new EncounterFrame();
        OceanTrader.encounterFrame.setOppPanel(id);
        OceanTrader.encounterFrame.updatePanel();
        OceanTrader.encounterFrame.setLocationRelativeTo(OceanTrader.window);
        OceanTrader.encounterFrame.setAlwaysOnTop(true);
        OceanTrader.encounterFrame.setVisible(true);
    }

    protected static boolean getOutcome(int skillLevel) {
        return rand.nextInt(100) + 1 <= 30 + 3 * skillLevel;
    }

    protected static void damageShip() {
        Ship ship = player.getShip();
        ship.setHealth(ship.getHealth() - (int) (ship.getMaxHealth()
                * ((rand.nextInt(30 - 10 + 1) + 10) / 100.0)));
        OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
    }

    protected static void modifyKarma(int amount, String type) {
        player.modifyKarma(amount);
        JOptionPane.showMessageDialog(OceanTrader.window,
                String.format("You have %s %d negative karma from this interaction!"
                                + "\nYour total negative karma is currently at: %d",
                        type, Math.abs(amount), player.getKarma()));
    }
}