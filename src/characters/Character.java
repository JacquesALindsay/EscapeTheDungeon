package characters;

import exploration.Position;

import java.util.Random;

public abstract class Character {

    private String name;
    private String attribute;
    private int level;
    private int maxHP;
    private int attack;
    private int defense;
    private int luck;
    private int currentHP;
    private int attackBoost;
    private int defenseBoost;

    /*
     * Generates a character based on its data under the form of a String
     */
    protected Character(String characterData) {
        String[] splitData = characterData.split("\r\n");
        this.name = splitData[0];
        this.attribute = splitData[1];
        this.level = Integer.parseInt(splitData[2]);
        this.maxHP = Integer.parseInt(splitData[3]);
        this.attack = Integer.parseInt(splitData[4]);
        this.defense = Integer.parseInt(splitData[5]);
        this.luck = Integer.parseInt(splitData[6]);
        this.currentHP = Integer.parseInt(splitData[7]);
        reinitializeStatBoosts();
    }

    protected Character(String name, String attribute, int level, int maxHP, int attack, int defense, int luck, int currentHP) {
        this.name = name;
        this.attribute = attribute;
        this.level = level;
        this.maxHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.luck = luck;
        this.currentHP = currentHP;
        reinitializeStatBoosts();
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getLuck() {
        return luck;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    /*
     * Heals up the character
     */
    public void healUp() {
        currentHP = maxHP;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    /*
     * Boosts the creature's Attack
     */
    public void boostAttack() {
        attackBoost++;
    }

    /*
     * Boosts the creature's Defense
     */
    public void boostDefense() {
        defenseBoost++;
    }

    /*
     * Calculates the damage dealt to this creature by another character using a move of a certain power and a certain accuracy and deals it
     */
    public int calculateDamage(Character character, int power, double accuracy) {
        // this ratio is used to calculate misses
        Random random = new Random();
        double ratio = ((double) character.getLuck()) / ((double) this.getLuck()) * accuracy;
        // if the move hits
        if (random.nextFloat() < ratio) {
            int damage = power * character.getAttackBoost() * character.getAttack() / (defense * defenseBoost);
            currentHP -= damage;
            if (currentHP < 0) {
                currentHP = 0;
            }
            return damage;
        }
        else {
            return 0;
        }
    }

    /*
     * Returns true if the character has lost, false otherwise
     */
    public boolean hasLost() {
        return currentHP == 0;
    }

    /*
     * Reinitializes the stat boosts
     */
    public void reinitializeStatBoosts() {
        attackBoost = 1;
        defenseBoost = 1;
    }
}
