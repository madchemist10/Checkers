package checker;

import board.BoardSquare;
import constants.Constants;
import gameplay.CheckerGame;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class represents a single checker piece and all the states
 * and possible moves associated with this checker piece.
 */
public class Checker extends JToggleButton{

    /** Color associated with this checker piece.*/
    private Color checkerColor;

    /** Type of this checker piece. Standard or King.*/
    private Piece checkerType = Piece.STANDARD;

    /** Unique ID of this checker piece.*/
    private final String checkerID;

    /** Location on checker board where this checker is located.*/
    private BoardSquare currentSquare;

    /** State of if this checker toggle button is clicked.*/
    private ClickedState clickedState = ClickedState.NOT_CLICKED;

    /** Starting side of this checker. Black or White.*/
    private StartSide startSide;

    /** Keep up with the checker game that this checker is apart of.*/
    private CheckerGame checkerGame;

    /** Flag to tell if the piece has been captured.*/
    private boolean captured = false;

    /**PropertyChangeListeners that are awaiting events to be generated.*/
    private final ArrayList<PropertyChangeListener> listeners;

    /**
     * Constructor to create a new checker piece.
     * @param checkerColor Color of this checker piece.
     * @param checkerID integer identifier for this checker piece.
     *                  Color prepended to checkerID.
     */
    public Checker(Color checkerColor, int checkerID){
        listeners = new ArrayList<>();
        this.checkerColor = checkerColor;
        this.checkerID = this.checkerColor.toString() + "_" + checkerID;
        this.determineStartSide();
        setCheckerGuiPreferences();
        this.setText(Integer.toString(checkerID));
    }

    /**
     * Determine the start side based on the starting
     * color.
     */
    private void determineStartSide(){
        switch(this.checkerColor){
            case BLACK:
                this.startSide = StartSide.BLACK;
                break;
            case WHITE:
                this.startSide = StartSide.WHITE;
                break;
        }
    }

    /**
     * Alert requester if this checker is captured.
     * @return true if this piece is captured,
     *          false if this piece is not captured.
     */
    public boolean isCaptured(){
        return this.captured;
    }

    /**
     * Set flag that this checker has
     * been captured.
     */
    public void setCaptured(){
        this.captured = true;
    }

    /**
     * Assign a checker game to this checker piece.
     * @param checkerGame the checker game associated
     *                    with this checker.
     */
    public void setCheckerGame(CheckerGame checkerGame){
        this.checkerGame = checkerGame;
    }

    /**
     * Retrieve the start side of this checker piece.
     * @return Starting side of this checker.
     */
    public StartSide getStartSide(){
        return this.startSide;
    }

    /**
     * Retrieve this checker piece type.
     * @return KING or STANDARD
     */
    public Piece getCheckerPiece(){
        return this.checkerType;
    }

    /**
     * Set GUI preferences about this checker.
     */
    private void setCheckerGuiPreferences(){
        this.setVisible(true);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        setCheckerIcon();
        addCheckerMouseListener();
    }

    /**
     * Set the icon of this checker piece.
     * Either Black or White
     * Either Standard or King
     */
    private void setCheckerIcon(){
        String checkerIcon = null;
        if(this.checkerColor.toString().equals(Constants.BLACK)){
            if(this.checkerType.toString().equals(Constants.STANDARD)) {
                checkerIcon = Constants.BLACK_CHECKER_ICON;
            }else if(this.checkerType.toString().equals(Constants.KING)){
                checkerIcon = Constants.BLACK_CHECKER_KING_ICON;
            }
        }else if(this.checkerColor.toString().equals(Constants.WHITE)){
            if(this.checkerType.toString().equals(Constants.STANDARD)) {
                checkerIcon = Constants.WHITE_CHECKER_ICON;
            }else if(this.checkerType.toString().equals(Constants.KING)){
                checkerIcon = Constants.WHITE_CHECKER_KING_ICON;
            }
        }
        if(checkerIcon == null){
            return;
        }
        URL iconLocation = getClass().getClassLoader().getResource(checkerIcon);
        if(iconLocation != null){
            this.setIcon(new ImageIcon(iconLocation));
        }
    }

    /**
     * Promote this checker piece to a king.
     * Occurs when a checker reaches the far side of the
     * opponent's territory.
     */
    public void promoteToKing(){
        this.checkerType = Piece.KING;
        SwingUtilities.invokeLater(new KingPromotion(this));
    }

    /**
     * Retrieve the checker ID associated with this checker piece.
     * @return String representation of checkerID = COLOR_#
     */
    public String getCheckerID(){
        return this.checkerID;
    }

    /**
     * Retrieve the current location of this checker.
     * @return board.BoardSquare of where the piece is located.
     */
    public BoardSquare getCurrentSquare(){
        return this.currentSquare;
    }

    /**
     * Assign a new location for this checker.
     * @param boardSquare location on the checkerBoard where the piece is now located.
     */
    public void setCurrentSquare(BoardSquare boardSquare){
        this.currentSquare = boardSquare;
    }

    /**
     * Retrieve the clicked state of this checker.
     * @return Clicked or Not_Clicked
     */
    public ClickedState getClickedState(){
        return this.clickedState;
    }

    /**
     * Set the clicked state of this checker.
     * @param clickedState Clicked or Not_Clicked
     */
    public void setClickedState(ClickedState clickedState){
        this.clickedState = clickedState;
    }

    /**
     * Retrieve the color of this checker piece.
     * Used for determining if this piece can move.
     * @return BLACK or WHITE
     */
    public Color getCheckerColor(){
        return this.checkerColor;
    }

    /**
     * Add the mouse listener to this checker
     * for determining when the checker can move.
     */
    private void addCheckerMouseListener(){
        this.addMouseListener(new CheckerMouseListener(this));
    }

    /**
     * This mouse listener is responsible for changing the
     * clicked state of the checker piece.
     */
    private static class CheckerMouseListener implements MouseListener{
        private Checker checker;

        CheckerMouseListener(Checker checker){
            this.checker = checker;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.checker.notifyListeners(Constants.RESET_BOARD,this.checker);
            if(this.checker.getClickedState().equals(ClickedState.CLICKED)){
                this.checker.setClickedState(ClickedState.NOT_CLICKED);
                this.checker.notifyListeners(Constants.CHECKER_CLICKED,ClickedState.NOT_CLICKED);
            }else if(this.checker.getClickedState().equals(ClickedState.NOT_CLICKED)){
                this.checker.setClickedState(ClickedState.CLICKED);
                this.checker.notifyListeners(Constants.CHECKER_CLICKED,ClickedState.CLICKED);
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
     * This class is responsible for promoting the standard
     * checker piece to be a king piece.
     */
    private static class KingPromotion implements Runnable{
        private Checker checker;

        KingPromotion(Checker checker){
            this.checker = checker;
        }

        @Override
        public void run() {
            this.checker.setCheckerIcon();
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
     * Debugging override to retrieve relevant data for a checker.
     * @return String that is comprised checker id, checker type, and current square.
     */
    @Override
    public String toString(){
        String checkerString = "[*Checker: "+
                this.getCheckerID()+
                "_"+
                this.checkerType.toString();
        checkerString+="]";
        return checkerString;
    }
}
