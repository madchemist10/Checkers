package checker;

import constants.Constants;

/**
 * Enum to represent possible colors of pieces.
 */
public enum Color {

    BLACK (Constants.BLACK),
    WHITE (Constants.WHITE),
    RED (Constants.RED),
    BLUE (Constants.BLUE),
    GREEN (Constants.GREEN),    //jump move
    ORANGE (Constants.ORANGE);  //normal move

    /**String representation of a color.*/
    private String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    Color(String text){
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
