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
 * the class for the level name indicator of the game.
 */
public class LevelNameIndicator implements Sprite {
    private int x;
    private int y;
    private String levelName;

    /**
     * constructor.
     *
     * @param x         is the start x of the text
     * @param y         is the start y of the text
     * @param levelName is the name of the level
     */
    public LevelNameIndicator(int x, int y, String levelName) {
        this.x = x;
        this.y = y;
        this.levelName = levelName;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, "Level Name: " + this.levelName, 15);
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