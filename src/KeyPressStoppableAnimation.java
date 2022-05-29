/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * decorator class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isPressed;

    /**
     * constructor.
     *
     * @param sensor is the keyboard sensor
     * @param k      is the string to press to continue
     * @param a      is the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation a) {
        this.keyboardSensor = sensor;
        this.key = k;
        this.animation = a;
        this.stop = false;
        this.isPressed = false;
    }

    /**
     * does one frame in the animation.
     *
     * @param d is the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!(this.keyboardSensor.isPressed(key))) {
            this.isPressed = false;
        }
        if (this.keyboardSensor.isPressed(key) && (!isPressed)) {
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
