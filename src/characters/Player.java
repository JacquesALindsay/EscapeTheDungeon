package characters;

import exploration.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Player extends Character {

    private int maxMagic;
    private int currentMagic;
    private int exp;
    private final ArrayList<String> spells;
    private int healthPotions;
    private int magicPotions;

    private Position position;
    private final HashSet<Integer> foundZones;

    /*
     * Generates a player based on its data under the form of a String
     */
    public Player(String playerData) {
        super(playerData);
        String[] splitData = playerData.split("\r\n");
        this.maxMagic = Integer.parseInt(splitData[8]);
        this.currentMagic = Integer.parseInt(splitData[9]);
        this.exp = Integer.parseInt(splitData[10]);
        this.spells = new ArrayList<>();
        spells.add(splitData[11]);
        spells.add(splitData[12]);
        spells.add(splitData[13]);
        spells.add(splitData[14]);
        this.healthPotions = Integer.parseInt(splitData[15]);
        this.magicPotions = Integer.parseInt(splitData[16]);
        this.position = new Position(Integer.parseInt(splitData[17]), Integer.parseInt(splitData[18]), Integer.parseInt(splitData[19]));
        this.foundZones = new HashSet<>();
        for(String zone : splitData[20].split(" ")) {
            foundZones.add(Integer.parseInt(zone));
        }
    }

    @Override
    public boolean hasLost() {
        return super.hasLost() || currentMagic == 0;
    }

    /*
     * This constructor is package protected to force creation via the builder
     */
    Player(String name, String attribute, int level, int maxHP, int attack, int defense, int luck, int currentHP, int maxMagic, int currentMagic, int exp, ArrayList<String> spells, int healthPotions, int magicPotions) {
        super(name, attribute, level, maxHP, attack, defense, luck, currentHP);
        this.maxMagic = maxMagic;
        this.currentMagic = currentMagic;
        this.exp = exp;
        this.spells = spells;
        this.healthPotions = healthPotions;
        this.position = Position.DEFAULT_POSITION;
        this.foundZones = new HashSet<>();
        this.magicPotions = magicPotions;
    }

    /*
     * Displays the player
     */
    public String display() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(" the Level ");
        builder.append(getLevel());
        builder.append(" ");
        builder.append(getAttribute());
        builder.append(" Wizard\r\n");
        builder.append("HP: ");
        builder.append(getCurrentHP());
        builder.append("/");
        builder.append(getMaxHP());
        builder.append("\r\n");
        builder.append("Magic: ");
        builder.append(getCurrentMagic());
        builder.append("/");
        builder.append(getMaxMagic());
        builder.append("\r\n");
        builder.append("Attack: ");
        builder.append(getAttack());
        builder.append("\r\n");
        builder.append("Defense: ");
        builder.append(getDefense());
        builder.append("\r\n");
        builder.append("Luck: ");
        builder.append(getLuck());
        builder.append("\r\n");
        builder.append("Experience: ");
        builder.append(getExp());
        builder.append("\r\n");
        builder.append("Spells:\r\n");
        for(String spell : spells) {
            builder.append(spell);
            builder.append("\r\n");
        }
        return builder.toString();
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public ArrayList<String> getSpells() {
        return spells;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    /*
     * Player gains a certain number of health potions
     */
    public void gainHealthPotions(int gained) {
        this.healthPotions += gained;
    }

    /*
     * returns the player after they usa a health potion
     */
    public Player useHealthPotion() {
        if (healthPotions > 0) {
            healthPotions--;
            healUp();
        }
        return this;
    }

    public int getMagicPotions() {
        return magicPotions;
    }

    /*
     * Player gains a certain number of magic potions
     */
    public void gainMagicPotions(int gained) {
        this.magicPotions += gained;
    }

    /*
     * Player gains a certain number of magic potions
     */
    public Player useMagicPotion() {
        if (magicPotions > 0) {
            magicPotions--;
            currentMagic = maxMagic;
        }
        return this;
    }

    public int getExp() {
        return exp;
    }

    /*
     * Player gains a certain amount of EXP
     */
    public void gainExp(int gained) {
        exp += gained;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /*
     * This function returns true if the zone has been found
     */
    public boolean hasFound(int zone) {
        return foundZones.contains(zone);
    }

    /*
     * This function adds a zone to the discovered zones
     */
    public void discover(int zone) {
        foundZones.add(zone);
    }

    /*
     * Player uses up a certain amount of magic
     */
    public void useMagic(int cost) {
        currentMagic -= cost;
    }
}
