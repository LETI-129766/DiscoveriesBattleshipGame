```java
/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a Caravel ship in the Battleship game.
 * A Caravel occupies two consecutive positions on the board,
 * depending on its bearing.
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a new Caravel with the specified bearing and initial position.
     * The Caravel occupies two positions, either vertically when its bearing
     * is NORTH or SOUTH, or horizontally when its bearing is EAST or WEST.
     *
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     * @throws NullPointerException if the bearing is null
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Returns the size of the Caravel.
     *
     * @return the number of positions occupied by the Caravel
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
```
