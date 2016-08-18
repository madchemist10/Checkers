package computer;

import board.BoardSquare;
import checker.Checker;
import checker.Color;
import gameplay.CheckerGame;

import java.util.ArrayList;
import java.util.Map;

/**
 * Framework for creation of a hard computer player.
 * Makes moves with decent intelligence.
 */
public class HardComputerPlayer extends AComputerPlayer{
    /**
     * Create a new hard computer player with given
     * color and checkerGame.
     * @param color       BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     */
    public HardComputerPlayer(Color color, CheckerGame checkerGame) {
        super(color, checkerGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void findAndPerformMove(Map<Checker, Map<String, ArrayList<BoardSquare>>> possibleMoves, String moveType) {

    }
}
