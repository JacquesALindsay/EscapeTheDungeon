package test.exploration;

import characters.Player;
import menus.exploration.BagMenu;
import menus.exploration.ExplorationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;

public class BagMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private InputStream inputStream;

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
    public void testOpenBagMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Bag".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(printStream, inputStream) instanceof BagMenu);
    }

    @Test
    public void testBagMenuDisplay() throws IOException {
        new BagMenu(player).run(printStream, defaultInputStream);
        Assertions.assertEquals("Health Potion x15\r\n" +
                "Magic Potion x14\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testBagMenuExit() throws IOException {
        Assertions.assertTrue(new BagMenu(player).run(emptyPrintStream, defaultInputStream) instanceof ExplorationMenu);
    }
}
