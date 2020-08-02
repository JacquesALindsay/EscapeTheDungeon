package test.mainmenu;

import menus.mainmenu.AttributeMenu;
import menus.mainmenu.NameMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


class NameMenuTest {

    private static InputStream defaultInputStream;
    private static PrintStream emptyPrintStream;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    public void beforeEach() {
        defaultInputStream = new ByteArrayInputStream("default".getBytes());
        emptyPrintStream = new PrintStream(new ByteArrayOutputStream());
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    public void testNameMenuDisplay() throws IOException {
        new NameMenu().run(printStream, defaultInputStream);
        Assertions.assertEquals("Enter your name!\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void testNameMenuExit() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Jeff".getBytes());
        Assertions.assertTrue(new NameMenu().run(emptyPrintStream, inputStream) instanceof AttributeMenu);
    }
}