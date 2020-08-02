package menus.mainmenu;

import menus.Menu;
import menus.exploration.ExplorationMenu;
import save.SaveUtils;

import java.io.*;

/*
 * This menu initializes the game and asks to create a new game or continue
 */
public class MainMenu extends Menu {

    @Override
    protected void display(PrintStream stream) {
        stream.println("Welcome to Escape the Dungeon!");
        stream.println("Do you want to start a NEW game, or CONTINUE your saved game?");
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "n": case "new": case "new game":
                return newGame();
            case "l": case "load": case "c": case "continue":
                if (SaveUtils.playerFilePresent()) {
                    return continueGame();
                }
                // we can't continue if we don't have a save file
                else {
                    return newGame();
                }
            default:
                return this;
        }
    }

    /*
     * starts a new game
     */
    private NameMenu newGame() {
        // we generate the temp map from the default map
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.TEMP_MAP);
        return new NameMenu();
    }

    /*
     * continues a game based on the data from the file
     */
    private ExplorationMenu continueGame() {
        try {
            // we generate the temp map from the saved map
            SaveUtils.copyMap(SaveUtils.SAVED_MAP, SaveUtils.TEMP_MAP);
            return new ExplorationMenu(SaveUtils.loadPlayer());
        }
        catch(IOException e) {
            throw new RuntimeException("Should never happen!");
        }
    }
}
