/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * class CountdownAnimation make the count down to 0.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection spriteCollection;
    private boolean stop;
    private double seconds;
    private final Sleeper sleeper;

    /**
     * constructor.
     * @param spriteCollection all the sprites in the games.
     */
    public CountdownAnimation(SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
        seconds = 3;
        sleeper = new Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        spriteCollection.drawAllOn(d);
        if (2.1 <= seconds && seconds <= 3) {
            spriteCollection.drawAllOn(d);
            d.setColor(Color.white);
            d.drawText(380, 370, "3", 100);
        } else if (1.1 <= seconds && seconds < 2.1) {
            spriteCollection.drawAllOn(d);
            d.setColor(Color.red);
            d.drawText(380, 370, "2", 100);
        } else if (0 <= seconds && seconds < 1.1) {
            spriteCollection.drawAllOn(d);
            d.setColor(Color.blue);
            d.drawText(380, 370, "1", 100);
        }
        seconds = seconds - 0.02;
    }

    /**
     * updafe the seconds.
     * @param seconds
     */
    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
