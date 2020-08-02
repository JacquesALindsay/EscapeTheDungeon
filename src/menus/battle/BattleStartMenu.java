package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * The menu that displays when the battle starts
 */
public class BattleStartMenu extends BattleMenu {

    public BattleStartMenu(Player player, Creature creature) {
        super(player, creature);
    }

    @Override
    protected String turnDisplay() {
        return "You are fighting against a " + getCreature().display() + "!";
    }
}
