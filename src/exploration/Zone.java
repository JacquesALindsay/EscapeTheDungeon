package exploration;

import save.SaveUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/*
 * This class represents the zone of the
 */
public class Zone {

    // the characters representing each tile
    public static final char EMPTY = '.';
    public static final char WALL = '#';
    public static final char CHEST = 'C';

    // the 2D array representation of the zone
    private final char[][] array;

    // The links from one position to another
    private final HashMap<Position, Position> links;

    /*
     * This constructor loads the zone and sets the player's position
     */
    public Zone(Position position) {
        links = new HashMap<>();
        array = SaveUtils.loadZone(position, links);
        array[position.getYPos()][position.getXPos()] = 'P';
    }

    /*
     * Displays the zone as a string of characters
     */
    public String display() {
        StringBuilder builder = new StringBuilder();
        for (char[] line : array) {
            builder.append(String.valueOf(line));
            builder.append("\r\n");
        }
        return builder.toString();
    }

    /*
     * returns a link between a position and another
     */
    public Position link(Position position) {
        return links.getOrDefault(position, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Arrays.equals(array, zone.array) &&
                Objects.equals(links, zone.links);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(links);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    /*
     * Returns true if the player can move on the tile at the given position
     */
    public boolean canMove(Position position) {
        return position.getYPos() >= 0
                && position.getYPos() < array.length
                && position.getXPos() >= 0
                && position.getXPos() < array[position.getXPos()].length
                && array[position.getYPos()][position.getXPos()] != WALL;
    }

    /*
     * Returns true if the tile at the given position is a chest
     */
    public boolean isChest(Position position) {
        return position.getYPos() >= 0
                && position.getYPos() < array.length
                && position.getXPos() >= 0
                && position.getXPos() < array[position.getXPos()].length
                && array[position.getYPos()][position.getXPos()] == CHEST;
    }
}
