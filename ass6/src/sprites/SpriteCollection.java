/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package sprites;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * sprites.SpriteCollection, collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * sprites.SpriteCollection constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * addSprite, add sprite o the list.
     * @param s the sprite we add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * getSprites.
     * @return return the list of the sprites.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * notifyAllTimePassed, call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : copySprites) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn, call drawOn(d) on all sprites.
     * @param d the surface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copySprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : copySprites) {
            sprite.drawOn(d);
        }
    }
}