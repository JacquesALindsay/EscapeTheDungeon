package menus.battle;

import characters.Creature;
import characters.Player;
import menus.Menu;

/*
 * The menu that displays in battle when you post an invalid command
 */
public class InvalidCommandMenu extends BattleMenu {

    public InvalidCommandMenu(Player player, Creature creature) {
        super(player, creature);
    }

    @Override
    protected String turnDisplay() {
        return "Please input a valid command!";
    }
}
