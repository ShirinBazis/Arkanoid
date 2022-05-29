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
 * The Ball program implements an application that creates an object Ball,
 * with the field of center point, radius, color, velocity, zoneStart and
 * zoneEnd.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * constructor.
     *
     * @param center is the center point of the ball
     * @param r      is the radius of the ball
     * @param color  is the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * accessor.
     *
     * @return the x of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return the y of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return the radius of the center point of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor.
     *
     * @return the color of the center point of the ball
     */
    private java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this function draws the ball on the given DrawSurface.
     *
     * @param surface is the surface the ball will be drawn on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * adds the sprite to a game.
     *
     * @param g is the game we want to add this sprite to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this function set the velocity of the ball.
     *
     * @param v is the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this function set the velocity of the ball by its dx and dy.
     *
     * @param dx is the x of the velocity
     * @param dy is the y of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this function returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this function returns the game environment of the ball.
     *
     * @return the game environment of the ball
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * this function set the game environment of the ball.
     *
     * @param e is the game environment of the ball
     */
    public void setGameEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**
     * this function moves the current ball the way that when it hits a collidablle to its left or to its right,
     * it changes its horizontal direction, and when it hits the border on the top or the bottom,
     * it should change its vertical direction.
     */
    public void moveOneStep() {
        if (velocity == null) {
            return;
        }
        Point end = velocity.applyToPoint(this.center);
        Line ballTrajectory = new Line(center, end);
        CollisionInfo c = environment.getClosestCollision(ballTrajectory);
        // If moving on this trajectory won't hit anything, then move the ball to the end of the trajectory.
        if (c == null) {
            //move
            this.center = end;
            // Otherwise (there is a hit): move the ball to "almost" the hit point, but just slightly before it.
        } else {
            Line l = new Line(this.center, c.collisionPoint());
            this.center = velocity.applyToClosePoint(this.center, l, ballTrajectory);
            // update the velocity to the new velocity returned by the hit() method.
            this.setVelocity(c.collisionObject().hit(this, c.collisionPoint(), this.velocity));
        }
    }

    /**
     * removes a ball from a game.
     *
     * @param game is the game we want to remove this ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}