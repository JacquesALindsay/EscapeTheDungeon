package menus.exploration;

import characters.Player;
import menus.Menu;
import menus.exploration.ExplorationMenu;

import java.io.PrintStream;

/*
 * This menu displays the items
 */
public class BagMenu extends Menu {

    private final Player player;

    public BagMenu(Player player) {
        this.player = player;
    }

    @Override
    protected void display(PrintStream stream) {
        if (player.getHealthPotions() > 0) {
            stream.println("Health Potion x" + player.getHealthPotions());
        }
        if (player.getHealthPotions() > 0) {
            stream.println("Magic Potion x" + player.getMagicPotions());
        }
    }

    @Override
    protected Menu manageInput(String input) {
        return new ExplorationMenu(player);
    }
}
