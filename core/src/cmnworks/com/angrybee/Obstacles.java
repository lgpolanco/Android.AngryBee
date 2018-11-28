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
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Obstacles {
    public Polygon polygon;
    public float getGameOverMSHold = 1;
    public String setTextureObstacles      = "obstacles/island.png";
    public String setTextureIntersection   = "obstacles/island_intersection.png";
    public String setPsh                   = "obstacles/island.psh";
    public float setTotal                  = 8;

    private Array<Obstacle> islands        = new Array<Obstacle>();
    private Intersector intersector        = new Intersector();
    private TextureRegion island;
    private TextureRegion islandFlip;
    private Texture target;
    private boolean intersectionEffect     = false;
    private float positionX;

    public Obstacles() {}
    public void ready() {
        this.target                = new Texture(this.setTextureObstacles);
        PolygonRegionLoader loader = new PolygonRegionLoader();
        PolygonRegion region       = loader.load(new TextureRegion(target), Gdx.files.internal(this.setPsh));
        this.polygon               = new Polygon(region.getVertices());
    }
    public void draw(AngryBee parent) {
        TextureRegion targetRegion = new TextureRegion();
        boolean hadIntersection    = false;
        for (Obstacle island : this.islands) {
            if(!parent.Game.isOver()) {
                this.positionPolygon(island.position.x, island.position.y);
            }
            if( hadIntersection = this.isIntersectionOccur(parent, this.polygon, parent.Actor.polygon)) targetRegion = island.image;
            if(parent.Game.isOver()) {
                /* intersection effect */
                if( !this.intersectionEffect ) {
                    if (hadIntersection) parent.Sound.intersection.play();
                    parent.Music.running.stop();
                    parent.Music.gameOver.play();
                    if(!parent.Actor.isScreenOut(parent) && hadIntersection) {
                        island.image = new TextureRegion(targetRegion);
                        island.image.setRegion(new Texture(this.setTextureIntersection));
                        if(targetRegion.isFlipY()) island.image.flip(false, true);
                    }
                    this.intersectionEffect = true;
                }
                ++this.getGameOverMSHold;
            }
            float x = parent.Game.isRunning() ? island.position.x -= 3 : island.position.x;
            parent.batch.draw(island.image,  x, island.position.y);
        }
    }
    public boolean update(AngryBee parent) {
        if(parent.Game.isOver()) return true;
        for (Obstacle islander : this.islands) {
            if(parent.Camera.main.position.x - islander.position.x > 400 + islander.image.getRegionWidth()) {
                islander.position.x += this.positionX;
                islander.position.y = randomY();
                if(MathUtils.randomBoolean()) {
                    islander.image      = island;
                } else {
                    islander.image      = islandFlip;
                }
            }
        }
        return false;
    }
    public void reset() {
        this.intersectionEffect = false;
        this.positionX       = 0;
        this.getGameOverMSHold = 0;
        this.island          = new TextureRegion(this.target);
        this.islandFlip      = new TextureRegion(this.island);
        this.islandFlip.flip(false, true);

        this.setFilter();
        this.islands.clear();

        for(int i = 0; i < this.setTotal; ++i) {
            this.positionX += (i < 1) ? Gdx.graphics.getWidth() - 200
                    : this.randomX();
            float positionY      = randomY();
            boolean flip         = MathUtils.randomBoolean();
            this.islands.add(new Obstacle(positionX, positionY, flip, this.island));
        }
    }
    public void dispose() { }
    private void setFilter() {
        this.target.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
    private void positionPolygon(float x, float y) {
        this.polygon.setPosition(x, y);
    }
    private boolean isIntersectionOccur(AngryBee parent, Polygon p1, Polygon p2) {
        if (this.intersector.overlapConvexPolygons(p1, p2)) {
            parent.Game.setState = parent.Game.OVER;
            return true;
        }
        return false;
    }
    private float randomX() {
        float random  =  MathUtils.random(80, 150);
        return 80 + random;
    }
    private float randomY() {
        float rand =  MathUtils.random(10 - this.island.getRegionHeight(), Gdx.graphics.getHeight() - (this.island.getRegionHeight()+10));
        return rand;
    }
    /* Class Obstacle */
    private class Obstacle {
        private Vector2 position = new Vector2();
        private TextureRegion image;
        private boolean flip = false;

        public Obstacle(float x, float y, boolean flip, TextureRegion image) {
            this.position.x = x;
            this.position.y = y;
            this.image      = new TextureRegion(image);
            this.flip       = flip;
        }
    }
}