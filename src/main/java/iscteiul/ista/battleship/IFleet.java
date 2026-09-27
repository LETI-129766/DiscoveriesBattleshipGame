package iscteiul.ista.battleship;

import java.util.List;

/**
 * Defines the operations for managing a fleet of ships.
 */
public interface IFleet {

    /**
     * The length of each side of the square game board.
     */
    Integer BOARD_SIZE = 10;

    /**
     * The number of ships in the fleet.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Returns the ships in the fleet.
     *
     * @return the list of ships
     */
    List<IShip> getShips();

    /**
     * Adds a ship to the fleet.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was added; {@code false} otherwise
     */
    boolean addShip(IShip s);

    /**
     * Returns the ships that match the specified category.
     *
     * @param category the category to search for
     * @return the list of ships in that category
     */
    List<IShip> getShipsLike(String category);

    /**
     * Returns the ships that are still afloat.
     *
     * @return the list of floating ships
     */
    List<IShip> getFloatingShips();

    /**
     * Finds the ship occupying the specified position.
     *
     * @param pos the position to check
     * @return the ship at that position, or {@code null} if there is none
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the current status of the fleet.
     */
    void printStatus();
}
