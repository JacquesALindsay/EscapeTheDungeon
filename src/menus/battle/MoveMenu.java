package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * A battle menu where the player moves
 */
public abstract class MoveMenu extends BattleMenu {

    // the damage dealt by the creature
    private final int damage;

    protected MoveMenu(Player player, Creature creature, int damage) {
        super(player, creature);
        this.damage = damage;
    }

    @Override
    protected String turnDisplay() {
        StringBuilder builder = new StringBuilder();
        builder.append(playerMoveDisplay())
                .append("\r\n")
                .append(getCreature().displayAttack())
                .append("\r\n");
        if (damage > 0) {
            builder.append("It deals ")
                    .append(damage)
                    .append(" damage to you!");
        }
        else {
            builder.append("It deals no damage to you!");
        }
        return builder.toString();
    }

    /*
     * The display for the player's move
     */
    protected abstract String playerMoveDisplay();
}
