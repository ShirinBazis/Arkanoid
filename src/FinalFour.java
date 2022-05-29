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
 * FinalFour is the final level in the game.
 */
public class FinalFour implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return 3;
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
        l.add(Velocity.fromAngleAndSpeed(0, speed));
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
        return "Final Four";
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
        rec.setColor(new Color(51, 153, 255));
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
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE,
                Color.PINK, Color.CYAN};
        int j = -1, x = 775, y = 150, width = 50, height = 25;

        for (int i = 0; i < 7; i++) {
            for (++j; j < 16; j++) {
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
        return 105;
    }

    /**
     * draws the image.
     *
     * @param d the draw surface
     */
    @Override
    public void drawBackgroundAdditions(DrawSurface d) {
        d.setColor(new Color(51, 153, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        int i;
        for (i = 0; i < 10; ++i) {
            d.drawLine(105 + i * 10, 400, 80 + i * 10, 600);
        }
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(100, 400, 23);
        d.fillCircle(120, 420, 27);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(140, 390, 29);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle(160, 420, 22);
        d.fillCircle(180, 400, 32);
        d.setColor(Color.WHITE);
        for (i = 0; i < 10; ++i) {
            d.drawLine(605 + i * 10, 520, 580 + i * 10, 600);
        }
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(600, 500, 23);
        d.fillCircle(620, 540, 27);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(640, 510, 29);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle(660, 530, 22);
        d.fillCircle(680, 520, 32);
    }
}
