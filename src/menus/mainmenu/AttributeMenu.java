package menus.mainmenu;

import characters.CharacterBuilder;
import menus.Menu;

import java.io.PrintStream;

/*
 * This menu asks for an attribute and assigns it to the player
 */
public class AttributeMenu extends Menu {

    private final CharacterBuilder builder;

    public AttributeMenu(CharacterBuilder builder) {
        this.builder = builder;
    }

    @Override
    protected void display(PrintStream stream) {
        stream.println("Choose your attribute!");
        stream.println("Available attributes are:\r\n" +
                "- Healthy\r\n" +
                "- Wise\r\n" +
                "- Strong\r\n" +
                "- Careful\r\n" +
                "- Lucky");
    }

    @Override
    protected Menu manageInput(String input) {
        switch (input) {
            case "healthy":
                builder.setAttribute("Healthy");
                return new FirstAttackingSpellMenu(builder);
            case "wise":
                builder.setAttribute("Wise");
                return new FirstAttackingSpellMenu(builder);
            case "strong":
                builder.setAttribute("Strong");
                return new FirstAttackingSpellMenu(builder);
            case "careful":
                builder.setAttribute("Careful");
                return new FirstAttackingSpellMenu(builder);
            case "lucky":
                builder.setAttribute("Lucky");
                return new FirstAttackingSpellMenu(builder);
            case "legendary":
                builder.setAttribute("Legendary");
                return new FirstAttackingSpellMenu(builder);
            default:
                return this;
        }
    }
}
