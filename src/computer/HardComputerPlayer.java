package computer;

import checker.Color;
import gameplay.CheckerGame;

/**
 * Framework for creation of a hard computer player.
 * Makes moves with decent intelligence.
 */
public class HardComputerPlayer extends AComputerPlayer{
    /**
     *
     * @param color       BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     */
    public HardComputerPlayer(Color color, CheckerGame checkerGame) {
        super(color, checkerGame);
    }
}
