package board;

import constants.Constants;

/**
 * checker.State of a square on a checker board.
 * Occupied or Vacant.
 */
public enum SquareState {

    VACANT (Constants.VACANT),
    OCCUPIED (Constants.OCCUPIED);

    /**String representation of a color.*/
    private String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    SquareState(String text){
        this.text = text;
    }

    /**
     * Overrode toString method.
     * @return String representation of the enum.
     */
    @Override
    public String toString() {
        return this.text;
    }
}
