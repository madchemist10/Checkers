package checker;

import constants.Constants;

/**
 * Enum to represent the type of piece.
 */
public enum Piece {

    STANDARD (Constants.STANDARD),
    KING (Constants.KING);

    /**String representation of a color.*/
    private String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    Piece(String text){
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
