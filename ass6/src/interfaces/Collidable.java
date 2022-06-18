package interfaces;

import Shape.Rectangle;
import biuoop.DrawSurface;
import charcters.Ball;
import geometry.Point;
import geometry.Velocity;

/**
 * @author nadav oxenberg
 * ID: 207952144
 */

/**
 * interface collidable, every object in the game that can collide will use these methods.
 */
public interface Collidable {
    /**
     * getCollisionRectangle, get the shape of the collision object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * hit, Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the point we collide.
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hit.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * drawOn, draw the surface.
     * @param surface the surface we draw.
     */
    void drawOn(DrawSurface surface);
}