package board;

import checker.*;
import constants.Constants;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Representation of a checker.Checker board square.
 */
public class BoardSquare extends JPanel{

    /**checker.State of the square Vacant or Occupied.*/
    private SquareState squareState = SquareState.VACANT;

    /**Row on checker board where this square is located.*/
    private final int row;

    /**Column on the checker board where this square is located.*/
    private final int column;

    /**The current state of this board square, clicked or not clicked.*/
    private ClickedState clickedState = ClickedState.NOT_CLICKED;

    /**Reference to the official color of this square so that
     * the square can be reverted to the original color.*/
    private checker.Color squareColor;

    /**Flag to tell if this board square has been added to the checkerBoard.*/
    public boolean added = false;

    /**PropertyChangeListeners that are awaiting events to be generated.*/
    private final ArrayList<PropertyChangeListener> listeners;

    /**
     * Constructor to create a board square at a specified location
     * on the checker board.
     * @param row horizontal location on the checker board.
     * @param column vertical location on the checker board.
     */
    BoardSquare(int row, int column){
        this.listeners = new ArrayList<>();
        this.row = row;
        this.column = column;
        setSquareGuiPreferences();
    }

    /**
     * Set GUI preferences about this square.
     */
    private void setSquareGuiPreferences(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension dim = new Dimension(Constants.WIDTH_SQUARE, Constants.HEIGHT_SQUARE);
        this.setSize(dim);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setBorder(new LineBorder(Color.black));
        this.setVisible(true);
        addSquareMouseListener();
    }

    /**
     * Set the state of the checker square.
     * @param state new state of the square.
     */
    private void setSquareState(SquareState state){
        this.squareState = state;
    }

    /**
     * Get the state of this square.
     * @return current state of this square.
     */
    public SquareState getSquareState(){
        return this.squareState;
    }

    /**
     * Get the horizontal location of this square.
     * @return row location.
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Get the vertical location of this square.
     * @return column location.
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Assign the square a color.
     * If the color is RED or BLUE (Original),
     * Assign the squareColor param of this boardSquare.
     * Valid colors are:
     *      RED (Original)
     *      BLUE (Original)
     *      GREEN (Jump)
     *      ORANGE (Normal Move)
     * @param color any valid checker.Color to set the
     *              square background to.
     */
    public void setSquareColor(checker.Color color){
        switch(color){
            case RED:
                this.squareColor = color;
                this.setBackground(Color.RED);
                break;
            case BLUE:
                this.squareColor = color;
                this.setBackground(Color.BLUE);
                break;
            case GREEN:
                this.setBackground(Color.GREEN);
                break;
            case ORANGE:
                this.setBackground(Color.ORANGE);
                break;
        }
    }

    /**
     * Retrieve the square color of this board square.
     * @return checker.Color of this board square.
     */
    checker.Color getSquareColor(){
        return this.squareColor;
    }

    /**
     * Assign the clicked state of this board square.
     * CLICKED or NOT_CLICKED.
     * @param clickedState new state of this board square.
     */
    void setClickedState(ClickedState clickedState){
        this.clickedState = clickedState;
    }

    /**
     * Retrieve the clicked state of this board square.
     * @return CLICKED or NOT_CLICKED.
     */
    private ClickedState getClickedState(){
        return this.clickedState;
    }

    /**
     * Assign this board square a mouse listener.
     */
    private void addSquareMouseListener(){
        this.addMouseListener(new SquareMouseListener(this));
    }

    /**
     * This mouse listener is responsible for moving a checker piece
     * to a new square if the move is legal.
     */
    private static class SquareMouseListener implements MouseListener{
        private final BoardSquare square;

