/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package charcters;

import Shape.Rectangle;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * class charcters.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
   private Rectangle rectangle;
   private Color color;
   private List<HitListener> hitListeners;

    /**
     * charcters.Block constructor, create a block.
     * @param rectangle class, every block has rect.
     * @param color the color of the block.
     */
   public Block(Rectangle rectangle, Color color) {
       this.rectangle = rectangle;
       this.color = color;
       hitListeners = new ArrayList<HitListener>();
   }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Random rand = new Random();
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        // right bound
        if (Double.compare(rectangle.getUpperRight().getX(), (int) collisionPoint.getX()) == 0) {
            //check for right upper corner
            if (rectangle.getUpperRight().getY() <= collisionPoint.getY()
                    && collisionPoint.getY() <= rectangle.getUpperRight().getY() + 1) {
                //also upper so it is a corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else if (rectangle.getLowerRight().getY() - 1 <= collisionPoint.getY()
                    && collisionPoint.getY() <= rectangle.getLowerRight().getY()) {
                //right lower corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else {
                return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            }
        }
       //lower bound
        if (Double.compare(rectangle.getLowerLeft().getY(), (int) collisionPoint.getY()) == 0)  {
            //check for lower right corner
            if (rectangle.getLowerRight().getX() - 1 <= collisionPoint.getX()
                    && collisionPoint.getX() <= rectangle.getLowerRight().getX()) {
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else if (rectangle.getLowerLeft().getX() <= collisionPoint.getX()
                    && collisionPoint.getX() <= rectangle.getLowerLeft().getX() + 1) {
                //check for lower left corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else {
                return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            }
        }
       //upper bound
        if (Double.compare(rectangle.getUpperLeft().getY(), (int) collisionPoint.getY()) == 0) {
            //check for right upper corner
            if (rectangle.getUpperRight().getX() - 1 <= collisionPoint.getX()
                    && collisionPoint.getX() <= rectangle.getUpperRight().getX()) {
                //also upper so it is a corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else if (rectangle.getUpperLeft().getX() <= collisionPoint.getX()
                    && collisionPoint.getX() <= rectangle.getUpperLeft().getX() + 1) {
                //it is upper left corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else {
                return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            }
        }
        // left bound
        if (Double.compare(rectangle.getUpperLeft().getX(), (int) collisionPoint.getX()) == 0) {
            //left up corner
            if (rectangle.getUpperLeft().getY() <= collisionPoint.getY()
                    && collisionPoint.getY() <= rectangle.getUpperRight().getY() + 3) {
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else if (rectangle.getLowerLeft().getY() - 3 <= collisionPoint.getY()
                    && collisionPoint.getY() <= rectangle.getLowerLeft().getY()) {
                //left lower corner
                return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            } else {
                return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY(),
                        currentVelocity.getSpeedVector());
            }
        }
        return null;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        //draw the rectangle.
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        //System.out.println("good");
    }
    @Override
    public void addToGame(GameLevel g) {
       g.addSprite(this);
       g.addCollidable(this);
    }

    /**
     * removeFromGame, remove the block from the game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
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
