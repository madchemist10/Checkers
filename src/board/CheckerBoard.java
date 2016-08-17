package board;

import checker.ClickedState;
import checker.Color;
import constants.Constants;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.WeakReference;

/**
 * Checker board that acts as a JPanel for the
 * checkers and board squares to reside on.
 */
public class CheckerBoard extends JPanel{

    /** Two-Dimensional array of Board Squares.*/
    private BoardSquare[][] checkerBoard = new BoardSquare[Constants.CHECKER_ROWS][Constants.CHECKER_COLUMNS];

    /**
     * Constructor to create checkerboard with board squares.
     */
    public CheckerBoard(){
        this.setLayout(new GridBagLayout());    //assign layout to checkerboard
        assignBoardSquaresToCheckerBoard();
    }

    /**
     * Retrieve the checker board.
     * @return two dimensional array of board squares that represents
     *          this checker board.
     */
    public BoardSquare[][] getCheckerBoard(){
        return this.checkerBoard;
    }

    /**
     * Fill the checkerBoard with BoardSquares.
     */
    private void assignBoardSquaresToCheckerBoard(){
        for(int row = 0; row < Constants.CHECKER_ROWS; row++){
            for(int column = 0; column < Constants.CHECKER_COLUMNS; column++){
                BoardSquare boardSquare = new BoardSquare(row,column);
                /*Used to offset colors into actual grid pattern*/
                if((column+row)%2 == 0){
                    boardSquare.setSquareColor(Color.BLUE);
                }else{
                    boardSquare.setSquareColor(Color.RED);
                }
                this.checkerBoard[row][column] = boardSquare;
            }
        }
    }

    /**
     * Assign each board square their original square color.
     */
    public void resetBoardSquares(){
        for(BoardSquare[] boardSquareRow : this.checkerBoard){
            for(BoardSquare boardSquare : boardSquareRow){
                boardSquare.setSquareColor(boardSquare.getSquareColor());
                boardSquare.setClickedState(ClickedState.NOT_CLICKED);
            }
        }
    }

    /**
     * Add a board square to this checkerboard.
     * @param boardSquare square to be added to this board.
     */
    public void addSquareToBoard(BoardSquare boardSquare){
        SwingUtilities.invokeLater(new AddSquareToBoard(this,boardSquare));
    }

    /**
     * Add a square to the board,
     * must be run on the GUI thread.
     */
    private static class AddSquareToBoard implements Runnable{
        private final WeakReference<CheckerBoard> wCheckerBoard;
        private final WeakReference<BoardSquare> wBoardSquare;

        AddSquareToBoard(CheckerBoard checkerBoard, BoardSquare boardSquare){
            this.wCheckerBoard = new WeakReference<>(checkerBoard);
            this.wBoardSquare = new WeakReference<>(boardSquare);
        }
        @Override
        public void run() {
            CheckerBoard checkerBoard = this.wCheckerBoard.get();
            BoardSquare boardSquare = this.wBoardSquare.get();
            if(checkerBoard == null || boardSquare == null){
                return;
            }
            GridBagConstraints grid = new GridBagConstraints();
            grid.gridx = boardSquare.getColumn();
            grid.gridy = boardSquare.getRow();
            checkerBoard.add(boardSquare,grid);
            MainFrame.validateComponent(boardSquare);
            MainFrame.validateComponent(checkerBoard);
        }
    }
}
