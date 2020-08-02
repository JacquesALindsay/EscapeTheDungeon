package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * This class represents failing to run from the opposing creature
 */
public class FailedRunMenu extends MoveMenu {

    public FailedRunMenu(Player player, Creature creature, int damage) {
        super(player, creature, damage);
    }

    @Override
    protected String playerMoveDisplay() {
        return "You tried to run from the " + getCreature().display() + " but failed!";
    }
}
