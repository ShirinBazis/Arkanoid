/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * EndScreen is the end screen of the game (win\lost).
 */
public class EndScreen implements Animation {
    private Counter scoreCounter;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private boolean isWin;

    /**
     * constructor.
     *
     * @param score  is the score of the user
     * @param sensor is the keyboard sensor
     * @param iw     is the "is win" value
     */
    public EndScreen(Counter score, KeyboardSensor sensor, boolean iw) {
        this.scoreCounter = score;
        this.keyboardSensor = sensor;
        this.isWin = iw;
        this.stop = false;
    }

    /**
     * does one frame in the animation.
     *
     * @param d is the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String gameOverMessage = "You Win! ";
        if (!this.isWin) {
            gameOverMessage = "Game Over. ";
        }
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(60, 150, gameOverMessage + "Your score is " + this.scoreCounter.getValue(), 50);
        if (this.isWin) {
            d.setColor(Color.GREEN);
        } else {
            d.setColor(Color.RED);
        }
        d.drawText(62, 151, gameOverMessage + "Your score is " + this.scoreCounter.getValue(), 50);
        d.setColor(Color.BLACK);
        d.drawText(65, 152, gameOverMessage + "Your score is " + this.scoreCounter.getValue(), 50);
        d.setColor(Color.BLACK);
        d.drawText(250, 400, "Press space to exit", 32);
        if (this.isWin) {
            d.setColor(Color.GREEN);
        } else {
            d.setColor(Color.RED);
        }
        d.drawText(251, 401, "Press space to exit", 32);
        d.setColor(Color.BLACK);
        d.drawText(253, 402, "Press space to exit", 32);
        if (this.keyboardSensor.isPressed("space")) {
            this.stop = true;
        }
    }

    /**
     * returns if the frame should stop.
     *
     * @return if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
