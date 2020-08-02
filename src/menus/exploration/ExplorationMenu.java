package menus.exploration;

import characters.Dragon;
import characters.Player;
import characters.Slime;
import exploration.Position;
import exploration.Zone;
import menus.*;
import menus.battle.BattleStartMenu;
import save.SaveUtils;

import java.io.PrintStream;
import java.util.Random;

/*
 * The primary menu for exploration
 */
public class ExplorationMenu extends Menu {

    private final Player player;
    private final Zone zone;

    public ExplorationMenu(Player player) {
        this.player = player;
        this.zone = new Zone(player.getPosition());
    }

    @Override
    protected void display(PrintStream stream) {
        stream.print(zone.display());
    }

    @Override
    protected Menu manageInput(String input) {
        Position newPosition;
        switch (input) {
            case "^": case "u": case "up": case "n": case "north":
                newPosition = player.getPosition().up();
                return moveNewPosition(newPosition);
            case "v": case "d": case "down": case "s": case "south":
                newPosition = player.getPosition().down();
                return moveNewPosition(newPosition);
            case "<": case "l": case "left": case "w": case "west":
                newPosition = player.getPosition().left();
                return moveNewPosition(newPosition);
            case ">": case "r": case "right": case "e": case "east":
                newPosition = player.getPosition().right();
                return moveNewPosition(newPosition);
            case "m": case "map":
                return new MapMenu(player);
            case "b": case "bag":
                return new BagMenu(player);
            case "save":
                return new SaveMenu(player);
            case "p": case "player":
                return new PlayerMenu(player);
            case "health": case "health potion":
                return new ExplorationMenu(player.useHealthPotion());
            case "magic": case "magic potion":
                return new ExplorationMenu(player.useMagicPotion());
            case "x": case "exit":
                SaveUtils.deleteTempFiles();
                return null;
            default:
                return this;
        }
    }

    /*
     * moves the player to the new position if possible and returns the next menu
     */
    private Menu moveNewPosition(Position newPosition) {
        if (zone.canMove(newPosition)) {
            if (zone.link(newPosition) != null) {
                newPosition = zone.link(newPosition);
                player.discover(newPosition.getZone());
            }
            player.setPosition(newPosition);
        }
        // if it is a chest, we open it remove it in the save data
        if (zone.isChest(newPosition)) {
            return new ChestMenu(player);
        }
        else if (newPosition.equals(Dragon.SPAWN)) {
            return new BattleStartMenu(player, new Dragon());
        }
        else {
            Random random = new Random();
            if (random.nextInt(player.getLuck()) == 0) {
                int index = random.nextInt(4);
                switch (index) {
                    case 0: return new BattleStartMenu(player, new Slime("Healthy", 10, 5, 5, 5));
                    case 1: return new BattleStartMenu(player, new Slime("Strong", 5, 10, 5, 5));
                    case 2: return new BattleStartMenu(player, new Slime("Careful", 5, 5, 10, 5));
                    case 3: return new BattleStartMenu(player, new Slime("Lucky", 5, 5, 5, 10));
                }
            }
        }
        return new ExplorationMenu(player);
    }
}
