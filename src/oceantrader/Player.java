package oceantrader;

/**
 * This class represents a player and stores information regarding the player's
 * stats and currency.
 *
 * @author Thieu
 * @version 1.0
 */

public class Player {

    /**
     * The name of the player designated on initialization of game
     */
    private String name;

    /**
     * The difficulty of the game, determines starting currency ___ tba___.
     */
    private String difficulty;

    /**
     * The amount of money the player has.
     */
    private int currency;

    /**
     * The various skills a player can allocate a point into.
     */
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;

    public Player(String name, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints, String difficulty) {

        this.name = name;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;

        switch (difficulty) {
            case "Easy" :
                currency = 1000;
                break;
            case "Normal" :
                currency = 500;
                break;
            case "Hard" :
                currency = 100;
                break;
        }
    }

    public Player(String name, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints) {
        this(name, pilotPoints, fighterPoints, traderPoints, engineerPoints,
                "Hard");
    }

    public Player(String name) {
        this(name, 0, 0, 0, 0, "Hard");
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getSkillLevel(String skill) {
        switch (skill) {
            case "Pilot" :
                return pilotPoints;
            case "Fighter" :
                return fighterPoints;
            case "Trader" :
                return traderPoints;
            case "Engineer" :
                return engineerPoints;
        }
        throw new IllegalArgumentException("Skill not found.");
    }
}