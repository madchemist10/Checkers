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
     * Find a random valid move and perform that move if a move
     * exists.
     * @param possibleMoves map of checker piece to (map of specific move type to moves)
     * @param moveType type of move to find and perform JUMP or NORMAL
     */
    private void findAndPerformRandomMove(Map<Checker, Map<String,ArrayList<BoardSquare>>> possibleMoves,
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

    /**
     * {@inheritDoc}
     * Create an appropriate random move and
     * perform this move.
     */
    @Override
    public void makeMove() {
        Map<Checker, Map<String,ArrayList<BoardSquare>>> checkerBoardSquareMap = findPossibleMovesForComputer();
        /*Find how many jump moves there are*/
        Map<Checker, Map<String,ArrayList<BoardSquare>>> jumpMoves = new HashMap<>(1);
        Map<Checker, Map<String,ArrayList<BoardSquare>>> normalMoves = new HashMap<>(1);
        for(Checker checker : checkerBoardSquareMap.keySet()) {
            Map<String,ArrayList<BoardSquare>> moveTypeToSquareMap = checkerBoardSquareMap.get(checker);
            for (String moveType : moveTypeToSquareMap.keySet()) {
                switch(moveType){
                    case Constants.JUMP:
                        jumpMoves.put(checker, moveTypeToSquareMap);
                        break;
                    case Constants.NORMAL:
                        normalMoves.put(checker, moveTypeToSquareMap);
                        break;
                }
            }
        }
        /*We have at least one valid jump move.*/
        if(!jumpMoves.isEmpty()){
            findAndPerformRandomMove(jumpMoves, Constants.JUMP);
            return; //do not continue to the normal moves.
        }
        /*No Jump moves found.*/
        /*We have at least one valid normal move.*/
        if(!normalMoves.isEmpty()){
            findAndPerformRandomMove(normalMoves, Constants.NORMAL);
            /*Retrieve possible jump moves for a given checker.*/
        }
    }
}
