/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment is the class of the environment of the game.
 */
public class GameEnvironment {
    private List<Collidable> list;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }

    /**
     * adds the given collidable to the environment.
     *
     * @param c is a collidable.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory is the line that the object move from its start to its end.
     * @return the collision information about the closest collision that is going to occur.
     * if there won't be collision, return null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (list.isEmpty()) {
            return null;
        }
        // get the collision information from the first collidable in the list
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(list.get(0).getCollisionRectangle());
        Collidable collisionObject = list.get(0);

        /* pass all the collidable in the list and find the closest collision that is going to occur*/
        for (int i = 1; i < list.size(); i++) {
            Point nextCollisionPoint = trajectory.closestIntersectionToStartOfLine(list.get(i)
                    .getCollisionRectangle());
            if (collisionPoint != null) {
                if (nextCollisionPoint != null && trajectory.start().distance(nextCollisionPoint)
                        < trajectory.start().distance(collisionPoint)) {
                    collisionPoint = nextCollisionPoint;
                    collisionObject = list.get(i);
                }
            } else {
                collisionPoint = nextCollisionPoint;
                collisionObject = list.get(i);
            }
        }
        if (collisionPoint != null && collisionObject != null) {
            return new CollisionInfo(collisionPoint, collisionObject);
        }
        return null;
    }

    /**
     * removes the given collidable from the environment.
     *
     * @param c is a collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }
}
