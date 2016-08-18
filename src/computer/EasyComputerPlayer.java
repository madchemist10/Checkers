package computer;

import checker.Color;
import gameplay.CheckerGame;

/**
 * Framework for creation of a easy computer player.
 * Makes moves with little to no intelligence.
 */
public class EasyComputerPlayer extends AComputerPlayer{
    /**
     *
     * @param color       BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     */
    public EasyComputerPlayer(Color color, CheckerGame checkerGame) {
        super(color, checkerGame);
    }
}
