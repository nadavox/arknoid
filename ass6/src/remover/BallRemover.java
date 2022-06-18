/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package remover;
import charcters.Ball;
import charcters.Block;
import counter.Counter;
import game.GameLevel;
import interfaces.HitListener;

/**
 *  remover.BallRemover class.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constactor for the class.
     * @param gameLevel the game object
     * @param removedBalls the counter to remove balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
