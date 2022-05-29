/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

/**
 * the interface that is responsible for the animation.
 */
public interface Animation {

    /**
     * does one frame in the animation.
     * @param d is the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns if the animation should stop.
     * @return if the animation should stop
     */
    boolean shouldStop();
}

