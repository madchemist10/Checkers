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
class AComputerPlayer implements IComputerPlayer {

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
     * {@inheritDoc}
     */
    public Color getColor(){
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
