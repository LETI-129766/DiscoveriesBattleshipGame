package iscteiul.ista.battleship;

/**
 * Represents the possible compass directions used by ships.
 * Each direction is associated with a character representation.
 *
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Creates a compass direction with the specified character.
     *
     * @param c the character representing the direction
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character associated with this compass direction.
     *
     * @return the character representing the direction
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the string representation of this compass direction.
     *
     * @return the character representing the direction as a string
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character into its corresponding compass direction.
     * If the character does not represent a valid direction, UNKNOWN is returned.
     *
     * @param ch the character to be converted
     * @return the corresponding compass direction
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}

