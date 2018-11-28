/**
 * AngryBee AngryMe
 * This is my first Basic 2D Android Game app way back year 2013.
 * Using a java game development framework called LibGDX - https://libgdx.badlogicgames.com/
 *
 * @author        Christopher M. Natan
 * @version       1.0
 */
package cmnworks.com.angrybee;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AngryBee extends ApplicationAdapter {
	public Display Display        = new Display();
	public Game Game              = new Game();
	public Sound Sound            = new Sound();
	public Music Music            = new Music();
	public Notify Notify          = new Notify();
	public BackGround BackGround  = new BackGround();
	public Sky Sky                = new Sky();
	public Ground Ground          = new Ground();
	public Actor Actor            = new Actor();
	public Obstacles Obstacles    = new Obstacles();
	public Camera Camera          = new Camera();

	public SpriteBatch batch;
	public Float deltaTime;
	public Integer[] getTouchedInputXY = new Integer[2];
	public enum GameType {Easy, Hard, Core}

	@Override
	public void create () {
		this.batch = new SpriteBatch();

		Camera.ready();
		BackGround.ready();
		Sky.ready();
		Ground.ready();
		Actor.ready();
		Obstacles.ready();
		Notify.ready();
		Music.ready();
		Sound.ready();
		Display.ready();

		this.resetWorld();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.updateWorld();
		this.drawWorld();
	}

	private void drawWorld() {
		Camera.main.update();
		this.batch.setProjectionMatrix(Camera.main.combined);
		this.batch.begin();
		BackGround.draw(this);
		Sky.draw(this);
		Ground.draw(this);
		Actor.draw(this);
		Obstacles.draw(this);
		this.batch.end();

		this.batch.setProjectionMatrix(Camera.screen.combined);
		this.batch.begin();
		Notify.draw(this);
		Display.draw(this);
		this.batch.end();
	}

	private void updateWorld() {
		this.deltaTime = Gdx.graphics.getDeltaTime();

		if(Gdx.input.justTouched()) {
			Sound.touch.play();
			if(Game.setState == Game.START) {
				Game.setState = Game.RUNNING;
				Music.running.play();
			}
			if(Game.setState == Game.RUNNING) {
				Actor.setVelocity();
				getTouchedInputXY[0] = Gdx.input.getX();
				getTouchedInputXY[1] = Gdx.input.getY();
			}
			if(Game.isOver()) {
				if(Obstacles.getGameOverMSHold > 500) {
					Game.setState = Game.START;
					resetWorld();
				}
			}
		}

		Actor.update(this);
		Obstacles.update(this);
		Ground.update(this);
		Sky.update(this);
		Camera.update(this);
	}

	public void resetWorld() {
		Camera.reset();
		Ground.reset();
		Actor.reset();
		Obstacles.reset();
		Music.reset();
		Sound.reset();
		Sky.reset();
		Display.reset();
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		Obstacles.dispose();
		BackGround.dispose();
		Notify.dispose();
		Music.dispose();
		Sound.dispose();
		Display.dispose();
	}

	@Override
	public void resize(int width, int height) {

	}
}