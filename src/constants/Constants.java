package constants;

/**
 * This interface contains all the constants used throughout this checker
 * application.
 */
public interface Constants {
    int FRAME_PADDING = 25;

    /*Number of checkers per color*/
    int CHECKER_COUNT = 12;

    /*Number of rows and columns on a checker board*/
    int CHECKER_ROWS = 8;
    int CHECKER_COLUMNS = 8;

    /*Size of each square*/
    int WIDTH_SQUARE = 75;
    int HEIGHT_SQUARE = 75;

    /*Size of mainFrame*/
    int WIDTH_FRAME = (WIDTH_SQUARE * CHECKER_COLUMNS) + FRAME_PADDING;
    int HEIGHT_FRAME = (HEIGHT_SQUARE * CHECKER_ROWS) + (FRAME_PADDING * 5);

    /*Size of GamePlayChoice*/
    int WIDTH_GAME_PLAY = 100;
    int HEIGHT_GAME_PLAY = 200;

    /*Checker icons*/
    String BLACK_CHECKER_ICON = "black_checker_small.png";
    String WHITE_CHECKER_ICON = "white_checker_small.png";
    String BLACK_CHECKER_KING_ICON = "black_checker_small_king.png";
    String WHITE_CHECKER_KING_ICON = "white_checker_small_king.png";

    /*Colors of checkers.*/
    String BLACK = "BLACK";
    String WHITE = "WHITE";
    /*Colors of Squares*/
    String BLUE = "BLUE";
    String RED = "RED";
    String GREEN = "GREEN"; //possible jump color.
    String ORANGE = "ORANGE"; //possible normal move color.

    /*checker.Piece type.*/
    String STANDARD = "STANDARD";
    String KING = "KING";


    /*Clicked state of a checker piece*/
    String CLICKED = "CLICKED";
    String NOT_CLICKED = "NOT_CLICKED";

    /*States of a square on a checkerboard*/
    String VACANT = "VACANT";
    String OCCUPIED = "OCCUPIED";

    /*Possible moves for a checker piece*/
    String FORWARD_LEFT = "FORWARD_LEFT";
    String FORWARD_RIGHT = "FORWARD_RIGHT";
    String BACKWARD_LEFT = "BACKWARD_LEFT";
    String BACKWARD_RIGHT = "BACKWARD_RIGHT";
    String FORWARD_JUMP_LEFT = "FORWARD_JUMP_LEFT";
    String FORWARD_JUMP_RIGHT = "FORWARD_JUMP_RIGHT";
    String BACKWARD_JUMP_LEFT = "BACKWARD_JUMP_LEFT";
    String BACKWARD_JUMP_RIGHT = "BACKWARD_JUMP_RIGHT";

    /*GUI constants*/
    String CHECKERS = "CHECKERS";

    /*Move determination*/
    String JUMP = "JUMP";
    String NORMAL = "NORMAL";

    /*PropertyChangeEvents*/
    String SQUARE_CLICKED = "SQUARE_CLICKED";
    String CHECKER_CLICKED = "CHECKER_CLICKED";
    String RESET_BOARD = "RESET_BOARD";

    /*StatusPanel*/
    String STATUS_PANEL = "Status Panel";
    String WINNER = "Winner: ";
    String CURRENT_TURN = "Current Turn: ";
    String WHITE_PIECES_REMAIN = "White(Remain): ";
    String BLACK_PIECES_REMAIN = "Black(Remain): ";
    String DOUBLE_JUMP = ": Double Jump";

    /*Button labels for game play popup*/
    String TWO_PLAYER_BUTTON = "Two Player";
    String RANDOM_PC_BUTTON = "Random PC";
    String EASY_PC_BUTTON = "Easy PC";
    String MEDIUM_PC_BUTTON = "Medium PC";
    String HARD_PC_BUTTON = "Hard PC";
}
