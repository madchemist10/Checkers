package gui;

import constants.Constants;

/**
 * Enum to represent possible game play types.
 */
public enum GamePlay {

    TWO_PLAYER (Constants.TWO_PLAYER_BUTTON),
    RANDOM (Constants.RANDOM_PC_BUTTON),
    EASY (Constants.EASY_PC_BUTTON),
    MEDIUM (Constants.MEDIUM_PC_BUTTON),
    HARD (Constants.HARD_PC_BUTTON);

    /**String representation of a game play type.*/
    private String text;

    /**
     * Constructor to create a game play type.
     * @param text String representation of the game play type.
     */
    GamePlay(String text){
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
