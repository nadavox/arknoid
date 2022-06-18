/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;

import Shape.Rectangle;
import charcters.Block;
import geometry.Point;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The LevelFour class is a class have all the information for level four of the game.
*/
public class LevelFour implements LevelInformation {

    /**
     * This is the numberOfBalls method which returns the number of balls.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * This is the initialBallVelocities method which initialize all the velocities for the balls.
     *
     * @return the the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        //creates the list
        List<Velocity> ballVelocity = new ArrayList<Velocity>();
        //create the velocities and add them to the list
        ballVelocity.add(Velocity.fromAngleAndSpeed(3, 8));
        ballVelocity.add(Velocity.fromAngleAndSpeed(10, 7));
        ballVelocity.add(Velocity.fromAngleAndSpeed(-10, 9));
        //return the list
        return ballVelocity;
    }

    /**
     * This is the getBackGroundColor method which returns the background color.
     *
     * @return the the background color
     */
    public Color getBackGroundColor() {
        //create the color
        return new Color(26, 120, 180);
    }

    /**
     * This is the paddleSpeed method which returns the paddle speed.
     *
     * @return the the paddle speed
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * This is the levelName method which returns the level name.
     *
     * @return the the level name
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * This is the getBackground method which returns the background of the level.
     *
     * @return the background of the level
     */
    public Sprite getBackground() {
        Rectangle backGroundRect = new Rectangle(new Point(0, 0), 800, 600);
        return new BackGround(backGroundRect, this.getBackGroundColor(), 4);
    }

    /**
     * This is the paddleHeight method which returns the paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * This is the blocks method which returns a list full of the blocks of the level.
     *
     * @return the list of the blocks of the level
     */
    public List<Block> blocks() {
        //creates the lost
        List<Block> blocks = new ArrayList<Block>();
        //set the start point
        int startX = 40, startY = 120;
        // set an array of colors
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        //creates the blocks
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                blocks.add(new Block(new Rectangle(new Point(startX + 48 * j, 120 + 22 * i),
                        48, 22), colors[i]));
            }
        }
        //return the list
        return blocks;
    }

    /**
     * This is the numberOfBlocksToRemove method which returns the number of block to remove.
     *
     * @return the number of block to remove.
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
