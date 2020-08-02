import menus.mainmenu.MainMenu;
import menus.Menu;

import java.io.IOException;

public class EscapeTheDungeon {
    public static void main(String[] args) throws IOException {
        Menu currentMenu = new MainMenu();
        while (currentMenu != null) {
            currentMenu = currentMenu.run(System.out, System.in);
        }
    }
}