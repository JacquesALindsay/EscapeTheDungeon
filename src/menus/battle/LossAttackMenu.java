package menus.battle;

import characters.Creature;
import characters.Player;
import menus.Menu;

/*
 * This class represents a loss after choosing an attacking spell
 */
public class LossAttackMenu extends LossMenu {

    private final String playerMove;
    private final int creatureDamage;

    public LossAttackMenu(Creature creature, int playerDamage, String playerMove, int creatureDamage) {
        super(creature, playerDamage);
        this.playerMove = playerMove;
        this.creatureDamage = creatureDamage;
    }

    @Override
    protected String playerMoveDisplay() {
        StringBuilder builder = new StringBuilder();
        builder.append("You cast ")
                .append(playerMove)
                .append(" on the ")
                .append(getCreature().display());
        if (creatureDamage > 0) {
            builder.append("!")
                    .append("\r\n")
                    .append("It deals ")
                    .append(creatureDamage)
                    .append(" damage to it!");
        }
        else {
            builder.append(", but the spell fails!");
        }
        return builder.toString();
    }
}
