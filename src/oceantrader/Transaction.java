package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Transaction {

    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;
    private static Ship ship = player.getShip();

    protected void processTransaction(Item item) {

        double price = calculatePrice(item);

        if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
            String fullInv = "Your ship's inventory is full!" +
                    "\nPlease upgrade your inventory before purchase.";
            JOptionPane.showMessageDialog(window, fullInv);
        } else if (player.getCurrency() < price) {
            String errorMsg = String.format("Not enough currency!\n"
                            + "You only have %d.\n%s costs %d.",
                    player.getCurrency(), item.getName(), price);
            JOptionPane.showMessageDialog(window, errorMsg);
        } else {
            String confirmMsg = String.format("You have %d.\n%s costs %d."
                    + "\nConfirm Purchase?",
                    player.getCurrency(), item.getName(), price);
            int yesOrNo = JOptionPane.showConfirmDialog(window, confirmMsg,
                    "Purchase Confirmation", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == 0) {
                processTransaction(item);
                updateCurrency((int) price);
                updateCargoList(item);
            }
        }
    }

    private double calculatePrice(Item item) {
        return item.getPrice() * calculateDiscount()
                * ((100.0 + player.getRegion().getTax()) / 100.0);
    }

    private double calculateDiscount() {
        return (100 - (player.getSkillLevel("Trader") * 3.0)) / 100;
    }

    private void updateCurrency(int price) {
        player.setCurrency(player.getCurrency() - price);
    }

    private void updateCargoList(Item item) {
        ship.getCargoList().add(item);
    }
}