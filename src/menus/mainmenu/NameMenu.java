package menus.mainmenu;
import characters.CharacterBuilder;
import menus.Menu;
import menus.mainmenu.AttributeMenu;

import java.io.PrintStream;

/*
 * This menu asks for a name and assigns it to the player
 */
public class NameMenu extends Menu {

    @Override
    protected void display(PrintStream stream) {
        stream.println("Enter your name!");
    }

    @Override
    protected Menu manageInput(String input) {
        return new AttributeMenu(new CharacterBuilder(input));
    }
}
