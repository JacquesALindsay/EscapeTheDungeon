package test.exploration;
import characters.Player;
import menus.exploration.ExplorationMenu;
import menus.exploration.MapMenu;
import menus.exploration.SaveMenu;
import menus.mainmenu.MainMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;

public class SaveMenuTest {

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
                "1\r\n" +
                "4\r\n" +
                "1 5 4\r\n");
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.SAVED_MAP);
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
        File file = new File(SaveUtils.SAVED_MAP);
        file.delete();
        file = new File(SaveUtils.PLAYER);
        file.delete();
    }

    @Test
    public void testOpenSaveMenu() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Save".getBytes());
        Assertions.assertTrue(new ExplorationMenu(player).run(emptyPrintStream, inputStream) instanceof SaveMenu);
    }

    @Test
    public void testSaveMenuDisplay() throws IOException {
        new SaveMenu(player).run(printStream, defaultInputStream);
        Assertions.assertEquals("Progress saved!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testContinueAfterSave() throws IOException {
        SaveUtils.savePlayer(player);
        PrintStream defaultPrintStream = new PrintStream(new ByteArrayOutputStream());
        InputStream inputStream = new ByteArrayInputStream("Continue".getBytes());
        Assertions.assertTrue(new MainMenu().run(defaultPrintStream, inputStream) instanceof ExplorationMenu);
    }

    @Test
    public void testSaveItemCollection() throws IOException {
        player.gainHealthPotions(5);
        SaveUtils.savePlayer(player);
        Assertions.assertEquals(20, SaveUtils.loadPlayer().getHealthPotions());
    }

    @Test
    public void testSaveExp() throws IOException {
        player.gainExp(500);
        SaveUtils.savePlayer(player);
        Assertions.assertEquals(1000, SaveUtils.loadPlayer().getExp());
    }

    @Test
    public void testSaveHealthChange() throws IOException {
        player.healUp();
        SaveUtils.savePlayer(player);
        Assertions.assertEquals(SaveUtils.loadPlayer().getCurrentHP(), 5);
    }

    @Test
    public void testSaveMagicChange() throws IOException {
        player.useMagicPotion();
        SaveUtils.savePlayer(player);
        Assertions.assertEquals(SaveUtils.loadPlayer().getCurrentMagic(), 6);
    }

    @Test
    public void testNoSave() throws IOException {
        SaveUtils.savePlayer(player);
        player.gainHealthPotions(5);
        Assertions.assertEquals(SaveUtils.loadPlayer().getHealthPotions(), 15);
    }
}