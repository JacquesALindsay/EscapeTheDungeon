package menus.battle;

import characters.Creature;
import characters.Player;
import menus.Menu;
import save.SaveUtils;

import java.io.PrintStream;
import java.util.Random;

/*
 * Superclass of all the classes that represent a battle between the player and a creature
 */
public abstract class BattleMenu extends Menu {

    private final Player player;

    private final Creature creature;

    protected BattleMenu(Player player, Creature creature) {
        this.player = player;
        this.creature = creature;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println(turnDisplay());
        stream.println("What do you do?");
        stream.println("Current moves:");
        for(String spell : player.getSpells()) {
            stream.println(spell);
        }
        stream.println("Current HP: " + player.getCurrentHP() + " / " + player.getMaxHP());
        stream.println("Current Magic: " + player.getCurrentMagic() + " / " + player.getMaxMagic());
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "neutral": case "neutral attack":
                if (player.getSpells().contains("Neutral Attack")) {
                    return turn("Neutral Attack");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "strong": case "strong attack":
                if (player.getSpells().contains("Strong Attack")) {
                    return turn("Strong Attack");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "risky": case "risky attack":
                if (player.getSpells().contains("Risky Attack")) {
                    return turn("Risky Attack");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "healing":
            case "magic healing":
                if (player.getSpells().contains("Magic Healing")) {
                    return turn("Magic Healing");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "sword":
            case "magic sword":
                if (player.getSpells().contains("Magic Sword")) {
                    return turn("Magic Sword");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "shield":
            case "magic shield":
                if (player.getSpells().contains("Magic Shield")) {
                    return turn("Magic Shield");
                } else {
                    return new InvalidCommandMenu(player, creature);
                }
            case "health": case "health potion":
                return turn("Health Potion");
            case "magic": case "magic potion":
                return turn("Magic Potion");
            case "r": case "run":
                return turn("Run");
            case "x": case "exit":
                SaveUtils.deleteTempFiles();
                return null;
            default:
                return new InvalidCommandMenu(player, creature);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Creature getCreature() {
        return creature;
    }

    /*
     * Performs a turn and returns the appropriate menu
     */
    private Menu turn(String playerMove) {
        Random random = new Random();
        // run attempt
        if (playerMove.equals("Run")) {
            if (random.nextInt(2) == 0) {
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (creature.hasLost()) {
                    return new LossFailedRunMenu(creature, playerDamage);
                }
                return new FailedRunMenu(player, creature, playerDamage);
            }
            else {
                return new SuccessfulRunMenu(player, creature);
            }
        }
        // risky attack is less accurate
        switch (playerMove) {
            case "Neutral Attack" : {
                int creatureDamage = creature.calculateDamage(player, 2, 1);
                player.useMagic(1);
                if (creature.hasLost()) {
                    return new WinMenu(player, creature, playerMove, creatureDamage);
                }
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossAttackMenu(creature, playerDamage, playerMove, creatureDamage);
                }
                return new AttackMenu(player, creature, playerDamage, playerMove, creatureDamage);
            }
            // powerful attack is stronger
            case "Strong Attack" : {
                int creatureDamage = creature.calculateDamage(player, 4, 1);
                player.useMagic(2);
                if (creature.hasLost()) {
                    return new WinMenu(player, creature, playerMove, creatureDamage);
                }
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossAttackMenu(creature, playerDamage, playerMove, creatureDamage);
                }
                return new AttackMenu(player, creature, playerDamage, playerMove, creatureDamage);
            }
            // risky attack is less accurate
            case "Risky Attack" : {
                int creatureDamage = creature.calculateDamage(player, 4, 0.5);
                player.useMagic(1);
                if (creature.hasLost()) {
                    return new WinMenu(player, creature, playerMove, creatureDamage);
                }
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossAttackMenu(creature, playerDamage, playerMove, creatureDamage);
                }
                return new AttackMenu(player, creature, playerDamage, playerMove, creatureDamage);
            }
            case "Magic Healing" : {
                player.healUp();
                player.useMagic(2);
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossSupportMenu(creature, playerDamage, playerMove);
                }
                return new SupportMenu(player, creature, playerDamage, playerMove);
            }
            case "Magic Sword" : {
                player.boostAttack();
                player.useMagic(2);
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossSupportMenu(creature, playerDamage, playerMove);
                }
                return new SupportMenu(player, creature, playerDamage, playerMove);
            }
            case "Magic Shield" : {
                player.boostDefense();
                player.useMagic(2);
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossSupportMenu(creature, playerDamage, playerMove);
                }
                return new SupportMenu(player, creature, playerDamage, playerMove);
            }
            case "Health Potion" : {
                player.useHealthPotion();
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossItemMenu(creature, playerDamage, playerMove);
                }
                return new ItemMenu(player, creature, playerDamage, playerMove);
            }
            case "Magic Potion" : {
                player.useMagicPotion();
                int playerDamage = player.calculateDamage(creature, creature.attackPower(), creature.attackAccuracy());
                if (player.hasLost()) {
                    return new LossItemMenu(creature, playerDamage, playerMove);
                }
                return new ItemMenu(player, creature, playerDamage, playerMove);
            }
            default : throw new RuntimeException("Should never happen!");
        }
    }

    /*
     * The display that is specific to the turn of the battle
     */
    protected abstract String turnDisplay();

}
