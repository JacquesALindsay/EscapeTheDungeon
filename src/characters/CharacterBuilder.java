package characters;

import java.util.ArrayList;

public class CharacterBuilder {

    private final String name;
    private String attribute;
    private final ArrayList<String> spells;

    public CharacterBuilder(String name) {
        this.name = name;
        spells = new ArrayList<>();
    }

    public CharacterBuilder setAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public CharacterBuilder addToSpells(String spell) {
        spells.add(spell);
        return this;
    }

    public Player createPlayer() {
        switch (attribute) {
            case "Healthy":
                return new Player(name, "Healthy", 1, 10, 5, 5, 5, 10, 5, 5, 0, spells, 10,10);
            case "Wise":
                return new Player(name, "Wise", 1, 5, 5, 5, 5, 5, 10, 10, 0, spells, 10, 10);
            case "Strong":
                return new Player(name, "Wise", 1, 5, 10, 5, 5, 5, 5, 5, 0, spells, 10, 10);
            case "Careful":
                return new Player(name, "Wise", 1, 5, 5, 10, 5, 5, 5, 5, 0, spells, 10, 10);
            case "Lucky":
                return new Player(name, "Wise", 1, 5, 5, 5, 10, 5, 5, 5, 0, spells, 10, 10);
            default:
                throw new RuntimeException("Should never happen!");
        }
    }
}
