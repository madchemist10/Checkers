package computer;

import board.BoardSquare;
import checker.Checker;
import checker.Color;
import constants.Constants;
import gameplay.CheckerGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstraction of computer player that maintains the necessary
 * functionality for a computer player.
 */
abstract class AComputerPlayer implements IComputerPlayer {

    /** Time for the computer player to wait before performing move
     * in milliseconds. 1 second.*/
    static final int COMPUTER_WAIT_TIME = 1000;
    /** Color assigned to this computer player.*/
    protected final Color color;
    /** Checker game that this computer player is a part of.*/
    final CheckerGame checkerGame;

    /**
     * Create an abstract computer player with a given
     * color and the checker game this player is to
     * play in. Protected as to only allow extending
     * classes to call super.
     * @param color BLACK or WHITE from the (Checker.Color) class.
     * @param checkerGame reference to the checker game that is creating
     *                    this computer player.
     */
    AComputerPlayer(Color color, CheckerGame checkerGame){
        this.color = color;
        this.checkerGame = checkerGame;
    }

    /**
     * Find a valid move and perform that move if a move exists.
     * @param possibleMoves map of checker piece to (map of specific move type to moves)
     * @param moveType type of move to find and perform JUMP or NORMAL
     */
    protected abstract void findAndPerformMove(Map<Checker, Map<String,ArrayList<BoardSquare>>> possibleMoves,
                                   String moveType);

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(){
        return this.color;
    }

    /**
     * {@inheritDoc}
     * Implementation is complete here, no need to
     * override in extending class.
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
            findAndPerformMove(jumpMoves, Constants.JUMP);
            return; //do not continue to the normal moves.
        }
        /*No Jump moves found.*/
        /*We have at least one valid normal move.*/
        if(!normalMoves.isEmpty()){
            findAndPerformMove(normalMoves, Constants.NORMAL);
            /*Retrieve possible jump moves for a given checker.*/
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Checker, Map<String,ArrayList<BoardSquare>>> findPossibleMovesForComputer(){
        ArrayList<Checker> checkerList = getAllComputerCheckers();
        Map<Checker, Map<String,ArrayList<BoardSquare>>> checkerBoardSquareMap = new HashMap<>();

        /*This algorithm ensures that each checker key will only
        * have jump moves or normal moves.*/
        for(Checker checker : checkerList) {
            ArrayList<BoardSquare> possibleJumps = this.checkerGame.jumpAvailable(checker);
            ArrayList<BoardSquare> possibleMoves = this.checkerGame.moveAvailable(checker);

            /*All jump moves are added to map*/
            Map<String,ArrayList<BoardSquare>> moveToBoardSquareMap = new HashMap<>(4);
            ArrayList<BoardSquare> boardSquares = new ArrayList<>(4);
            for(BoardSquare boardSquare : possibleJumps){
                if(boardSquare != null){
                    boardSquares.add(boardSquare);
                }
            }
            if(!boardSquares.isEmpty()){
                moveToBoardSquareMap.put(Constants.JUMP,boardSquares);
                checkerBoardSquareMap.put(checker, moveToBoardSquareMap);

                /*we have valid jumps, no need to check for
                * valid regular moves as we must make a jump.*/
                continue;   //move to next checker
            }

            /*All normal moves are added to map.*/
            for(BoardSquare boardSquare : possibleMoves){
                if(boardSquare != null){
                    boardSquares.add(boardSquare);
                }
            }

            /*We have valid normal moves, add them
            * to the map.*/
            if(!boardSquares.isEmpty()){
                moveToBoardSquareMap.put(Constants.NORMAL,boardSquares);
                checkerBoardSquareMap.put(checker, moveToBoardSquareMap);
            }
        }
        return checkerBoardSquareMap;
    }
}
