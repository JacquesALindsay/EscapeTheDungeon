package test.exploration;

import characters.Player;
import menus.exploration.ChestMenu;
import menus.exploration.ExplorationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;

public class ChestMenuTest {

    private static InputStream defaultInputStream1;
    private static InputStream defaultInputStream2;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private InputStream inputStream1;
    private InputStream inputStream2;

    private Player player;

    @BeforeEach
    public void beforeEach() {
        defaultInputStream1 = new ByteArrayInputStream("default".getBytes());
        defaultInputStream2 = new ByteArrayInputStream("default".getBytes());
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
                "1000\r\n" +
                "4\r\n" +
                "6\r\n" +
                "3\r\n" +
                "5000\r\n" +
                "Strong Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n" +
                "15\r\n" +
                "14\r\n" +
                "4\r\n" +
                "1\r\n" +
                "4\r\n" +
                "1 5 4\r\n");
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testOpenChestMenu() throws IOException {
        inputStream1 = new ByteArrayInputStream("Left".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(emptyPrintStream, inputStream1) instanceof ChestMenu);
    }

    @Test
    public void testUpdatePlayer() throws IOException {
        inputStream1 = new ByteArrayInputStream("Left".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, defaultInputStream1);
        Assertions.assertTrue(player.getHealthPotions() > 15 || player.getMagicPotions() > 14);
    }

    @Test
    public void testUpdateMap() throws IOException {
        inputStream1 = new ByteArrayInputStream("Left".getBytes());
        inputStream2 = new ByteArrayInputStream("Right".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, defaultInputStream1)
                .run(emptyPrintStream, inputStream2)
                .run(printStream, defaultInputStream2);
        Assertions.assertEquals("#####.#####\r\n" +
                                        "#...P.....#\r\n" +
                                        "#.........#\r\n" +
                                        "#.........#\r\n" +
                                        "#.........#\r\n" +
                                        "#..........\r\n" +
                                        "#....C....#\r\n" +
                                        "#.........#\r\n" +
                                        "#.C....C..#\r\n" +
                                        "#.........#\r\n" +
                                        "#####.#####\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testCloseChestMenu() throws IOException {
        inputStream1 = new ByteArrayInputStream("Left".getBytes());
        Assertions.assertTrue(new ChestMenu(player).run(emptyPrintStream, defaultInputStream1) instanceof ExplorationMenu);
    }
}