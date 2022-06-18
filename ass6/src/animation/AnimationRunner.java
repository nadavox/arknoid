/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * AnimationRunner class, run the annimation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * constructor.
     * @param gui the screen.
     * @param framesPerSecond how many frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        sleeper = new Sleeper();
    }

    /**
     * run the game.
     * @param animation the animtion we want to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
//        DrawSurface d = gui.getDrawSurface();
//        animation.doOneFrame(d);
//        gui.show(d);
        //this.sleeper.sleepFor(200);
    }
}