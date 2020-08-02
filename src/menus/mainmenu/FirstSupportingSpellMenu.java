package menus.mainmenu;

import characters.CharacterBuilder;
import menus.Menu;

import java.io.PrintStream;

/*
 This menu allows the use to select the first supporting spell
 */
public class FirstSupportingSpellMenu extends Menu {

    private final CharacterBuilder builder;

    public FirstSupportingSpellMenu(CharacterBuilder builder) {
        this.builder = builder;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("Choose your first supporting spell!");
        stream.println("Available supporting spells are:\r\n" +
                "- Magic Healing\r\n" +
                "- Magic Sword\r\n" +
                "- Magic Shield");
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "healing":
            case "magic healing":
                return new SecondSupportingSpellMenu(builder.addToSpells("Magic Healing"), "Magic Healing");
            case "sword":
            case "magic sword":
                return new SecondSupportingSpellMenu(builder.addToSpells("Magic Sword"), "Magic Sword");
            case "shield":
            case "magic shield":
                return new SecondSupportingSpellMenu(builder.addToSpells("Magic Shield"), "Magic Shield");
            default: return this;
        }
    }
}