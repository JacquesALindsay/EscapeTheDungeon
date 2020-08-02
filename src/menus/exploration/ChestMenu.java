package menus.exploration;

import characters.Player;
import exploration.Zone;
import menus.Menu;
import save.SaveUtils;

import java.io.PrintStream;
import java.util.Random;

/*
 * This menu represents the opening of a chest
 */
public class ChestMenu extends Menu {

    private final Player player;

    public ChestMenu(Player player) {
        this.player = player;
    }

    @Override
    protected void display(PrintStream stream) {
        Random random = new Random();
        int gained = random.nextInt(player.getLuck())+1;
        if (random.nextInt(2) == 0) {
            player.gainHealthPotions(gained);
            stream.println("You opened a chest and gained " + gained + " health potions!");
        }
        else {
            player.gainMagicPotions(gained);
            stream.println("You opened a chest and gained " + gained + " magic potions!");
        }
        SaveUtils.saveTile(player.getPosition(), Zone.EMPTY);
    }

    @Override
    protected Menu manageInput(String input) {
        return new ExplorationMenu(player);
    }
}