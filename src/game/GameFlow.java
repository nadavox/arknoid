/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

/**
 * gameflow class. from here we can move between levels.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private GameLevel gameLevel;
    private int score;
    private int lives;
    private GUI gui;
    private int flagWinOrLose;

    /**
     * the constructor pf gameflow.
     * @param ks the keyboard sensor
     * @param gui the screen.
     */
    public GameFlow(KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.keyboardSensor = ks;
        score = 0;
        lives = 1;
        flagWinOrLose = 0;
        runner = new AnimationRunner(gui, 60);
    }

    /**
     * run all the levels.
     * @param levels array with the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            gameLevel = new GameLevel(levelInfo, this.keyboardSensor, score, lives, gui);

            gameLevel.initialize();

            while (gameLevel.getNumberOfBlocksReamin() != 0 && gameLevel.getLives() != 0) {
                gameLevel.run();
            }
            score = gameLevel.getScore();
            lives = gameLevel.getLives();

            if (gameLevel.getNumberBalls() == 0) {
                flagWinOrLose = 1; // we lose.
                break;
            }
        }
        if (flagWinOrLose == 0) {
            KeyPressStoppableAnimation keyForWin = new KeyPressStoppableAnimation(keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new WinScrreen(this.keyboardSensor, score));
            this.runner.run(keyForWin);
        }
        gui.close();

    }
}
