/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the class for the lives indicator of the game.
 */
public class LivesIndicator implements Sprite {
    private int x;
    private int y;
    private Counter livesCounter;

    /**
     * constructor.
     * @param x is the start x of the text
     * @param y is the start y of the text
     * @param livesCounter is the lives counter
     */
    public LivesIndicator(int x, int y, Counter livesCounter) {
        this.x = x;
        this.y = y;
        this.livesCounter = livesCounter;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, "Lives: " + this.livesCounter.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adds the sprite to a game.
     *
     * @param g is the game we want to add this sprite to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
