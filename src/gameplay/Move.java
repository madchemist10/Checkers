package gameplay;

import constants.Constants;

/**
 * Possible moves for a single checker piece.
 * Forward_Left
 * Forward_Right
 * Backward_Left
 * Backward_Right
 */
public enum Move {

    FORWARD_LEFT (Constants.FORWARD_LEFT),
    FORWARD_RIGHT (Constants.FORWARD_RIGHT),
    BACKWARD_LEFT (Constants.BACKWARD_LEFT),
    BACKWARD_RIGHT (Constants.BACKWARD_RIGHT),
    FORWARD_JUMP_LEFT (Constants.FORWARD_JUMP_LEFT),
    FORWARD_JUMP_RIGHT (Constants.FORWARD_JUMP_RIGHT),
    BACKWARD_JUMP_LEFT (Constants.BACKWARD_JUMP_LEFT),
    BACKWARD_JUMP_RIGHT (Constants.FORWARD_JUMP_RIGHT);

    /**String representation of a color.*/
    private String text;

    /**
     * Constructor to create a color.
     * @param text String representation of the color.
     */
    Move(String text){
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
