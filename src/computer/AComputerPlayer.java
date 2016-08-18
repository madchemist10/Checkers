package computer;

import checker.Checker;
import checker.Color;
import gameplay.CheckerGame;

import java.util.ArrayList;

/**
 * Abstraction of computer player that maintains the necessary
 * functionality for a computer player.
 */
public class AComputerPlayer implements IComputerPlayer {

    /** Color assigned to this computer player.*/
    protected final Color color;
    /** Checker game that this computer player is a part of.*/
    protected final CheckerGame checkerGame;

    /**
     * Create an abstract computer player with a given
     * color and the checker game this player is to
     * play in. Protected as to only allow extending
     * classes to call super.
     * @param color BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     *                    this computer player.
     */
    protected AComputerPlayer(Color color, CheckerGame checkerGame){
        this.color = color;
        this.checkerGame = checkerGame;
    }

    /**
     * Retrieve the color of this computer player.
     * @return BLACK or WHITE from (Checker.Color).
     */
    protected Color getColor(){
        return this.color;
    }

    /**
     * {@inheritDoc}
     * Must be overridden by extending classes.
     */
    @Override
    public void makeMove() {
        //do nothing for base class
    }

    /**
     * {@inheritDoc}
     * Implementation is complete here, no need to
     * override in extending class.
     */
    @Override
    public ArrayList<Checker> getAllComputerCheckers() {
        ArrayList<Checker> checkerList = new ArrayList<>(12);
        for(Checker checker : this.checkerGame.getCheckerMap().values()){
            if(checker.getCheckerColor().equals(this.getColor()) &&
                    !checker.isCaptured()) {
                checkerList.add(checker);
            }
        }
        return checkerList;
    }


}
