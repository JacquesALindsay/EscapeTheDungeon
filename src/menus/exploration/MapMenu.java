package menus.exploration;

import characters.Player;
import menus.Menu;
import menus.exploration.ExplorationMenu;

import java.io.PrintStream;

public class MapMenu extends Menu {

    private final Player player;

    public MapMenu(Player player) {
        this.player = player;
    }

    @Override
    protected void display(PrintStream stream) {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                char c = ' ';
                if (player.getPosition().getZone() == 1 + 3*i + j) {
                    c = 'P';
                }
                else if (player.hasFound(1 + 3*i + j)) {
                    c = 'X';
                }
                stream.print(c);
            }
            stream.print("\r\n");
        }
    }

    @Override
    protected Menu manageInput(String input) {
        return new ExplorationMenu(player);
    }
}
