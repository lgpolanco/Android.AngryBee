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

public class Music {
    public com.badlogic.gdx.audio.Music running;
    public com.badlogic.gdx.audio.Music gameOver;
    public String setMp3Running    = "music/running.mp3";
    public String setMp3GameOver   = "music/gameOver.mp3";

    public Music() {}
    public void ready() {
        this.running     = Gdx.audio.newMusic(Gdx.files.internal(this.setMp3Running));
        this.gameOver = Gdx.audio.newMusic(Gdx.files.internal(this.setMp3GameOver));
        this.running.setLooping(true);
        this.gameOver.setLooping(false);
    }
    public void reset() {
        running.stop();
        gameOver.stop();
    }
    public void dispose() {
        running.dispose();
        gameOver.dispose();
    }
}
