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
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Display {
    public String setFontName      = "fonts/font.fnt";
    public String setTextTraveled  = "Traveled  : ";
    public String setTextRemaining = "Remaining: ";
    private BitmapFont font;

    public void Display() {}
    public void ready() {
        font = new BitmapFont(Gdx.files.internal(setFontName));
    }
    public boolean draw(AngryBee parent) {
        if(!parent.Game.isOver() && !parent.Game.isRunning()) return false;

        int height = Gdx.graphics.getHeight() - 10;
        this.font.draw(parent.batch, this.setTextTraveled + parent.Actor.getKmTraveled()  +  " km", 10, height);
        this.font.draw(parent.batch, this.setTextRemaining + parent.Actor.getKmRemaining() + " km", 10, height - 20);
        return true;
    }
    public void dispose() {
        font.dispose();
    }
    public void reset() {}
}
