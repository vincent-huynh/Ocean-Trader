package oceantrader;

/**
 * This class represents a player and stores information regarding the player's
 * stats and currency.
 *
 * @author Thieu, with a little bit of Vincent :D
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
    private Difficulty difficulty;

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
                  int traderPoints, int engineerPoints, Difficulty difficulty) {

        this.name = name;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;

        switch (difficulty) {
            case EASY:
                currency = 1000;
                this.difficulty = Difficulty.EASY;
                break;
            case MEDIUM:
                currency = 500;
                this.difficulty = Difficulty.MEDIUM;
                break;
            case HARD:
                currency = 100;
                this.difficulty = Difficulty.HARD;
                break;
        }
    }

    public Player(String name, int pilotPoints, int fighterPoints, int traderPoints, int engineerPoints) {
        this(name, pilotPoints, fighterPoints, traderPoints, engineerPoints, Difficulty.HARD);
    }

    public Player(String name) {
        this(name, 0, 0, 0, 0, Difficulty.HARD);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCurrency() {
        return currency;
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

    public int getTotalSkill() {
        return pilotPoints + fighterPoints + traderPoints + engineerPoints;
    }

    @Override
    public String toString() {
        return name + ":"
                + "\n\tPilot: " + this.pilotPoints
                + "\n\tFighter: " + this.fighterPoints
                + "\n\tTrader: " + this.traderPoints
                + "\n\tEngineer: " + this.engineerPoints
                + "\n\tDifficulty: " + this.difficulty;
    }
}