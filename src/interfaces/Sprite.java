/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package interfaces;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * interface interfaces.Sprite represent a character in the game.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed, notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * addToGame, add spirite to the game.
     * @param g
     */
    void addToGame(GameLevel g);
}