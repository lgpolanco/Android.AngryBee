/**
 * AngryBee AngryMe
 * This is my first Basic 2D Android Game app way back year 2013.
 * Using a java game development framework called LibGDX - https://libgdx.badlogicgames.com/
 *
 * @author        Christopher M. Natan
 * @version       1.0
 */
package cmnworks.com.angrybee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackGround {
    public String setTextureBackGround = "background/background.png";
    public TextureRegion background;
    private Texture target;

    public BackGround() {}
    public void ready() {
        this.target       = new Texture(this.setTextureBackGround);
        this.background = new TextureRegion(target);
        this.target.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
    public void draw(AngryBee parent) {
        parent.batch.draw(this.background, parent.Camera.main.position.x - this.target.getWidth() / 2, 0);
    }
    public  void dispose() {
        this.target.dispose();
    }
}