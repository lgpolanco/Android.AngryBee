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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Actor {
    public Polygon polygon;
    public Animation actor;
    public Integer setTravelInterval     = 200;
    public Integer setMaximumKm          = 50;
    public Integer setStartX             = 50;
    public Integer setStartY             = 250;
    public Integer setImpulse            = 350;
    public Integer setVelocityX          = 100;
    public Float setFlappingSpeed        = 0.01f;
    public Float setFlyingSpeed          = 0.90f;
    public String setFolder              = "actor/path1/";
    public String setPsh                 = setFolder + "actor.psh";

    private Vector2 position             = new Vector2();
    private Vector2 velocity             = new Vector2();
    private float stateTime              = 0;
    private float startingYPoint         = 0;
    private float previousInputY         = 0;
    private float[] dimension            = new float[2];
    private int kmCounter                = 0;
    private int kmTraveled               = 0;
    private int kmRemaining              = 50;
    private boolean isGoingUp            = false;


    public void setVelocity() {
        this.velocity.set(this.setVelocityX, this.setImpulse);
    }
    public float getPositionX() {
        return this.position.x;
    }
    public Integer getKmTraveled() {
        return this.kmTraveled;
    }
    public Integer getKmRemaining() {
        return this.kmRemaining;
    }


    public void draw(AngryBee parent) {
        Integer inputY        = parent.Game.isOver() || parent.Game.isStart() ? null : parent.getTouchedInputXY[1];
        float currentPosition = Gdx.graphics.getHeight() - (this.startingYPoint + this.dimension[1]);

        if (inputY != null) {
            if(this.previousInputY != inputY) {
                if (inputY <= currentPosition)  this.isGoingUp = true;
                if (inputY >= currentPosition)  this.isGoingUp = false;
            }
            if (this.isGoingUp)  this.startingYPoint += this.setFlyingSpeed;
            if (!this.isGoingUp) this.startingYPoint -= this.setFlyingSpeed;
            this.previousInputY = inputY;
        }
        this.isScreenOut(parent);
        if(parent.Game.isOver()) { --this.startingYPoint;  ++this.position.x; }
        else {
            if(!parent.Game.isFinished()) {
                if (this.kmCounter >= this.setTravelInterval) {
                    ++this.kmTraveled;
                    --this.kmRemaining;
                    this.kmCounter = 0;
                }
                if (this.kmTraveled >= this.setMaximumKm) {
                    parent.Game.setState = parent.Game.FINISHED;
                }
                ++this.kmCounter;
            }
        }

        this.positionPolygon(this.position.x, this.startingYPoint);
        parent.batch.draw(this.actor.getKeyFrame(stateTime), position.x, this.startingYPoint);

    }
    private void positionPolygon(float x, float y) {
        this.polygon.setPosition(x, y);
    }
    public boolean isScreenOut(AngryBee parent) {
        float currentPosition = this.startingYPoint;
        if(currentPosition <= 0  - this.dimension[1] || currentPosition >= Gdx.graphics.getHeight()) {
            if(!parent.Game.isOver()) parent.Game.setState = parent.Game.OVER;
            return true;
        }
        return false;
    }
    public void reset() {
        position.set(this.setStartX, this.setStartY);
        velocity.set(0, 0);
        this.startingYPoint = this.setStartY;
        this.kmTraveled     = 0;
        this.kmCounter      = 0;
        this.kmRemaining    = this.setMaximumKm;
    }
    public void  resize(float width, float height) {

    }
}