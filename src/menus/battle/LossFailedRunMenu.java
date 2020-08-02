package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * This class represents losing to a creature after failing to run away from it
 */
public class LossFailedRunMenu extends LossMenu {

    public LossFailedRunMenu(Creature creature, int playerDamage) {
        super(creature, playerDamage);
    }

    @Override
    protected String playerMoveDisplay() {
        return "You tried to run from the " + getCreature().display() + " but failed!";
    }
}