package oceantrader;

import java.util.ArrayList;
import java.util.Random;

public class Trader extends Ship {
    //Vincent --> Method updatePanel()
    private boolean negotiable = true;

    public Trader () {
        super(ShipType.MERCHANT, 45, 650, 1250);
        generateCargo();
    }

    public Trader(ArrayList<Item> cargoList) {
        super(ShipType.MERCHANT, 45, 650, 1250);
        this.getCargoList().addAll(cargoList);
    }

    public void generateCargo() {
        Random rand = new Random();
        ArrayList<Item> allItems = new ArrayList<>();
        allItems.addAll(TechLevel.FUTURISTIC.getItems());
        allItems.addAll(TechLevel.FUTURISTIC.getFormerItem());
        for (int i = 0; i < rand.nextInt(5) + 5; i++) {
            this.getCargoList().add(allItems.remove(rand.nextInt(allItems.size())));
            this.getCargoList().get(i).setSellPrice((int) (this.getCargoList().get(i).getPrice() * 0.75));
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
        boolean success =  NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Trader"));
        Random rand = new Random();
        double discount = 0;
        if (success && negotiable) {
            discount = (OceanTrader.player.getSkillLevel("Trader")
                    * rand.nextDouble() * 0.3) / 100;
            for (int i = 0; i < getCargoSize(); i++) {
                Item item = getCargoList().get(i);
                item.setPrice((int) (item.getPrice() * (1 - discount)));
            }
        }
        negotiable = false;
        return (int) (discount * 100 * 100) / 100.0;
    }

    public ArrayList<Item> robbed() {
        boolean robbed = NPCEncounter.getOutcome(OceanTrader.player.getSkillLevel("Fighter"));
        Random rand = new Random();
        ArrayList<Item> stolen = new ArrayList<>();
        if (robbed) {
            for (int i = 0; i < rand.nextInt(getCargoSize() / 2 - 1) + 1; i++) {
                stolen.add(getCargoList().remove(rand.nextInt(getCargoSize())));
            }
        }
        return stolen;
    }
}
