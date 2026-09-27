package iscteiul.ista.battleship;

/**
 * Represents a frigate that occupies four positions on the board.
 * Its bearing and initial position determine the positions it occupies.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate with the specified bearing and initial position.
     *
     * @param bearing the direction in which the frigate is placed
     * @param pos the frigate's initial position on the board
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Returns the size of the frigate.
     *
     * @return the number of positions occupied by the frigate
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}
