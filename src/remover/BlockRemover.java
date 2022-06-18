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
 *a remover.BlockRemover is in charge of removing blocks from the game, as well as keeping count,
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constuctor for the class.
     * @param gameLevel the gmae object
     * @param removedBlocks the counter to remove blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}