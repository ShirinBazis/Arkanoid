/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * the HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that is being hit
     * @param hitter   is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
