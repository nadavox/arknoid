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
 * The LevelFour class is a class have all the information for level one of the game.
 */
public class LevelOne implements LevelInformation {
    private BackGround backGround;

    /**
     * This is the numberOfBalls method which returns the number of balls.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * This is the initialBallVelocities method which initialize all the velocities for the balls.
     *
     * @return the the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        //creates the list
        List<Velocity> ballVelocity = new ArrayList<Velocity>();
        //add the velocity
        ballVelocity.add(Velocity.fromAngleAndSpeed(0, 6));
        //return the list
        return ballVelocity;
    }

    /**
     * This is the getBackGroundColor method which returns the background color.
     *
     * @return the the background color
     */
    public Color getBackGroundColor() {
        return Color.black;
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
        return "Direct Hit";
    }

    /**
     * This is the getBackground method which returns the background of the level.
     *
     * @return the background of the level
     */
    public Sprite getBackground() {
        Rectangle backGroundRect = new Rectangle(new Point(0, 0), 800, 600);
        backGround = new BackGround(backGroundRect, this.getBackGroundColor(), 1);
        return backGround;
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
        //creates the list
        List<Block> blocks = new ArrayList<Block>();
        //add the block
        blocks.add(new Block(new Rectangle(new Point(390, 180), 30, 30), Color.red));
        //return the list
        return blocks;
    }

    /**
     * This is the numberOfBlocksToRemove method which returns the number of block to remove.
     *
     * @return the number of block to remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
