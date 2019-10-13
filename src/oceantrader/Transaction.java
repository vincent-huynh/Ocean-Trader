package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.HashMap;

public class Transaction {

    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;
    private static HashMap<Integer, Double> offset = null;
    private static Ship ship = player.getShip();

    protected static void processTransaction(Item item) {

        double price = calculatePrice(item);

        if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
            String fullInv = "Your ship's inventory is full!" +
                    "\nPlease upgrade your inventory before purchasing.";
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

    private static double calculatePrice(Item item) {
        return item.getPrice() * dateOffset() * skillDiscount() * tax();
    }

    private static double skillDiscount() {
        return (100 - (player.getSkillLevel("Trader") * 3.0)) / 100;
    }

    private static double tax() {
        return (100.0 + player.getRegion().getTax()) / 100.0;
    }

    private static double dateOffset() {
        Date date = new Date();
        return offsetMap().getOrDefault(date.getDay(), 1.0)
                * offsetMap().getOrDefault(10 + date.getHours(), 1.0);
    }

    private static HashMap<Integer, Double> offsetMap() {
        if (offset == null) {
            offset = new HashMap<>();
            offset.put(0, 1.21);
            offset.put(5, 1.21);
            offset.put(6, 1.21);
            for (int i = 29; i < 34; ++i) {
                offset.put(i, 1.24);
            }
        }
        return offset;
    }

    private static void updateCurrency(int price) {
        player.setCurrency(player.getCurrency() - price);
    }

    private static void updateCargoList(Item item) {
        ship.getCargoList().add(item);
    }
}