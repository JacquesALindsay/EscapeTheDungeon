package menus.battle;

import characters.Creature;
import characters.Dragon;
import characters.Player;
import menus.Menu;
import menus.exploration.ExplorationMenu;
import save.SaveUtils;

import java.io.PrintStream;

/*
 * This menu represents a battle win
 */
public class WinMenu extends Menu {

    private final String playerMove;
    private final Player player;
    private final Creature creature;
    private final int damage;

    public WinMenu(Player player, Creature creature, String playerMove, int damage) {
        this.player = player;
        this.creature = creature;
        this.playerMove = playerMove;
        this.damage = damage;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("You cast a " + playerMove + " on the " + creature.display() + "!");
        stream.println("You deal " + damage + " damage to it!");
        stream.println("The " + creature.display() + " dies! You win!");
        stream.println("You gain " + creature.getExpYield() + " experience points!");
        if (creature instanceof Dragon) {
            stream.println("Congratulations! You have found your way out of the dungeon! You win!");
        }
    }

    @Override
    protected Menu manageInput(String input) {
        player.gainExp(creature.getExpYield());
        player.reinitializeStatBoosts();
        if (creature instanceof Dragon) {
            SaveUtils.deleteTempFiles();
            return null;
        }
        else {
            return new ExplorationMenu(player);
        }
    }
}
