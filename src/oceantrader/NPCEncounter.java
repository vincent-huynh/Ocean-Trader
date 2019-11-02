/*
So this code should basically allow you to test your NPCs to make sure all the
interactions are correct and there are no bugs. The forceEncounter variable
will let you roll your particular NPC every time you travel, which should make
it easier with the thousands of game restarts you'll do testing and making sure
your code functions appropriately.

Also I passed in the fuelCost variable for the cases where the player actually
does get past the NPC and travels. If the user is able to travel, you'll want
to utilize the following lines of code:

                        Travel.updateFuel(fuelCost);
                        Travel.travel();

Lastly, the getOutcome() method does the calculation for the chance outcomes:
returns true if player is successful, and false otherwise. The damageShip()
method damages the player's ship. Use these in your code. These methods are
created for consistency among all the NPCs.

To begin testing your code, refer to line 56 in Travel.java
 */

package oceantrader;

import java.util.Random;
import java.util.HashMap;

public class NPCEncounter {

    private static Random rand = new Random();
    private static HashMap<Difficulty, Integer> chances = null;

    protected static void initiateEncounter(int fuelCost) {



        //CHANGE THIS VALUE TO ENABLE 100% ENCOUNTER RATE//
                    int forceEncounter = 3;             //
        // 1 = BANDIT       2 = POLICE      3 = TRADER //

        if (forceEncounter == 1) {
            OceanTrader.encounterFrame.setOppPanel(1);
            OceanTrader.encounterFrame.updatePanel();
//            Bandit.initiateBanditEncounter(fuelCost);
        } else if (forceEncounter == 2) {
//            Police.initiatePoliceEncounter(fuelCost);
        } else if (forceEncounter == 3) {
            OceanTrader.encounterFrame.setOppPanel(3);
            OceanTrader.encounterFrame.updatePanel();
//            Trader.initiateTraderEncounter(fuelCost);
        } else if (rand.nextInt(100) + 1
                <= getEncounterChance(OceanTrader.player.getDifficulty())) {
            if (OceanTrader.player.getShip().getCargoSize() > 0) {
                callNPC(rand.nextInt(3));
            } else {
                callNPC(rand.nextInt(2));
            }
        }
        OceanTrader.encounterFrame.setLocationRelativeTo(OceanTrader.window);
        OceanTrader.encounterFrame.setVisible(true);
        OceanTrader.encounterFrame.setAlwaysOnTop(true);
    }

    private static int getEncounterChance(Difficulty diff) {
        if (chances == null) {
            chances = new HashMap<>();
            chances.put(Difficulty.EASY, 25);
            chances.put(Difficulty.MEDIUM, 37);
            chances.put(Difficulty.HARD, 50);
        }
        return chances.getOrDefault(diff, 100);
    }

    private static void callNPC(int id) {
        if (id == 0) {
//            Bandit.initiateBanditEncounter(fuelCost);
        } else if (id == 1) {
//            Police.initiatePoliceEncounter(fuelCost);
        } else {
//            Trader.initiateTraderEncounter(fuelCost);
        }
    }

    protected static boolean getOutcome(int skillLevel) {
        return rand.nextInt(100) + 1 <= 30 + 3 * skillLevel ? true : false;
    }

    protected static void damageShip() {
        Ship ship = OceanTrader.player.getShip();
        ship.setHealth(ship.getHealth() - (int) (ship.getMaxHealth()
                * ((rand.nextInt(30 - 10 + 1) + 10) / 100.0)));
        OceanTrader.regionDisplay.shipDisplay
                .updateShipDisplay(OceanTrader.player);
    }
}