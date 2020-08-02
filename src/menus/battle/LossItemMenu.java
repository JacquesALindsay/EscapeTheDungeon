package menus.battle;

import characters.Creature;
import characters.Player;
import menus.Menu;

/*
 * This class represents a loss after using an item in battle
 */
public class LossItemMenu extends LossMenu {

    private final String item;

    public LossItemMenu(Creature creature, int playerDamage, String item) {
        super(creature, playerDamage);
        this.item = item;
    }

    @Override
    protected String playerMoveDisplay() {
        return "You use a " + item + "!";
    }
}