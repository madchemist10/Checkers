package computer;

import board.BoardSquare;
import checker.Checker;
import checker.ClickedState;
import checker.Color;
import constants.Constants;
import gameplay.CheckerGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Framework for creation of a random computer player.
 * Makes moves with no intelligence, only randomness.
 */
public class RandomComputerPlayer extends AComputerPlayer{

    /** Random generator to define new random numbers for random moves.*/
    private static final Random randomGenerator = new Random(System.currentTimeMillis());

    /**
     * Create a new random computer player with given
     * color and checkerGame.
     * @param color BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     *                    this computer player.
     */
    public RandomComputerPlayer(Color color, CheckerGame checkerGame){
        super(color,checkerGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void findAndPerformMove(Map<Checker, Map<String,ArrayList<BoardSquare>>> possibleMoves,
                                          String moveType){
        int randomChecker = randomGenerator.nextInt(possibleMoves.size());
        /*Retrieve possible jump moves for a given checker.*/
        Checker checkerToMove = null;
        int checkerCounter = 0;
        for(Checker checker : possibleMoves.keySet()){
            if(checkerCounter == randomChecker){
                checkerToMove = checker;
                break;
            }
            checkerCounter++;
        }
        if(checkerToMove == null){
            return; //should never occur.
        }
        Map<String,ArrayList<BoardSquare>> checkerPossibleMoves = possibleMoves.get(checkerToMove);
        ArrayList<BoardSquare> checkerPossibleSquares = checkerPossibleMoves.get(moveType);
        int randomMove = randomGenerator.nextInt(checkerPossibleSquares.size());
        BoardSquare boardSquareToMoveTo = null;
        int moveCounter = 0;
        for(BoardSquare boardSquare : checkerPossibleSquares){
            if(moveCounter == randomMove){
                boardSquareToMoveTo = boardSquare;
            }
            moveCounter++;
        }
        if(boardSquareToMoveTo == null){
            return; //should never occur.
        }
        this.checkerGame.assignPossibleMoves(checkerToMove);
        /*Wait one seconds before making move.
        * Busy loop.*/
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while(elapsedTime < AComputerPlayer.COMPUTER_WAIT_TIME){
            elapsedTime = (System.currentTimeMillis()-startTime);
        }
        /*Move the checker after setting state to clicked.*/
        checkerToMove.setClickedState(ClickedState.CLICKED);
        this.checkerGame.moveSelectedChecker(boardSquareToMoveTo);
    }
}
