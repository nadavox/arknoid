/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package interfaces;
import biuoop.DrawSurface;

/**
 * interface animation.
 */
public interface Animation {
    /**
     * run one frame.
     * @param d the surface we draw.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shoud stop tell me when to stop.
     * @return true or false if to stop or not. true to stop false to continue.
     */
    boolean shouldStop();
}