/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package geometry;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 * class velocity, specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speedVector;
    /**
     * velocity constructor.
     * @param dx the x speed.
     * @param dy the y speed.
     * @param speedVector the speed vector.
     */
    public Velocity(double dx, double dy, double speedVector) {
        this.dx = dx;
        this.dy = dy;
        this.speedVector = speedVector;
    }
    /**
     * velocity static constructor.
     * @param angle the angle, direction.
     * @param speed in the y term.
     * @return new velocity dx to dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * sin(Math.toRadians(angle));
        double dy = speed * -cos(Math.toRadians(angle));
        return new Velocity(dx, dy, speed);
    }

    /**
     * getSpeedVector, get the speed vector.
     * @return the speed vector.
     */
    public double getSpeedVector() {
        return speedVector;
    }
    /**
     * getDX.
     * @return the x speed
     */
    public double getDX() {
        return dx;
    }
    /**
     * getDY.
     * @return the y speed
     */
    public double getDY() {
        return dy;
    }
    /**
     * applyToPoint, Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point which we add velocity.
     * @return the new point after we add velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(dx + p.getX(), dy + p.getY());
    }
    /**
     * nextsecond, return the point of the next move.
     * @param p get the current point.
     * @return the next point it will be.
     */
    public Point nextsecond(Point p) {
        return applyToPoint(p);
    }
}