/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package geometry;

import Shape.Rectangle;

import java.util.List;

/**
 * class geometry.Line, represent a line, a geometry.Line represent by two points.
 */
public class Line {
    private Point start;
    private Point end;
    private Point startSpeedVector; //this is for the trajectory vector, so we can seave the center of the ball.
    /**
     * the constructor function, get two point and make a line.
     * @param start the start point.
     * @param end the end point.
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else if (start.getX() > end.getX()) {
            this.start = end;
            this.end = start;
        } else {
            if (start.getY() < end.getY()) {
                this.start = start;
                this.end = end;
            } else {
                this.end = start;
                this.start = end;
            }
        }
    }
    /**
     * constructor number two, get two point but in x1,y1 and x2,y2.
     * @param x1 the x of the start point.
     * @param y1 the y of the start point.
     * @param x2 the x of the end point.
     * @param y2 the y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (Double.compare(x1, x2) == 0) {
            if (y1 < y2) {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
            } else {
                this.start = new Point(x2, y2);
                this.end = new Point(x1, y1);
            }
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
    }

    /**
     * setStartSpeedVector, it should make the center point of the ball.
     * @param center
     */
    public void setStartSpeedVector(Point center) {
        this.startSpeedVector = center;
    }
    /**
     * length, Return the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * middle, Returns the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double x, y;
        x = (this.start.getX() + this.end.getX()) / 2;
        y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }
    /**
     * start, Returns the start point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }
    /**
     * end, Returns the end point of the line.
     * @return Returns the end point of the line
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }
    /**
     * getDY_DX, calculate the derivative.
     * @return the derivative.
     */
    public double getDYDX() {
        // m1 = (y2 - y1) / (x2 - x1)
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }
    /**
     * getIntersectionWithY calculate the intersiction with the y.
     * @return the intersection of y.
     */
    public double getIntersectionWithY() {
        // b1 = y - m1x
        return this.start.getY() - this.getDYDX() * this.start.getX();
    }
    /**
     * isIntersecting, Returns true if the lines intersect, false otherwise.
     * @param other line.
     * @return true if they're intersecting.
     */
    public boolean isIntersecting(Line other) {
        //this line has infinty incline - this line is vertical the other is normal
        if ((Double.compare(this.start.getX(), this.end.getX()) == 0)
                && (Double.compare(other.start.getX(), other.end.getX()) != 0)) {
            Point interPoint = this.thisLineVericallintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        //the other line has infinty icline - the other line is vertical this line is normal
        if (Double.compare(this.start.getX(), this.end.getX()) != 0
                && Double.compare(other.start.getX(), other.end.getX()) == 0) {
            Point interPoint = this.otherLineVericallintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        // both of them has infinty incline - both of them vertical.
        if (Double.compare(this.start.getX(), this.end.getX()) == 0
                && Double.compare(other.start.getX(), other.end.getX()) == 0) {
            Point interPoint = this.bothLineVericallintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        // this line has zero incline - this line is horzintal and the other is normal
        if (Double.compare(this.start.getY(), this.end.getY()) == 0
                && Double.compare(other.start.getY(), other.end.getY()) != 0) {
            Point interPoint = this.thisLineHorizontalintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        //the other line has zero incline - other line is horzintal and this line is normal
        if (Double.compare(this.start.getY(), this.end.getY()) != 0
                && Double.compare(other.start.getY(), other.end.getY()) == 0) {
            Point interPoint = this.otherLineHorizontalintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        //both of them have zero incline - both of them horizntal lines.
        if (Double.compare(this.start.getY(), this.end.getY()) == 0
                && Double.compare(other.start.getY(), other.end.getY()) == 0) {
            Point interPoint = this.bothLineHorizontalintersection(other);
            if (interPoint == null) {
                return false;
            }
            return true;
        }
        //regular intersection
        Point interPoint = regularIntersectionPoint(other);
        // ig there is no intersection
        if (interPoint == null) {
            return false;
        }
        if (start.getX() <= interPoint.getX() && interPoint.getX() <= end().getX()
                && other.start.getX() <= interPoint.getX() && interPoint.getX() <= other.end.getX()) {
            return true;
        }
        return false;
    }
    /**
     * other geometry.Line Horizontal intersection, the other line is horizntal and this line is regular.
     * @param other line.
     * @return the point intersection.
     */
    public Point otherLineHorizontalintersection(Line other) {
        double b1 = this.getIntersectionWithY();
        double m1 = this.getDYDX();
        //y = mx + b
        //x = ( y - b ) / m
        double x = (other.start.getY() - b1) / m1;
        if (this.isOnLine(new Point(x, other.start.getY())) && other.isOnLine(new Point(x, other.start.getY()))) {
            return new Point(x, other.start.getY());
        }
        return null;
    }
    /**
     * checking if point is on another line.
     * @param intersection point.
     * @return true or false.
     */
    public boolean isOnLine(Point intersection) {
        //startx is if the point is close to the x point of the stat
        double startX = Double.compare(intersection.getX(), start.getX());
        //endx is if the point is colsoe to the x end of the line
        double endX =  Double.compare(intersection.getX(), end.getX());
        //starty is the y start of the point
        double startY = Double.compare(intersection.getY(), start.getY());
        //endy is the y end of the line
        double endY = Double.compare(intersection.getY(), end.getY());
        // if the point is on line.
        if ((startX >= 0 && endX <= 0 && startY >= 0 && endY <= 0)
                || (startX >= 0 && endX <= 0 && startY <= 0 && endY >= 0)) {
            return true;
        }
        return false;
    }
    /**
     * this geometry.Line Horizontal intersection, this line is horizntal and other line is regular.
     * @param other line.
     * @return point intersection.
     */
    public Point thisLineHorizontalintersection(Line other) {
        double b1 = other.getIntersectionWithY();
        double m1 = other.getDYDX();
        //y = mx + b
        //x = ( y - b ) / m
        double x = (this.start.getY() - b1) / m1;
        if (this.start.getX() <= x && x <= this.end.getX()) {
            return new Point(x, this.start.getY());
        }
        return null;
    }
    /**
     * both geometry.Line Horizontal intersection, both line horiztnal.
     * @param other line
     * @return point intersection
     */
    public Point bothLineHorizontalintersection(Line other) {
        if (this.getDYDX() != other.getDYDX()) {
            return null;
        }
        if (Double.compare(this.start.getX(), other.end.getX()) == 0) {
            return new Point(start.getX(), start.getY());
        }
        if (Double.compare(this.end.getX(), other.start.getX()) == 0) {
            return new Point(end.getX(), end.getY());
        }
        if (other.start.getX() < this.start.getX() && this.start.getX() < other.end.getX()) {
            return new Point(0, 0);
        }
        if (other.start.getX() < this.end.getX() && this.start.getX() < other.end.getX()) {
            return new Point(0, 0);
        }
        return null;
    }
    /**
     * other geometry.Line Verical lintersection, the other line is Verical and this line is regular.
     * @param other line.
     * @return the intersection point.
     */
    public Point otherLineVericallintersection(Line other) {
        double b1 = this.getIntersectionWithY();
        double m1 = this.getDYDX();
        //y = mx + b
        double y = m1 * other.start.getX() + b1;
        //if the intersection point on the line.
        if (this.isOnLine(new Point(other.start.getX(), y)) && other.isOnLine(new Point(other.start.getX(), y))) {
            return new Point(other.start.getX(), y);
        }
        //not interscting
        return null; // it is speacail but not intersect
    }
    /**
     * this geometry.Line Vericall intersection, this line is Verical and other line is regular.
     * @param other line.
     * @return the point intersection.
     */
    public Point thisLineVericallintersection(Line other) {
        double b1 = other.getIntersectionWithY();
        double m1 = other.getDYDX();
        //y = mx + b
        double y = m1 * this.start.getX() + b1;
        //if the intersection point on the line.
        if (other.isOnLine(new Point(start.getX(), y)) && this.isOnLine(new Point(start.getX(), y))) {
            return new Point(start.getX(), y); // it is intersection and speacil
        }
        //not interscting
        return null; // it is speacail but not intersect
    }
    /**
     * both geometry.Line Vericall, both line is vertical.
     * @param other line.
     * @return the point intersection.
     */
    public Point bothLineVericallintersection(Line other) {
        if (this.start.getX() != other.start.getX()) {
            return null;
        }
        if (Double.compare(this.start.getY(), other.end.getY()) == 0) {
            return new Point(start.getX(), start.getY());
        }
        if (Double.compare(this.end.getY(), other.start.getY()) == 0) {
            return new Point(end.getX(), end.getY());
        }
        if (other.start.getY() < this.start.getY() && this.start.getY() < other.end.getY()) {
            return new Point(0, 0);
        }
        if (other.start.getY() < this.end.getY() && this.start.getY() < other.end.getY()) {
            return new Point(0, 0);
        }
        return null;
    }
    /**
     * regularIntersectionPoint, find the intersection geometry.Point of two lines.
     * @param other line.
     * @return the point of the intersection.
     */
    public Point regularIntersectionPoint(Line other) {
        // m1 = (y2 - y1) / (x2 - x1)
        double m1 = this.getDYDX();
        // m2 = (y2 - y1) / (x2 -x1) of the other line
        double m2 = other.getDYDX();
        //check if they same dydx so no intersection.
        if (m1 == m2) {
            return null;
        }
        // b1 = y - m1x
        double b1 = this.getIntersectionWithY();
        // b2 = y - m2x
        double b2 = other.getIntersectionWithY();
        // x = (b2 - b1) / (m1 -m2)
        double x = (b2 - b1) / (m1 - m2);
        // y = m1x + b1
        double y = m1 * x + b1;
        return new Point(x, y);
    }
    /**
     * intersectionWith, Returns the intersection point if the lines intersect,
     * and null otherwise.
     * @param other line
     * @return the point if the lines intersection.
     */
    public Point intersectionWith(Line other) {
        //intersect or not: 2 it is intersect and speacil.
        // 1 is for not intersection and speacil.
        // 3 not intersection regular case.
        if (this.isIntersecting(other)) {
            //this line has infinty incline - this line is vertical the other is normal
            if (Double.compare(this.start.getX(), this.end.getX()) == 0
                    && Double.compare(other.start.getX(), other.end.getX()) != 0) {
                Point interPoint = this.thisLineVericallintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            //the other line has infinty icline - the other line is vertical this line is normal
            if (Double.compare(this.start.getX(), this.end.getX()) != 0
                    && Double.compare(other.start.getX(), other.end.getX()) == 0) {
                Point interPoint = this.otherLineVericallintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            // both of them has infinty incline - both of them vertical.
            if (Double.compare(this.start.getX(), this.end.getX()) == 0
                    && Double.compare(other.start.getX(), other.end.getX()) == 0) {
                Point interPoint = this.bothLineVericallintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            // this line has zero incline - this line is horzintal and the other is normal
            if (Double.compare(this.start.getY(), this.end.getY()) == 0
                    && Double.compare(other.start.getY(), other.end.getY()) != 0) {
                Point interPoint = this.thisLineHorizontalintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            //the other line has zero incline - other line is horzintal and this line is normal
            if (Double.compare(this.start.getY(), this.end.getY()) != 0
                    && Double.compare(other.start.getY(), other.end.getY()) == 0) {
                Point interPoint = this.otherLineHorizontalintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            //both of them have zero incline - both of them horizntal lines.
            if (Double.compare(this.start.getY(), this.end.getY()) == 0
                    && Double.compare(other.start.getY(), other.end.getY()) == 0) {
                Point interPoint = this.bothLineHorizontalintersection(other);
                if (interPoint == null) {
                    return null;
                }
                return interPoint;
            }
            //regular intersection
            Point interPoint = regularIntersectionPoint(other);
            if (start.getX() <= interPoint.getX() && interPoint.getX() <= end().getX()
                    && other.start.getX() <= interPoint.getX() && interPoint.getX() <= other.end.getX()) {
                return interPoint;
            }
        }
        return null;
    }
    /**
     * equals, equals -- return true is the lines are equal, false otherwise.
     * @param other line.
     * @return True if they equal.
     */
    public boolean equals(Line other) {
        return this.length() == other.length();
    }

    /**
     * closestIntersectionToStartOfLine , find the closest inter to the rect.
     * @param rect the rectengale we check.
     * @return the closest intersection point with the rect.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double min = 1000;
        Point closetPoint = null;
        List<Point> intersectionPoint = rect.intersectionPoints(this);
        //check if the list is empty, if empty.
        if (intersectionPoint.isEmpty()) {
            return null;
        }
        //for loop that go over the list
        for (Point inter:intersectionPoint) {
            if (min > this.startSpeedVector.distance(inter)) {
                min = this.startSpeedVector.distance(inter);
                closetPoint = inter;
            }
        }
        return closetPoint;
    }

    /**
     * getspeeedvector, the speed vector.
     * @return the speed vector. the size.
     */
    public Point getStartSpeedVector() {
        return startSpeedVector;
    }
}