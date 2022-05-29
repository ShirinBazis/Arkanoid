/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * A HitListener to update the score counter of the game when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int POINTS_TO_BLOCK = 5;
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the counter of the score of the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that is being hit
     * @param hitter   is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(POINTS_TO_BLOCK);
    }
}