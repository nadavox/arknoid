/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package Shape;
import geometry.Line;
import geometry.Point;
/**
 * @author nadav oxenberg
 * ID: 207952144
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Shape.Rectangle  class. this class define what is rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point lowerRight;
    private Point lowerLeft;
    private Line upperLine;
    private Line lowerLine;
    private Line rightLine;
    private Line leftLine;
    /**
     * Shape.Rectangle constarctor, build the rectangle.
     * @param upperLeft the upperleft point.
     * @param width the width of the rectangle
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerRight = new Point(upperRight.getX(), upperLeft.getY() + height);
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerLine = new Line(lowerLeft, lowerRight);
        this.upperLine = new Line(upperLeft, upperRight);
        this.rightLine = new Line(upperRight, lowerRight);
        this.leftLine = new Line(upperLeft, lowerLeft);
    }

    /**
     * setUpperLeft, cahange the upper left point of the rect.
     * @param upperLeft the new upper point.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerRight = new Point(upperRight.getX(), upperLeft.getY() + height);
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerLine = new Line(lowerLeft, lowerRight);
        this.upperLine = new Line(upperLeft, upperRight);
        this.rightLine = new Line(upperRight, lowerRight);
        this.leftLine = new Line(upperLeft, lowerLeft);
    }

    /**
     * getWidth get the width of the rectangle.
     * @return Return the width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight get the height of the rectangle.
     * @return Return the hegiht.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getUpperLeft, get the upper left point.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getRightUpper get the right upper.
     * @return the right upper
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * getLowerRight get the right lower point.
     * @return the right lower point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }
    /**
     * getLowerLeft get the lower left point.
     * @return lower left point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }
    /**
     * getUpperLine, return the upper line.
     * @return the upper line.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /**
     * get the lower line.
     * @return the lower line.
     */
    public Line getLowerLine() {
        return this.lowerLine;
    }
    /**
     * get the tight line.
     * @return the right line.
     */
    public Line getRightLine() {
        return this.rightLine;
    }
    /**
     * getLeftLine get the left line.
     * @return the left line.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * java.util.List return a list of the intersection points.
     * @param line the line we want to check if there is a intersection.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create a list of points
        List<Point> pointsIntersection = new ArrayList<Point>();
        if (line.isIntersecting(getUpperLine())) {
            pointsIntersection.add(line.intersectionWith(getUpperLine()));
        }
        if (line.isIntersecting(getLowerLine())) {
            pointsIntersection.add(line.intersectionWith(getLowerLine()));
        }
        if (line.isIntersecting(getRightLine())) {
            pointsIntersection.add(line.intersectionWith(getRightLine()));
        }
        if (line.isIntersecting(getLeftLine())) {
            pointsIntersection.add(line.intersectionWith(getLeftLine()));
        }
        return pointsIntersection;
    }

}