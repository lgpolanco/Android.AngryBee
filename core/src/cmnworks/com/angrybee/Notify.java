/**
 * AngryBee AngryMe
 * This is my first Basic 2D Android Game app way back year 2013.
 * Using a java game development framework called LibGDX - https://libgdx.badlogicgames.com/
 *
 * @author        Christopher M. Natan
 * @version       1.0
 */
package cmnworks.com.angrybee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Notify{
    public TextureRegion gameOver;
    public TextureRegion gameStart;
    public String setTextureGameOver     = "notify/gameOver.png";
    public String setTextureGameStart    = "notify/gameStart.png";
    private TextureRegion select;

    public Notify() {}
    public void ready() {
        gameOver = new TextureRegion(new Texture(this.setTextureGameOver));
        gameStart = new TextureRegion(new Texture(this.setTextureGameStart));
        gameOver.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        gameStart.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
    public boolean draw(AngryBee parent) {
        if(parent.Game.isRunning()) return false;
        if(parent.Game.isOver())  this.select = gameOver;
        if(parent.Game.isStart()) this.select = gameStart;

        parent.batch.draw(this.select, Gdx.graphics.getWidth() / 2 - this.select.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - this.select.getRegionHeight() / 2);
        return true;
    }
    public  void dispose() {}
}
