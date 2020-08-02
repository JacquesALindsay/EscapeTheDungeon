package test.mainmenu;

import characters.CharacterBuilder;
import menus.mainmenu.AttributeMenu;
import menus.mainmenu.FirstAttackingSpellMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class AttributeMenuTest {

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
    public void testAttributeMenuDisplay() throws IOException {
        new AttributeMenu(new CharacterBuilder("Jeff")).run(printStream, defaultInputStream);
        Assertions.assertEquals("Choose your attribute!\r\n" +
                        "Available attributes are:\r\n" +
                        "- Healthy\r\n" +
                        "- Wise\r\n" +
                        "- Strong\r\n" +
                        "- Careful\r\n" +
                        "- Lucky\r\n",
                new String(outputStream.toByteArray()));
    }

    @Test
    public void testAttributeMenuExit() throws IOException {
        inputStream = new ByteArrayInputStream("Lucky".getBytes());
        Assertions.assertTrue(new AttributeMenu(new CharacterBuilder("Jeff")).run(emptyPrintStream, inputStream) instanceof FirstAttackingSpellMenu);
    }

    @Test
    public void testAttributeMenuInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("Brave".getBytes());
        Assertions.assertTrue(new AttributeMenu(new CharacterBuilder("Jeff")).run(emptyPrintStream, inputStream) instanceof AttributeMenu);
    }

}