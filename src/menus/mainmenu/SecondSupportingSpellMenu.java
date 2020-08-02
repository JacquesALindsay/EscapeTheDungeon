package menus.mainmenu;

import characters.CharacterBuilder;
import menus.Menu;
import menus.exploration.PlayerMenu;

import java.io.PrintStream;

/*
 This menu allows the use to select the second supporitng spell
 */
public class SecondSupportingSpellMenu extends Menu {

    private final CharacterBuilder builder;
    // we need to know the previously selected spell, if it is null, then this is the first attacking spell selected
    private final String previousSpell;

    public SecondSupportingSpellMenu(CharacterBuilder builder, String previousSpell) {
        this.builder = builder;
        this.previousSpell = previousSpell;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("Choose your second supporting spell!");
        stream.println("Available supporting spells are:");
        if (!previousSpell.equals("Magic Healing")) {
            stream.println("- Magic Healing");
        }
        if (!previousSpell.equals("Magic Sword")) {
            stream.println("- Magic Sword");
        }
        if (!previousSpell.equals("Magic Shield")) {
            stream.println("- Magic Shield");
        }
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "healing": case "magic healing":
                if (previousSpell.equals("Magic Healing")) {
                    return this;
                }
                else {
                    return new PlayerMenu(builder.addToSpells("Magic Healing").createPlayer());
                }
            case "sword": case "magic sword":
                if (previousSpell.equals("Magic Sword")) {
                    return this;
                }
                else {
                    return new PlayerMenu(builder.addToSpells("Magic Sword").createPlayer());
                }
            case "shield": case "magic shield":
                if (previousSpell.equals("Magic Shield")) {
                    return this;
                }
                else {
                    return new PlayerMenu(builder.addToSpells("Magic Shield").createPlayer());
                }
            default:
                return this;
        }
    }
}
