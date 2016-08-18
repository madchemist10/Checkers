package computer;

import board.BoardSquare;
import checker.Checker;
import checker.Color;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for creating various types of computer players.
 */
public interface IComputerPlayer {

    /**
     * Retrieve the color of this computer player.
     * @return BLACK or WHITE from (Checker.Color).
     */
    Color getColor();

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

    /**
     * Find all possible jumps and normal moves for all given checker pieces.
     * @return Map of checkers to
     *         map of move types (JUMP, NORMAL) to
     *              List of BoardSquares.
     */
    Map<Checker, Map<String,ArrayList<BoardSquare>>> findPossibleMovesForComputer();
}
