package test.exploration;

import characters.Player;
import menus.exploration.ExplorationMenu;
import menus.exploration.MapMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MapMenuTest {

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
    public void testOpenMapMenu() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Map".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(emptyPrintStream, inputStream) instanceof MapMenu);
    }

    @Test
    public void testMapMenuDisplay() throws IOException {
        new MapMenu(player).run(printStream, defaultInputStream);
        Assertions.assertEquals("X  \r\n" +
                                        "PX \r\n" +
                                        "   \r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testMapMenuExit() throws IOException {
        Assertions.assertTrue(new MapMenu(player).run(emptyPrintStream, defaultInputStream) instanceof ExplorationMenu);
    }
}