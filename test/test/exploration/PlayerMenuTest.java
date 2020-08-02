package test.exploration;
import characters.Player;
import menus.exploration.ExplorationMenu;
import menus.exploration.PlayerMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

class PlayerMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    private Player player;

    @BeforeEach
    public void beforeEach() {
        defaultInputStream = new ByteArrayInputStream("default".getBytes());
        emptyPrintStream = new PrintStream(new ByteArrayOutputStream());
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.TEMP_MAP);
        player = new Player("Timothy\r\n" +
                "Lucky\r\n" +
                "1\r\n" +
                "5\r\n" +
                "7\r\n" +
                "8\r\n" +
                "9\r\n" +
                "4\r\n" +
                "6\r\n" +
                "3\r\n" +
                "500\r\n" +
                "Strong Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n" +
                "15\r\n" +
                "14\r\n" +
                "4\r\n" +
                "9\r\n" +
                "2\r\n" +
                "1 5 4\r\n");
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testOpenPlayerMenu() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Player".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(emptyPrintStream, inputStream) instanceof PlayerMenu);
    }

    @Test
    public void testPlayerMenuDisplay() throws IOException {
        new PlayerMenu(player).run(printStream, defaultInputStream);
        Assertions.assertEquals("Timothy the Level 1 Lucky Wizard\r\n" +
                "HP: 4/5\r\n" +
                "Magic: 3/6\r\n" +
                "Attack: 7\r\n" +
                "Defense: 8\r\n" +
                "Luck: 9\r\n" +
                "Experience: 500\r\n" +
                "Spells:\r\n" +
                "Strong Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testPlayerMenuExit() throws IOException {
        Assertions.assertTrue(new PlayerMenu(player).run(emptyPrintStream, defaultInputStream) instanceof ExplorationMenu);
    }

}