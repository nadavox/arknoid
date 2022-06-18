/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package interfaces;
import charcters.Block;
import charcters.Ball;
/**
 * the interfaces.HitListener interface is active when event of hitting occure.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the charcters.Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the charcters.Ball that's doing the hitting.
     * @param beingHit the Blcock that hit
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
