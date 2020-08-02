package menus.exploration;

import characters.Player;
import menus.Menu;
import save.SaveUtils;

import java.io.PrintStream;

/*
 * This menu represents saving
 */
public class SaveMenu extends Menu {

    private final Player player;

    public SaveMenu(Player player) {
        this.player = player;

    }

    @Override
    protected void display(PrintStream stream) {
        SaveUtils.copyMap(SaveUtils.TEMP_MAP, SaveUtils.SAVED_MAP);
        SaveUtils.savePlayer(player);
        stream.println("Progress saved!");
    }

    @Override
    protected Menu manageInput(String input) {
        return new ExplorationMenu(player);
    }
}
