package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Transaction {
    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;
    private double updatedPrice;

    public void confirmPrice(Item item) {
        if (player.getCurrency() < item.getPrice()) {
            JOptionPane.showMessageDialog(window, "Not enough "
                    + "currency.");
        } else {
            String confirmMsg = String.format("You currently have %f amount "
                    + "of currency", player.getCurrency());
            int yesOrNo = JOptionPane.showConfirmDialog(window, confirmMsg,
                    "Are you sure you want to buy this item?",
                    JOptionPane.YES_NO_OPTION);
            if (yesOrNo == 0) {
                processTransaction(item);
                updateCurrency();
                updateCargoList(item);
            }
        }
    }

    public void processTransaction(Item item) {
        double price = item.getPrice() - calculateDiscount()
                * OceanTrader.player.getRegion().getTax();
        updatedPrice = price;
    }

    public double calculateDiscount() {
        return (100 - (player.getSkillLevel("Trader") * 3.0)) / 100;
    }

    public void updateCurrency() {
        player.setCurrency(player.getCurrency() - updatedPrice);
    }

    public void updateCargoList(Item item) {
        OceanTrader.player.getShip().getCargoList().add(item);
    }
}
