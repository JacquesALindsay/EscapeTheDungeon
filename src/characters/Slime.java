package characters;

/*
 * The class for a slime
 */
public class Slime extends Creature {

    public Slime(String attribute, int maxHP, int attack, int defense, int luck) {
        super("Slime", attribute, 1, maxHP, attack, defense, luck, maxHP, 100);
    }

    @Override
    public int attackPower() {
        return 1;
    }

    @Override
    public int attackAccuracy() {
        return 1;
    }

    @Override
    public String displayAttack() {
        return "The " + display() + " tackles you!";
    }
}
