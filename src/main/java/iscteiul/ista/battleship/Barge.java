```java
package iscteiul.ista.battleship;

/**
 * Represents a barge in the Battleship game.
 *
 * <p>A barge is a type of ship with a size of one position.
 * Its position is defined by the given upper-left position and
 * its bearing.</p>
 */
public class Barge extends Ship {

    /** The size of a barge. */
    private static final Integer SIZE = 1;

    /** The name of the ship type. */
    private static final String NAME = "Barca";

    /**
     * Creates a new barge with the specified bearing and position.
     *
     * @param bearing the bearing that determines the barge's orientation
     * @param pos the upper-left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the barge.
     *
     * @return the size of the barge, which is always {@value #SIZE}
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
```
