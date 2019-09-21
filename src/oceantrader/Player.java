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
    private int skill1;
    private int skill2;
    private int skill3;
    private int skill4;
    /**
     * ...skill capacity
     */
    private int skillCap = 16;

    public Player(String name, int skill1, int skill2, int skill3, int skill4,
                  Difficulty difficulty) {

        this.name = name;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;

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
    public Player(String name, int skill1, int skill2, int skill3, int skill4) {
        this(name,skill1,skill2,skill3,skill4, Difficulty.HARD);
    }
    public Player(String name) {
        this(name,0,0,0,0, Difficulty.HARD);
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

    public int getSkillLevel(int skill) {
        if (skill < 0 || skill > 4) {
            throw new IllegalArgumentException("Skill not found.");
        }
        switch (skill) {
            case 1 :
                return skill1;
            case 2 :
                return skill2;
            case 3 :
                return skill3;
            case 4 :
                return skill4;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + ":"
                + "\n\tPilot: " + this.skill1
                + "\n\tFighter: " + this.skill2
                + "\n\tTrader: " + this.skill3
                + "\n\tEngineer: " + this.skill4
                + "\n\tDifficulty: " + this.difficulty;
    }
}
