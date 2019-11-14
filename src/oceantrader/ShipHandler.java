package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShipHandler {

    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;
    private static Ship ship = player.getShip();

    protected static void refuelShip(int amount) {
        int diff = ship.getMaxFuelCapacity() - ship.getFuelCapacity();
        if (amount == 0) {
            JOptionPane.showMessageDialog(window, "No fuel selected!");
        } else if (amount == -1) {
            refuel(String.format("Refuel to full?\nCost: %d coins", diff), diff);
        } else if (amount > diff) {
            refuel(String.format("You have selected more fuel than your ship can carry!"
                    + " Refuel to full instead?\nCost: %d", diff), diff);
        } else {
            refuel(String.format("Refueling %d will cost %d coins.\nConfirm refuel?",
                    amount, amount), amount);
        }
    }

    private static void refuel(String msg, int amount) {
        int yesOrNo = JOptionPane.showConfirmDialog(window, msg,
                "Refuel Confirmation", JOptionPane.YES_NO_OPTION);
        if (yesOrNo == 0) {
            if (player.getCurrency() >= amount) {
                player.setCurrency(player.getCurrency() - amount);
                ship.setFuelCapacity(ship.getFuelCapacity() + amount);
                OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
                OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
                JOptionPane.showMessageDialog(window, String.format("Your ship's fuel"
                        + " has been replenished by %d!", amount));
                OceanTrader.regionDisplay.regionPanel.update();
            } else {
                JOptionPane.showMessageDialog(window,
                        String.format("You do not have enough coins to refuel %d!", amount));
            }
        }
    }

    protected static void repairShip(int amount) {
        int diff = ship.getMaxHealth() - ship.getHealth();
        double savings = (100 - player.getSkillLevel("Engineer") * 3.0) / 100;
        int cost = (int) (diff * savings);
        if (amount == 0) {
            JOptionPane.showMessageDialog(window, "No repair amount selected!");
        } else if (amount == -1) {
            repair(String.format("Repair to full?\nOriginal cost: %d coins"
                    + "\nFinal cost after skill discount: %d coins", diff, cost), diff, cost);
        } else if (amount > diff) {
            repair(String.format("You have selected more repairs than needed!"
                    + " Repair to full instead?\nCost: %d\nFinal cost after"
                    + " skill discount: %d coins", diff, cost), diff, cost);
        } else {
            cost = (int) (amount * savings);
            repair(String.format("Repairing %d cost %d coins.\nNew cost after"
                    + " skill discount: %d coins\nConfirm repair?",
                    amount, amount, cost), amount, cost);
        }
    }

    private static void repair(String msg, int amount, int cost) {
        int yesOrNo = JOptionPane.showConfirmDialog(window, msg,
                "Repair Confirmation", JOptionPane.YES_NO_OPTION);
        if (yesOrNo == 0) {
            if (player.getCurrency() >= cost) {
                player.setCurrency(player.getCurrency() - cost);
                ship.setHealth(ship.getHealth() + amount);
                OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
                OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
                JOptionPane.showMessageDialog(window, String.format("Your ship's health"
                        + " has been replenished by %d!", amount));
                OceanTrader.regionDisplay.regionPanel.update();
            } else {
                JOptionPane.showMessageDialog(window,
                        String.format("You do not have enough coins to repair %d!", amount));
            }
        }
    }
}