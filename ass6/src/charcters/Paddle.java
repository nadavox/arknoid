/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package charcters;
import Shape.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;
import java.util.Random;


/**
 * class paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private int widthpaddle;

    /**
     * paddle, constructor.
     * @param gui the screen.
     * @param width width of the paddle.
     * @param height the height of the paddle.
     * @param heightpaddle the high of the paddle.
     * @param speed the speed of the paddle.
     * @param widthpaddle the sidth of the paddle.
     */
    public Paddle(GUI gui, int width, int height, int widthpaddle, int heightpaddle, int speed) {
        this.keyboard = gui.getKeyboardSensor();
        rectangle = new Rectangle(new Point(width, height), widthpaddle, heightpaddle);
        this.color = Color.yellow;
        this.speed = speed;
        this.widthpaddle = widthpaddle;
    }
    /**
     * This is the moveLeft method which move the paddle left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() >= 50) {
            double x = rectangle.getUpperLeft().getX() - speed;
            double y = rectangle.getUpperLeft().getY();
            rectangle.setUpperLeft(new Point(x, y));
        }
    }
    /**
     * This is the moveRight method which move the paddle right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() <= 750 - widthpaddle) {
            double x = speed + rectangle.getUpperLeft().getX();
            double y = rectangle.getUpperLeft().getY();
            rectangle.setUpperLeft(new Point(x, y));
        }
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //System.out.println("the 'left arrow' key is pressed");
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //System.out.println("the 'right arrow' key is pressed");
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        //set the color of the paddle
        d.setColor(color);
        // draw the rectangle of the ball
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        //set the color
        d.setColor(Color.black);
        //draw the rectangle
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());    }
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Random rand = new Random();
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        double area1 = widthpaddle * 0.20;
        double area2 = widthpaddle * 0.40;
        double area3 = widthpaddle * 0.60;
        double area4 = widthpaddle * 0.80;
        double area5 = widthpaddle;
        //point one is the lefter point.
        Point pointOne = new Point(rectangle.getUpperLeft().getX() - 5, rectangle.getUpperLeft().getY());
        //point two is point one + area 1
        Point pointTwo = new Point(rectangle.getUpperLeft().getX() + area1, rectangle.getUpperLeft().getY());
        //point three is point one + area 2
        Point pointThree = new Point(rectangle.getUpperLeft().getX() + area2, rectangle.getUpperLeft().getY());
        //point four is point one + area 3
        Point pointFour = new Point(rectangle.getUpperLeft().getX() + area3, rectangle.getUpperLeft().getY());
        //point five is point one + area 4.
        Point pointFive = new Point(rectangle.getUpperLeft().getX() + area4, rectangle.getUpperLeft().getY());
        //point six is the right upper point.
        Point pointSix = new Point(rectangle.getUpperLeft().getX() + area5 + 5, rectangle.getUpperLeft().getY());
        //upper bound
        if (Double.compare(rectangle.getUpperLeft().getY(), collisionPoint.getY()) == 0) {
            //hit area one
            if (pointOne.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointTwo.getX()) {
                //return new geometry.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeedVector());
            }
            //hit area two
            if (pointTwo.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointThree.getX()) {
                //geometry.Velocity newV = new geometry.Velocity(currentVelocity.getDX(), currentVelocity.getDY());
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeedVector());
            }
            //hit area three
            if (pointThree.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointFour.getX()) {
                return new Velocity(currentVelocity.getDX(),
                        -currentVelocity.getDY(), currentVelocity.getSpeedVector());
            }
            //hit area four
            if (pointFour.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointFive.getX()) {
                //return new geometry.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeedVector());
            }
            //hit area five
            if (pointFive.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointSix.getX()) {
                //return new geometry.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeedVector());
            }
        }
        //hit the right wall
        if (Double.compare(rectangle.getUpperRight().getX(), collisionPoint.getX()) == 0) {
            //hit area five
            //return new geometry.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeedVector());
        } else {
            //hit area one and left wall
            if (pointOne.getX() <= collisionPoint.getX() && collisionPoint.getX() < pointTwo.getX()) {
                //return new geometry.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeedVector());
            }
        }
        return new Velocity(currentVelocity.getDX(),
                -currentVelocity.getDY(), currentVelocity.getSpeedVector());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * get the left up x of the paddle.
     * @return the x left top.
     */
    public int getLeftPaddleX() {
        return (int) this.rectangle.getUpperLeft().getX();
    }

    /**
     * get the left up y of the paddle.
     * @return the y left top.
     */
    public int getLeftPaddleY() {
        return (int) this.rectangle.getUpperLeft().getY();
    }

    /**
     * get the right up x of the paddle.
     * @return the x right top.
     */
    public int getRightPaddleX() {
        return (int) this.rectangle.getUpperRight().getX();
    }
}