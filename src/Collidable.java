/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * Collidable is an interface used by things that can be collided with.
 */
public interface Collidable {

    /**
     * This method returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method returns the new velocity expected after the hit.
     *
     * @param hitter          is the hitter ball
     * @param collisionPoint  is the collision point of the objects
     * @param currentVelocity is the current velocity of the object that we collided with it
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
