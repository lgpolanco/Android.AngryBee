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
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
    public OrthographicCamera main;
    public OrthographicCamera screen;
    public float defaultMainXPosition = 400;

    private static final int VIRTUAL_WIDTH = 800;
    private static final int VIRTUAL_HEIGHT = 480;

    public Camera() {}
    public void ready() {
        this.main   = new OrthographicCamera();
        this.main.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

        this.screen = new OrthographicCamera();
        this.screen.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.screen.update();
    }
    public void update(AngryBee parent) {
        this.main.position.x   = parent.Actor.getPositionX() + 350;
    }
    public void reset() {
        this.main.position.x = this.defaultMainXPosition;
    }
}
