package launch;

import gameplay.CheckerGame;
import gui.MainFrame;
import gui.StatusPanel;

/**
 * Game launcher for checker game.
 */
public class GameLaunch {
    public static void main(String[] args) {
        CheckerGame checkerGame = new CheckerGame(new StatusPanel());
        MainFrame mainFrame = new MainFrame(checkerGame);
        mainFrame.setVisible(true);
    }
}
