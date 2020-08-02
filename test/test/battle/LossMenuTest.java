package test.battle;

import characters.Creature;
import characters.Player;
import characters.Slime;
import menus.battle.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class LossMenuTest {

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
                "Weak\r\n" +
                "1\r\n" +
                "1\r\n" +
                "1\r\n" +
                "1\r\n" +
                "1\r\n" +
                "1\r\n" +
                "6\r\n" +
                "2\r\n" +
                "500\r\n" +
                "Strong Attack\r\n" +
                "Risky Attack\r\n" +
                "Magic Healing\r\n" +
                "Magic Sword\r\n" +
                "15\r\n" +
                "14\r\n" +
                "2\r\n" +
                "2\r\n" +
                "5\r\n" +
                "1 5 4\r\n");
        creature = new Slime("Legendary", 10, 10, 10, 10);
    }

    @Test
    public void testLossIfHPDepleted() throws IOException {
        inputStream = new ByteArrayInputStream("Strong Attack".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof LossMenu);
    }
    @Test
    public void testLossIfMagicDepleted() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Healing".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof LossMenu);
    }

    @Test
    public void testLossAttackMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Strong Attack".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof LossAttackMenu);
    }

    @Test
    public void testLossAttackMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Strong Attack".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You cast Strong Attack on the Legendary Slime, but the spell fails!\r\n" +
                                        "The Legendary Slime tackles you!\r\n" +
                                        "It deals 10 damage to you!\r\n" +
                                        "You have died to the Legendary Slime!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testLossSupportMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Sword".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof LossSupportMenu);
    }

    @Test
    public void testLossSupportMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Sword".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You cast Magic Sword!\r\n" +
                                        "The Legendary Slime tackles you!\r\n" +
                                        "It deals 10 damage to you!\r\n" +
                                        "You have died to the Legendary Slime!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testLossItemMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Potion".getBytes());
        Assertions.assertTrue(new BattleStartMenu(player, creature).run(emptyPrintStream, inputStream) instanceof LossItemMenu);
    }

    @Test
    public void testLossItemMenuDisplay() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Potion".getBytes());
        new BattleStartMenu(player, creature)
                .run(emptyPrintStream, inputStream)
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("You use a Magic Potion!\r\n" +
                                        "The Legendary Slime tackles you!\r\n" +
                                        "It deals 10 damage to you!\r\n" +
                                        "You have died to the Legendary Slime!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testLossMenuExit() throws IOException {
        Assertions.assertNull(new LossFailedRunMenu(creature, 1).run(emptyPrintStream, defaultInputStream));
    }
}