/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * SpriteCollection is the class of the collection of sprite.
 */
public class SpriteCollection {
    private List<Sprite> list;

    /**
     * constructor- creates new list of sprites.
     */
    public SpriteCollection() {
        this.list = new LinkedList<>();
    }

    /**
     * adds a new sprite to the list.
     *
     * @param s is the new sprite
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new LinkedList<Sprite>(this.list);
        for (Sprite s : spriteList) {
            s.timePassed();
        }
    }

    /**
     * draws all the sprites on the DrawSurface.
     *
     * @param d is the drawSurface that the sprites will be drown on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : list) {
            s.drawOn(d);
        }
    }

    /**
     * removes a sprite from the list.
     *
     * @param s is a sprite to remove
     */
    public void removeSprite(Sprite s) {
        list.remove(s);
    }
}
