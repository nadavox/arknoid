/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package interfaces;

import charcters.Block;
import geometry.Velocity;
import java.util.List;

/**
 * the interface of the levels.
 */
public interface LevelInformation {
    /**
     * number of balls.
     * @return the number of balss in the level.
     */
    int numberOfBalls();

    /**
     * init the velocity of each ball.
     * @return list of all the velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * get the paddle speed.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * get the padddle width.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * get the name level.
     * @return the level name.
     */
    String levelName();

    /**
     *  get background func.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return the list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     * @return the number of blocks remain.
     */
    int numberOfBlocksToRemove();
}