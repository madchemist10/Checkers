package checker;

import constants.Constants;

/**
 * Maintains the clicked state of a checker piece.
 */
public enum ClickedState {

    CLICKED (Constants.CLICKED),
    NOT_CLICKED (Constants.NOT_CLICKED);

    /**String representation of a color.*/
    private String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    ClickedState(String text){
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
