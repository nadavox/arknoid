/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package score;

import biuoop.DrawSurface;
import charcters.Block;
import counter.Counter;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * score.ScoreIndicator class that tell me the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Block block;
    private String score;

    /**
     * the constactor.
     * @param scoreCounter counter for the score
     * @param block the block we present the score.
     */
    public ScoreIndicator(Counter scoreCounter, Block block) {
        this.scoreCounter = scoreCounter;
        this.block = block;
        score = "Score: ";
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillRectangle((int) block.getCollisionRectangle().getUpperLeft().getX(),
                (int) block.getCollisionRectangle().getUpperLeft().getY(),
                (int) block.getCollisionRectangle().getWidth(), (int) block.getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        //draw the rectangle.
        surface.drawRectangle((int) block.getCollisionRectangle().getUpperLeft().getX(),
                (int) block.getCollisionRectangle().getUpperLeft().getY(),
                (int) block.getCollisionRectangle().getWidth(),
                (int) block.getCollisionRectangle().getHeight());
        surface.drawText(340, 25, score + scoreCounter.getValue(), 18);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
