/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package collidables;

import geometry.Point;
import interfaces.Collidable;

/**
 * collidables.CollisionInfo class, include point and the object we collide to know the information about collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * collidables.CollisionInfo, constructor for the class. init the fields.
     * @param collisionPoint the point we collide.
     * @param collisionObject the object we collide.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     *  collisionPoint.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}