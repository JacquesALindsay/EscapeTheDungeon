package test.battle;

import characters.Creature;
import characters.Player;
import characters.Slime;
import menus.battle.BattleStartMenu;
import menus.battle.WinMenu;
import menus.exploration.ExplorationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;

class WinMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private InputStream inputStream1;
    private InputStream inputStream2;
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
                "Regular\r\n" +
                "1\r\n" +
                "5\r\n" +
                "5\r\n" +
                "5\r\n" +
                "5\r\n" +
                "4\r\n" +
                "6\r\n" +
                "3\r\n" +
                "500\r\n" +
                "Neutral Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n" +
                "15\r\n" +
                "14\r\n" +
                "2\r\n" +
                "2\r\n" +
                "5\r\n" +
                "1 5 4\r\n");
        creature = new Slime("Frail", 1, 5, 5, 5);
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testWinMenu() throws IOException {
        inputStream1 = new ByteArrayInputStream("Neutral Attack".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream1) instanceof WinMenu);
    }

    @Test
    public void testWinMenuDisplay() throws IOException {
        inputStream1 = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream1)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You cast a Neutral Attack on the Frail Slime!\r\n" +
                "You deal 2 damage to it!\r\n" +
                "The Frail Slime dies! You win!\r\n" +
                "You gain 100 experience points!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testWinMenuExit() throws IOException {
        Assertions.assertTrue(new WinMenu(player, creature, "Neutral Attack", 1).run(emptyPrintStream, defaultInputStream) instanceof ExplorationMenu);
    }

    @Test
    public void testConserveHP() throws IOException {
        inputStream1 = new ByteArrayInputStream("Magic Sword".getBytes());
        inputStream2 = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, inputStream2)
                .run(emptyPrintStream, defaultInputStream);
        Assertions.assertEquals(3, player.getCurrentHP());
    }

    @Test
    public void testConserveMagic() throws IOException {
        inputStream1 = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature).run(emptyPrintStream, defaultInputStream);
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, defaultInputStream);
        Assertions.assertEquals(2, player.getCurrentMagic());
    }

    @Test
    public void testConserveItems() throws IOException {
        inputStream1 = new ByteArrayInputStream("Health Potion".getBytes());
        inputStream2 = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, inputStream2)
                .run(emptyPrintStream, defaultInputStream);
        Assertions.assertEquals(14, player.getHealthPotions());
    }

    @Test
    public void testGainEXP() throws IOException {
        new WinMenu(player, creature, "Neutral Attack", 1).run(emptyPrintStream, defaultInputStream);
        Assertions.assertEquals(600, player.getExp());
    }

    @Test
    public void testRevertBoosts() throws IOException {
        inputStream1 = new ByteArrayInputStream("Magic Sword".getBytes());
        inputStream2 = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream1)
                .run(emptyPrintStream, inputStream2)
                .run(emptyPrintStream, defaultInputStream);
        Assertions.assertEquals(1, player.getAttackBoost());
    }

}