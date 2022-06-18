/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * class game envirmonet.
 */
public class GameEnvironment {
    private List<Collidable> collideWith;

    /**
     * getCollideWith get the list.
     * @return the list
     */
    public List<Collidable> getCollideWith() {
        return collideWith;
    }

    /**
     * game.GameEnvironment constructor, build a list with a collide object.
     */
    public GameEnvironment() {
        this.collideWith =  new ArrayList<Collidable>();
    }

    /**
     * addCollidable, add the given collidable to the environment.
     * @param c the collide object we add
     */
    public void addCollidable(Collidable c) {
        this.collideWith.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * getClosestCollision, Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables,
     * in this collection, return null. Else, return the information,
     * about the closest collision that is going to occur.
     * @param trajectory the line, speed vector.
     * @return the collison info point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
    //list of all the point that collise with other rectangel.
    List<Point> closetPoint = new ArrayList<>();
    //list of all the objet of each point theat the ball is hiting
    List<Collidable> closeObject = new ArrayList<>();
    //the object that close
    Collidable theclosetObject = null;
    //the point that close
    Point closePoint = null;
    //the min to know when it close
    double min = 1000;
    //copy of the list of points.
    List<Collidable> copyCollidables = new ArrayList<Collidable>(this.collideWith);
    //for loop to find all of the closet points
    for (Collidable object : copyCollidables) {
        List interListPoint = object.getCollisionRectangle().intersectionPoints(trajectory);
        //if it is not empty.
        if (!interListPoint.isEmpty()) {
            Point closetInterPoint = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            closetPoint.add(closetInterPoint);
            closeObject.add(object);
        }
    }
    //check if the list is empty
    if (closetPoint.isEmpty()) {
        return null;
    }
    //for loop that go over the list
    for (int i = 0; i < closetPoint.size(); i++) {
            if (min > trajectory.getStartSpeedVector().distance(closetPoint.get(i))) {
                min = trajectory.getStartSpeedVector().distance(closetPoint.get(i));
                closePoint = closetPoint.get(i);
                theclosetObject = closeObject.get(i);
            }
        }
        return new CollisionInfo(closePoint, theclosetObject);
    }
}