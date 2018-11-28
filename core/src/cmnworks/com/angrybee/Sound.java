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

public class Sound {
    public com.badlogic.gdx.audio.Sound touch;
    public com.badlogic.gdx.audio.Sound intersection;
    public String setWavTouch        = "sound/touch.wav";
    public String setWavIntersection = "sound/intersection.wav";

    public Sound() {}
    public void ready() {
        this.touch        = Gdx.audio.newSound(Gdx.files.internal(this.setWavTouch));
        this.intersection = Gdx.audio.newSound(Gdx.files.internal(this.setWavIntersection));
    }
    public void reset() {
        this.touch.stop();
        this.intersection.stop();
    }
    public void dispose() {
        this.touch.dispose();
        this.intersection.dispose();
    }
}