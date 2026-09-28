package iscteiul.ista.battleship;

/**
 * Represents a galleon that occupies five positions on the board.
 * Its bearing and initial position determine the positions it occupies.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Creates a galleon with the specified bearing and initial position.
     *
     * @param bearing the direction in which the galleon is placed
     * @param pos the galleon's initial position on the board
     * @throws NullPointerException if the bearing is {@code null}
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Returns the size of the galleon.
     *
     * @return the number of positions occupied by the galleon
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Adds the positions occupied by a north-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Adds the positions occupied by a south-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Adds the positions occupied by an east-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Adds the positions occupied by a west-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}
