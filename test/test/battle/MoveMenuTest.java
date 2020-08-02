package test.battle;

import characters.Creature;
import characters.Player;
import characters.Slime;
import menus.battle.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MoveMenuTest {

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
                "Magic Sword\r\n" +
                "Magic Shield\r\n" +
                "15\r\n" +
                "14\r\n" +
                "2\r\n" +
                "2\r\n" +
                "5\r\n" +
                "1 5 4\r\n");
        creature = new Slime("Regular", 5, 5, 5, 5);
    }

    @Test
    public void testAttackMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof AttackMenu);
    }

    @Test
    public void testAttackMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You cast a Neutral Attack on the Regular Slime!\r\n" +
                                        "You deal 2 damage to it!\r\n" +
                                        "The Regular Slime tackles you!\r\n" +
                                        "It deals 1 damage to you!\r\n" +
                                        "What do you do?\r\n" +
                                        "Current moves:\r\n" +
                                        "Neutral Attack\r\n" +
                                        "Risky Attack\r\n" +
                                        "Magic Sword\r\n" +
                                        "Magic Shield\r\n" +
                                        "Current HP: 3 / 5\r\n" +
                                        "Current Magic: 2 / 6\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testSupportMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Shield".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof SupportMenu);
    }

    @Test
    public void testSupportMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Shield".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You cast Magic Shield!\r\n" +
                                        "The Regular Slime tackles you!\r\n" +
                                        "It deals no damage to you!\r\n" +
                                        "What do you do?\r\n" +
                                        "Current moves:\r\n" +
                                        "Neutral Attack\r\n" +
                                        "Risky Attack\r\n" +
                                        "Magic Sword\r\n" +
                                        "Magic Shield\r\n" +
                                        "Current HP: 4 / 5\r\n" +
                                        "Current Magic: 1 / 6\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testItemMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Health Potion".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof ItemMenu);
    }

    @Test
    public void testItemMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Health Potion".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You use a Health Potion!\r\n" +
                                        "The Regular Slime tackles you!\r\n" +
                                        "It deals 1 damage to you!\r\n" +
                                        "What do you do?\r\n" +
                                        "Current moves:\r\n" +
                                        "Neutral Attack\r\n" +
                                        "Risky Attack\r\n" +
                                        "Magic Sword\r\n" +
                                        "Magic Shield\r\n" +
                                        "Current HP: 4 / 5\r\n" +
                                        "Current Magic: 3 / 6\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testDamageDealt() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream);
        Assertions.assertEquals(3, player.getCurrentHP());
    }

    @Test
    public void testDamageTaken() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream);
        Assertions.assertEquals(3, creature.getCurrentHP());
    }

    @Test
    public void testHPRestored() throws IOException {
        inputStream = new ByteArrayInputStream("Health Potion".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream);
        Assertions.assertEquals(4, player.getCurrentHP());
    }

    @Test
    public void testMagicSpent() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream);
        Assertions.assertEquals(2, player.getCurrentMagic());
    }

    @Test
    public void testMagicRestored() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Potion".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream);
        Assertions.assertEquals(6, player.getCurrentMagic());
    }
}
