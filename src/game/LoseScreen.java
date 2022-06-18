/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;

import Shape.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import interfaces.Animation;

import java.awt.Color;

/**
 * the lose screen class.
 */
public class LoseScreen implements Animation {
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor for the class.
     * @param score the score.
     * @param keyboard the keyboard sensor.
     */
    public LoseScreen(KeyboardSensor keyboard, int score) {
        this.rectangle = new Rectangle(new Point(0, 0), 800, 600);
        this.keyboard = keyboard;
        this.stop = false;
        this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(300, 240, "YOU LOSE", 42);
        d.drawText(285, 290, "your score: " + score, 42);
        d.drawText(270, 340, "press space to exit: ", 32);
        d.drawCircle(420, 300, 200);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
