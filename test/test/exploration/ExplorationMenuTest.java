package test.exploration;
import characters.Player;
import menus.exploration.ExplorationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;

public class ExplorationMenuTest {

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
                "1000\r\n" +
                "4\r\n" +
                "6\r\n" +
                "3\r\n" +
                "4\r\n" +
                "Strong Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n" +
                "15\r\n" +
                "14\r\n" +
                "4\r\n" +
                "1\r\n" +
                "4\r\n" +
                "5 4\r\n");
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testDisplay() throws IOException {
        new ExplorationMenu(player).run(printStream, defaultInputStream);
        Assertions.assertEquals("#####.#####\r\n" +
                                        "#..CP.....#\r\n" +
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
    public void testMovement() throws IOException {
        inputStream = new ByteArrayInputStream("Down".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("#####.#####\r\n" +
                                        "#..C......#\r\n" +
                                        "#...P.....#\r\n" +
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
    public void testWrongMovement() throws IOException {
        inputStream = new ByteArrayInputStream("Up".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("#####.#####\r\n" +
                                        "#..CP.....#\r\n" +
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
    public void testZoneMovement() throws IOException {
        inputStream = new ByteArrayInputStream("Right".getBytes());
        InputStream inputStream2 = new ByteArrayInputStream("Up".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream)
                .run(emptyPrintStream, inputStream2)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("###########\r\n" +
                                        "#.........#\r\n" +
                                        "#...C.....#\r\n" +
                                        "#......C..#\r\n" +
                                        "#.C.......#\r\n" +
                                        "#..........\r\n" +
                                        "#.........#\r\n" +
                                        "#...C.....#\r\n" +
                                        "#......C..#\r\n" +
                                        "#.........#\r\n" +
                                        "#####P#####\r\n",
                new String(outputStream.toByteArray()));
        Assertions.assertTrue(player.hasFound(1));
    }

    @Test
    public void testItemUsage() throws IOException {
        inputStream = new ByteArrayInputStream("Health Potion".getBytes());
        new ExplorationMenu(player).run(emptyPrintStream, inputStream);
        Assertions.assertEquals(player.getMaxHP(), player.getCurrentHP());
        Assertions.assertEquals(14, player.getMagicPotions());
    }

    @Test
    public void testExitFromExplorationMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Exit".getBytes());
        Assertions.assertNull(new ExplorationMenu(player).run(emptyPrintStream, inputStream));
        Assertions.assertFalse(new File(SaveUtils.TEMP_MAP).exists());
    }

}
