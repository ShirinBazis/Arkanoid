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
 * the class of the pause screen of the game.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k is the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * does one frame in the animation.
     *
     * @param d is the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.PINK);
        d.fillCircle(400, 200, 100);
        d.setColor(Color.BLACK);
        d.fillRectangle(350, 150, 40, 100);
        d.fillRectangle(410, 150, 40, 100);
        d.setColor(Color.BLACK);
        d.drawText(150, 400, "Paused -- press space to continue", 32);
        d.setColor(Color.pink);
        d.drawText(151, 401, "Paused -- press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(153, 402, "Paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
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
