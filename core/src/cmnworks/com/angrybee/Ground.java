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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ground {
    public String setTextureGround  = "ground/ground.png";
    public Particles Particles      = new Particles();
    public TextureRegion ground;
    private Integer offsetX         = 0;

    public Ground() {}
    public void ready() {
        this.ground = new TextureRegion(new Texture(this.setTextureGround));
        ground.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Particles.ready();
    }
    public void draw(AngryBee parent) {
        parent.batch.draw(this.ground, this.offsetX, 0);
        parent.batch.draw(this.ground, this.offsetX + this.ground.getRegionWidth(), 0);

        Particles.draw(parent);
    }
    public void update(AngryBee parent) {
        if(parent.Camera.main.position.x - this.offsetX > this.ground.getRegionWidth() + 400) {
            this.offsetX += this.ground.getRegionWidth();
        }

        Particles.update(parent);
    }
    public void reset() {
        this.offsetX   =   0;
        Particles.reset();
    }
    public  void dispose() {}

    /* Class Particles */
    private class Particles {
        public String  setParticles      = "ground/particles.png";
        public Integer setTotal          = 10;
        public Integer setMaxSize        = 100;
        public Integer setMinSize        = 30;
        public TextureRegion particles;

        private Array<Holder> holders = new Array<Holder>();
        private int positionX = 0;

        private Particles() {}
        public void ready() {
            this.particles   = new TextureRegion(new Texture(this.setParticles));
            this.particles.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        public void reset() {
            int size   = 0;

            this.holders.clear();
            this.positionX = 0;
            for(int i = 0; i < this.setTotal; ++i) {
                size  = MathUtils.random(this.setMinSize, this.setMaxSize);
                this.positionX  += (i < 1) ? Gdx.graphics.getWidth() - 200  : this.randomX();
                this.holders.add(new Holder(this.positionX, this.randomY(), size, this.particles));
            }
        }
        public void update(AngryBee parent) {
            for (Holder holder : this.holders) {
                if(parent.Camera.main.position.x - holder.position.x > 400 + holder.image.getRegionWidth()) {
                    holder.position.x  += this.positionX + MathUtils.random(50);
                    holder.position.y   = this.randomY();
                    holder.image        = this.particles;
                    holder.size         = MathUtils.random(this.setMinSize, this.setMaxSize);
                }
            }
        }
        public void draw(AngryBee parent) {
            for (Holder holder : this.holders) {
                parent.batch.draw(holder.image,  holder.position.x, holder.position.y, holder.size, holder.size);
            }
        }
        private int randomX() {
            int random  =  MathUtils.random(80, 150);
            return 20 + random;
        }
        private float randomY() {
            return  MathUtils.random(1, (Gdx.graphics.getHeight() / 2) - (this.particles.getRegionHeight() - 50) );
        }
        /* Class Holder */
        private class Holder {
            private Vector2 position = new Vector2();
            private TextureRegion image;
            private Integer size;

            public Holder(float x, float y, Integer size, TextureRegion image) {
                this.position.x = x;
                this.position.y = y;
                this.image      = new TextureRegion(image);
                this.size       = size;
            }
        }
    }
}
