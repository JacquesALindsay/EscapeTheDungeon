package menus.mainmenu;

import characters.CharacterBuilder;
import menus.Menu;

import java.io.PrintStream;

/*
 This menu allows the use to select the first attacking spell
 */
public class FirstAttackingSpellMenu extends Menu {

    private final CharacterBuilder builder;

    public FirstAttackingSpellMenu(CharacterBuilder builder) {
        this.builder = builder;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("Choose your first attacking spell!");
        stream.println("Available attacking spells are:\r\n" +
                "- Neutral Attack\r\n" +
                "- Strong Attack\r\n" +
                "- Risky Attack");
    }

    @Override
    protected Menu manageInput(String input) {
         switch (input) {
             case "neutral":
             case "neutral attack" :
                 return new SecondAttackingSpellMenu(builder.addToSpells("Neutral Attack"), "Neutral Attack");
             case "strong":
             case "strong attack" :
                 return new SecondAttackingSpellMenu(builder.addToSpells("Strong Attack"), "Strong Attack");
             case "risky":
             case "risky attack" :
                return new SecondAttackingSpellMenu(builder.addToSpells("Risky Attack"), "Risky Attack");
            default : return this;
        }
    }
}