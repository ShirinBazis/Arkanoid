/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {

    /**
     * returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    int numberOfBalls();

    /**
     * returns The initial velocity of each ball.
     *
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle speed.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * returns the level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen
     */
    String levelName();

    /**
     * returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * returns the Blocks that make up this level (each block contains its size, color and location).
     *
     * @return the Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * returns the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed before the level is considered to be "cleared"
     */
    int numberOfBlocksToRemove();

    /**
     * draws the image.
     *
     * @param d the draw surface
     */
    void drawBackgroundAdditions(DrawSurface d);
}
