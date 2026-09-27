package iscteiul.ista.battleship;

import java.util.List;

/**
 * Defines the operations for managing a Battleship game and retrieving its
 * current status.
 */
public interface IGame {

    /**
     * Fires a shot at the specified position.
     *
     * @param pos the position targeted by the shot
     * @return the ship sunk by this shot, or {@code null} if no ship was sunk
     */
    IShip fire(IPosition pos);

    /**
     * Returns the positions of the shots recorded in the game.
     *
     * @return the list of recorded shot positions
     */
    List<IPosition> getShots();

    /**
     * Returns the number of repeated shots.
     *
     * @return the number of repeated shots
     */
    int getRepeatedShots();

    /**
     * Returns the number of invalid shots.
     *
     * @return the number of invalid shots
     */
    int getInvalidShots();

    /**
     * Returns the number of shots that hit a ship.
     *
     * @return the number of hits
     */
    int getHits();

    /**
     * Returns the number of ships sunk.
     *
     * @return the number of sunk ships
     */
    int getSunkShips();

    /**
     * Returns the number of ships that are still afloat.
     *
     * @return the number of remaining ships
     */
    int getRemainingShips();

    /**
     * Prints the board showing valid shots that have been fired.
     */
    void printValidShots();

    /**
     * Prints the board showing the fleet.
     */
    void printFleet();
}
