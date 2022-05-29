import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * DirectHit is the first level in the game.
 */
public class DirectHit implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * returns The initial velocity of each ball.
     *
     * @return The initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        LinkedList<Velocity> l = new LinkedList<>();
        l.add(new Velocity(0, 7));
        return l;
    }

    /**
     * returns the paddle speed.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * returns the paddle width.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 85;
    }

    /**
     * returns the level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        Point start = new Point(0, 0);
        Rectangle rec = new Rectangle(start, GUI_WIDTH, GUI_HEIGHT);
        rec.setColor(Color.BLACK);
        return new Block(rec);
    }

    /**
     * returns the Blocks that make up this level (each block contains its size, color and location).
     *
     * @return the Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {
        Point start = new Point(385, 150);
        Rectangle rec = new Rectangle(start, 30, 30);
        rec.setColor(Color.RED);
        return Arrays.asList(new Block(rec));
    }

    /**
     * returns the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed before the level is considered to be "cleared"
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * draws the image.
     *
     * @param d the draw surface
     */
    @Override
    public void drawBackgroundAdditions(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);
        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 142, 400, 22);
    }
}
