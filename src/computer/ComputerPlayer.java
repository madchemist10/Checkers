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
 * This is the computer AI that will
 * make moves based on the current situation of the game.
 */
public class ComputerPlayer {
    private final Color computerColor;
    private final CheckerGame checkerGame;
    private static final Random randomGenerator = new Random(System.currentTimeMillis());
    private static final int COMPUTER_WAIT_TIME = 1000;    //one second timeout

    public ComputerPlayer(Color color, CheckerGame checkerGame){
        this.computerColor = color;
        this.checkerGame = checkerGame;
    }

    public Color getComputerColor(){
        return this.computerColor;
    }

    /**
     * Perform a move based on the possible moves available.
     * This must be ran on a unique thread so the possible
     * move squares have time to render.
     */
    public void makeMove(){
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
        while(elapsedTime < ComputerPlayer.COMPUTER_WAIT_TIME){
            elapsedTime = (System.currentTimeMillis()-startTime);
        }
        /*Move the checker after setting state to clicked.*/
        checkerToMove.setClickedState(ClickedState.CLICKED);
        this.checkerGame.moveSelectedChecker(boardSquareToMoveTo);
    }

    /**
     * Find all possible jumps and normal moves for all given checker pieces.
     * @return Map of checkers to board squares.
     */
    private Map<Checker, Map<String,ArrayList<BoardSquare>>> findPossibleMovesForComputer(){
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

    /**
     * Retrieve all the computer checker pieces based
     * on the computer's color.
     * @return list of checkers for computer.
     */
    private ArrayList<Checker> getAllComputerCheckers(){
        ArrayList<Checker> checkerList = new ArrayList<>(12);
        for(Checker checker : this.checkerGame.getCheckerMap().values()){
            if(checker.getCheckerColor().equals(this.computerColor) &&
                    !checker.isCaptured()) {
                checkerList.add(checker);
            }
        }
        return checkerList;
    }

}
