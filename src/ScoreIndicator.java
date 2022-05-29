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
 * the class for the score indicator of the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int x;
    private int y;

    /**
     * constructor.
     *
     * @param x is the start x of the text
     * @param y is the start y of the text
     * @param s a counter
     */
    public ScoreIndicator(int x, int y, Counter s) {
        this.score = s;
        this.x = x;
        this.y = y;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, 800, 25);
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, "Score: " + score.getValue(), 15);
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
