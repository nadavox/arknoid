/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package geometry;

/**
 * class point, represent a point in the world.
 * have x and y coordinate.
 */
public class Point {
    private static final double EPSILON = 0.000000001;
    private final double x;
    private final double y;
    /**
     * constructor geometry.Point. build the point.
     * @param x the x point.
     * @param y the y point.
     */
    public Point(double x, double y) {
        this.x  = x;
        this.y = y;
    }
    /**
     * distance -- return the distance of this point to the other point.
     * @param other point
     * @return the distance.
     */
    public double distance(Point other) {
        // ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }
    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other point.
     * @return if the points equals.
     */
    public boolean equals(Point other) {
        double distance = distance(other);
        // return True if the distance 0 else False.
        if (-EPSILON <= distance && distance <= EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * getX Return the x value of this point.
     * @return the x point.
     */
    public double getX() {
        return x;
    }
    /**
     * getY Return the y value of this point.
     * @return the y point.
     */
    public double getY() {
        return y;
    }
}
