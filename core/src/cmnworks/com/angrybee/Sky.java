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

public class Sky {
    public TextureRegion sky;
    public String setTextureSky = "sky/sky.png";
    private Integer offsetX     = 0;

    public Sky() {}
    public void ready() {
        this.sky = new TextureRegion(new Texture(this.setTextureSky));
        this.sky.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
    public void draw(AngryBee parent) {
        parent.batch.draw(this.sky, offsetX, 480 - this.sky.getRegionHeight());
        parent.batch.draw(this.sky, offsetX + this.sky.getRegionWidth(), 480 - this.sky.getRegionHeight());
    }
    public void update(AngryBee parent) {
        if(parent.Camera.main.position.x - this.offsetX > this.sky.getRegionWidth() + 400) {
            this.offsetX += this.sky.getRegionWidth();
        }
    }
    public void reset() {
        this.offsetX = 0;
    }
}