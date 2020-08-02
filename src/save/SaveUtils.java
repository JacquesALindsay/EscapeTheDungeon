package save;

import characters.Player;
import exploration.Position;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/*
 * This class contains static functions and constants that helps us handle saving sata
 */
public class SaveUtils {

    public static final String PLAYER = "data/player.txt";
    public static final String DEFAULT_MAP = "data/defaultmap.txt";
    public static final String SAVED_MAP = "data/savemap.txt";
    public static final String TEMP_MAP = "data/tempmap.txt";

    private SaveUtils() {
        // do not instantiate
    }

    /*
     * Copies the given map from one file to another
     */
    public static void copyMap(String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        BufferedReader reader;
        FileWriter writer;
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            StringBuilder fileContents = new StringBuilder();
            while (line != null) {
                fileContents.append(line).append("\r\n");
                line = reader.readLine();
            }
            // write all the lines
            writer = new FileWriter(targetFile);
            writer.write(fileContents.toString());
        }
        catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
        try {
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
    }

    /*
     * checks if the player file is present
     */
    public static boolean playerFilePresent() {
        return new File(PLAYER).canRead();
    }

    /*
     * Saves the player into the save file
     */
    public static void savePlayer(Player player) {
        try {
            new File(PLAYER).createNewFile();
            FileWriter writer = new FileWriter(PLAYER);
            writer.write(player.getName());
            writer.write("\r\n");
            writer.write(player.getAttribute());
            writer.write("\r\n");
            writer.write(String.valueOf(player.getLevel()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getMaxHP()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getAttack()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getDefense()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getLuck()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getCurrentHP()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getMaxMagic()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getCurrentMagic()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getExp()));
            writer.write("\r\n");
            writer.write(player.getSpells().get(0));
            writer.write("\r\n");
            writer.write(player.getSpells().get(1));
            writer.write("\r\n");
            writer.write(player.getSpells().get(2));
            writer.write("\r\n");
            writer.write(player.getSpells().get(3));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getHealthPotions()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getMagicPotions()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getPosition().getZone()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getPosition().getYPos()));
            writer.write("\r\n");
            writer.write(String.valueOf(player.getPosition().getXPos()));
            writer.write("\r\n");
            for(int i = 1; i <= 9; i++) {
                if(player.hasFound(i)) {
                    writer.write(String.valueOf(i));
                    writer.write(" ");
                }
            }
            writer.write("\r\n");
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
    }

    /*
     * Loads the player based on save data
     */
    public static Player loadPlayer() throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(SaveUtils.PLAYER));
        // generates the player based on the save data
        return new Player(new String(encoded, StandardCharsets.UTF_8));
    }

    /*
     * Loads a zone from the temporary map and returns its 2D array, and adds its links to the links hashmap
     */
    public static char[][] loadZone(Position position, HashMap<Position, Position> links) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(SaveUtils.TEMP_MAP));
            String line = reader.readLine();
            // We read the file until we reach the zone
            while (!line.equals("zone" + position.getZone())) {
                line = reader.readLine();
            }
            line = reader.readLine();
            String[] split = line.split(" ");
            char[][] array = new char[Integer.parseInt(split[0])][Integer.parseInt(split[0])];
            int i = 0;
            line = reader.readLine();
            // We read the file until we reach a digit
            while (!Character.isDigit(line.charAt(0))) {
                array[i] = line.toCharArray();
                i++;
                line = reader.readLine();
            }
            while(!line.equals("")) {
                split = line.split(" ");
                links.put(new Position(position.getZone(), Integer.parseInt(split[0]), Integer.parseInt(split[1])),
                        new Position(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4])));
                line = reader.readLine();
            }
            reader.close();
            return array;
        } catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
    }

    /*
     * Sets the tile at the given position to the given character in the temporary data
     */
    public static void saveTile(Position position, char c) {
        File file = new File(TEMP_MAP);
        BufferedReader reader;
        FileWriter writer;
        try {
            reader = new BufferedReader(new FileReader(file));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            StringBuilder fileContents = new StringBuilder();
            // copy all the lines until we reach the zone
            while (!line.equals("zone" + position.getZone())) {
                fileContents.append(line).append("\r\n");
                line = reader.readLine();
            }
            // copy all the lines until we reach the y position
            for(int i = 0; i < position.getYPos() + 2; i++) {
                fileContents.append(line).append("\r\n");
                line = reader.readLine();
            }
            line = reader.readLine();
            // copy the line but replace the x position with c
            fileContents.append(line, 0, position.getXPos()).append(c).append(line.substring(position.getXPos() + 1)).append("\r\n");
            while (line != null) {
                fileContents.append(line).append("\r\n");
                line = reader.readLine();
            }
            // write all the lines
            writer = new FileWriter(file);
            writer.write(fileContents.toString());
        }
        catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
        try {
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Should never happen!");
        }
    }

    public static void deleteTempFiles() {
        File file = new File(TEMP_MAP);
        file.delete();
    }
}
