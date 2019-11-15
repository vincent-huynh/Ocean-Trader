package oceantrader;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
                OceanTrader.endGame();
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

    }

    public void negotiable() {

    }
}