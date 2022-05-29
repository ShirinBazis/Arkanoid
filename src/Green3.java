/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Green3 is the third level in the game.
 */
public class Green3 implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * returns The initial velocity of each ball.
     *
     * @return The initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new LinkedList();
        double speed = 7;
        l.add(Velocity.fromAngleAndSpeed(40, speed));
        l.add(Velocity.fromAngleAndSpeed(-40, speed));
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
        return "Green 3";
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
        List<Block> blocks = new LinkedList();
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int j = -1, x = 775, y = 150, width = 50, height = 25;

        for (int i = 0; i < 5; i++) {
            for (++j; j < 10 - i; j++) {
                Point p = new Point(x - j * width, y);
                Rectangle r = new Rectangle(p, width, height);
                r.setColor(colors[i]);
                blocks.add(new Block(r));
            }
            y += height;
            j = -1;
        }
        return blocks;
    }

    /**
     * returns the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed before the level is considered to be "cleared"
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * draws the image.
     *
     * @param d the draw surface
     */
    @Override
    public void drawBackgroundAdditions(DrawSurface d) {
        // draw the background
        d.setColor(new Color(0, 115, 0));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(110, 200, 10, 200);
        d.setColor(new Color(255, 204, 140));
        d.fillCircle(115, 200, 12);
        d.setColor(new Color(255, 69, 0));
        d.fillCircle(115, 200, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 200, 3);
        d.setColor(new Color(60, 60, 60));
        d.fillRectangle(100, 400, 30, 200);
        // draw the big black rectangle
        d.setColor(Color.BLACK);
        d.fillRectangle(65, 450, 100, 200);
        int x = 75;
        int y = 460;
        d.setColor(Color.WHITE);
        // draw the little white rectangles on the big black rectangle
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                d.fillRectangle(x + j * 18, y + i * 32, 10, 25);
            }
        }
    }
}
