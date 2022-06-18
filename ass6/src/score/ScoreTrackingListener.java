/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package score;

import charcters.Ball;
import charcters.Block;
import counter.Counter;
import interfaces.HitListener;

/**
 * score.ScoreTrackingListener counter the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constactor for the class.
     * @param scoreCounter the counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}