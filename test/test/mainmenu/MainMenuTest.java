package test.mainmenu;
import menus.exploration.ExplorationMenu;
import menus.mainmenu.MainMenu;
import menus.mainmenu.NameMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.*;


class MainMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private InputStream inputStream;

    @BeforeEach
    public void beforeEach() {
        defaultInputStream = new ByteArrayInputStream("default".getBytes());
        emptyPrintStream = new PrintStream(new ByteArrayOutputStream());
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
        File file = new File(SaveUtils.SAVED_MAP);
        file.delete();
    }

    @Test
    public void testMainMenuDisplay() throws IOException {
        new MainMenu().run(printStream, defaultInputStream);
        Assertions.assertEquals("Welcome to Escape the Dungeon!\r\n" +
                "Do you want to start a NEW game, or CONTINUE your saved game?\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testNewGame() throws IOException {
        inputStream = new ByteArrayInputStream("New".getBytes());
        Assertions.assertTrue(new MainMenu().run(emptyPrintStream, inputStream) instanceof NameMenu);
    }

    @Test
    public void testContinue() throws IOException {
        PrintWriter out = new PrintWriter(SaveUtils.PLAYER);
        out.print("Timothy\r\n" +
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
        out.close();
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.SAVED_MAP);
        inputStream = new ByteArrayInputStream("Continue".getBytes());
        Assertions.assertTrue(new MainMenu().run(emptyPrintStream, inputStream) instanceof ExplorationMenu);
    }

    @Test
    public void testContinueIfNoFileFound() throws IOException {
        File file = new File(SaveUtils.PLAYER);
        file.delete();
        inputStream = new ByteArrayInputStream("Continue".getBytes());
        Assertions.assertTrue(new MainMenu().run(emptyPrintStream, inputStream) instanceof NameMenu);
    }

    @Test
    public void testMainMenuInvalidInput() throws IOException {
        Assertions.assertTrue(new MainMenu().run(emptyPrintStream, defaultInputStream) instanceof MainMenu);
    }
}