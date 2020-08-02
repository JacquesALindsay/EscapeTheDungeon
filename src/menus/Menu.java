package menus;

import java.io.*;

/*
 * The abstract class for all menus
 */
public abstract class Menu {

    /*
     * This function runs the menu.
     * Running a menu consists of displaying it, asking for an input, and then, managing it
     * run() takes a scanner as a parameter, which, in tests, can be customized. In main, it is the command line
     * if the input is invalid, we display an error message. We then continue to collect the inputs until we get a valid one
     */
    public Menu run(PrintStream outputStream, InputStream inputStream) throws IOException {
        display(outputStream);
        String input = new BufferedReader(new InputStreamReader(inputStream))
                .readLine();
        if (input != null) {
            input = input.toLowerCase();
        }
        return manageInput(input);
    }

    /*
     * This function displays the menu. It is a simple output on the command line
     *
     */
    protected abstract void display(PrintStream stream);

    /*
     * This function collects and manages a player input, returning the menu
     */
    protected abstract Menu manageInput(String input);


}
