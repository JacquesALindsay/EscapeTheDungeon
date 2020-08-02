package test.mainmenu;

import characters.CharacterBuilder;
import menus.exploration.PlayerMenu;
import menus.mainmenu.FirstAttackingSpellMenu;
import menus.mainmenu.FirstSupportingSpellMenu;
import menus.mainmenu.SecondAttackingSpellMenu;
import menus.mainmenu.SecondSupportingSpellMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


class SpellsMenuTest {

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
    @Test
    public void testFirstAttackingSpellMenuDisplay() throws IOException {
        new FirstAttackingSpellMenu(new CharacterBuilder("Jeff"))
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("Choose your first attacking spell!\r\n" +
                        "Available attacking spells are:\r\n" +
                        "- Neutral Attack\r\n" +
                        "- Strong Attack\r\n" +
                        "- Risky Attack\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testFirstAttackingSpellMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        Assertions.assertTrue(new FirstAttackingSpellMenu(new CharacterBuilder("Jeff"))
                .run(emptyPrintStream, inputStream) instanceof SecondAttackingSpellMenu);
    }

    @Test
    public void testFirstAttackingSpellMenuInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("Ice Beam".getBytes());
        Assertions.assertTrue(new FirstAttackingSpellMenu(new CharacterBuilder("Jeff"))
                .run(emptyPrintStream, inputStream) instanceof FirstAttackingSpellMenu);
    }

    @Test
    public void testSecondAttackingSpellMenuDisplay() throws IOException {
        new SecondAttackingSpellMenu(new CharacterBuilder("Jeff"), "Risky Attack")
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("Choose your second attacking spell!\r\n" +
                        "Available attacking spells are:\r\n" +
                        "- Neutral Attack\r\n" +
                        "- Strong Attack\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testSecondAttackingSpellMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        Assertions.assertTrue(new SecondAttackingSpellMenu(new CharacterBuilder("Jeff"), "Risky Attack")
                .run(emptyPrintStream, inputStream) instanceof FirstSupportingSpellMenu);
    }

    @Test
    public void testSecondAttackingSpellMenuInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("Neutral Attack".getBytes());
        Assertions.assertTrue(new SecondAttackingSpellMenu(new CharacterBuilder("Jeff"), "Neutral Attack")
                .run(emptyPrintStream, inputStream) instanceof SecondAttackingSpellMenu);
    }

    @Test
    public void testFirstSupportingSpellMenuDisplay() throws IOException {
        new FirstSupportingSpellMenu(new CharacterBuilder("Jeff"))
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("Choose your first supporting spell!\r\n" +
                        "Available supporting spells are:\r\n" +
                        "- Magic Healing\r\n" +
                        "- Magic Sword\r\n" +
                        "- Magic Shield\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testFirstSupportingSpellMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Healing".getBytes());
        Assertions.assertTrue(new FirstSupportingSpellMenu(new CharacterBuilder("Jeff"))
                .run(emptyPrintStream, inputStream) instanceof SecondSupportingSpellMenu);
    }

    @Test
    public void testFirstSupportingSpellMenuInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("Recover".getBytes());
        Assertions.assertTrue(new FirstSupportingSpellMenu(new CharacterBuilder("Jeff"))
                .run(emptyPrintStream, inputStream) instanceof FirstSupportingSpellMenu);
    }

    @Test
    public void testSecondSupportingSpellMenuDisplay() throws IOException {
        new SecondSupportingSpellMenu(new CharacterBuilder("Jeff"), "Magic Sword")
                .run(printStream, defaultInputStream);
        Assertions.assertEquals("Choose your second supporting spell!\r\n" +
                        "Available supporting spells are:\r\n" +
                        "- Magic Healing\r\n" +
                        "- Magic Shield\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testSecondSupportingSpellMenu() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Healing".getBytes());
        Assertions.assertTrue(new SecondSupportingSpellMenu(new CharacterBuilder("Jeff").setAttribute("Careful"), "Magic Sword")
                .run(emptyPrintStream, inputStream) instanceof PlayerMenu);
    }

    @Test
    public void testSecondSupportingSpellMenuInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("Magic Healing".getBytes());
        Assertions.assertTrue(new SecondSupportingSpellMenu(new CharacterBuilder("Jeff").setAttribute("Careful"), "Magic Healing")
                .run(emptyPrintStream, inputStream) instanceof SecondSupportingSpellMenu);
    }

}