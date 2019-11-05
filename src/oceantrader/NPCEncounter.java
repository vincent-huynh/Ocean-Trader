/*
So this code should basically allow you to test your NPCs to make sure all the
interactions are correct and there are no bugs. The forceEncounter variable
will let you roll your particular NPC every time you travel, which should make
it easier with the thousands of game restarts you'll do testing and making sure
your code functions appropriately. Note: The first 3 if statements that force
the encounter are only here for testing purposes, and will be removed later.

If the user is able to travel, utilize the following lines of code:

                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();

Lastly, the getOutcome() method does the calculation for the chance outcomes:
returns true if player is successful, and false otherwise. The damageShip()
method damages the player's ship. Use these in your code. These methods are
created for consistency among all the NPCs.
 */

package oceantrader;

import java.util.HashMap;
import java.util.Random;

public abstract class NPCEncounter {

    private static Random rand = new Random();
    private static HashMap<Difficulty, Integer> chances = null;

    protected static void initiateEncounter(int fuelCost) {

        int forceEncounter = 1; //0 -> Bandit, 1 -> Trader, 2 -> Police

        if (forceEncounter == 0) {
            callNPC(0);
        } else if (forceEncounter == 1) {
            callNPC(1);
        } else if (forceEncounter == 2) {
            callNPC(2);
        } else if (rand.nextInt(100) + 1
                <= getEncounterChance(OceanTrader.player.getDifficulty())) {
            if (OceanTrader.player.getShip().getCargoSize() > 0) {
                callNPC(rand.nextInt(3));
            } else {
                callNPC(rand.nextInt(2));
            }
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
        if (id == 0) {
            OceanTrader.encounterFrame.setOppPanel(0);
            OceanTrader.encounterFrame.updatePanel();
        } else if (id == 1) {
            OceanTrader.encounterFrame.setOppPanel(1);
            OceanTrader.encounterFrame.updatePanel();
        } else {
            OceanTrader.encounterFrame.setOppPanel(2);
            OceanTrader.encounterFrame.updatePanel();
        }
        OceanTrader.encounterFrame.setLocationRelativeTo(OceanTrader.window);
        OceanTrader.encounterFrame.setVisible(true);
        OceanTrader.encounterFrame.setAlwaysOnTop(true);
    }

    protected static boolean getOutcome(int skillLevel) {
        return rand.nextInt(100) + 1 <= 30 + 3 * skillLevel;
    }

    protected static void damageShip() {
        Ship ship = OceanTrader.player.getShip();
        ship.setHealth(ship.getHealth() - (int) (ship.getMaxHealth()
                * ((rand.nextInt(30 - 10 + 1) + 10) / 100.0)));
        OceanTrader.regionDisplay.shipDisplay
                .updateShipDisplay(OceanTrader.player);
    }
}