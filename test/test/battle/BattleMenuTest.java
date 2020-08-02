package test.battle;

import characters.Creature;
import characters.Player;
import characters.Slime;
import menus.battle.BattleStartMenu;
import menus.battle.InvalidCommandMenu;
import menus.exploration.ExplorationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;


class BattleMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private InputStream inputStream;
    private Player player;
    private Creature creature;

    @BeforeEach
    public void beforeEach() {
        defaultInputStream = new ByteArrayInputStream("default".getBytes());
        emptyPrintStream = new PrintStream(new ByteArrayOutputStream());
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.TEMP_MAP);
        player = new Player("Timothy\r\n" +
                "Unlucky\r\n" +
                "1\r\n" +
                "5\r\n" +
                "5\r\n" +
                "5\r\n" +
                "1\r\n" +
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
        creature = new Slime("Regular", 5, 5, 5, 5);
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testBattleStartMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Right".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(emptyPrintStream, inputStream) instanceof BattleStartMenu);
    }

    @Test
    public void testBattleStartMenuDisplay() throws IOException {
        new BattleStartMenu(player, creature).run(printStream, defaultInputStream);
        Assertions.assertEquals("You are fighting against a Regular Slime!\r\n" +
                                        "What do you do?\r\n" +
                                        "Current moves:\r\n" +
                                        "Strong Attack\r\n" +
                                        "Risky Attack\r\n" +
                                        "Magic Healing\r\n" +
                                        "Magic Sword\r\n" +
                                        "Current HP: 4 / 5\r\n" +
                                        "Current Magic: 3 / 6\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testInvalidCommandMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Regular Attack".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof InvalidCommandMenu);
    }

    @Test
    public void testInvalidCommandMenuDisplay() throws IOException {
        new InvalidCommandMenu(player, creature).run(printStream, defaultInputStream);
        Assertions.assertEquals("Please input a valid command!\r\n" +
                                        "What do you do?\r\n" +
                                        "Current moves:\r\n" +
                                        "Strong Attack\r\n" +
                                        "Risky Attack\r\n" +
                                        "Magic Healing\r\n" +
                                        "Magic Sword\r\n" +
                                        "Current HP: 4 / 5\r\n" +
                                        "Current Magic: 3 / 6\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testExitFromBattleMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Exit".getBytes());
        Assertions.assertNull(new ExplorationMenu(player).run(emptyPrintStream, inputStream));
        Assertions.assertFalse(new File(SaveUtils.TEMP_MAP).exists());
    }
}