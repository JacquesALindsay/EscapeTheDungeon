package menus.battle;

import characters.Creature;
import characters.Dragon;
import characters.Player;
import menus.Menu;
import save.SaveUtils;

import java.io.PrintStream;

/*
 * Abstract class that represents all losses
 */
public abstract class LossMenu extends Menu {

    private final Creature creature;
    private final int playerDamage;

    protected LossMenu(Creature creature, int playerDamage) {
        this.creature = creature;
        this.playerDamage = playerDamage;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println(playerMoveDisplay());
        stream.println(creature.displayAttack());
        if(playerDamage > 0) {
            stream.println("It deals " + playerDamage + " damage to you!");
        }
        else {
            stream.println("It deals no damage to you!");
        }
        stream.println("You have died to the " + creature.display() + "!");
        if (creature instanceof Dragon) {
            stream.println("The dragon adds you to its pile of corpses");
        }
    }

    @Override
    protected Menu manageInput(String string) {
        SaveUtils.deleteTempFiles();
        return null;
    }

    public Creature getCreature() {
        return creature;
    }

    /*
     * The display for the player's move
     */
    protected abstract String playerMoveDisplay();
}
