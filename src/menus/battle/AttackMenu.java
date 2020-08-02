package menus.battle;

import characters.Creature;
import characters.Player;

/*
 * A menu that is called when you use an attacking spell
 */
public class AttackMenu extends MoveMenu {

    private final String playerMove;
    private final int creatureDamage;

    public AttackMenu(Player player, Creature creature, int playerDamage, String playerMove, int creatureDamage) {
        super(player, creature, playerDamage);
        this.playerMove = playerMove;
        this.creatureDamage = creatureDamage;
    }

    @Override
    protected String playerMoveDisplay() {
        StringBuilder builder = new StringBuilder();
        builder.append("You cast a ")
                .append(playerMove)
                .append(" on the ")
                .append(getCreature().display());
        if (creatureDamage > 0) {
            builder.append("!")
                    .append("\r\n")
                    .append("You deal ")
                    .append(creatureDamage)
                    .append(" damage to it!");
        }
        else {
            builder.append(", but the spell fails!");
        }
        return builder.toString();
    }
}
