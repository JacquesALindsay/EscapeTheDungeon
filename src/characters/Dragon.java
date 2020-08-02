package characters;

import exploration.Position;

public class Dragon extends Creature {

    // the spawn point of the dragon
    public static final Position SPAWN = new Position(2, 0, 5);

    public Dragon() {
        super("Dragon", "Legendary", 5, 10, 10, 10, 10, 10, 1000);
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
        return "The " + display() + " attacks you with its fiery breath!";
    }
}
