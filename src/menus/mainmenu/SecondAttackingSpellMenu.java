package menus.mainmenu;

import characters.CharacterBuilder;
import menus.Menu;
import menus.mainmenu.FirstSupportingSpellMenu;

import java.io.PrintStream;

/*
 This menu allows the use to select the second attacking spell
 */
public class SecondAttackingSpellMenu extends Menu {

    private final CharacterBuilder builder;
    // we need to know the previously selected spell, if it is null, then this is the first attacking spell selected
    private final String previousSpell;

    public SecondAttackingSpellMenu(CharacterBuilder builder, String previousSpell) {
        this.builder = builder;
        this.previousSpell = previousSpell;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("Choose your second attacking spell!");
        stream.println("Available attacking spells are:");
        if (!previousSpell.equals("Neutral Attack")) {
            stream.println("- Neutral Attack");
        }
        if (!previousSpell.equals("Strong Attack")) {
            stream.println("- Strong Attack");
        }
        if (!previousSpell.equals("Risky Attack")) {
            stream.println("- Risky Attack");
        }
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "neutral": case "neutral attack":
                if (previousSpell.equals("Neutral Attack")) {
                    return this;
                }
                else {
                    return new FirstSupportingSpellMenu(builder.addToSpells("Neutral Attack"));
                }
            case "strong": case "strong attack":
                if (previousSpell.equals("Strong Attack")) {
                    return this;
                }
                else {
                    return new FirstSupportingSpellMenu(builder.addToSpells("Strong Attack"));
                }
            case "risky": case "risky attack":
                if (previousSpell.equals("Risky Attack")) {
                    return this;
                }
                else {
                    return new FirstSupportingSpellMenu(builder.addToSpells("Risky Attack"));
                }
            default:
                return this;
        }
    }
}
