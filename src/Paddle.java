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
 * Paddle is the paddle of the game that the user can move by the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private static final int FIRST_DEG = 300;
    private static final int SECOND_DEG = 330;
    private static final int THIRD_DEG = 30;
    private static final int FORTH_DEG = 60;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = 3;
    private static final int SPICES_NUMBER = 5;
    private static final int GUI_WIDTH = 800;
    private static final int FRAME_WIDTH = 20;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speed;

    /**
     * constructor.
     *
     * @param rec      is the rectangle (the shape of the paddle)
     * @param keyboard is the keyboard of the game user
     * @param s        is the peed of the paddle
     */
    public Paddle(Rectangle rec, biuoop.KeyboardSensor keyboard, int s) {
        this.keyboard = keyboard;
        this.rectangle = rec;
        this.speed = s;
    }

    /**
     * moves the paddle left.
     */
    public void moveLeft() {
        rectangle.getUpperLeft().setX(rectangle.getUpperLeft().getX() - speed);
    }

    /**
     * moves the paddle right.
     */
    public void moveRight() {
        rectangle.getUpperLeft().setX(rectangle.getUpperLeft().getX() + speed);
    }

    /**
     * draws the sprite to the screen.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(rectangle.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        // draw the black frame to the block
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * move the paddle right or left according to the paddle edge of the collision.
     */
    @Override
    public void timePassed() {
        double rectangleStartX = this.rectangle.getUpperLeft().getX();
        /* if the movement of the rectangle wouldn't lead it to pass the right boundary of the gui,
        and the right key is pressed- move the rectangle to the right*/
        if (rectangleStartX <= GUI_WIDTH - FRAME_WIDTH - rectangle.getWidth()
                && keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && !(keyboard.isPressed(KeyboardSensor.LEFT_KEY))) {
            moveRight();
        }

        /* if the movement of the rectangle wouldn't lead it to pass the left boundary of the gui,
         and the left key is pressed- move the rectangle to the left*/
        if (rectangleStartX >= FRAME_WIDTH + speed && keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && !(keyboard.isPressed(KeyboardSensor.RIGHT_KEY))) {
            moveLeft();
        }
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g is the game we want to add this sprite to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * returns the collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * returns the velocity of the hit after the collision.
     *
     * @param hitter          is the hitter ball
     * @param collisionPoint  is the collision point of the objects
     * @param currentVelocity is the current velocity of the object that we collided with it
     * @return the velocity of the hit after the collision (according to the edge of the paddle in the collision)
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // array of the rectangle edges
        Line[] edges = rectangle.createEdgesArray();
        // split the rectangle to 5 spices
        double spice = rectangle.getWidth() / SPICES_NUMBER;
        double rectangleStartX = rectangle.getUpperLeft().getX();
        // if the ball hits the upper edge of the paddle
        if (edges[UP].isPointOnLine(collisionPoint)) {

            /* if the collision point hits the left-most spice of the upper edge of the paddle-
            move it with an angle of 300*/
            if (collisionPoint.getX() >= rectangleStartX && collisionPoint.getX() < rectangleStartX + spice) {
                return Velocity.fromAngleAndSpeed(FIRST_DEG, currentVelocity.getSpeed());
            }
            /* if the collision point hits the second left spice of the upper edge of the paddle-
            move it with an angle of 330*/
            if (collisionPoint.getX() >= rectangleStartX + spice && collisionPoint.getX()
                    < rectangleStartX + spice * (SPICES_NUMBER - 3)) {
                return Velocity.fromAngleAndSpeed(SECOND_DEG, currentVelocity.getSpeed());
            }
            // if the collision point hits the middle spice of the upper edge of the paddle- move it straight up
            if (collisionPoint.getX() >= rectangleStartX + spice * (SPICES_NUMBER - 3) && collisionPoint.getX()
                    < rectangleStartX + spice * (SPICES_NUMBER - 2)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            }
            /* if the collision point hits the 4 spice (from left) of the upper edge of the paddle-
            move it with an angle of 30*/
            if (collisionPoint.getX() >= rectangleStartX + spice * (SPICES_NUMBER - 2) && collisionPoint.getX()
                    < rectangleStartX + spice * (SPICES_NUMBER - 1)) {
                return Velocity.fromAngleAndSpeed(THIRD_DEG, currentVelocity.getSpeed());
            }
            /* if the collision point hits the second right-most spice of the upper edge of the paddle-
            move it with an angle of 60*/
            if (collisionPoint.getX() >= rectangleStartX + spice * (SPICES_NUMBER - 1) && collisionPoint.getX()
                    < rectangleStartX + spice * SPICES_NUMBER) {
                return Velocity.fromAngleAndSpeed(FORTH_DEG, currentVelocity.getSpeed());
            }
        }
        // if the ball hit the right\left edge of the paddle
        if (edges[RIGHT].isPointOnLine(collisionPoint) || edges[LEFT].isPointOnLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    /**
     * returns the rectangle of the paddle.
     *
     * @return the rectangle of the paddle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * sets the upper left x of the paddle.
     *
     * @param x the upper left x of the paddle
     */
    public void setX(double x) {
        double y = this.rectangle.getUpperLeft().getY();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        Point p = new Point(x, y);
        this.rectangle = new Rectangle(p, width, height);
    }
}