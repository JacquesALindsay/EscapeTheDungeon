package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * A menu that is called when you use a support spell
 */
public class SupportMenu extends MoveMenu {

    private final String playerMove;

    public SupportMenu(Player player, Creature creature, int playerDamage, String playerMove) {
        super(player, creature, playerDamage);
        this.playerMove = playerMove;
    }

    @Override
    protected String playerMoveDisplay() {
        return "You cast " + playerMove + "!";
    }
}
