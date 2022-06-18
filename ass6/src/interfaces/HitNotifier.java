/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package interfaces;

/**
 * the hitnotifer class. tell the game there is a hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl the listenr we add
     */
    void addHitListener(HitListener hl);

    /**
     *  Remove hl from the list of listeners to hit events.
     * @param hl the listenr we remove.
     */
    void removeHitListener(HitListener hl);
}