package oceantrader;

public class Player {

    private String name;
    private Difficulty difficulty;
    private Region region;
    private Ship ship;
    private int currency;
    private int karma;

    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;

    public Player(String name, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints, Difficulty difficulty) {

        this.name = name;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.ship = Ship.newShip(ShipType.DEFAULT);
        this.karma = 0;

        switch (difficulty) {
        case EASY:
            currency = 1000;
            this.difficulty = Difficulty.EASY;
            break;
        case MEDIUM:
            currency = 750;
            this.difficulty = Difficulty.MEDIUM;
            break;
        case HARD:
            currency = 500;
            this.difficulty = Difficulty.HARD;
            break;
        default:
            break;
        }
    }

    public Player(String name, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints) {
        this(name, pilotPoints, fighterPoints, traderPoints, engineerPoints, Difficulty.HARD);
    }

    public Player(String name) {
        this(name, 0, 0, 0, 0, Difficulty.HARD);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public void setCurrency() {
        this.currency = currency;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getKarma() {
        return karma;
    }

    public void modifyKarma(int amount) {
        karma = karma + amount < 0 ? 0 : karma + amount;
    }

    public int getSkillLevel(String skill) {
        return skill.equals("Pilot") ? pilotPoints
                : skill.equals("Fighter") ? fighterPoints
                : skill.equals("Trader") ? traderPoints
                : skill.equals("Engineer") ? engineerPoints
                : null;
    }

    public int getTotalSkill() {
        return pilotPoints + fighterPoints + traderPoints + engineerPoints;
    }

    @Override
    public String toString() {
        return name + ":"
                + "\n\tSeamanship: " + this.pilotPoints
                + "\n\tBattle Ability: " + this.fighterPoints
                + "\n\tTradesmanship: " + this.traderPoints
                + "\n\tWorkmanship: " + this.engineerPoints
                + "\n\tDifficulty: " + this.difficulty;
    }
}