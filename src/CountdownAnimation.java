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
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds,
 * and on top of them it will show a countdown from countFrom back to 1, where each number will appear on the screen
 * for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;
    private long relativeTime;
    private long beginRelativeTime;
    private LevelInformation levelInformation;

    /**
     * constructor.
     *
     * @param numOfSeconds the seconds the countdown will be shown on the screen
     * @param countFrom    the number to start count from
     * @param gameScreen   the screen of the game
     * @param levelInfo    is the level information
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              LevelInformation levelInfo) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.beginRelativeTime = (long) (1000 * this.numOfSeconds / this.countFrom);
        this.relativeTime = beginRelativeTime;
        this.levelInformation = levelInfo;
    }

    /**
     * implements one frame in the animation.
     *
     * @param d is the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.levelInformation.drawBackgroundAdditions(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 50);
        long passedTime = System.currentTimeMillis() - startTime;
        // if the time of a single digit passed - decrease the countFrom in one and add the begin relative time to
        // the relative time
        if (passedTime > relativeTime) {
            countFrom--;
            relativeTime += beginRelativeTime;
        }
        if (countFrom == 0) {
            stop = true;
        }
    }

    /**
     * returns if the frame should stop.
     *
     * @return if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
