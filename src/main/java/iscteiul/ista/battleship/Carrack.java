```java
/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a Carrack ship in the Battleship game.
 * A Carrack occupies three consecutive positions on the board,
 * depending on its bearing.
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a new Carrack with the specified bearing and initial position.
     * The Carrack occupies three positions, either vertically when its bearing
     * is NORTH or SOUTH, or horizontally when its bearing is EAST or WEST.
     *
     * @param bearing the bearing where the Carrack heads to
     * @param pos the initial point for positioning the Carrack
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Returns the size of the Carrack.
     *
     * @return the number of positions occupied by the Carrack
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
```
