/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package nameLevel;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * the name and level string class.
 */
public class NameLevelString implements Sprite {
    private String levelName;
    private int lives;

    /**
     * constructor for the class.
     * @param levelName the level name
     * @param lives he lives that the player left.
     */
    public NameLevelString(String levelName, int lives) {
        this.levelName = levelName;
        this.lives = lives;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(550, 27, "level name: " + levelName, 20);
        surface.drawText(20, 27, "lives: " + lives, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * updateing the lives. minos one.
     */
    public void updateLives() {
        lives = lives - 1;
    }

    /**
     * get the lives.
     * @return the amount of lives the player have.
     */
    public int getLives() {
        return lives;
    }
}

