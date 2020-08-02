package exploration;

import java.util.Objects;

/*
 * This class represents the position of the character
 */
public class Position {

    // the default position
    public static final Position DEFAULT_POSITION = new Position(8, 9, 5);

    private final int zone;
    private int ypos;
    private int xpos;

    public Position(int zone, int ypos, int xpos) {
        this.zone = zone;
        this.ypos = ypos;
        this.xpos = xpos;
    }

    public int getZone() {
        return zone;
    }

    public int getYPos() {
        return ypos;
    }

    public int getXPos() {
        return xpos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return zone == position.zone &&
                ypos == position.ypos &&
                xpos == position.xpos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone, ypos, xpos);
    }

    /*
     * ´Returns the position that is up one tile
     */
    public Position up() {
        return new Position(zone, ypos-1, xpos);
    }
    /*
     * ´Returns the position that is down one tile
     */
    public Position down() {
        return new Position(zone, ypos+1, xpos);
    }

    /*
     * ´Returns the position that is left one tile
     */
    public Position left() {
        return new Position(zone, ypos, xpos-1);
    }

    /*
     *´Returns the position that is right one tile
     */
    public Position right() {
        return new Position(zone, ypos, xpos+1);
    }

}
