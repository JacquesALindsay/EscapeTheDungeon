package characters;

/*
 * Class for a creature you can fight
 */
public abstract class Creature extends Character {

    private final int expYield;

    public Creature(String name, String attribute, int level, int maxHP, int attack, int defense, int luck, int currentHP, int expYield) {
        super(name, attribute, level, maxHP, attack, defense, luck, currentHP);
        this.expYield = expYield;
    }

    public int getExpYield() {
        return expYield;
    }

    /*
     * Displays the creature
     */
    public String display() {
        return getAttribute() + " " + getName();
    }

    /*
     * The creature's attack power
     */
    public abstract int attackPower();

    /*
     * The creature'S attack accuracy
     */
    public abstract int attackAccuracy();

    /*
     * Displays the creature attacking
     */
    public abstract String displayAttack();
}
