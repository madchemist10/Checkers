package computer;

import checker.Color;
import gameplay.CheckerGame;

/**
 * Framework for creation of a medium computer player.
 * Makes moves with minor intelligence.
 */
public class MediumComputerPlayer extends AComputerPlayer{
    /**
     *
     * @param color       BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     */
    public MediumComputerPlayer(Color color, CheckerGame checkerGame) {
        super(color, checkerGame);
    }
}
