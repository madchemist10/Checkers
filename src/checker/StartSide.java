package checker;

import constants.Constants;

/**
 * Maintain location of starting side of piece.
 * Either White or Black.
 */
public enum StartSide {

    BLACK (Constants.BLACK),
    WHITE (Constants.WHITE);

    /**String representation of a color.*/
    private final String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    StartSide(String text){
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
