/**
 * @author nadav oxenberg
 * ID: 207952144
 */

package charcters;
import biuoop.DrawSurface;
import collidables.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * ball class, represent a ball.
 * it has point center, radios, color, and velocity
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int radios;
    private Color color;
    private Velocity velocity = new Velocity(0, 0, 0);
    private GameEnvironment environment;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * constactor for charcters.Ball.
     * @param center the center point of the ball.
     * @param r the radios.
     * @param color the color of the ball.
     * @param environment the enviorment of the ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.radios = r;
        this.color = color;
        this.environment = environment;
    }
    /**
     * getX.
     *
     * @return the x center
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * getY.
     *
     * @return the y center
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * getSize.
     *
     * @return the radios.
     */
    public int getSize() {
        return radios;
    }
    /**
     * java.awt.Color.
     *
     * @return the color.
     */
    public java.awt.Color getColor() {
        return color;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), radios);
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), radios);
    }
    /**
     * setVelocity, set the velocity of a ball.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * setVelocity, set the velocity of a ball.
     *
     * @param dx the x velocity.
     * @param dy the y velocity.
     * @param speedVector the speed of the vector speed.
     */
    public void setVelocity(double dx, double dy, double speedVector) {
        this.velocity = new Velocity(dx, dy, speedVector);
    }
    /**
     * getVelocity, get the velocity.
     *
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * createTrajectory, create the trajectory of the ball.
     * @return the line trajectory.
     */
    public Line createTrajectory() {
        Point nextPoint = this.velocity.nextsecond(this.center);
        Line trajectory = new Line(this.center, nextPoint);
        trajectory.setStartSpeedVector(this.center);
        return trajectory;
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * moveonestep, moving the ball by one time we add velocity.
     */
    public void moveOneStep() {
        //create the trajectory.
        Line trajectory = createTrajectory();
        CollisionInfo infoPoint = this.environment.getClosestCollision(trajectory);
        if (infoPoint == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            if (infoPoint.collisionPoint() == null) {
                this.center = this.getVelocity().applyToPoint(this.center);
            } else {
                //there is a hit, change the center to close of the interection point.
                //change the velocity to a new velocity.

                //make averge point
                Line lineHit = new Line(trajectory.getStartSpeedVector(), infoPoint.collisionPoint());
                this.center = lineHit.middle();
                //this.center = infoPoint.collisionPoint();
                this.velocity = infoPoint.collisionObject().hit(this, infoPoint.collisionPoint(), this.velocity);
                //check if is inside a rect if it dose move again so it go outside.
                Point upperLeft = infoPoint.collisionObject().getCollisionRectangle().getUpperLeft();
                Point upperRight = infoPoint.collisionObject().getCollisionRectangle().getUpperRight();
                Point lowerLeft = infoPoint.collisionObject().getCollisionRectangle().getLowerLeft();
                //if the ball inside a rect.
                while (upperLeft.getY() <= center.getY() && center.getY() <= lowerLeft.getY()
                        && upperLeft.getX() <= center.getX() && center.getX() <= upperRight.getX()) {
                    this.center = this.getVelocity().applyToPoint(this.center);
                }
            }
        }
    }
    @Override
    public void addToGame(GameLevel g) {
        Sprite s = this;
         g.addSprite(s);
    }

    /**
     * removeFromGame, remove the block from the game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}