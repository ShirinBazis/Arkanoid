import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * WideEasy is the first level in the game.
 */
public class WideEasy implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        l.add(Velocity.fromAngleAndSpeed(50, speed));
        l.add(Velocity.fromAngleAndSpeed(40, speed));
        l.add(Velocity.fromAngleAndSpeed(30, speed));
        l.add(Velocity.fromAngleAndSpeed(20, speed));
        l.add(Velocity.fromAngleAndSpeed(10, speed));
        l.add(Velocity.fromAngleAndSpeed(-10, speed));
        l.add(Velocity.fromAngleAndSpeed(-20, speed));
        l.add(Velocity.fromAngleAndSpeed(-30, speed));
        l.add(Velocity.fromAngleAndSpeed(-40, speed));
        l.add(Velocity.fromAngleAndSpeed(-50, speed));
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
        return 600;
    }

    /**
     * returns the level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
        rec.setColor(Color.RED);
        return new Block(rec);
    }

    /**
     * returns the current block's color.
     *
     * @param blockNum is the number of the block
     * @return the color of the rectangle according to the number of the block
     */
    public Color getThisBlockColor(int blockNum) {
        switch (blockNum) {
            case 0:
            case 1:
                return Color.RED;
            case 2:
            case 3:
                return Color.ORANGE;
            case 4:
            case 5:
                return Color.YELLOW;
            case 6:
            case 7:
            case 8:
                return Color.GREEN;
            case 9:
            case 10:
                return Color.BLUE;
            case 11:
            case 12:
                return Color.PINK;
            case 13:
            case 14:
                return Color.CYAN;
            default:
                return Color.BLACK;
        }
    }

    /**
     * returns the Blocks that make up this level (each block contains its size, color and location).
     *
     * @return the Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList();
        int x = 25, width = 50, height = 25;
        double y = 250;
        for (int i = 0; i < 15; i++) {
            Point p = new Point(x + i * width, y);
            Rectangle r = new Rectangle(p, width, height);
            r.setColor(getThisBlockColor(i));
            blocks.add(new Block(r));
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
        return 15;
    }

    /**
     * draws the image.
     *
     * @param d the draw surface
     */
    @Override
    public void drawBackgroundAdditions(DrawSurface d) {
        // draw the background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        // draw the big frame to the sun
        d.setColor(new Color(238, 232, 170));
        d.fillCircle(150, 150, 60);
        int numRays = 100;
        int startX = 25;
        int endX = 775;

        // draw the lines from the sun
        for (int i = 1; i <= numRays; ++i) {
            d.drawLine(150, 150, (endX - startX) / numRays * i, 250);
        }
        // draw the small frame to the sun
        d.setColor(Color.decode("#ecd749"));
        d.fillCircle(150, 150, 50);
        // draw the sun
        d.setColor(new Color(255, 230, 0));
        d.fillCircle(150, 150, 40);
    }
}
