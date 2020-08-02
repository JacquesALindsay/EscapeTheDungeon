package menus.exploration;

import characters.Player;
import menus.Menu;
import menus.exploration.ExplorationMenu;

import java.io.PrintStream;

/*
 * This menu displays the player's info
 */
public class PlayerMenu extends Menu {

    private final Player player;

    public PlayerMenu(Player player) {
        this.player = player;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.print(player.display());
    }

    @Override
    protected Menu manageInput(String input) {
        return new ExplorationMenu(player);
    }
}
