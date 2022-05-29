/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

/**
 * Sprite is the sprite interface of the game.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adds the sprite to a game.
     *
     * @param g is the game we want to add this sprite to
     */
    void addToGame(GameLevel g);
}
