package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * A menu that is called when you use an item in battle
 */
public class ItemMenu extends MoveMenu {

    private final String item;

    public ItemMenu(Player player, Creature creature, int playerDamage, String item) {
        super(player, creature, playerDamage);
        this.item = item;
    }

    @Override
    protected String playerMoveDisplay() {
        return "You use a " + item + "!";
    }
}
