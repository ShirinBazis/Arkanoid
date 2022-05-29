/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block is something we collide into, with a shape of rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private Rectangle rec;
    private List<HitListener> hitListeners;


    /**
     * constructor.
     *
     * @param rec is a rectangle
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * This method returns the new velocity expected after the hit.
     *
     * @param hitter          is the hitter ball
     * @param collisionPoint  is the collision point of the objects
     * @param currentVelocity is the current velocity of the object that we collided with it
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] edges = rec.createEdgesArray();
        if (edges[UP].isPointOnLine(collisionPoint) || edges[DOWN].isPointOnLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (edges[RIGHT].isPointOnLine(collisionPoint) || edges[LEFT].isPointOnLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param surface the drawing surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(rec.getColor());
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        // draw the black frame to the block
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adds the block to a game.
     *
     * @param g is the game we want to add this block to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes a block from a game.
     *
     * @param game is the game we want to remove this sprite from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * will be called whenever a hit() occurs, and will notify all of the registered HitListener objects by calling
     * their hitEvent method.
     *
     * @param hitter is a hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl is a listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl is a listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
