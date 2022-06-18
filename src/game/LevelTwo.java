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
 * The LevelFour class is a class have all the information for level two of the game.
 */
public class LevelTwo implements LevelInformation {

    /**
     * This is the numberOfBalls method which returns the number of balls.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 13;
    }

    /**
     * This is the initialBallVelocities method which initialize all the velocities for the balls.
     *
     * @return the the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        //creates a list
        List<Velocity> ballVelocity = new ArrayList<Velocity>();
        //add the velocities
        for (int i = 0; i < this.numberOfBalls() * 15; i += 15) {
            ballVelocity.add(Velocity.fromAngleAndSpeed(270 + i, 8));
        }
        //return the list
        return ballVelocity;
    }

    /**
     * This is the getBackGroundColor method which returns the background color.
     *
     * @return the the background color
     */
    public Color getBackGroundColor() {
        return Color.white;
    }

    /**
     * This is the paddleSpeed method which returns the paddle speed.
     *
     * @return the the paddle speed
     */
    public int paddleSpeed() {
        return 1;
    }

    /**
     * This is the levelName method which returns the level name.
     *
     * @return the the level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * This is the getBackground method which returns the background of the level.
     *
     * @return the background of the level
     */
    public Sprite getBackground() {
        Rectangle backGroundRect = new Rectangle(new Point(0, 0), 800, 600);
        return new BackGround(backGroundRect, this.getBackGroundColor(), 2);
    }

    /**
     * This is the paddleHeight method which returns the paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * This is the blocks method which returns a list full of the blocks of the level.
     *
     * @return the list of the blocks of the level
     */
    public List<Block> blocks() {
        //creates the list
        List<Block> blocks = new ArrayList<Block>();
        //set the start point
        int startX = 40, startY = 250;
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            // add the blocks to the list
            if (i == 0 || i == 1) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.red));
            }
            if (i == 2 || i == 3) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.orange));
            }
            if (i == 4 || i == 5) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.yellow));
            }
            if (i == 6 || i == 7 || i == 8) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.green));
            }
            if (i == 9 || i == 10) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.blue));
            }
            if (i == 11 || i == 12) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.pink));
            }
            if (i == 13 || i == 14) {
                blocks.add(new Block(new Rectangle(new Point(startX + i * 48, startY), 48,
                        22), Color.CYAN));
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
        return 15;
    }

}


