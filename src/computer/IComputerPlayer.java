package computer;

import checker.Checker;

import java.util.ArrayList;

/**
 * Interface for creating various types of computer players.
 */
public interface IComputerPlayer {

    /**
     * Alert this computer player that he can now
     * perform a move by his unique algorithm.
     */
    void makeMove();

    /**
     * Retrieve all computer checkers that are not
     * already captured.
     * @return List of checkers for this computer player.
     */
    ArrayList<Checker> getAllComputerCheckers();
}
