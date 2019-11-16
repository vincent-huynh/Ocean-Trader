package oceantrader;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Trader extends Ship implements TraderNPC {

    private boolean negotiable = true;
    private static Random rand = new Random();

    public Trader() {
        super(ShipType.MERCHANT, 45, 650, 1250);
        generateCargo();
    }

    public Trader(ArrayList<Item> cargoList) {
        super(ShipType.MERCHANT, 45, 650, 1250);
        this.getCargoList().addAll(cargoList);
    }

    public void generateCargo() {
        ArrayList<Item> allItems = new ArrayList<>();
        allItems.addAll(TechLevel.FUTURISTIC.getItems());
        allItems.addAll(TechLevel.FUTURISTIC.getFormerItem());
        for (int i = 0; i < rand.nextInt(5) + 5; i++) {
            this.getCargoList().add(allItems.remove(rand.nextInt(allItems.size())));
            this.getCargoList().get(i).setSellPrice((int)
                    (this.getCargoList().get(i).getPrice() * 0.75));
        }
    }

    public String sellItems(Item item) {
        Player player = OceanTrader.player;
        Ship ship = player.getShip();
        if (player.getCurrency() < item.getPrice()) {
            return "broke";
        } else if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
            return "space";
        } else {
            player.setCurrency(player.getCurrency() - item.getPrice());
            return "success";
        }
    }

    public double negotiate() {
        boolean success = NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Trader"));
        double discount = 0;
        if (!negotiable) {
            return -12345;
        } else if (success && negotiable) {
            discount = (OceanTrader.player.getSkillLevel("Trader") * rand.nextDouble() * 0.3) / 100;
            for (int i = 0; i < getCargoSize(); i++) {
                Item item = getCargoList().get(i);
                item.setPrice((int) (item.getPrice() * (1 - discount)));
            }
        } else if (rand.nextDouble() < 0.4) {
            discount = (OceanTrader.player.getSkillLevel("Trader") * rand.nextDouble() * 0.3) / 100;
            for (int i = 0; i < getCargoSize(); i++) {
                Item item = getCargoList().get(i);
                item.setPrice((int) (item.getPrice() * (1 + discount)));
            }
            discount *= -1;
        }
        negotiable = false;
        return (int) (discount * 100 * 100) / 100.0;
    }

    public ArrayList<Item> robbed() {
        boolean robbed = NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Fighter"));
        ArrayList<Item> stolen = new ArrayList<>();
        if (robbed) {
            for (int i = 0; i < rand.nextInt(getCargoSize() / 2 - 1) + 1; i++) {
                stolen.add(getCargoList().remove(rand.nextInt(getCargoSize())));
            }
        }
        return stolen;
    }

    public void fightable() {
        JPanel traderPanel = OceanTrader.encounterFrame.getTraderPanel();
        Player player = OceanTrader.player;
        Ship ship = player.getShip();
        ArrayList<Item> stolen = robbed();
        if (stolen.size() == 0) {
            javax.swing.JOptionPane.showMessageDialog(traderPanel,
                    "You were not able to steal anything and the "
                            + "trader inflicted damage on your ship!");
            NPCEncounter.damageShip();
            if (player.getShip().getHealth() <= 0) {
                OceanTrader.endGame(1);
                return;
            }
        } else {
            for (Item item : stolen) {
                if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
                    javax.swing.JOptionPane.showMessageDialog(traderPanel,
                            "Your inventory is full, cannot take " + item.getName() + "!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(traderPanel,
                            "You have gained: " + item.getName());
                    ship.getCargoList().add(item);
                }
            }
        }
        OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
        OceanTrader.regionDisplay.regionPanel.update();
        OceanTrader.encounterFrame.setVisible(false);
        NPCEncounter.modifyKarma(1, "gained");
        Travel.updateFuel((int) Travel.getCost());
        Travel.travel();
    }

    public void avertable() {
        OceanTrader.encounterFrame.setVisible(false);
        Travel.updateFuel((int) Travel.getCost());
        Travel.travel();
    }

    public void concedable() {
        TraderEncounter traderPanel = (TraderEncounter) OceanTrader.encounterFrame.getTraderPanel();
        Trader trader = traderPanel.getTrader();
        Item buyItem = traderPanel.getBuyItem();
        JTable traderItems = traderPanel.getTraderItems();
        if (buyItem == null) {
            javax.swing.JOptionPane.showMessageDialog(traderItems, "No Item Selected!");
        } else {
            switch (trader.sellItems(buyItem)) {
            case "broke":
                javax.swing.JOptionPane.showMessageDialog(traderItems,
                        "You lack sufficient funds!");
                break;
            case "space":
                javax.swing.JOptionPane.showMessageDialog(traderItems, "No space available!");
                break;
            case "success":
                javax.swing.JOptionPane.showMessageDialog(traderItems,
                        buyItem.getName() + " was bought!");
                OceanTrader.player.getShip().getCargoList().add(buyItem);
                OceanTrader.encounterFrame.setVisible(false);
                OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
                OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
                NPCEncounter.modifyKarma(-1, "lost");
                Travel.updateFuel((int) Travel.getCost());
                Travel.travel();
                break;
            default:
                break;
            }
            traderPanel.setBuyItem(null);
            traderPanel.setTrader(new Trader());
        }
    }

    public void negotiable() {
        TraderEncounter traderPanel = (TraderEncounter) OceanTrader.encounterFrame.getTraderPanel();
        Trader trader = traderPanel.getTrader();
        JTable traderItems = traderPanel.getTraderItems();
        double dis = trader.negotiate();
        if (dis > 0) {
            javax.swing.JOptionPane.showMessageDialog(traderItems,
                    "You were able to haggle down the price by " + dis + "%!");
            traderPanel.updatePanel();
        } else if (dis == -12345) {
            javax.swing.JOptionPane.showMessageDialog(traderItems,
                    "You cannot negotiate with the trader again!");
        } else if (dis < 0) {
            javax.swing.JOptionPane.showMessageDialog(traderItems,
                    "The trader got angry and increased prices by " + (dis * -1) + "%!");
            traderPanel.updatePanel();
        } else {
            javax.swing.JOptionPane.showMessageDialog(traderItems,
                    "You were unsuccessful in negotiating.");
        }
    }
}