package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Transaction {

    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;


    protected void processTransaction(Item item) {
        double price = calculatePrice(item);
        if (player.getCurrency() < price) {
            String errorMsg = String.format("Not enough currency!\nYou only " +
                            "have %d.\nThe item costs %d.",
                    player.getCurrency(), price);
            JOptionPane.showMessageDialog(window, errorMsg);
        } else {
            String confirmMsg = String.format("You have %d.\nThe item costs %d."
                    + "\nConfirm purchase?", player.getCurrency(), price);
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
        double price = item.getPrice() * calculateDiscount()
                * ((100 + OceanTrader.player.getRegion().getTax()) / 100);
        return price;
    }

    private double calculateDiscount() {
        return (100 - (player.getSkillLevel("Trader") * 3.0)) / 100;
    }

    private void updateCurrency(int price) {
        player.setCurrency(player.getCurrency() - price);
    }

    private void updateCargoList(Item item) {
        OceanTrader.player.getShip().getCargoList().add(item);
    }
}
