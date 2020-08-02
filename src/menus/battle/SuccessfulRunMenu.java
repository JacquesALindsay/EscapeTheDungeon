package menus.battle;

import characters.Creature;
import characters.Dragon;
import characters.Player;
import menus.Menu;
import menus.exploration.ExplorationMenu;

import java.io.PrintStream;

/*
 * This menu represents the player sucessfully running away from the opponent
 */
public class SuccessfulRunMenu extends Menu {

    private final Player player;
    private final Creature creature;

    public SuccessfulRunMenu(Player player, Creature creature) {
        this.player = player;
        this.creature = creature;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("You tried to run from the " + creature.display() + "... and succeeded!");
        if (creature instanceof Dragon) {
            stream.println("Afraid to face the dragon again, you are forever stuck in this dungeon. You lose!");
        }
    }

    @Override
    protected Menu manageInput(String input) {
        player.reinitializeStatBoosts();
        return new ExplorationMenu(player);
    }
}
