package oceantrader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.HashMap;

public class Transaction {

    //Variables to help keep the code clean and easier to read.
    private static JFrame window = OceanTrader.window;
    private static Player player = OceanTrader.player;
    private static HashMap<Integer, Double> offset = null;
    private static Ship ship = player.getShip();

    /*
    * Method to process the transaction details when a player buys an item
    */
    protected static void processTransactionBuy(Item item) {

        int price = (int) calculatePrice(item);

        if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
            String fullInv = "Your ship's inventory is full!"
                    + "\nPlease upgrade your inventory before purchasing.";
            JOptionPane.showMessageDialog(window, fullInv);

        } else if (player.getCurrency() < price) {
            String errorMsg = String.format("Not enough currency!\n"
                            + "You only have %d coins.\n%s costs %d coins.",
                    player.getCurrency(), item.getName(), price);
            JOptionPane.showMessageDialog(window, errorMsg);

        } else {
            String confirmMsg = String.format("You have %d coins."
                            + "\n%s costs %d coins.\nConfirm Purchase?",
                    player.getCurrency(), item.getName(), price);
            int yesOrNo = JOptionPane.showConfirmDialog(window, confirmMsg,
                    "Purchase Confirmation", JOptionPane.YES_NO_OPTION);

            if (yesOrNo == 0) {

                item.setSellPrice(price);
                updateCurrency(-1 * price);
                ship.getCargoList().add(item);
                confirmationDialog("Purchased", item);

                if (item.getName().equals(Universe.getInstance().endGame.getName())) {
                    //System.out.println("End Game Item Bought!");
                    JOptionPane.showMessageDialog(OceanTrader.window, "You have purchased the "
                        + "ultimate weapon in the game. Snap your fingers to end this cruel,"
                        + " harsh world!");
                    OceanTrader.regionDisplay.regionPanel.enableSnap();
                }

                InvMarketDisplay.buyItem = null;
                OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
            }
        }
    }

    /*
     * Method to process the transaction details when a player sells an item
     */
    protected static void processTransactionSell(Item item) {

        int price = (int) (item.getSellPrice() * (75.0 / 100.0));

        String confirmMsg = String.format("%s sells for %d coins."
                + "\nConfirm Sell?", item.getName(), price);
        int yesOrNo = JOptionPane.showConfirmDialog(window, confirmMsg,
                "Sell Confirmation", JOptionPane.YES_NO_OPTION);

        if (yesOrNo == 0) {

            updateCurrency(price);
            ship.getCargoList().remove(item);
            confirmationDialog("Sold", item);

            InvMarketDisplay.sellItem = null;
            OceanTrader.regionDisplay.shipDisplay.updateShipDisplay(player);
        }
    }

    /*
     * Method to calculate the price of an item
     */
    private static double calculatePrice(Item item) {
        return item.getPrice() * dateOffset() * skillDiscount() * tax();
    }

    /*
     * Method to get the price values of an item
     */
    protected static double[] getPriceValues(Item item) {
        return new double[] {item.getPrice(), dateOffset(), skillDiscount(),
                tax(), calculatePrice(item)};
    }

    /*
     * Method to calculate the discount of an item based on the player's skill
     */
    private static double skillDiscount() {
        return (100 - (player.getSkillLevel("Trader") * 3.0)) / 100;
    }

    /*
     * Method to calculate the tax of an item
     */
    private static double tax() {
        return (100.0 + player.getRegion().getTax()) / 100.0;
    }

    /*
     * Method to calculate the date offset for the price based on the day
     */
    private static double dateOffset() {
        if (offset == null) {
            offset = new HashMap<>();
            // +5% increase in price for date and/or time match
            offset.put(0, 1.05); //Sunday
            offset.put(5, 1.05); //Friday
            offset.put(6, 1.05); //Saturday
            offset.put(29, 1.05); //7pm
            offset.put(30, 1.05); //8pm
            offset.put(31, 1.05); //9pm
            offset.put(32, 1.05); //10pm
            offset.put(33, 1.05); //11pm
        }
        Date date = new Date();
        return offset.getOrDefault(date.getDay(), 1.0)
                * offset.getOrDefault(10 + date.getHours(), 1.0);
    }

    /*
     * Method to update the player's currency
     */
    private static void updateCurrency(int price) {
        player.setCurrency(player.getCurrency() + price);
    }

    /*
     * Method to display a confirmation dialog to the player when they buy or
     *  sell an item
     */
    private static void confirmationDialog(String s, Item item) {
        String msg = String.format("Successfully %s %s!", s, item.getName());
        JOptionPane.showMessageDialog(window, msg);
    }
}