        SquareMouseListener(BoardSquare square){
            this.square = square;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            /*Change the clicked state of this board square*/
            if(this.square.getClickedState().equals(ClickedState.CLICKED)){
                this.square.setClickedState(ClickedState.NOT_CLICKED);
                this.square.notifyListeners(Constants.SQUARE_CLICKED,ClickedState.NOT_CLICKED);
            }else if(this.square.getClickedState().equals(ClickedState.NOT_CLICKED)){
                this.square.setClickedState(ClickedState.CLICKED);
                this.square.notifyListeners(Constants.SQUARE_CLICKED,ClickedState.CLICKED);
            }

            /*If the clicked state of this square is clicked,
            * move the checker piece that clicked this square to
            * where it clicked if it was a valid move.
            * Reset the clicked state because this is an
            * atomic operation.*/
            if(this.square.getClickedState().equals(ClickedState.CLICKED)){
                this.square.setClickedState(ClickedState.NOT_CLICKED);
                this.square.notifyListeners(Constants.RESET_BOARD,null);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //nothing
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //nothing
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //nothing
        }
    }

    /**
     * Alert all listeners that an event has occurred on the board
     * square.
     * @param propertyChange the type of event that has occurred.
     * @param newValue new value that the item has taken on.
     */
    private void notifyListeners(String propertyChange, Object newValue){
        for(PropertyChangeListener listener : this.listeners){
            listener.propertyChange(new PropertyChangeEvent(this, propertyChange, null, newValue));
        }
    }

    /**
     * Allow other PropertyChangeListeners to add themselves to this class
     *  to be alerted when an event is thrown.
     * @param listener new listener that can listen and act on events
     *                 from this square.
     */
    public void addChangeListener(PropertyChangeListener listener){
        this.listeners.add(listener);
    }

    /**
     * Debugging override to retrieve relevant data for a square.
     * @return String that is comprised of row and column data for the square.
     */
    @Override
    public String toString(){
        return "{Square: "+
                "row-"+
                this.getRow()+
                "_"+
                "column-"+
                this.getColumn()+
                "}";
    }

    /**
     * Remove a checker from a specific square.
     * Set the parameters on the square and checker
     * before executing the GUI updates. This allows
     * for the AI or another player to make a move
     * even if the GUI is not updated.
     * @param checker Checker to be removed from a square.
     */
    public void removeCheckerFromSquare(Checker checker){
        /*Set the params of the checker and square*/
        checker.setCurrentSquare(null);    //no longer on a square if removed.
        this.setSquareState(SquareState.VACANT);    //square state change
        /*Do GUI update*/
        SwingUtilities.invokeLater(new RemoveCheckerFromSquare(this,checker));
    }

    /**
     * Remove a checker for a given square,
     * must be performed on the GUI thread.
     */
    private static class RemoveCheckerFromSquare implements Runnable{
        private BoardSquare boardSquare;
        private Checker checker;

        RemoveCheckerFromSquare(BoardSquare boardSquare, Checker checker){
            this.boardSquare = boardSquare;
            this.checker = checker;
        }

        @Override
        public void run() {
            this.boardSquare.remove(this.checker);  //gui remove
            this.checker.setVisible(false); //checker visibility
            MainFrame.validateComponent(this.boardSquare);
            MainFrame.validateComponent(this.checker);
        }
    }

    /**
     * Add a checker to a specific square.
     * Set the parameters on the square and checker
     * before executing the GUI updates. This allows
     * for the AI or another player to make a move
     * even if the GUI is not updated.
     * @param checker Checker to be added to a square.
     */
    public void addCheckerToSquare(Checker checker){
        /*Set the params of the checker and square*/
        this.setSquareState(SquareState.OCCUPIED);  //square state change
        checker.setCurrentSquare(this);    //checker occupies a new square
        /*Do GUI update*/
        SwingUtilities.invokeLater(new AddCheckerToSquare(this,checker));
    }

    /**
     * Runnable class for adding a checker to a given square,
     * must be performed on the GUI thread.
     */
    private static class AddCheckerToSquare implements Runnable{
        private BoardSquare boardSquare;
        private Checker checker;

        AddCheckerToSquare(BoardSquare boardSquare, Checker checker){
            this.boardSquare = boardSquare;
            this.checker = checker;

        }

        @Override
        public void run() {
            this.boardSquare.add(this.checker); //gui add
            this.checker.setVisible(true);  //check is visible
            MainFrame.validateComponent(this.boardSquare);
            MainFrame.validateComponent(this.checker);
        }
    }
}
