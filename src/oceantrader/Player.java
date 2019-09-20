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
    private int skill1;
    private int skill2;
    private int skill3;
    private int skill4;


    public Player(String name, int skill1, int skill2, int skill3, int skill4,
                  String difficulty) {

        this.name = name;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;

        switch (difficulty) {
            case "easy" :
                currency = 1000;
                break;
            case "normal" :
                currency = 500;
                break;
            case "hard" :
                currency = 100;
                break;
        }
    }
    public Player(String name, int skill1, int skill2, int skill3, int skill4) {
        this(name,skill1,skill2,skill3,skill4, "hard");
    }
    public Player(String name) {
        this(name,0,0,0,0,"hard");
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

}
