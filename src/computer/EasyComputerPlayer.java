package computer;

import board.BoardSquare;
import checker.Checker;
import checker.Color;
import gameplay.CheckerGame;

import java.util.ArrayList;
import java.util.Map;

/**
 * Framework for creation of a easy computer player.
 * Makes moves with little to no intelligence.
 */
public class EasyComputerPlayer extends AComputerPlayer{
    /**
     * Create a new easy computer player with given
     * color and checkerGame.
     * @param color       BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     */
    public EasyComputerPlayer(Color color, CheckerGame checkerGame) {
        super(color, checkerGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void findAndPerformMove(Map<Checker, Map<String, ArrayList<BoardSquare>>> possibleMoves, String moveType) {

    }
}
