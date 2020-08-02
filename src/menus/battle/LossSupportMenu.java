package menus.battle;

import characters.Creature;
import characters.Player;
import menus.Menu;

/*
 * This class represents a loss after choosing a supporting spell
 */
public class LossSupportMenu extends LossMenu {

    private final String playerMove;

    public LossSupportMenu(Creature creature, int playerDamage, String playerMove) {
        super(creature, playerDamage);
        this.playerMove = playerMove;
    }

    @Override
    protected String playerMoveDisplay() {
        return "You cast " + playerMove + "!";
    }
}